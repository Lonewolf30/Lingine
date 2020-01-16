package com.lonewolf.lingine.Engine.Rendering;


import com.lonewolf.lingine.Engine.CoreEngine.Input;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.memAllocInt;
import static org.lwjgl.system.MemoryUtil.memFree;

public class Window
{
    private long display;
    private Input input;
    private int displayWidth = 1000;
    private int displayHeight = 600;
    private RenderingEngine renderEngine;
    private Vector2f resizedSize;

    private static boolean isInit = true;
    
    private boolean fullscreen = false;
    private boolean decorated = true;
    
    public void createWidow()
    {
        if(isInit && !glfwInit())
            Logger.LogE(error());

        isInit = false;

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        
        if (!fullscreen)
            display = glfwCreateWindow(displayWidth,displayHeight, "Lingine", 0,0);
        else
        {
            display = glfwCreateWindow(dimension.width, dimension.height, "Lingine", glfwGetPrimaryMonitor(), 0);
            if (decorated)
                glfwSetWindowAttrib(display, GLFW_DECORATED, GLFW_FALSE);
            glfwMaximizeWindow(display);
            displayHeight = (int) dimension.getHeight();
            displayWidth = (int) dimension.getWidth();
        }
        
        
        if (display == 0)
        {
    
            if (!decorated)
                glfwSetWindowAttrib(display, GLFW_DECORATED, GLFW_FALSE);
            else
                glfwSetWindowAttrib(display, GLFW_DECORATED, GLFW_TRUE);
    
        }

        
        if (!fullscreen)
            glfwSetWindowPos(display, (int)dimension.getWidth()/2 - displayWidth/2, (int)dimension.getHeight()/2 - displayHeight/2);
        setIcon();
        input = new Input(this);
        glfwSetFramebufferSizeCallback(display, new GLFWFramebufferSizeCallback()
        {
            @Override
            public void invoke(long window, int width, int height)
            {
                resizedSize = new Vector2f(width-displayWidth, height - displayHeight);
                displayWidth = width;
                displayHeight = height;
                GL11.glViewport(0,0, width, height);
                renderEngine.setDisplayResized(true);
            }
        });
        
        loadGlCap();
    }
    
    public void setFullscreen()
    {
        fullscreen = true;
    }
    
    private void setIcon()
    {
        IntBuffer w = memAllocInt(1);
        IntBuffer h = memAllocInt(1);
        IntBuffer comp = memAllocInt(1);
        
        // Icons
        {
            ByteBuffer icon16;
            ByteBuffer icon32;
            try {
                icon16 = ioResourceToByteBuffer("./res/texture/logo.png", 2048);
                icon32 = ioResourceToByteBuffer("./res/texture/logo.png", 4096);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            
            try ( GLFWImage.Buffer icons = GLFWImage.malloc(2) ) {
                ByteBuffer pixels16 = STBImage.stbi_load_from_memory(icon16, w, h, comp, 4);
                icons
                        .position(0)
                        .width(w.get(0))
                        .height(h.get(0))
                        .pixels(pixels16);
                
                ByteBuffer pixels32 = STBImage.stbi_load_from_memory(icon32, w, h, comp, 4);
                icons
                        .position(1)
                        .width(w.get(0))
                        .height(h.get(0))
                        .pixels(pixels32);
                
                icons.position(0);
                glfwSetWindowIcon(display, icons);
                
                STBImage.stbi_image_free(pixels32);
                STBImage.stbi_image_free(pixels16);
            }
        }
        
        memFree(comp);
        memFree(h);
        memFree(w);
        
    }
    
    private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }
    
    private static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws IOException {
        ByteBuffer buffer;
        
        Path path = Paths.get(resource);
        if ( Files.isReadable(path) ) {
            try (SeekableByteChannel fc = Files.newByteChannel(path)) {
                buffer = BufferUtils.createByteBuffer((int)fc.size() + 1);
                while ( fc.read(buffer) != -1 ) ;
            }
        } else {
            try (
                    InputStream source = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
                    ReadableByteChannel rbc = Channels.newChannel(source)
            ) {
                buffer = BufferUtils.createByteBuffer(bufferSize);
                
                while ( true ) {
                    int bytes = rbc.read(buffer);
                    if ( bytes == -1 )
                        break;
                    if ( buffer.remaining() == 0 )
                        buffer = resizeBuffer(buffer, buffer.capacity() * 2);
                }
            }
        }
        
        buffer.flip();
        return buffer;
    }
    
    public void setSize(Vector2f size)
    {
        glfwSetWindowSize(display, (int)size.GetX(), (int)size.GetY());
    }

    public void setFloatingPane()
    {
        decorated = false;
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
    
    public Vector2f getResizedSize()
    {
        return resizedSize;
    }
    
    public long getDisplay()
    {return display;
    }
}
