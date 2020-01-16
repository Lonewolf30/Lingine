package com.lonewolf.lingine;

import com.lonewolf.lingine.Engine.CoreEngine.CoreEngine;
import com.lonewolf.lingine.Scenes.MainMenu;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Main
{
	private static ArrayList<Thread> engines = new ArrayList<>();
	private static boolean saveLogs = true;

	public static void main(String[] args)
	{
		loadargs(args);
		
		Logger logger = new Logger();
		Logger.LogI("Main Program Start");
		Logger.LogI("Max Memory Allocated: " + Runtime.getRuntime().maxMemory() / 1024 / 1024 / 1024 + " GB");

		Thread thread1 = new Thread(() ->
                new CoreEngine(new MainMenu()).start());
		engines.add(thread1);
		engines.get(engines.size() - 1).start();

		while (!engines.isEmpty())
		{
			ArrayList<Thread> temp = new ArrayList<>(engines);
			for (Thread thread : temp)
			{
				if (!thread.isAlive())
				{
					Logger.LogI("Thread Closed");
					engines.remove(thread);
				}
			}
		}

		Logger.LogI("Program End");
		
		if (saveLogs)
			logger.saveLogs();
	}
	
	private static void loadargs(String[] args)
	{
		HashMap<String, String> values = new HashMap<>();
		for (String string:args)
		{
			String[] val = string.split("=");
			values.put(val[0],val[1]);
		}
		
		saveLogs = values.containsKey("saveLog") && Boolean.parseBoolean(values.get("saveLogs"));
		Logger.setDebugLog(values.containsKey("debugLog") && Boolean.parseBoolean(values.get("debugLog")));
		Reference.fileDirectory = values.containsKey("moduleDir") ? values.get("moduleDir")+"/" : Reference.fileDirectory;
	}

	public static void addEngine(@NotNull CoreEngine engine)
	{
		Logger.LogI("Thread Add");
		Thread thread = new Thread(engine::start);
		engines.add(thread);
		engines.get(engines.size() - 1).start();
	}
}
