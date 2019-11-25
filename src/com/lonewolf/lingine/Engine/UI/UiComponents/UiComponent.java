package com.lonewolf.lingine.Engine.UI.UiComponents;

import com.lonewolf.lingine.Engine.CoreEngine.CoreEngine;
import com.lonewolf.lingine.Engine.CoreEngine.Input;
import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Engine.Rendering.Shader;
import com.lonewolf.lingine.Engine.UI.UiObject;

public class UiComponent
{
	protected CoreEngine engine;
	protected UiObject parent;
	
	public void update(float delta){}
	
	public void input(Input input){}
	
	public void render(Shader shader, RenderingEngine renderEngine){}
	
	public void setParent(UiObject parent)
	{
		this.parent = parent;
	}
	
	public void setEngine(CoreEngine engine)
	{
		this.engine = engine;
	}
}
