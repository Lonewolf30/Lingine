package com.lonewolf.lingine.Engine.UI.UIScalation;

import com.lonewolf.lingine.Engine.Rendering.Window;
import com.lonewolf.lingine.Engine.UI.UiObject;

public class WindowScale implements IScalation
{
	private float scale;
	
	private int width;
	private int height;
	
	public WindowScale(float scale)
	{
		this.scale = scale;
		
		this.width = 1;
		this.height = 1;
	}
	
	@Override
	public void setParent(UiObject parent)
	{
	
	}
	
	@Override
	public void calculateScalation(Window window)
	{
		width = (int) (window.getWindowSize().GetX() * scale);
		height = (int) (window.getWindowSize().GetY() * scale);
	}
	
	@Override
	public int getWidth()
	{
		return width;
	}
	
	@Override
	public int getHeight()
	{
		return height;
	}
}
