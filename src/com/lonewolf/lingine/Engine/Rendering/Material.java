package com.lonewolf.lingine.Engine.Rendering;

import com.lonewolf.lingine.Engine.Rendering.resourceManagement.MappedValues;

import java.util.HashMap;

public class Material extends MappedValues
{
	private HashMap<String, Texture> m_textureHashMap;

	public Material(Texture diffuse, float specularIntensity, float specularPower, Texture normal,
					Texture dispMap, float dispMapScale, float dispMapOffset)
	{
		super();
		m_textureHashMap = new HashMap<>();
		AddTexture("diffuse", diffuse);
		AddFloat("specularIntensity", specularIntensity);
		AddFloat("specularPower", specularPower);
		AddTexture("normalMap", normal);
		AddTexture("dispMap", dispMap);

		float baseBias = dispMapScale/2.0f;
		AddFloat("dispMapScale", dispMapScale);
		AddFloat("dispMapBias", -baseBias + baseBias * dispMapOffset);
	}
	
	public Material(Texture texture)
	{
		this(texture, 1,1,texture,texture,1,0);
	}

	public void AddTexture(String name, Texture texture) { m_textureHashMap.put(name, texture); }

	public Texture GetTexture(String name)
	{
		Texture result = m_textureHashMap.get(name);
		if(result != null)
			return result;

		return new Texture("test.png");
	}
}
