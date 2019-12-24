package com.lonewolf.minecraft.Block;

import com.lonewolf.minecraft.Block.Block;

public class Chunk
{
	private Block[][][] blocks;
	
	public Chunk()
	{
		blocks = new Block[16][16][16];
	}
}
