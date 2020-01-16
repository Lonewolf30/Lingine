package com.lonewolf.minecraft;

import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;
import com.lonewolf.lingine.Entities.Player;
import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Modules.Mod;
import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.minecraft.Block.Block;

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
				addObject(new Block(new Vector3f(0,0,0), null));
//				addObject(new Minecraft());
			}
		});
	}
}
