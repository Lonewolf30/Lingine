package com.lonewolf.lingine;

import com.lonewolf.lingine.Engine.CoreEngine.CoreEngine;
import com.lonewolf.lingine.Scenes.MainMenu;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

public class Main
{
	private static ArrayList<Thread> engines = new ArrayList<>();

	public static void main(String[] args)
	{
		Logger logger = new Logger();
		Logger.LogI("Main Program Start");

		Thread thread1 = new Thread(() ->
                new CoreEngine(new MainMenu()).start());
		engines.add(thread1);
		engines.get(engines.size() - 1).start();
		engines.get(engines.size() - 1).setPriority(Thread.MAX_PRIORITY);

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
		logger.saveLogs();
	}

	public static void addEngine(@NotNull CoreEngine engine)
	{
		Logger.LogI("Thread Add");
		Thread thread = new Thread(engine::start);
		engines.add(thread);
		engines.get(engines.size() - 1).start();
		engines.get(engines.size()-1).setPriority(Thread.NORM_PRIORITY);
	}
}
