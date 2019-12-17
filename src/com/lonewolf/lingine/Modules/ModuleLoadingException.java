package com.lonewolf.lingine.Modules;

public class ModuleLoadingException extends Exception
{
	public ModuleLoadingException()
	{
		super("Module Load Error");
	}
	
	public ModuleLoadingException(String message)
	{
		super(message);
	}
}
