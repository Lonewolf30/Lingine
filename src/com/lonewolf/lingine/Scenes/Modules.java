package com.lonewolf.lingine.Scenes;

import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.UI.UiColor;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiBlock;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiButton;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiText;
import com.lonewolf.lingine.Engine.UI.UiObject;

public class Modules extends Game
{
	@Override
	public void init()
	{
		addUiElement(new UiObject().addComponent(new UiBlock(new UiColor(128,128,128,255), 0)));
		
		
		addUiElement(loadExitButton());
	}
	
	private UiObject loadExitButton()
	{
		UiObject button = new UiObject().addComponent(
				new UiButton(new UiColor(100, 100, 100, 255), 30, 25)
				{
					@Override
					public void run()
					{
						engine.loadGame(getPrevGame(), null);
					}
				});
		
		
		
		button.setScale(new Vector2f(0.25f,0.1f));
		button.setPos(new Vector2f(150,75));
		button.addComponent(new UiText(new UiColor(0,0,0,255),"Main Menu", false));
		return button;
	}
}
