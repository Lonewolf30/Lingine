package com.lonewolf.lingine.Engine.UI.UiComponents.UiSlider;

import com.lonewolf.lingine.Engine.UI.UiComponents.UiComponent;

public class UiSlider extends UiComponent
{
	private UiSliderRecp recp;
	private boolean init = true;
	
	public UiSlider(UiSliderRecp recp)
	{
		this.recp = recp;
	}
	
	private void loadUiSlider()
	{
	
	}
	
	@Override
	public void update(float delta)
	{
		if (init)
		{
			init = false;
			loadUiSlider();
		}
	}
}
