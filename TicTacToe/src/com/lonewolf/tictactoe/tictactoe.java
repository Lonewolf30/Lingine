package com.lonewolf.tictactoe;

import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Modules.Mod;

@Mod(moduleID = "ttc", moduleName = "Tic Tac Toe", moduleVersion = "v1.0", masterModule = true)
public class tictactoe
{
	public void moduleLoad(Game game)
	{
		Logger.LogI(game);
		
		game.engine.loadGame(new Game()
		{
			@Override
			public void init()
			{
			
			}
		});
	}
}
