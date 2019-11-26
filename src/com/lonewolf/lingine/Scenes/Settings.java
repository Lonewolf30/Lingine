package com.lonewolf.lingine.Scenes;

import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.UI.UiColor;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiBlock;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiButton;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiText;
import com.lonewolf.lingine.Engine.UI.UiObject;

public class Settings extends Game
{
	@Override
	public void init()
	{
		addUiElement(new UiObject().addComponent(new UiBlock(new UiColor(128,128,128,255), 0)));
		
		addUiElement(loadMainMenuButton());
	}
	
	private UiObject loadMainMenuButton()
	{
		UiObject object = new UiObject();
		object.addComponent(new UiButton(new UiColor(100,100,100,255), 30, 25)
		{
			@Override
			public void run()
			{
				engine.loadGame(new MainMenu());
			}
		});
		
		object.setScale(new Vector2f(0.25f, 0.1f));
		object.setPos(new Vector2f(150, 75));
		object.addComponent(new UiText(new UiColor(0,0,0,255),"MainMenu", false));
		
		return object;
	}
}
