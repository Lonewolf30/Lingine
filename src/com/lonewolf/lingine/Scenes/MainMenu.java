package com.lonewolf.lingine.Scenes;

import com.lonewolf.lingine.Engine.CoreEngine.CoreEngine;
import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Engine.Rendering.Window;
import com.lonewolf.lingine.Engine.UI.UIModifier;
import com.lonewolf.lingine.Engine.UI.UIScalation.AspectScale;
import com.lonewolf.lingine.Engine.UI.UIScalation.WindowScale;
import com.lonewolf.lingine.Engine.UI.UITranslation.PercentTranlation;
import com.lonewolf.lingine.Engine.UI.UiColor;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiBlock;
import com.lonewolf.lingine.Engine.UI.UiObject;

public class MainMenu extends Game
{
	@Override
	public void setEngine(CoreEngine engine)
	{
		super.setEngine(engine);
	}
	
	@Override
	public void setWindowAttrib(Window window)
	{
		window.setFullscreen();
		window.setFloatingPane();
	}
	
	@Override
	public void init()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiBlock(new UiColor(128, 128, 128, 255), 0));

		modifier.setWidth(new WindowScale(0.5f));
		modifier.setHeight(new AspectScale());
		modifier.setX(new PercentTranlation(0.5f));
		modifier.setY(new PercentTranlation(0.5f));
		
		background.setModifer(modifier);
		addUiElement(background);
	}
}