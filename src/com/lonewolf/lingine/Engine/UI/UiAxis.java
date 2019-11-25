package com.lonewolf.lingine.Engine.UI;

import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;

public enum UiAxis
{
	Vertical(new Vector2f(0,1)),
	Horizontal(new Vector2f(1,0));
	
	private Vector2f axis;
	
	UiAxis(Vector2f axis)
	{
		this.axis = axis;
	}
	
	public Vector2f getAxis()
	{
		return axis;
	}
}
