package com.lonewolf.lingine.Engine.UI.UIScalation;

import com.lonewolf.lingine.Engine.Rendering.Window;
import com.lonewolf.lingine.Engine.UI.UiObject;

public class ParentScale implements IScalation
{
	private UiObject parent;
	private float scale;
	
	private int width;
	private int height;
	
	public ParentScale(float scale)
	{
		this.scale = scale;
		this.width = 1;
		this.height  = 1;
	}
	
	@Override
	public void setParent(UiObject parent)
	{
		this.parent = parent;
	}
	
	@Override
	public void calculateScalation(Window window)
	{
		width = (int) (parent.getModifier().getWidth() * scale);
		height = (int) (parent.getModifier().getHeight() * scale);
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
