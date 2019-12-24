package com.lonewolf.lingine.Modules;

import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Logger;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@SuppressWarnings("ALL")
public class Module
{
	private String version;
	private String name;
	private String id;
	
	private Object baseClass;
	private Method moduleLoadMethod;
	
	private ArrayList<String> erros;
	
	public Module(JarFile module, String path)
	{
		erros = new ArrayList<>();
		loadMainClass(module, path);
	}
	
	private void loadMainClass(JarFile module, String path)
	{
		try
		{
			JarFile jarFile = module;
			Enumeration<JarEntry> e = jarFile.entries();
			
			URL[] urls = { new URL("jar:file:"+path+"!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);
			while (e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				if(je.isDirectory() || !je.getName().endsWith(".class") || je.getName().contains("META")){
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
					
					for (Method meth : c.getDeclaredMethods())
					{
						if (meth.getName().equals("moduleLoad"))
						{
							baseClass = meth.getDeclaringClass().newInstance();
							moduleLoadMethod = meth;
						}
					}
					
					checkErrors(c);
				}
			}
			
			if (moduleLoadMethod == null)
				erros.add("No moduleLoad method found");
		} catch (Exception e)
		{
			Logger.LogE(e);
		}
	}
	
	private void checkErrors(Class baseClass)
	{
		if (!id.toLowerCase().equals(id))
			erros.add(name +  ": moduleID needs to be lowercase");
	}
	
	public void loadModule(Game game)
	{
		try
		{
			moduleLoadMethod. invoke(baseClass, game);
		} catch (Exception e)
		{
			Logger.LogE(e);
		}
	}
	
	public ArrayList<String> getErros()
	{
		return erros;
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
}
