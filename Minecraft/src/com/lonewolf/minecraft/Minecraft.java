package com.lonewolf.minecraft;

import com.lonewolf.lingine.Engine.CoreEngine.GameObject;
import com.lonewolf.minecraft.Block.World;

public class Minecraft extends GameObject
{
	private World world;
	
	public Minecraft()
	{
		world = new World();
		
		addComponent(world);
	}
}
