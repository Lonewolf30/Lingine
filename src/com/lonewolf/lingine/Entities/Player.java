package com.lonewolf.lingine.Entities;

import com.lonewolf.lingine.Engine.CoreEngine.CoreEngine;
import com.lonewolf.lingine.Engine.CoreEngine.Matrix4f;
import com.lonewolf.lingine.Engine.GameComponents.Camera;
import com.lonewolf.lingine.Engine.GameComponents.FreeFlight;
import com.lonewolf.lingine.Engine.GameComponents.FreeLook;
import com.lonewolf.lingine.Engine.GameComponents.FreeMove;

public class Player extends Entity
{
	public Player()
	{
		super();
	}
	
	@Override
	public float getMaxHealth()
	{
		return 20;
	}
	
	@Override
	public void setEngine(CoreEngine engine)
	{
		this.engine = engine;
		initObjects();
		super.setEngine(engine);
	}
	
	private void initObjects()
	{
		addComponent(new FreeMove(5));
		addComponent(new FreeLook(0.5f));
		addComponent(new Camera(new Matrix4f().InitPerspective((float) Math.toRadians(60),
				getEngine().getRenderEngine().getWindow().getAspect(),
				0.01f, 1000)));
		addComponent(new FreeFlight(10));
	}
}
