package com.lonewolf.lingine.Engine.UI;

import com.lonewolf.lingine.Engine.Rendering.Window;
import com.lonewolf.lingine.Engine.UI.UIScalation.IScalation;
import com.lonewolf.lingine.Engine.UI.UITranslation.ITranslation;

public class UIModifier
{
	private IScalation width;
	private IScalation height;
	
	private ITranslation x;
	private ITranslation y;
	
	public void init(UiObject parent)
	{
		width.setParent(parent);
		height.setParent(parent);
	}
	
	public int getWidth()
	{
		return width.getWidth();
	}
	
	public void setWidth(IScalation width)
	{
		this.width = width;
	}
	
	public int getHeight()
	{
		return height.getHeight();
	}
	
	public void setHeight(IScalation height)
	{
		this.height = height;
	}
	
	public int getX()
	{
		return x.GetXTranslation();
	}
	
	public void setX(ITranslation x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y.GetYTranslation();
	}
	
	public void setY(ITranslation y)
	{
		this.y = y;
	}
	
	public IScalation widthScale()
	{
		return width;
	}
	
	public IScalation heightScale()
	{
		return height;
	}
	
	public ITranslation xLocation()
	{
		return x;
	}
	
	public ITranslation yLocation()
	{
		return y;
	}
	
	public void resized(Window window)
	{
		width.calculateScalation(window);
		height.calculateScalation(window);
		x.calculateTranslation(window.getWindowSize());
		y.calculateTranslation(window.getWindowSize());
	}
}
