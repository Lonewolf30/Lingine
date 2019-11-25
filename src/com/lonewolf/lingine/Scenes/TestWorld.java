package com.lonewolf.lingine.Scenes;

import com.lonewolf.lingine.Engine.CoreEngine.CoreEngine;
import com.lonewolf.lingine.Engine.CoreEngine.Game;
import com.lonewolf.lingine.Engine.CoreEngine.GameObject;
import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;
import com.lonewolf.lingine.Engine.GameComponents.DirectionalLight;
import com.lonewolf.lingine.Engine.GameComponents.MeshRenderer;
import com.lonewolf.lingine.Engine.Rendering.Material;
import com.lonewolf.lingine.Engine.Rendering.Mesh;
import com.lonewolf.lingine.Engine.Rendering.Texture;
import com.lonewolf.lingine.Entities.Player;

public class TestWorld extends Game
{
	@Override
	public void init()
	{
		GameObject light = new GameObject().addComponent(new DirectionalLight(new Vector3f(1,1,1),1));
		light.getTransform().SetPos(new Vector3f(5,0,0));
		addObject(light);
		
		addObject(new Player());
		
		Material material = new Material(new Texture("bricks2.jpg"), 1, 8,
				new Texture("bricks2_normal.png"), new Texture("bricks2_disp.jpg"), 0.04f, -1.0f);
		
		
		GameObject cube = new GameObject().addComponent(new MeshRenderer(new Mesh("cube.obj"), material));
		cube.getTransform().SetPos(new Vector3f(5,0,0));
		addObject(cube);
	}
	
	@Override
	public void setEngine(CoreEngine engine)
	{
		super.setEngine(engine);
	}
}
