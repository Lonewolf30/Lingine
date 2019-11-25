package com.lonewolf.lingine.Engine.UI.UiComponents;

import com.lonewolf.lingine.Engine.CoreEngine.CoreEngine;
import com.lonewolf.lingine.Engine.CoreEngine.Input;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Engine.Rendering.Shader;
import com.lonewolf.lingine.Engine.UI.UiColor;

public abstract class UiButton extends UiBlock
{
	private Vector2f windowSize;
	private boolean inced;
	private float colorShift;
	
	public UiButton(UiColor color, float cornerRadius, float colorShift)
	{
		super(color, cornerRadius);
		inced = false;
		this.colorShift = colorShift;
	}
	
	@Override
	public void render(Shader shader, RenderingEngine renderEngine)
	{
		windowSize = renderEngine.getWindow().getWindowSize();
		super.render(shader, renderEngine);
	}
	
	@Override
	public void setEngine(CoreEngine engine)
	{
		super.setEngine(engine);
		windowSize = engine.getRenderEngine().getWindow().getWindowSize();
	}
	
	public abstract void run();
	
	@Override
	public void input(Input input)
	{
		Vector2f mousePos = input.GetMousePosition();
		Vector2f scale = parent.getTransformedScale().Mul(windowSize);
		Vector2f location = parent.getTransformedPos();
		
		if (location.GetX() - scale.GetX()/2 < mousePos.GetX() && mousePos.GetX() < location.GetX() + scale.GetX()/2)
		{
			if (location.GetY() - scale.GetY()/2 < mousePos.GetY() && mousePos.GetY() < location.GetY() + scale.GetY()/2)
			{
				if (!inced)
				{
					color.inc(-(int)colorShift);
					generateTexture();
					inced = true;
				}
				
				if (input.GetMouseDown(0))
				{
					run();
				}
				
				return;
			}
		}
		
		if (inced)
		{
			color.inc((int)colorShift);
			generateTexture();
			inced = false;
		}
		
		
	}
	
	
}