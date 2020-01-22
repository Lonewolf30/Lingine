package com.lonewolf.lingine.Engine.UI.UiComponents.UiSlider;

import com.lonewolf.lingine.Engine.UI.UiColor;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiBlock;

public class UiSliderRecp extends UiBlock
{
	private UiColor uncolor;
	private UiColor chcolor;
	
	public UiSliderRecp(UiColor chcolor, UiColor uncolor)
	{
		super(uncolor, 0);
		this.chcolor = chcolor;
		this.uncolor = uncolor;
	}
	
	public void switchColor()
	{
		if (color == uncolor)
			color = chcolor;
		else
			color = uncolor;
		
		generateTexture();
	}
	
	@Override
	protected void generateTexture()
	{
		this.cornerRadius = parent.getTransformedScale().GetY()/2;
		super.generateTexture();
	}
}
