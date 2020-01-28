package com.lonewolf.lingine.Scenes;

import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Engine.CoreEngine.Input;
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
import com.lonewolf.lingine.Modules.ModuleLoader;

public class MainMenu extends Game
{
	private Game instance;
	private ModuleLoader loader;
	
	public MainMenu()
	{
		instance = this;
		loader = new ModuleLoader();
		loader.loadModules();
	}
	
	public ModuleLoader getLoader()
	{
		return loader;
	}
	
	@Override
	public void init()
	{
		loadBackground();
		
		newGameButton();
		loadGameButton();
		modulesButton();
		settingsButton();
		exitButton();
		
		loadNameText();
		loadMainDispLogo();
		loadTermOfUse();
	}
	
	private void loadGameButton()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiButton(new UiColor(39, 204, 32, 255), 4, 25)
		{
			@Override
			public void run()
			{
			
			}
			
			@Override
			public int keyBind()
			{
				return Input.KEY_L;
			}
		});
		
		background.addComponent(new UiText(new UiColor(255, 255, 255, 255), "Load Game", true));
		
		modifier.setX(new PercentTranslation(0.75f));
		modifier.setY(new PercentTranslation(0.4375f));
		modifier.setWidth(new WindowScale(0.3125f));
		modifier.setHeight(new WindowScale(0.075f));
		background.setModifier(modifier);
		
		addUiElement(background);
	}
	
	private void newGameButton()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiButton(new UiColor(39, 204, 32, 255), 4, 25)
		{
			@Override
			public void run()
			{
				loader.setGame(instance);
				loader.addModules();
			}
			
			@Override
			public int keyBind()
			{
				return Input.KEY_N;
			}
		});
		
		background.addComponent(new UiText(new UiColor(255, 255, 255, 255), "New Game", true));
		
		modifier.setX(new PercentTranslation(0.75f));
		modifier.setY(new PercentTranslation(0.5375f));
		modifier.setWidth(new WindowScale(0.3125f));
		modifier.setHeight(new WindowScale(0.075f));
		background.setModifier(modifier);
		
		addUiElement(background);
	}
	
	private void modulesButton()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiButton(new UiColor(39, 204, 32, 255), 4, 25)
		{
			@Override
			public void run()
			{
				engine.loadGame(new Modules(), instance);
			}
			
			@Override
			public int keyBind()
			{
				return Input.KEY_M;
			}
		});
		
		background.addComponent(new UiText(new UiColor(255, 255, 255, 255), "Modules", true));
		
		modifier.setX(new PercentTranslation(0.75f));
		modifier.setY(new PercentTranslation(0.3375f));
		modifier.setWidth(new WindowScale(0.3125f));
		modifier.setHeight(new WindowScale(0.075f));
		background.setModifier(modifier);
		
		addUiElement(background);
	}
	
	private void exitButton()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiButton(new UiColor(67, 66, 93, 255), 4, 10)
		{
			@Override
			public void run()
			{
				engine.stop();
			}
			
			@Override
			public int keyBind()
			{
				return Input.KEY_END;
			}
		});
		
		background.addComponent(new UiText(new UiColor(255, 255, 255, 175), "Exit", true));
		
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
		
		background.addComponent(new UiButton(new UiColor(67, 66, 93, 255), 4, 10)
		{
			@Override
			public void run()
			{
				engine.loadGame(new Settings(), instance);
			}
			
			@Override
			public int keyBind()
			{
				return 0;
			}
		});
		
		background.addComponent(new UiText(new UiColor(255, 255, 255, 175), "Settings", true));
		
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
		
		background.addComponent(new UiText(new UiColor(255, 255, 255, 255), "Terms Of Use", true));
		
		modifier.setX(new PercentTranslation(0.75f));
		modifier.setY(new PercentTranslation(1 - 0.971f));
		modifier.setWidth(new WindowScale(1));
		modifier.setHeight(new WindowScale(0.05f));
		background.setModifier(modifier);
		
		addUiElement(background);
	}
	
	private void loadMainDispLogo()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiImage("MainDisplayLogo.png", 0));
		
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
		
		background.addComponent(new UiImage("NameText.png", 0));
		
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
		
		background.addComponent(new UiBlock(new UiColor(50, 50, 50, 255), 0));
		
		modifier.setX(new PercentTranslation(0.5f));
		modifier.setY(new PercentTranslation(0.5f));
		modifier.setWidth(new WindowScale(1));
		modifier.setHeight(new AspectScale());
		background.setModifier(modifier);
		
		addUiElement(background);
	}
	
}