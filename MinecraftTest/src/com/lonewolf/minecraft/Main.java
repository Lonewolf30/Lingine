package com.lonewolf.minecraft;

import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Modules.Mod;
import com.lonewolf.lingine.Engine.CoreEngine.Game;

@Mod(moduleID = "main", moduleVersion = "v1.0", moduleName = "Module Test")
public class Main
{
	public void moduleLoad(Game game)
	{
		Logger.LogI("Module Loaded");
	}
}
