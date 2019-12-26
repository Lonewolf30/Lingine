package com.lonewolf.minecraft.Block;

import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;

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
		
		for (int i = 0; i < 16; i++)
			for (int j = 0; j < 16; j++)
				for (int k = 0; k < 16; k++)
				{
					loadBlock(new Block(chunkPos.Add(new Vector3f(i, j, k)), world));
				}
	}
	
	private void loadBlock(Block block)
	{
		blocks[(int) (block.getPos().GetX() - chunkPos.GetX())][(int) (block.getPos().GetY() - chunkPos.GetY())][(int) (block.getPos().GetZ() - chunkPos.GetZ())] = block;
	}
	
	public void update()
	{
	
	}
}
