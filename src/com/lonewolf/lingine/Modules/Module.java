package com.lonewolf.lingine.Modules;

import com.lonewolf.lingine.Engine.CoreEngine.Configuration;
import com.lonewolf.lingine.Engine.Resource.Resource;
import com.lonewolf.lingine.Engine.Resource.ResourceLoader;
import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Engine.CoreEngine.Game;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Module
{
	private String version;
	private String name;
	private String id;
	private boolean superMod;
	private ResourceLoader resourceLoader;
	private Configuration configuration;
	
	private Object baseClass;
	private Method moduleLoadMethod;
	private boolean enabled;
	
	private ArrayList<String> errors;
	
	public Module()
	{
		errors = new ArrayList<>();
		resourceLoader = new ResourceLoader();
		enabled = false;
	}
	
	public void load(JarFile module, String path)
	{
		try
		{
			Enumeration<JarEntry> e = module.entries();
			
			URL[] urls = { new URL("jar:file:"+path+"!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);
			while (e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				if(je.isDirectory() || je.getName().contains("META")){
					continue;
				}
				
				if (!je.getName().endsWith(".class"))
				{
					String[] data = je.getName().split("/");
					String type = data[data.length-1].substring(data[data.length-1].lastIndexOf(".")+1);
					String name = data[data.length-1].replace("."+type,"");
					Resource resource = new Resource(name, type, cl.getResource(je.getName()));
					resourceLoader.addResource(name,resource);
					continue;
				}
				
				String className = je.getName().substring(0,je.getName().length()-6);
				className = className.replace('/', '.');
				Class c = cl.loadClass(className);
				if (c.getAnnotation(Mod.class) != null)
				{
					Mod annotation = (Mod) c.getAnnotation(Mod.class);
					name = annotation.moduleName();
					version = annotation.moduleVersion();
					id = annotation.moduleID();
					superMod = annotation.masterModule();
					
					for (Method meth : c.getDeclaredMethods())
					{
						if (meth.getName().equals("moduleLoad"))
						{
							baseClass = meth.getDeclaringClass().newInstance();
							moduleLoadMethod = meth;
						}
					}
					
					for (Field field:c.getDeclaredFields())
					{
						if (field.isAnnotationPresent(com.lonewolf.lingine.Modules.Resource.class))
						{
							field.setAccessible(true);
							field.set(baseClass,resourceLoader);
						}
					}
				}
			}
			
			checkErrors();
			
			if (moduleLoadMethod == null)
				errors.add("No moduleLoad method found");
		} catch (Exception e)
		{
			Logger.LogE(e);
		}
	}
	
	private void checkErrors()
	{
		if (!id.toLowerCase().equals(id))
			errors.add(name +  ": moduleID needs to be lowercase");
	}
	
	public void loadModule(Game game)
	{
		try
		{
			moduleLoadMethod.invoke(baseClass, game);
		} catch (Exception e)
		{
			Logger.LogE(e);
		}
	}
	
	public ArrayList<String> getErrors()
	{
		return errors;
	}
	
	public String getVersion()
	{
		return version;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getId()
	{
		return id;
	}
	
	public boolean isSuperMod()
	{
		return superMod;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
		configuration.setConfigValue(id, String.valueOf(enabled));
		configuration.saveConfig();
	}
	
	public void setConfiguration(Configuration configuration)
	{
		this.configuration = configuration;
	}
}
