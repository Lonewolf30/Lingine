package com.lonewolf.lingine.Engine.Rendering;

public enum RenderTypes
{
    render3D,
    render2D;

    public static RenderTypes[] getAllRenderTypes()
    {
        RenderTypes[] renderTypes = new RenderTypes[2];
        renderTypes[0] = render2D;
        renderTypes[1] = render3D;
        return renderTypes;
    }
}
