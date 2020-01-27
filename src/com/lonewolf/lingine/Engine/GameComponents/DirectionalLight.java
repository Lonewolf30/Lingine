package com.lonewolf.lingine.Engine.GameComponents;

import com.lonewolf.lingine.Engine.Rendering.Shader;
import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;

public class DirectionalLight extends BaseLight
{
	public DirectionalLight(Vector3f color, float intensity)
	{
		super(color, intensity);

		SetShader(new Shader("forward-directional"));
	}

	public Vector3f GetDirection()
	{
		return GetTransform().GetTransformedRot().GetForward();
	}
}
