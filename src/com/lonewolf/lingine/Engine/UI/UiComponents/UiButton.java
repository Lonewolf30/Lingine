package com.lonewolf.lingine.Engine.UI.UiComponents;

import com.lonewolf.lingine.Engine.UI.UIAnimation.UIAnimator;
import com.lonewolf.lingine.Engine.UI.UiColor;
import com.lonewolf.lingine.Engine.CoreEngine.Input;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

public abstract class UiButton extends UiBlock
{
	private boolean inced;
	private float colorShift;
	protected UIAnimator animator;
	
	public UiButton(UiColor color, float cornerRadius, float colorShift)
	{
		super(color, cornerRadius);
		inced = false;
		this.colorShift = colorShift;
	}
	
	public abstract void run();
	
	public abstract int keyBind();
	
	@Override
	public void input(Input input)
	{
		Vector2f mousePos = input.GetMousePosition();
		Vector2f scale = parent.getTransformedScale();
		Vector2f location = parent.getTransformedPos();
		
		if (input.GetKeyDown(keyBind()))
			run();
		
		if (inced)
			if (input.GetMouseDown(0))
			{
				run();
				glBindTexture(GL_TEXTURE_2D, 0);
			}
		
		if (location.GetX() - scale.GetX() / 2 < mousePos.GetX() && mousePos.GetX() < location.GetX() + scale.GetX() / 2)
		{
			if (location.GetY() - scale.GetY() / 2 < mousePos.GetY() && mousePos.GetY() < location.GetY() + scale.GetY() / 2)
			{
				if (!inced)
				{
					color.inc(-(int) colorShift);
					generateTexture();
					inced = true;
				}
				
				return;
			}
		}
		
		if (inced)
		{
			color.inc((int) colorShift);
			generateTexture();
			inced = false;
		}
	}
}