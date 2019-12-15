package com.lonewolf.lingine.Modules;

import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Reference;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.JarFile;

@SuppressWarnings("ALL")
public class ModuleLoader
{
	private HashMap<String, Module> modules;
	
	public ModuleLoader()
	{
		modules = new HashMap<>();
	}
	
	private File getDirectory()
	{
		File directory = new File(Reference.fileDirectory + "modules/");
		
		if (!directory.exists())
		{
			directory.mkdirs();
		}
		
		return directory;
	}
	
	public void addModules(Game game)
	{
		modules.forEach((s, module) ->
		{
			if (module.isEnable())
				module.loadModule(game);
		});
	}
	
	public void loadModules()
	{
		for (File mod : getDirectory().listFiles())
		{
			if (mod.getName().contains(".jar"))
			{
				try
				{
					JarFile jar = new JarFile(mod);
					Module module = new Module(jar, mod.getPath());
					module.loadMainClass();
					if (module.getErros().size() > 1)
					{
						module.setEnable(false);
					}
					modules.put(module.getModuleName(), module);
				} catch (Exception e)
				{
					Logger.LogE(e);
				}
			}
		}
	}
}
