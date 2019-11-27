package com.lonewolf.lingine.Scenes;

import com.lonewolf.lingine.Engine.CoreEngine.CoreEngine;
import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Engine.Rendering.Window;
import com.lonewolf.lingine.Engine.UI.UIModifier;
import com.lonewolf.lingine.Engine.UI.UIScalation.AspectContraint;
import com.lonewolf.lingine.Engine.UI.UIScalation.ParentScale;
import com.lonewolf.lingine.Engine.UI.UIScalation.PixelScale;
import com.lonewolf.lingine.Engine.UI.UIScalation.WindowScale;
import com.lonewolf.lingine.Engine.UI.UITranslation.PixelTranslation;
import com.lonewolf.lingine.Engine.UI.UiColor;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiBlock;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiButton;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiText;
import com.lonewolf.lingine.Engine.UI.UiObject;
import com.lonewolf.lingine.Logger;

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
//		window.setFullscreen();
//		window.setFloatingPane();
	}
	
	@Override
	public void init()
	{
		UiObject background = new UiObject().addComponent(new UiBlock(new UiColor(128, 128, 128, 255), 0));
		UIModifier modifier = new UIModifier();

		modifier.setWidth(new WindowScale(0.5f));
		modifier.setHeight(new AspectContraint());
		modifier.setX(new PixelTranslation(0));
		modifier.setY(new PixelTranslation(0));
		
		background.setModifer(modifier);
		addUiElement(background);
	}
}