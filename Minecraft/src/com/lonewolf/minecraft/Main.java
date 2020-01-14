package com.lonewolf.minecraft;

import com.lonewolf.lingine.Engine.CoreEngine.Matrix4f;
import com.lonewolf.lingine.Engine.GameComponents.Camera;
import com.lonewolf.lingine.Entities.Player;
import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Modules.Mod;
import com.lonewolf.lingine.Engine.CoreEngine.Game;

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
				addObject(new Player());
				addObject(new Minecraft());
			}
		});
	}
}
