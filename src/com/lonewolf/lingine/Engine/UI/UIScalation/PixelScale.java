package com.lonewolf.lingine.Engine.UI.UIScalation;

import com.lonewolf.lingine.Engine.Rendering.Window;
import com.lonewolf.lingine.Engine.UI.UiObject;

public class PixelScale implements IScalation
{
	private float pixels;
	private int x;
	private int y;
	
	public PixelScale(float pixels)
	{
		this.pixels = pixels;
	}
	
	@Override
	public void setParent(UiObject parent)
	{
	
	}
	
	@Override
	public void calculateScalation(Window window)
	{
	}
	
	@Override
	public int getWidth()
	{
		return (int) pixels;
	}
	
	@Override
	public int getHeight()
	{
		return (int) pixels;
	}
}
