package com.lonewolf.minecraft;

import com.lonewolf.lingine.Engine.CoreEngine.CoreEngine;
import com.lonewolf.lingine.Engine.UI.UiColor;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiBlock;
import com.lonewolf.lingine.Engine.UI.UiObject;
import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Modules.Mod;
import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Scenes.MainMenu;

@Mod(moduleID = "main", moduleVersion = "v1.0", moduleName = "Module Test", masterModule = true)
public class Main
{
	public void moduleLoad(Game game)
	{
		Logger.LogI(game);
		
		game.engine.loadGame(new Game()
		{
			@Override
			public void init()
			{
				addObject(new Minecraft());
			}
		});
	}
}
