package com.lonewolf.minecraft.Block;

import com.lonewolf.lingine.Engine.CoreEngine.GameObject;
import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;
import com.lonewolf.lingine.Engine.GameComponents.MeshRenderer;
import com.lonewolf.lingine.Engine.Rendering.*;
import com.lonewolf.lingine.Logger;

import javax.management.remote.SubjectDelegationPermission;
import java.awt.image.BufferedImage;

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
		super();
		getTransform().SetPos(pos);
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
				0,7,2
		};
		
		mesh = new Mesh(vertices, indices, true);
		BufferedImage image = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
		image.setRGB(0,0,0x00FFFFFF);
		material = new Material(new Texture(image));
		
		loadComponents();
	}
	
	public Vector3f getPos()
	{
		return pos;
	}
	
	private void loadComponents()
	{
		addComponent(new MeshRenderer(mesh, material));
	}
}
