package com.lonewolf.lingine.Scenes;

import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Engine.CoreEngine.Input;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.UI.UIModifier;
import com.lonewolf.lingine.Engine.UI.UIScalation.AspectScale;
import com.lonewolf.lingine.Engine.UI.UIScalation.WindowScale;
import com.lonewolf.lingine.Engine.UI.UITranslation.PercentTranslation;
import com.lonewolf.lingine.Engine.UI.UiColor;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiBlock;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiButton;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiText;
import com.lonewolf.lingine.Engine.UI.UiObject;
import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Modules.Module;

import java.util.Collection;

public class Modules extends Game
{
	@Override
	public void init()
	{
		loadBackground();
		
		loadModListBackground();
		
		Collection<Module> mods = ((MainMenu)getPrevGame()).getLoader().getModules();
		
		for (int i = 0; i < mods.size(); i++)
		{
			loadMod((Module) mods.toArray()[i], i);
		}
		
		loadExitButton();
	}
	
	private void loadMod(Module mod, int offset)
	{
		Logger.LogI(mod.getName());
	}
	
	private void loadModListBackground()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiBlock(new UiColor(25, 25, 25, 255), 4));
		
		modifier.setX(new PercentTranslation(0.5f));
		modifier.setY(new PercentTranslation(0.5f));
		modifier.setWidth(new WindowScale(0.98f));
		modifier.setHeight(new WindowScale(0.95f));
		background.setModifier(modifier);
		
		addUiElement(background);
		
		UiObject forground = new UiObject();
		UIModifier mod1 = new UIModifier();
		
		forground.addComponent(new UiBlock(new UiColor(10, 10, 10, 255), 4));
		
		mod1.setX(new PercentTranslation(0.26f));
		mod1.setY(new PercentTranslation(0.5f));
		mod1.setWidth(new WindowScale(0.494f));
		mod1.setHeight(new WindowScale(0.94f));
		forground.setModifier(mod1);
		
		addUiElement(forground);
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
	
	private void loadExitButton()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiButton(new UiColor(67, 66, 93, 255), 4, 10)
		{
			@Override
			public void run()
			{
				engine.loadGame(getPrevGame(), null);
			}
			
			@Override
			public int keyBind()
			{
				return Input.KEY_ESCAPE;
			}
		});
		
		background.addComponent(new UiText(new UiColor(255, 255, 255, 175), "Return", false));
		
		modifier.setX(new PercentTranslation(0.85f));
		modifier.setY(new PercentTranslation(0.08f));
		modifier.setWidth(new WindowScale(0.115f));
		modifier.setHeight(new WindowScale(0.063f));
		background.setModifier(modifier);
		
		addUiElement(background);
	}
}
