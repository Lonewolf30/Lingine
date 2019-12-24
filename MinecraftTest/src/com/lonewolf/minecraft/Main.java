package com.lonewolf.minecraft;

import com.lonewolf.lingine.Engine.UI.UiColor;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiBlock;
import com.lonewolf.lingine.Engine.UI.UiObject;
import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Modules.Mod;
import com.lonewolf.lingine.Engine.CoreEngine.Game;

@Mod(moduleID = "main", moduleVersion = "v1.0", moduleName = "Module Test", masterModule = true)
public class Main
{
	public void moduleLoad(Game game)
	{
		Logger.LogI("Module Loaded");
		game.engine.loadGame(new Game()
		{
			@Override
			public void init()
			{
				addUiElement(new UiObject().addComponent(new UiBlock(new UiColor(255,255,255,255), 50)));
				addObject(new Minecraft());
			}
		});
	}
}
