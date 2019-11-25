package com.lonewolf.lingine.Engine.UI;

import java.awt.*;

public class UiColor
{
	private int red = 0;
	private int green = 0;
	private int blue = 0;
	private int alpha = 0;
	
	public UiColor(int red, int green, int blue, int alpha)
	{
		incRed(red);
		incGreen(green);
		incBlue(blue);
		incAlpha(alpha);
	}
	
	public Color getColor()
	{
		return new Color(red, green, blue, alpha);
	}
	
	public void incRed(int amount)
	{
		red += amount;
		red = Math.min(red, 255);
		red = Math.max(red, 0);
	}
	
	public void incGreen(int amount)
	{
		green += amount;
		green = Math.min(green, 255);
		green = Math.max(green, 0);
	}
	
	public void incBlue(int amount)
	{
		blue += amount;
		blue = Math.min(blue, 255);
		blue = Math.max(blue, 0);
	}
	
	public void incAlpha(int amount)
	{
		alpha += amount;
		alpha = Math.min(alpha, 255);
		alpha = Math.max(alpha, 0);
	}
	
	public int getRed()
	{
		return red;
	}
	
	public void setRed(int red)
	{
		this.red = red;
	}
	
	public int getGreen()
	{
		return green;
	}
	
	public void setGreen(int green)
	{
		this.green = green;
	}
	
	public int getBlue()
	{
		return blue;
	}
	
	public void setBlue(int blue)
	{
		this.blue = blue;
	}
	
	public int getAlpha()
	{
		return alpha;
	}
	
	public void setAlpha(int alpha)
	{
		this.alpha = alpha;
	}
	
	public void inc(int amount)
	{
		incBlue(amount);
		incRed(amount);
		incGreen(amount);
	}
}
