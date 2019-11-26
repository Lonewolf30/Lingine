package com.lonewolf.lingine.Scenes;

import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.UI.UiColor;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiBlock;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiButton;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiText;
import com.lonewolf.lingine.Engine.UI.UiObject;

public class MainMenu extends Game
{
	@Override
	public void init()
	{
		addUiElement(new UiObject().addComponent(new UiBlock(new UiColor(128, 128, 128, 255), 0)));
		
		addUiElement(loadExitButton());
		addUiElement(loadModuleButton());
		addUiElement(loadSettingsButton());
		addUiElement(loadSinglePlayer());
	}
	
	private UiObject loadModuleButton()
	{
		UiObject object = new UiObject();
		object.addComponent(new UiButton(new UiColor(100, 100, 100, 255), 30, 25)
		{
			@Override
			public void run()
			{
				engine.loadGame(new Modules());
			}
		});
		
		object.setScale(new Vector2f(0.25f, 0.1f));
		object.setPos(new Vector2f(150, 225));
		object.addComponent(new UiText(new UiColor(0, 0, 0, 255), "Modules", false));
		
		return object;
	}
	
	private UiObject loadSettingsButton()
	{
		UiObject object = new UiObject();
		object.addComponent(new UiButton(new UiColor(100, 100, 100, 255), 30, 25)
		{
			@Override
			public void run()
			{
				engine.loadGame(new Settings());
			}
		});
		
		object.setScale(new Vector2f(0.25f, 0.1f));
		object.setPos(new Vector2f(150, 150));
		object.addComponent(new UiText(new UiColor(0, 0, 0, 255), "Settings", false));
		
		return object;
	}
	
	private UiObject loadSinglePlayer()
	{
		UiObject object = new UiObject();
		object.addComponent(new UiButton(new UiColor(100, 100, 100, 255), 30, 25)
		{
			@Override
			public void run()
			{
				engine.loadGame(new TestWorld());
			}
		});
		
		object.setScale(new Vector2f(0.25f, 0.1f));
		object.setPos(new Vector2f(150, 550));
		object.addComponent(new UiText(new UiColor(0, 0, 0, 255), "Single Player", false));
		
		return object;
	}
	
	private UiObject loadExitButton()
	{
		UiObject button = new UiObject().addComponent(
				new UiButton(new UiColor(100, 100, 100, 255), 30, 25)
				{
					@Override
					public void run()
					{
						engine.stop();
					}
                });
		
		
		button.setScale(new Vector2f(0.25f, 0.1f));
		button.setPos(new Vector2f(150, 75));
		button.addComponent(new UiText(new UiColor(0, 0, 0, 255), "Exit", false));
		return button;
	}
}