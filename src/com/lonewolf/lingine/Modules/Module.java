package com.lonewolf.lingine.Modules;

import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Logger;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@SuppressWarnings("ALL")
public class Module
{
	private Class mainClass;
	private JarFile module;
	private String path;
	private String moduleName;
	private Object baseClass;
	private Method moduleLoadMethod;
	private boolean enable;
	
	private ArrayList<String> erros;
	
	public Module(JarFile module, String path)
	{
		this.module = module;
		this.path = path;
		erros = new ArrayList<>();
		enable = true;
	}
	
	public void loadMainClass()
	{
		try
		{
			String mainClassName = module.getManifest().getMainAttributes().getValue("Main-Class");
			
			JarFile jarFile = new JarFile(path);
			Enumeration<JarEntry> e = jarFile.entries();
			
			URL[] urls = { new URL("jar:file:" + path+"!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);
			
			while (e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				if(je.isDirectory() || !je.getName().endsWith(".class") || je.getName().contains("META")){
					continue;
				}
				String className = je.getName().substring(0,je.getName().length()-6);
				className = className.replace('/', '.');
				Class c = cl.loadClass(className);
				for (Method meth : c.getDeclaredMethods())
				{
					if (meth.getName().equals("moduleLoad"))
					{
						baseClass = meth.getDeclaringClass().newInstance();
						moduleLoadMethod = meth;
						moduleName = module.getName().substring(module.getName().lastIndexOf("\\")+1, module.getName().length()-4);
					}
				}
			}
			
			erros.add("No moduleLoad method found");
		} catch (Exception e)
		{
			Logger.LogE(e);
		}
	}
	
	public boolean isEnable()
	{
		return enable;
	}
	
	public void setEnable(boolean enable)
	{
		this.enable = enable;
	}
	
	public String getModuleName()
	{
		return moduleName;
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
	
	public ArrayList<String> getErros()
	{
		return erros;
	}
}
