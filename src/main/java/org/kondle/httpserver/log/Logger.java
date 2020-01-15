package org.kondle.httpserver.log;

import org.kondle.httpserver.tools.console.ConsoleColors;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Logger
{
	private static File logDir = new File("log");
	private static File logFile = new File(logDir.getPath() + File.separator + "log.txt");
	static
	{
		if (!logDir.exists())
		{
			logDir.mkdir();
		}
		if (!logFile.exists())
		{
			try
			{
				logFile.createNewFile();
			} catch (IOException e)
			{
				System.out.println(ConsoleColors.RED + "Error: error creating log or log directory" + ConsoleColors.RESET);
			}
		}
	}
	public static void log(Exception e)
	{
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(logFile,true);
			OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
			writer.write("Time:" + new Date().getTime() + "\n");
			StackTraceElement[] elements = e.getStackTrace();
			for (int i = 0; i < elements.length; i++)
			{
				writer.write(e.getStackTrace()[i].toString() + "\n");
				writer.flush();
			}
			writer.write("--------------------\n");
			writer.flush();
			fileOutputStream.close();

		} catch (IOException ex)
		{
			System.out.println(ConsoleColors.RED + "Error: error logging:" + ConsoleColors.RESET);
		}
	}

	public static void log(String s)
	{
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(logFile,true);
			OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);

			writer.write(s);
			writer.flush();
			fileOutputStream.close();
		} catch (IOException ex)
		{
			System.out.println(ConsoleColors.RED + "Error: error logging:" + ConsoleColors.RESET);
		}
	}
}
