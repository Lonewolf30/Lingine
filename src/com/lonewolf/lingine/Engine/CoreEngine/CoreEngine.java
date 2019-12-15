package com.lonewolf.lingine.Engine.CoreEngine;

import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Modules.ModuleLoader;

import java.io.File;

public class CoreEngine
{
	private Game game;
	private ModuleLoader loader;
	private Configuration configuration;
	private RenderingEngine renderEngine;
	private float delta;
	
	private boolean isRunning;
	private boolean frameSkip;
	
	public CoreEngine(Game game)
	{
		configuration = new Configuration();
		configuration.loadConfigurationFile(new File("settings"));
		Logger.LogD("Engine Start");
		this.game = game;
		this.frameSkip = configuration.getConfig("frameSkip", false);
		isRunning = false;
		loader = new ModuleLoader();
		loader.loadModules();
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
	
	private void MainLoop()
	{
		Logger.LogD("Main Game Loop Start");
		renderEngine = new RenderingEngine();
		game.setRenderingEngine(renderEngine);
		game.setWindowAttrib(renderEngine.getWindow());
		renderEngine.loadAll();
		game.setEngine(this);
		game.init();
		loader.addModules(game);
		int frames = 0;
		
		float frameTime = 1.0f / 60;
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
			
			if (processedTime > frameTime)
			{
				processedTime -= frameTime;
				
				if (frameSkip && processedTime > frameTime *3)
					processedTime -= frameTime*3;
				
				if (renderEngine.getWindow().isClosedRequested())
					stop();
				
				delta = usedTime;
				
				update();
				frames++;
				
				if (passedTime > 1)
				{
					Logger.LogD(String.valueOf(frames));
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
	
	public void loadGame(Game game)
	{
		this.game = game;
		renderEngine = new RenderingEngine();
		game.setRenderingEngine(renderEngine);
		game.setWindowAttrib(renderEngine.getWindow());
		renderEngine.loadAll();
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
