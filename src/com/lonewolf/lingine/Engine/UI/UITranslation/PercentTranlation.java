package com.lonewolf.lingine.Engine.UI.UITranslation;

import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;

public class PercentTranlation implements ITranslation
{
	private float amount;
	private int x;
	private int y;
	
	public PercentTranlation(float amount)
	{
		this.amount = amount;
		x = 0;
		y = 0;
	}
	
	@Override
	public void calculateTranslation(Vector2f windowSize)
	{
		x = (int) (amount * windowSize.GetX());
		y = (int) (amount * windowSize.GetY());
	}
	
	@Override
	public int GetXTranslation()
	{
		return x;
	}
	
	@Override
	public int GetYTranslation()
	{
		return y;
	}
}
