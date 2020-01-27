package com.lonewolf.lingine.Engine.GameComponents;

import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Engine.Rendering.Shader;
import com.lonewolf.lingine.Engine.CoreEngine.CoreEngine;
import com.lonewolf.lingine.Engine.CoreEngine.GameObject;
import com.lonewolf.lingine.Engine.CoreEngine.Input;
import com.lonewolf.lingine.Engine.CoreEngine.Transform;

public abstract class GameComponent
{
	private GameObject m_parent;
	protected CoreEngine engine;

	public void Input(Input input) {}
	public void Update(float delta) {}
	public void Render(Shader shader, RenderingEngine renderingEngine) {}

	public void SetParent(GameObject parent)
	{
		this.m_parent = parent;
	}

	public Transform GetTransform()
	{
		return m_parent.getTransform();
	}

	public void AddToEngine(CoreEngine engine) { this.engine = engine;}
}

