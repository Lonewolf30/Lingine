package com.lonewolf.TicTacToe;

import com.lonewolf.lingine.Engine.Resource.ResourceLoader;
import com.lonewolf.lingine.Modules.Mod;
import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Modules.Resource;

@Mod(moduleID = "ttt", moduleVersion = "v1.0", moduleName = "Tic Tac Toe", masterModule = true)
public class Main
{
	@Resource
	private ResourceLoader resources;
	
	public void moduleLoad(Game game)
	{
	
	}
}
