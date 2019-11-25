package com.lonewolf.lingine.Engine.CoreEngine;

public class Time
{
    public static long second = 1000000000L;
    private float Delta;

    public void setDelta(float delta)
    {
        this.Delta = delta;
    }

    public float getDelta()
    {
        return  Delta;
    }
}
