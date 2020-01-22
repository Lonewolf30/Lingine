package com.lonewolf.lingine.Scenes;

import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Engine.Rendering.Window;
import com.lonewolf.lingine.Engine.UI.UIModifier;
import com.lonewolf.lingine.Engine.UI.UIScalation.AspectScale;
import com.lonewolf.lingine.Engine.UI.UIScalation.WindowScale;
import com.lonewolf.lingine.Engine.UI.UITranslation.PercentTranslation;
import com.lonewolf.lingine.Engine.UI.UiColor;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiBlock;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiButton;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiSlider.UiSlider;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiSlider.UiSliderRecp;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiText;
import com.lonewolf.lingine.Engine.UI.UiObject;

public class Settings extends Game
{
	/*
	Language
	Theme
	Fullscreen
	Floating window
	 */
	
	@Override
	public void setWindowAttrib(Window window)
	{
		window.setFloatingPane();
	}
	
	@Override
	public void init()
	{
		loadBackground();

		loadUiSliderTest();
		loadMainMenuButton();
	}
	
	private void loadUiSliderTest()
	{
		loadSliderRec();
	}
	
	private UiObject loadSliderRec()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiSliderRecp(
				new UiColor(255,0,255,255),
				new UiColor(255,0,255,255)));
		
		modifier.setX(new PercentTranslation(0.5f));
		modifier.setY(new PercentTranslation(0.5f));
		modifier.setWidth(new WindowScale(0.25f));
		modifier.setHeight(new WindowScale(0.15f));
		background.setModifier(modifier);
		addUiElement(background);
		
		return background;
	}
	
	
	private void loadMainMenuButton()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiButton(new UiColor(67, 66, 93,255),4,10)
		{
			@Override
			public void run()
			{
				engine.loadGame(getPrevGame(), null);
			}
		});
		
		background.addComponent(new UiText(new UiColor(255,255,255,175),"Return",false));
		
		modifier.setX(new PercentTranslation(0.85f));
		modifier.setY(new PercentTranslation(0.08f));
		modifier.setWidth(new WindowScale(0.115f));
		modifier.setHeight(new WindowScale(0.063f));
		background.setModifier(modifier);
		
		addUiElement(background);
	}
	
	private void loadBackground()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiBlock(new UiColor(50,50,50,255), 0));
		
		modifier.setX(new PercentTranslation(0.5f));
		modifier.setY(new PercentTranslation(0.5f));
		modifier.setWidth(new WindowScale(1));
		modifier.setHeight(new AspectScale());
		background.setModifier(modifier);
		
		addUiElement(background);
	}
}
