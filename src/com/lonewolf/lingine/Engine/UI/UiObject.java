package com.lonewolf.lingine.Engine.UI;

import com.lonewolf.lingine.Engine.CoreEngine.CoreEngine;
import com.lonewolf.lingine.Engine.CoreEngine.Input;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Engine.Rendering.Shader;
import com.lonewolf.lingine.Engine.UI.UiComponents.UiComponent;

import java.util.ArrayList;

public class UiObject
{
	private UiObject parent;
	
	private ArrayList<UiComponent> components;
	private ArrayList<UiObject> children;
	private CoreEngine engine;
	
	private UIModifier modifier;
	
	private Vector2f pos;
	private Vector2f scale;
	
	public UiObject()
	{
		components = new ArrayList<>();
		children = new ArrayList<>();
		
		engine = null;
		parent = null;
		
		pos = new Vector2f(0, 0);
		scale = new Vector2f(1, 1);
	}
	
	public UIModifier getModifier()
	{
		return modifier;
	}
	
	public void setModifier(UIModifier modifier)
	{
		modifier.init(this);
		this.modifier = modifier;
	}
	
	public void resized(RenderingEngine renderingEngine)
	{
		getModifier().resized(renderingEngine.getWindow());
	}
	
	public UiObject addComponent(UiComponent component)
	{
		component.setParent(this);
		components.add(component);
		return this;
	}
	
	public UiObject addChild(UiObject object)
	{
		object.setParent(this);
		children.add(object);
		return this;
	}
	
	public void update(float delta)
	{
		components.forEach(component -> component.update(delta));
		children.forEach(uiObject -> uiObject.update(delta));
	}
	
	public void input(Input input)
	{
		components.forEach(component -> component.input(input));
		children.forEach(child -> child.input(input));
	}
	
	public void render(Shader shader, RenderingEngine renderEngine)
	{
		components.forEach(component -> component.render(shader, renderEngine));
		children.forEach(children -> children.render(shader, renderEngine));
	}
	
	public void setEngine(CoreEngine engine)
	{
		this.engine = engine;
		components.forEach(component -> component.setEngine(engine));
		children.forEach(child -> child.setEngine(engine));
		modifier.resized(engine.getRenderEngine().getWindow());
	}
	
	public void setParent(UiObject parent)
	{
		this.parent = parent;
	}
	
	public void setPos(Vector2f pos)
	{
		this.pos = pos;
	}
	
	public void setScale(Vector2f scale)
	{
		this.scale = scale;
	}
	
	public Vector2f getTransformedScale()
	{
		return getParentScale().Mul(new Vector2f(modifier.getWidth(), modifier.getHeight()));
	}
	
	public Vector2f getTransformedPos()
	{
		return getParentPos().Add(new Vector2f(modifier.getX(), modifier.getY()));
	}
	
	public Vector2f getParentScale()
	{
		if (parent == null)
		{
			return new Vector2f(1, 1);
		}
		
		return parent.getTransformedScale();
	}
	
	public Vector2f getParentPos()
	{
		if (parent == null)
		{
			return new Vector2f(0, 0);
		}
		
		return parent.getTransformedPos();
	}
}
