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
import com.lonewolf.lingine.Engine.UI.UiComponents.UiComponent;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiText;
import com.lonewolf.lingine.Engine.UI.UiObject;
import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Modules.Module;
import com.lonewolf.lingine.Reference;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class Modules extends Game
{
	private UiText modName;
	private UiText enableText;
	private Module selected;
	
	@Override
	public void init()
	{
		loadBackground();
		
		loadModListBackground();
		
		initText();
		
		Collection<Module> mods = ((MainMenu)getPrevGame()).getLoader().getModules();
		
		for (int i = 0; i < mods.size(); i++)
		{
			loadMod((Module) mods.toArray()[i], i);
		}
		
		if (mods.size() == 0)
		{
			loadEmptyMods();
		}
		else
		{
			enableButton();
			selected = (Module) mods.toArray()[0];
			loadModInfo();
		}
		
		openModsFolder();
		loadExitButton();
	}
	
	private void initText()
	{
		modName = new UiText(new UiColor(255,255,255,255),"", false);
		
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(modName);
		
		modifier.setX(new PercentTranslation(0.76f));
		modifier.setY(new PercentTranslation(0.5f));
		modifier.setWidth(new WindowScale(0.49f));
		modifier.setHeight(new WindowScale(0.95f));
		background.setModifier(modifier);
		
		addUiElement(background);
	}
	
	private void loadModInfo()
	{
		enableText.setText((selected.isEnabled() ? "Disable" : "Enable"));
		
		modName.setText(
				"Name:    "+selected.getName() + '\n'+
				"Version: "+selected.getVersion() + '\n'+
				"Enabled: "+selected.isEnabled() + '\n'+
				"Master Module: " + selected.isSuperMod()
		);
	}
	
	private void enableButton()
	{
		enableText = new UiText(new UiColor(255,255,255,255),"",true);
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiButton(new UiColor(67, 66, 93, 255), 4, 10)
		{
			@Override
			public void run()
			{
				selected.setEnabled(!selected.isEnabled());
				loadModInfo();
			}
			
			@Override
			public int keyBind()
			{
				return -1;
			}
		});
		
		background.addComponent(enableText);
		
		modifier.setX(new PercentTranslation(0.90f));
		modifier.setY(new PercentTranslation(0.18f));
		modifier.setWidth(new WindowScale(0.115f));
		modifier.setHeight(new WindowScale(0.063f));
		background.setModifier(modifier);
		
		addUiElement(background);
	}
	
	private void loadEmptyMods()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiBlock(new UiColor(25, 25, 25, 255), 4));
		background.addComponent(new UiText(new UiColor(200,200,200,255), "No Modules Loaded", true));
		
		modifier.setX(new PercentTranslation(0.26f));
		modifier.setY(new PercentTranslation(0.90f));
		modifier.setWidth(new PixelScale(200));
		modifier.setHeight(new PixelScale(40));
		background.setModifier(modifier);
		
		addUiElement(background);
	}
	
	private void loadMod(Module mod, int offset)
	{
		Logger.LogI(mod.getName());
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		UiText text = new UiText(new UiColor(210,210,200,255), " "+mod.getName(), false);
		
		background.addComponent(new UiButton(new UiColor(25, 25, 25, 255), 4, -15)
		{
			@Override
			public void run()
			{
				selected = mod;
				loadModInfo();
			}
			
			@Override
			public int keyBind()
			{
				return -1;
			}
		});
		
		background.addComponent(text);
		
		modifier.setX(new PercentTranslation(0.258f));
		modifier.setY(new PercentTranslation(0.92f-(offset*0.09f)));
		modifier.setWidth(new WindowScale(0.480f));
		modifier.setHeight(new WindowScale(0.08f));
		background.setModifier(modifier);
		
		addUiElement(background);
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
	
	private void openModsFolder()
	{
		UiObject background = new UiObject();
		UIModifier modifier = new UIModifier();
		
		background.addComponent(new UiButton(new UiColor(67, 66, 93, 255), 4, 10)
		{
			@Override
			public void run()
			{
				File file = new File (Reference.fileDirectory + "modules/");
				Desktop desktop = Desktop.getDesktop();
				try
				{
					desktop.open(file);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			@Override
			public int keyBind()
			{
				return -1;
			}
		});
		
		background.addComponent(new UiText(new UiColor(255, 255, 255, 175), "Open Modules Folder", true));
		
		modifier.setX(new PercentTranslation(0.67f));
		modifier.setY(new PercentTranslation(0.08f));
		modifier.setWidth(new WindowScale(0.25f));
		modifier.setHeight(new WindowScale(0.063f));
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
		
		background.addComponent(new UiText(new UiColor(255, 255, 255, 175), "Return", true));
		
		modifier.setX(new PercentTranslation(0.90f));
		modifier.setY(new PercentTranslation(0.08f));
		modifier.setWidth(new WindowScale(0.115f));
		modifier.setHeight(new WindowScale(0.063f));
		background.setModifier(modifier);
		
		addUiElement(background);
	}
}
