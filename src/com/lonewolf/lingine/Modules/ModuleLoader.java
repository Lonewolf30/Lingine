package com.lonewolf.lingine.Modules;

import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Reference;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;

@SuppressWarnings("ALL")
public class ModuleLoader
{
	private HashMap<String, Module> modules;
	private Game game;
	
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
		for (Map.Entry<String, Module> entry : modules.entrySet())
		{
			String s = entry.getKey();
			Module module = entry.getValue();
			module.loadModule(game);
		}
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
		for (File mod : getDirectory().listFiles())
		{
			if (mod.getName().contains(".jar"))
			{
				try
				{
					JarFile jar = new JarFile(mod);
					Module module = new Module();
					module.setLoader(this);
					module.load(jar, mod.getPath());
					if (module.getErros().size() > 0)
					{
						Logger.LogE(new ModuleLoadingException(module.getErros().get(0)));
					}
					modules.put(module.getId(), module);
				} catch (Exception e)
				{
					Logger.LogE(e);
				}
			}
		}
		loadErros();
		for (Module mod:modules.values())
		{
			Logger.LogI("Loaded: " + mod.getName());
		}
	}
}
