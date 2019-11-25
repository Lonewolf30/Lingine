package com.lonewolf.lingine.Engine.Rendering.resourceManagement;

import static org.lwjgl.opengl.GL11.glGenTextures;

public class TextureResource
{
	private int m_id;
	private int m_refCount;

	public TextureResource()
	{
		this.m_id = glGenTextures();
		this.m_refCount = 1;
	}

	public void AddReference()
	{
		m_refCount++;
	}

	public boolean RemoveReference()
	{
		m_refCount--;
		return m_refCount == 0;
	}

	public int GetId() { return m_id; }
}
