package com.lonewolf.lingine.Engine.CoreEngine;

import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Main;
import com.lonewolf.lingine.Modules.ModuleLoader;

import java.io.File;

public class CoreEngine
{
	private Game game;
	private Configuration configuration;
	private RenderingEngine renderEngine;
	private float delta;
	
	private boolean isRunning;
	
	public CoreEngine(Game game)
	{
		configuration = new Configuration();
		configuration.loadConfigurationFile(new File("settings"));
		Logger.LogD("Engine Start");
		this.game = game;
		isRunning = false;
	}
	
	public void start()
	{
		if (isRunning)
			return;
		
		isRunning = true;
		MainLoop();
	}
	
	public void stop()
	{
		if (!isRunning)
			return;
		
		isRunning = false;
	}
	
	public Configuration getConfiguration()
    {
        return configuration;
    }
    
    private void loadItems()
    {
	    renderEngine = new RenderingEngine();
	    
	    game.setRenderingEngine(renderEngine);
	    game.setWindowAttrib(renderEngine.getWindow());
	    
	    renderEngine.loadAll();
	    
	    game.setEngine(this);
	    
	    game.init();
    }
    
	private void MainLoop()
	{
		Logger.LogD("Main Game Loop Start");
		loadItems();
		
		int frames = 0;
		
		float renderTime = 1.0f / 60f;
		float passedTime = 0;
		float processedTime = 0;
		
		float pastTime = (float) System.nanoTime() / Time.second;
		while (isRunning)
		{
			float currentTime = (float) System.nanoTime() / Time.second;
			float usedTime = currentTime - pastTime;
			pastTime = currentTime;
			
			processedTime += usedTime;
			passedTime += usedTime;
			
			while (processedTime >= renderTime)
			{
				processedTime = processedTime - renderTime;
				
				if (renderEngine.getWindow().isClosedRequested())
					stop();
				
				delta = usedTime;
				
				update();
				frames++;
				
				if (passedTime >= 1)
				{
					passedTime = 0;
					frames = 0;
				}
			}
		}
		
		cleanUp();
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public void loadGame(Game game, Game prev)
	{
		this.game = game;
		game.setPrevGame(prev);
		game.setRenderingEngine(renderEngine);
		game.setWindowAttrib(renderEngine.getWindow());
		game.setEngine(this);
		game.init();
	}
	
	private void update()
	{
		game.Update(delta);
		game.Input(renderEngine.getWindow().getInput());
		renderEngine.getWindow().getInput().Update();
		game.Render();
	}
	
	public RenderingEngine getRenderEngine()
	{
		return renderEngine;
	}
	
	private void cleanUp()
	{
		renderEngine.destroy();
		configuration.saveConfig();
	}
}
