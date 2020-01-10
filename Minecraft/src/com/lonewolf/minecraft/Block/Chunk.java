package com.lonewolf.minecraft.Block;

import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;
import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Engine.Rendering.Shader;

public class Chunk
{
	private Block[][][] blocks;
	private Vector3f chunkPos;
	private World world;
	
	public Chunk(Vector3f chunkPos, World world)
	{
		blocks = new Block[16][16][16];
		this.chunkPos = chunkPos;
		this.world = world;
		
		loadBlock(new Block(new Vector3f(1,0,0).Add(chunkPos), world));
	}
	
	private void loadBlock(Block block)
	{
		blocks[(int) (block.getPos().GetX() - chunkPos.GetX())][(int) (block.getPos().GetY() - chunkPos.GetY())][(int) (block.getPos().GetZ() - chunkPos.GetZ())] = block;
	}
	
	public void render(Shader shader, RenderingEngine renderingEngine)
	{
		for (int i = 0; i < 16; i++)
			for (int j = 0; j < 16; j++)
				for (int k = 0; k < 16; k++)
				{
					Block block = blocks[i][j][k];
					if (block != null)
						block.Render(shader, renderingEngine);
				}
	}
	
	public void update()
	{
	
	}
}
