package com.lonewolf.lingine.Engine.UI.UIScalation;

import com.lonewolf.lingine.Engine.Rendering.Window;
import com.lonewolf.lingine.Engine.UI.UiObject;

public class AspectScale implements IScalation
{
	private int height;
	private UiObject parent;
	
	public AspectScale()
	{
		height = 0;
	}
	
	
	@Override
	public void setParent(UiObject parent)
	{
		this.parent = parent;
	}
	
	@Override
	public void calculateScalation(Window window)
	{
		height = (int) (getWidth() * window.getAspect());
	}
	
	@Override
	public int getWidth()
	{
		if (parent.getModifer().widthScale() instanceof AspectScale)
			return 0;
		return parent.getModifer().getWidth();
	}
	
	@Override
	public int getHeight()
	{
		return height;
	}
}
