package com.lonewolf.lingine.Scenes;

import com.lonewolf.lingine.Engine.CoreEngine.CoreEngine;
import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.Rendering.Window;
import com.lonewolf.lingine.Engine.UI.UIAnimation.UIAnimator;
import com.lonewolf.lingine.Engine.UI.UIModifier;
import com.lonewolf.lingine.Engine.UI.UIScalation.AspectScale;
import com.lonewolf.lingine.Engine.UI.UIScalation.WindowScale;
import com.lonewolf.lingine.Engine.UI.UITranslation.PercentTranslation;
import com.lonewolf.lingine.Engine.UI.UiColor;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiBlock;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiButton;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiComponent;
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
//		window.setFullscreen();
//		window.setFloatingPane();
	}
	
	@Override
	public void init()
	{
	
	}
}