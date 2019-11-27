package com.lonewolf.lingine.Engine.UI.UIScalation;

import com.lonewolf.lingine.Engine.Rendering.Window;
import com.lonewolf.lingine.Engine.UI.UiObject;

public class AspectContraint implements IScalation
{
	private int height;
	private UiObject parent;
	
	public AspectContraint()
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
		if (parent.getModifer().widthScale() instanceof AspectContraint)
			return 0;
		return parent.getModifer().getWidth();
	}
	
	@Override
	public int getHeight()
	{
		return height;
	}
}
