package com.lonewolf.lingine.Engine.UI.UITranslation;

import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.UI.UiObject;

public class PixelTranslation implements ITranslation
{
	private int offset;
	
	public PixelTranslation(int offset)
	{
		this.offset = offset;
	}
	
	@Override
	public void setParent(UiObject parent)
	{
	
	}
	
	@Override
	public void calculateTranslation(Vector2f windowSize)
	{
	
	}
	
	@Override
	public int GetXTranslation()
	{
		return offset;
	}
	
	@Override
	public int GetYTranslation()
	{
		return offset;
	}
}
