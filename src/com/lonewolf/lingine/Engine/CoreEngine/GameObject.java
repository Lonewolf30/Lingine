package com.lonewolf.lingine.Engine.CoreEngine;

import com.lonewolf.lingine.Engine.GameComponents.GameComponent;
import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Engine.Rendering.Shader;

import java.util.ArrayList;

public class GameObject
{
    private Transform transform;
    private ArrayList<GameComponent> components;
    protected Game game;
    protected CoreEngine engine;

    public GameObject()
    {
        transform = new Transform();
        components = new ArrayList<>();
    }

    public GameObject addComponent(GameComponent component)
    {
        component.SetParent(this);
        components.add(component);
        return this;
    }

    public void Input(Input input)
    {
        components.forEach(component -> component.Input(input));
    }

    public void Update(float delta)
    {
        components.forEach(component -> component.Update(delta));
    }

    public Transform getTransform()
    {
        return transform;
    }

    public void setEngine(CoreEngine engine)
    {
        this.engine = engine;
        components.forEach(component -> component.AddToEngine(engine));
    }

    public CoreEngine getEngine()
    {
        return engine;
    }

    public void Render(Shader shader, RenderingEngine renderEngine)
    {
        components.forEach(gameComponent -> gameComponent.Render(shader, renderEngine));
    }
}
