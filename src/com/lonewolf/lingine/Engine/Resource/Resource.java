package com.lonewolf.lingine.Engine.Resource;

import java.net.URL;
import java.util.HashMap;
import java.io.*;

public class Resource
{
	private String name;
	private String type;
	private URL path;
	
	public Resource(String name, String type, URL path)
	{
		this.name = name;
		this.type = type;
		this.path = path;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getType()
	{
		return type;
	}
	
	public URL getPath()
	{
		return path;
	}
	
	@Override
	public String toString()
	{
		return "Resource{" +
					   "name='" + name + '\'' +
					   ", type='" + type + '\'' +
					   ", path=" + path +
					   '}';
	}
}
