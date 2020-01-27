package com.lonewolf.lingine.Engine.CoreEngine;

import com.lonewolf.lingine.Logger;
import com.lonewolf.lingine.Reference;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Configuration
{
	private HashMap<String, String> configs;
	private String configName;
	
	public Configuration()
	{
		configs = new HashMap<>();
	}
	
	private File getDirectory()
	{
		File directory = new File(Reference.fileDirectory + "config/");

		if (!directory.exists())
		{
			directory.mkdirs();
		}
		
		return directory;
	}
	
	public static File getSuggestedFile(String ProjectName)
	{
		return new File(String.format("%s", ProjectName));
	}
	
	public void loadConfigurationFile(File configurationFile)
	{
		configName = configurationFile.getName();
		
		Logger.LogD(configName);
		File configFile = new File(getDirectory().getAbsoluteFile() + "/" + configName + ".cfg");
		if (!configFile.exists())
		{
			try
			{
				configFile.createNewFile();
			} catch (IOException e)
			{
				Logger.LogE(e);
			}
		}
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(configFile));
			String line;
			while ((line = reader.readLine()) != null)
			{
				String[] vals = line.split("=");
				if (vals.length > 1)
					configs.put(vals[0], vals[1]);
			}
			
			reader.close();
		} catch (Exception e)
		{
			Logger.LogE(e);
		}
	}
	
	public String getConfig(String configName, String defaultValue)
	{
		if (configs.containsKey(configName))
			return configs.get(configName);
		else
		{
			configs.put(configName, defaultValue);
			return defaultValue;
		}
	}
	
	public void setConfigValue(String id, String value)
	{
		configs.replace(id, value);
	}
	
	public int getConfig(String configName, int defaultValue)
	{
		return Integer.parseInt(getConfig(configName, String.valueOf(defaultValue)));
	}
	
	public float getConfig(String configName, float defaultValue)
	{
		return Float.parseFloat(getConfig(configName, String.valueOf(defaultValue)));
	}
	
	public boolean getConfig(String configName, boolean defaultValue)
	{
		return Boolean.parseBoolean(getConfig(configName, String.valueOf(defaultValue)));
	}
	
	public void saveConfig()
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(getDirectory().getAbsoluteFile() + "/" + configName + ".cfg"));
			
			for (Map.Entry<String, String> entry : configs.entrySet())
			{
				String s = entry.getKey();
				String s2 = entry.getValue();
				try
				{
					writer.write(s + "=" + s2);
					writer.newLine();
				} catch (IOException e)
				{
					Logger.LogE(e);
				}
			}
			
			writer.close();
			
		} catch (Exception e)
		{
			Logger.LogE(e);
		}
		
	}
}
