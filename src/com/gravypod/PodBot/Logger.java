package com.gravypod.PodBot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * Log file maker
 * 
 * @author gravypod
 *
 */
public class Logger {
	
	/**
	 * 
	 * Log a message into a log file.
	 * 
	 * @param sender
	 * @param channel
	 * @param message
	 * 
	 */
	public static void Log(String sender, String channel, String message) {
		
		try {
			
			File logFile = new File(channel + "." + PropLoader.getLogfile() + "." + PropLoader.getLogExtention());
			
			if(!logFile.exists())
				logFile.createNewFile();
			
			FileWriter outFile = new FileWriter(logFile, true);
			PrintWriter out = new PrintWriter(outFile);
			out.println(PodBot.date.toString() + " <" + sender + ">" + ": " + message);
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
