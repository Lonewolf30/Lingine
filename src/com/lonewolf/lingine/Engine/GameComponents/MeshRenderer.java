package com.lonewolf.lingine.Engine.GameComponents;

import com.lonewolf.lingine.Engine.Rendering.Material;
import com.lonewolf.lingine.Engine.Rendering.Mesh;
import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Engine.Rendering.Shader;
import com.lonewolf.lingine.Logger;

public class MeshRenderer extends GameComponent
{
	private Mesh m_mesh;
	private Material m_material;

	public MeshRenderer(Mesh mesh, Material material)
	{
		this.m_mesh = mesh;
		this.m_material = material;
	}

	@Override
	public void Render(Shader shader, RenderingEngine renderingEngine)
	{
		shader.Bind();
		shader.UpdateUniforms(GetTransform(), m_material, renderingEngine);
		m_mesh.draw();
	}
}
