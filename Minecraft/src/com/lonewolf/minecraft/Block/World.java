package com.lonewolf.minecraft.Block;

import com.lonewolf.lingine.Engine.CoreEngine.GameObject;
import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;
import com.lonewolf.lingine.Engine.GameComponents.GameComponent;
import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Engine.Rendering.Shader;

import java.util.HashMap;
import java.util.UUID;

public class World extends GameComponent
{
	private Chunk[][][] chunks;
	private HashMap<UUID, GameObject> entities;
	
	public World()
	{
		chunks = new Chunk[1][1][1];
		entities = new HashMap<>();
		
		chunks[0][0][0] = new Chunk(new Vector3f(0,0,0), this);
	}
	
	private float accumTime = 0;
	@Override
	public void Update(float time)
	{
		float tickRate = 1 / 64f;
		accumTime += time;
		if (accumTime > tickRate)
		{
			fixedUpdate();
			accumTime -= tickRate;
		}
	}
	
	public void fixedUpdate()
	{
//		entities.forEach((id, e) -> e.Update(1));
	}
	
	@Override
	public void Render(Shader shader, RenderingEngine renderingEngine)
	{
		for (int i = 0; i < chunks.length; i++)
		{
			for (int j = 0; j < chunks[i].length; j++)
			{
				for (int k = 0; k < chunks[i][j].length; k++)
				{
					chunks[i][j][k].render(shader, renderingEngine);
				}
			}
		}
	}
}
