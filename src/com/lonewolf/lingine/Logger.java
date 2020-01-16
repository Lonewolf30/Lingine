package com.lonewolf.lingine;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Logger
{
	private static boolean logDebug = false;
	private static Logger instance;
	private static BlockingQueue<String> logs = new LinkedBlockingQueue<String>()
	{
		@Override
		public boolean add(@NotNull String log)
		{
			String timeStamp = new SimpleDateFormat("[HH:mm:ss] ").format(Calendar.getInstance().getTime());
			String classCaller = "[" + Thread.currentThread().getStackTrace()[4].getClassName() +"] ";
			System.out.println("\u001B[36m\u001b[49m" + timeStamp + classCaller + log);
			return super.add(timeStamp + log);
		}
	};
	
	/**
	 * Setting Where to log Debug Logs
	 *
	 * @param logDebug value to set log debug to.
	 */
	public static void setDebugLog(boolean logDebug)
	{
		Logger.logDebug = logDebug;
	}
	
	/**
	 * Logging an info to logger
	 *
	 * @param Message The message to be logged
	 */
	public static void LogI(Object Message)
	{
		logs.add("\u001B[32m[INFO]  " + Message + "\u001B[0m");
	}
	
	/**
	 * To Log an error and end program
	 *
	 * @param exception exception can be created by the user or an exception created by java.
	 */
	public static void LogE(Exception exception)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(exception.toString()).append("\n");
		for (StackTraceElement item : exception.getStackTrace())
			builder.append(item.toString()).append("\n");
		logs.add("\u001B[31m[ERROR] " + builder.toString() + "\u001B[0m");
		if (instance != null)
			instance.saveLogs();
		System.exit(1);
	}
	
	private static Random color = new Random();
	
	/**
	 * A Debug Logger
	 *
	 * @param Message The message to be logged
	 */
	public static void LogD(String Message)
	{
		StringBuilder log = new StringBuilder();
		String oldLog = "[DEBUG] " + Message;
		if (logDebug)
		{
			for (char letter : oldLog.toCharArray())
			{
				log
//						.append("\u001B[4")
//						.append(color.nextInt(8))
//						.append("m")
						.append("\u001B[3")
						.append(4)
						.append("m")
						.append(letter)
//						.append("\u001B[47")
						;
				
			}
			
			logs.add(log.toString());
		}
	}
	
	/**
	 * Save Logs to file
	 */
	public void saveLogs()
	{
		instance = this;
		String timeStamp = new SimpleDateFormat("[YYYY_MM_dd] [HH_mm_ss]").format(Calendar.getInstance().getTime());
		new File("./logs").mkdirs();
		File latest = new File("./logs/latest.log");
		File currentLog = new File("./logs/" + timeStamp + ".zip");
		try
		{
			latest.createNewFile();
			currentLog.createNewFile();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(latest));
			
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(currentLog));
			ZipEntry entry = new ZipEntry("latest.log");
			out.putNextEntry(entry);
			
			StringBuilder builder = new StringBuilder();
			
			while (!logs.isEmpty())
			{
				builder.append(logs.take()).append("\n");
			}
			
			writer.write(builder.toString());
			out.write(builder.toString().getBytes());
			out.closeEntry();
			
			out.close();
			writer.close();
			
		} catch (Exception ignored)
		{
		}
	}
}
