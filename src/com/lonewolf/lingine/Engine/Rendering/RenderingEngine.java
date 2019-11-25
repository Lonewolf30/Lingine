package com.lonewolf.lingine.Engine.Rendering;

import com.lonewolf.lingine.Engine.CoreEngine.GameObject;
import com.lonewolf.lingine.Engine.CoreEngine.Transform;
import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;
import com.lonewolf.lingine.Engine.GameComponents.BaseLight;
import com.lonewolf.lingine.Engine.GameComponents.Camera;
import com.lonewolf.lingine.Engine.Rendering.resourceManagement.MappedValues;
import com.lonewolf.lingine.Engine.UI.UiObject;
import com.lonewolf.lingine.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_VERSION;

public class RenderingEngine extends MappedValues
{
	private HashMap<String, Integer> m_samplerMap;
	private ArrayList<BaseLight> m_lights;
	private BaseLight m_activeLight;

	private Shader m_forwardAmbient;
	private Shader gui;
	private Camera m_mainCamera;
	private Window window;

	public RenderingEngine()
	{
		super();
		
		window = new Window();
		window.createDisplay();
		
		m_lights = new ArrayList<BaseLight>();
		m_samplerMap = new HashMap<String, Integer>();
		m_samplerMap.put("diffuse", 0);
		m_samplerMap.put("normalMap", 1);
		m_samplerMap.put("dispMap", 2);

		AddVector3f("ambient", new Vector3f(0.1f, 0.1f, 0.1f));

		m_forwardAmbient = new Shader("forward-ambient");
		gui = new Shader("shader2d");

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		//
		// glEnable(GL_DEPTH_CLAMP);

		glEnable(GL_TEXTURE_2D);
	}
	
	public void setDisplayResized(Boolean resized)
	{
	
	}

	public void UpdateUniformStruct(Transform transform, Material material, Shader shader, String uniformName, String uniformType)
	{
		throw new IllegalArgumentException(uniformType + " is not a supported type in RenderingEngine");
	}
	
	public Window getWindow()
	{
		return window;
	}
	
	public void Render(ArrayList<GameObject> object)
	{
		window.loadGlCap();
		
		if (GetMainCamera() == null)
			Logger.LogD("Error! Main camera not found. This is very very big bug, and game will crash.");
		else
		{
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			
			object.forEach(gameObject -> gameObject.Render(m_forwardAmbient, this));
			
			glEnable(GL_BLEND);
			glBlendFunc(GL_ONE, GL_ONE);
			glDepthMask(false);
			glDepthFunc(GL_EQUAL);
			
			for (BaseLight light : m_lights)
			{
				m_activeLight = light;
				object.forEach(gameObject -> gameObject.Render(light.GetShader(), this));
			}
			
			glDepthFunc(GL_LESS);
			glDepthMask(true);
			glDisable(GL_BLEND);
		}
	}
	
	public void Render2d(ArrayList<UiObject> uielements)
	{
		window.loadGlCap();
		glDisable(GL_CULL_FACE);
		glDisable(GL_DEPTH_TEST);
		
		gui.SetUniform("windowSize", window.getWindowSize());
		uielements.forEach(element -> element.render(gui, this));
		
		
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
	}

	public static String GetOpenGLVersion()
	{
		return glGetString(GL_VERSION);
	}

	public void AddLight(BaseLight light)
	{
		m_lights.add(light);
	}

	public void AddCamera(Camera camera)
	{
		m_mainCamera = camera;
	}

	public int GetSamplerSlot(String samplerName)
	{
		return m_samplerMap.get(samplerName);
	}

	public BaseLight GetActiveLight()
	{
		return m_activeLight;
	}

	public Camera GetMainCamera()
	{
		return m_mainCamera;
	}

	public void SetMainCamera(Camera mainCamera)
	{
		this.m_mainCamera = mainCamera;
	}
	
	public void destroy()
	{
		window.destroy();
	}
	
	public void update()
	{
		window.update();
	}
}
