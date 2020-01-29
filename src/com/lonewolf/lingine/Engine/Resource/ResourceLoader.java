package com.lonewolf.lingine.Engine.Resource;

import java.util.HashMap;

public class ResourceLoader
{
	private HashMap<String, Resource> resources;
	
	public ResourceLoader()
	{
		resources = new HashMap<>();
	}
	
	public void addResource(String name, Resource resource)
	{
		this.resources.put(name,resource);
	}
	
	public Resource getResource(String name)
	{
		return resources.get(name);
	}
}
