package com.lonewolf.lingine.Modules;

import com.lonewolf.lingine.Engine.CoreEngine.Configuration;
import com.lonewolf.lingine.Engine.Resource.Resource;
import com.lonewolf.lingine.Engine.Resource.ResourceLoader;
import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Reference;
import com.lonewolf.lingine.Engine.CoreEngine.Game;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;

@SuppressWarnings("ALL")
public class ModuleLoader
{
	private HashMap<String, Module> modules;
	private ResourceLoader resourceLoader;
	private Configuration configuration = new Configuration();
	private Game game;
	
	public ModuleLoader()
	{
		configuration.loadConfigurationFile(Reference.fileDirectory + "modules/enable.txt");
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
	
	public void setModules(HashMap<String, Module> modules)
	{
		this.modules = modules;
	}
	
	public void setGame(Game game)
	{
		this.game = game;
	}
	
	public void addModules()
	{
		Logger.LogI("Module Execution Started");
		loadErros();
		for (Map.Entry<String, Module> entry : modules.entrySet())
		{
			String s = entry.getKey();
			Module module = entry.getValue();
			Logger.LogI("Executing: " + module.getName());
			module.loadModule(game);
		}
		Logger.LogI("Module Execution Finished");
	}
	
	private void loadErros()
	{
		final boolean[] supermod = {false};
		
		modules.forEach((s, module) ->
		{
			if (module.isSuperMod() && !supermod[0])
				supermod[0] = true;
			else
				Logger.LogE(new ModuleLoadingException("More Than One Super Module Loaded"));
		});
	}
	
	public void loadModules()
	{
		Logger.LogI("Module Loading Start");
		for (File mod : getDirectory().listFiles())
		{
			if (mod.getName().contains(".jar"))
			{
				try
				{
					JarFile jar = new JarFile(mod);
					Module module = new Module();
					module.load(jar, mod.getPath());
					if (module.getErrors().size() > 0)
					{
						Logger.LogE(new ModuleLoadingException(module.getErrors().get(0)));
					}
					modules.put(module.getId(), module);
					module.setConfiguration(configuration);
					boolean enable = configuration.getConfig(module.getId(), false);
					module.setEnabled(enable);
				} catch (Exception e)
				{
					Logger.LogE(e);
				}
			}
		}
		for (Module mod:modules.values())
		{
			Logger.LogI("Loaded: " + mod.getName());
		}
		configuration.saveConfig();
		Logger.LogI("Module Loading Complete");
	}
	
	public void saveConfirguration()
	{
		configuration.saveConfig();
	}
	
	public Collection<Module> getModules()
	{
		return modules.values();
	}
}
