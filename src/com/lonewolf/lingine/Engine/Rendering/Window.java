package com.lonewolf.lingine.Engine.Rendering;


import com.lonewolf.lingine.Engine.CoreEngine.Input;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Logger;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;

public class Window
{
    private long display;
    private Input input;
    private int displayWidth;
    private int displayHeight;
    private RenderingEngine renderEngine;

    private static boolean isInit = true;
    public Window()
    {
        if(isInit && !glfwInit())
            Logger.LogE(error());

        isInit = false;
        
        displayWidth = 800;
        displayHeight = 600;

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        display = glfwCreateWindow(displayWidth,displayHeight, "Lingine",0,0);

        if (display == 0 ) Logger.LogE(error());

        input = new Input(this);
        glfwSetWindowPos(display, (int)dimension.getWidth()/2 - displayWidth/2, (int)dimension.getHeight()/2 - displayHeight/2);

        glfwSetFramebufferSizeCallback(display, new GLFWFramebufferSizeCallback()
        {
            @Override
            public void invoke(long window, int width, int height)
            {
                displayWidth = width;
                displayHeight = height;
                GL11.glViewport(0,0, width, height);
                renderEngine.setDisplayResized(true);
            }
        });
        
        loadGlCap();
    }

    public void setSize(Vector2f size)
    {
        glfwSetWindowSize(display, (int)size.GetX(), (int)size.GetY());
    }

    public void setFloatingPane(boolean state)
    {
        if (state)
            glfwSetWindowAttrib(display, GLFW_DECORATED, GLFW_FALSE);
        else
            glfwSetWindowAttrib(display, GLFW_DECORATED, GLFW_TRUE);
    }
    
    public void setRenderEngine(RenderingEngine renderEngine)
	{
		this.renderEngine = renderEngine;
	}

    public void destroy()
    {
        glfwTerminate();
    }

    public void loadGlCap()
    {
        glfwMakeContextCurrent(display);
    }

    public boolean isKeyDown(int keyCode)
    {
        return glfwGetKey(display, keyCode) == GLFW_PRESS;
    }

    public String getKeyName(int keyCode)
    {
        return glfwGetKeyName(keyCode, glfwGetKeyScancode(keyCode));
    }

    public boolean isMouseButtonDown(int mouseCode)
    {
        return glfwGetMouseButton(display, mouseCode) == GLFW_PRESS;
    }

    private Exception error()
    {
        return new Exception("Display Creation Failed");
    }
    
    public void setFullscreen()
    {
        glfwMaximizeWindow(display);
    }

    public boolean isClosedRequested()
    {
        return glfwWindowShouldClose(display);
    }
    
    public Vector2f getWindowSize()
    {
        return new Vector2f(displayWidth, displayHeight);
    }
    
    public float getAspect()
    {
        return (float)displayHeight / displayWidth;
    }

    public void createDisplay()
    {
        glfwShowWindow(display);
        glfwMakeContextCurrent(display);
        GL.createCapabilities();
    }
    
    public void update()
    {
        glfwPollEvents();
        glfwSwapBuffers(display);
    }

    public void setDisplayMouseGrabbed(boolean grabbed)
    {
        glfwSetInputMode(display, GLFW_CURSOR, grabbed ? GLFW_CURSOR_HIDDEN : GLFW_CURSOR_NORMAL);
    }

    public boolean isMouseGrabbed()
    {
        return glfwGetInputMode(display, GLFW_CURSOR) == GLFW_CURSOR_HIDDEN;
    }

    public Vector2f MousePos()
    {
        double[] x = {0};
        double[] y = {0};
        glfwGetCursorPos(display, x,y);
        return new Vector2f((float)x[0],displayHeight - (float)y[0]);
    }

    public void setCursorPosition(int getX, int getY)
    {
        glfwSetCursorPos(display, getX, getY);
    }

    public Input getInput() {
        return input;
    }
}
