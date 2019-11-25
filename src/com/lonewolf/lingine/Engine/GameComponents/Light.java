package com.lonewolf.lingine.Engine.GameComponents;

import com.lonewolf.lingine.Engine.CoreEngine.CoreEngine;
import com.lonewolf.lingine.Engine.CoreEngine.GameObject;
import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;

public class Light extends BaseLight
{
    private Vector3f color;
    private Vector3f pos;
    private float intensity;

    public Light(Vector3f color, float intensity) {
        super(color, intensity);
    }

    @Override
    public void AddToEngine(CoreEngine engine)
    {
        pos = GetTransform().GetPos();
        engine.getRenderEngine().AddLight(this);
    }
    
    @Override
    public void SetParent(GameObject parent)
    {
        pos = parent.getTransform().GetPos();
    }
    
    public Vector3f getColor() {
        return color;
    }

    public Vector3f getPos() {
        return pos;
    }

    public float getIntensity() {
        return intensity;
    }
}
