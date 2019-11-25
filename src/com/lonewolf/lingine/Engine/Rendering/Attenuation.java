package com.lonewolf.lingine.Engine.Rendering;


import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;


@SuppressWarnings("WeakerAccess")
public class Attenuation extends Vector3f
{
	public Attenuation(float constant, float linear, float exponent) {
		super(constant, linear, exponent);
	}

	public float GetConstant()
	{
		return GetX();
	}

	public float GetLinear()
	{
		return GetY();
	}

	public float GetExponent()
	{
		return GetZ();
	}
}
