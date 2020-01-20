package com.lonewolf.lingine.Scenes;

import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Engine.Rendering.Window;
import com.lonewolf.lingine.Engine.UI.UIModifier;
import com.lonewolf.lingine.Engine.UI.UIScalation.AspectScale;
import com.lonewolf.lingine.Engine.UI.UIScalation.PixelScale;
import com.lonewolf.lingine.Engine.UI.UIScalation.WindowScale;
import com.lonewolf.lingine.Engine.UI.UITranslation.PercentTranslation;
import com.lonewolf.lingine.Engine.UI.UiColor;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiBlock;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiButton;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiImage;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiText;
import com.lonewolf.lingine.Engine.UI.UiObject;
import com.lonewolf.lingine.Logger;

public class MainMenu extends Game
{
	@Override
	public void setWindowAttrib(Window window)
	{
	
	}
	
	@Override
	public void init()
	{
		loadBackground();

		settingsButton();
		exitButton();

		loadNameText();
		loadMainDispLogo();
		loadTermOfUse();
	}
	
	private void exitButton()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiButton(new UiColor(67, 66, 93,255),4,10)
		{
			@Override
			public void run()
			{
				engine.stop();
			}
		});
		
		background.addComponent(new UiText(new UiColor(255,255,255,175), "Exit", false));
		
		modifier.setX(new PercentTranslation(0.75f));
		modifier.setY(new PercentTranslation(0.1f));
		modifier.setWidth(new WindowScale(0.115f));
		modifier.setHeight(new WindowScale(0.063f));
		background.setModifier(modifier);
		
		addUiElement(background);
	}
	
	private void settingsButton()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiButton(new UiColor(67, 66, 93,255),4,10)
		{
			@Override
			public void run()
			{
				Logger.LogI("Setting Button");
			}
		});
		
		background.addComponent(new UiText(new UiColor(255,255,255,175),"Settings",false));
		
		modifier.setX(new PercentTranslation(0.75f));
		modifier.setY(new PercentTranslation(0.18f));
		modifier.setWidth(new WindowScale(0.115f));
		modifier.setHeight(new WindowScale(0.063f));
		background.setModifier(modifier);
		
		addUiElement(background);
	}
	
	private void loadTermOfUse()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiImage("tos.png",0));
		
		modifier.setX(new PercentTranslation(0.75f));
		modifier.setY(new PercentTranslation(1-0.971f));
		modifier.setWidth(new WindowScale(0.128f));
		modifier.setHeight(new WindowScale(0.018f));
		background.setModifier(modifier);
		
		addUiElement(background);
	}
	
	private void loadMainDispLogo()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiImage("MainDisplayLogo.png",0));
		
		modifier.setX(new PercentTranslation(0.25f));
		modifier.setY(new PercentTranslation(0.5f));
		modifier.setWidth(new WindowScale(0.5f));
		modifier.setHeight(new WindowScale(1));
		background.setModifier(modifier);
		
		addUiElement(background);
	}
	
	private void loadNameText()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiImage("NameText.png",0));
		
		modifier.setX(new PercentTranslation(0.75f));
		modifier.setY(new PercentTranslation(0.706f));
		modifier.setWidth(new PixelScale(160));
		modifier.setHeight(new PixelScale(67));
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