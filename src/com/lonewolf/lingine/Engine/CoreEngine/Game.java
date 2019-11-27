package com.lonewolf.lingine.Engine.CoreEngine;

import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Engine.Rendering.Window;
import com.lonewolf.lingine.Engine.UI.UiObject;

import java.util.ArrayList;

public abstract class Game
{
	private RenderingEngine renderingEngine;
	private ArrayList<GameObject> objects;
	private ArrayList<UiObject> uielements;
	public CoreEngine engine;
	
	public Game()
	{
		objects = new ArrayList<>();
		uielements = new ArrayList<>();
	}
	
	public abstract void init();
	
	public void addObject(GameObject object)
	{
		object.setEngine(engine);
		objects.add(object);
	}
	
	public void addUiElement(UiObject object)
	{
		object.setEngine(engine);
		uielements.add(object);
	}
	
	public void removeObject(GameObject object)
	{
		objects.remove(object);
	}
	
	public void removeObject(UiObject object)
	{
		uielements.remove(object);
	}
	
	public void Render()
	{
		renderingEngine.Render(objects);
		renderingEngine.Render2d(uielements);
		renderingEngine.update();
	}
	
	public void setWindowAttrib(Window window)
	{}
	
	public void Update(float delta)
	{
		for (int i = 0, objectsSize = objects.size(); i < objectsSize; i++)
		{
			GameObject gameObject = objects.get(i);
			gameObject.Update(delta);
		}
		for (int i = 0, uielementsSize = uielements.size(); i < uielementsSize; i++)
		{
			UiObject object = uielements.get(i);
			object.update(delta);
		}
	}
	
	public void Input(Input input)
	{
		for (int i = 0, objectsSize = objects.size(); i < objectsSize; i++)
		{
			GameObject object = objects.get(i);
			object.Input(input);
		}
		
		for (int i = 0, uielementsSize = uielements.size(); i < uielementsSize; i++)
		{
			UiObject element = uielements.get(i);
			element.input(input);
		}
	}
	
	public void setEngine(CoreEngine engine)
	{
		this.engine = engine;
	}
	
	public CoreEngine getEngine()
	{
		return engine;
	}
	
	public void setRenderingEngine(RenderingEngine renderingEngine)
	{
		this.renderingEngine = renderingEngine;
	}
	
	public RenderingEngine getRenderingEngine()
	{
		return renderingEngine;
	}
	
	public ArrayList<GameObject> getAllObjects()
	{
		return objects;
	}
	
	public void clearObjects()
	{
		objects.clear();
	}
}
