package com.lonewolf.minecraft.Block;

import com.lonewolf.lingine.Engine.CoreEngine.Time;
import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;
import com.lonewolf.lingine.Engine.GameComponents.GameComponent;

public class World extends GameComponent
{
	private Chunk[][][] chunks;
	
	public World()
	{
		chunks = new Chunk[1][1][1];
		chunks[0][0][0] = new Chunk(new Vector3f(0,0,0), this);
	}
	
	private float TickRate = 64f / Time.second;
	private float accumTime = 0;
	@Override
	public void Update(float time)
	{
		accumTime += time;
		if (accumTime > TickRate)
		{
			fixedUpdate();
			accumTime -= TickRate;
		}
	}
	
	public void fixedUpdate()
	{
		for(Chunk[][] z : chunks)
		{
			for(Chunk[] y : z)
			{
				for(Chunk x : y)
				{
					x.update();
				}
			}
		}
	}
	
}
