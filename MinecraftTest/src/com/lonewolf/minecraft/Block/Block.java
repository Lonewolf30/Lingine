package com.lonewolf.minecraft.Block;

import com.lonewolf.lingine.Engine.CoreEngine.GameObject;
import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;
import com.lonewolf.lingine.Engine.GameComponents.MeshRenderer;
import com.lonewolf.lingine.Engine.Rendering.Material;
import com.lonewolf.lingine.Engine.Rendering.Mesh;
import com.lonewolf.lingine.Engine.Rendering.Texture;
import com.lonewolf.lingine.Engine.Rendering.Vertex;

public class Block extends GameObject
{
	private Mesh mesh;
	private Vertex[] vertices;
	private int[] indices;
	private Material material;
	
	private Vector3f pos;
	private World world;
	
	public Block(Vector3f pos, World world)
	{
		this.pos = pos;
		this.world = world;
		
		vertices = new Vertex[]{
				new Vertex(new Vector3f(0, 0, 0)),
				new Vertex(new Vector3f(0, 0, 1)),
				new Vertex(new Vector3f(0, 1, 0)),
				new Vertex(new Vector3f(0, 1, 1)),
				new Vertex(new Vector3f(1, 0, 0)),
				new Vertex(new Vector3f(1, 0, 1)),
				new Vertex(new Vector3f(1, 1, 0)),
				new Vertex(new Vector3f(1, 1, 1))
		};
		
		indices = new int[]{
			1,2,3
		};
		
		mesh = new Mesh(vertices, indices, true);
		material = new Material(new Texture("test.png"));
	}
	
	private void loadComponents()
	{
		addComponent(new MeshRenderer(mesh, material));
	}
}
