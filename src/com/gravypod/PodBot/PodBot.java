package com.gravypod.PodBot;

import java.io.File;
import java.util.Date;
import java.util.regex.Pattern;

import com.gravypod.PodBot.config.PropLoader;

/**
 * 
 * The main class of PodBot
 * 
 * @author gravypod
 * 
 */
public class PodBot {
	
	/** The date */
	public static Date date = new java.util.Date();
	
	/** The path separator for the OS java is running on */
	public final static String pathSep = System.getProperty("file.separator");
	
	/** The line separator for the OS java is running on */
	public final static String lineSep = System.getProperty("line.separator");
	
	/** Pattern to match all urls */
	public static Pattern urlPattern = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
	
	public static Bot bot;
	
	public static void main(String[] args) {
	
		PropLoader.PropCheck();
		PropLoader.PropLoad();
		
		File commandFile = new File("commands");
		
		if (!commandFile.exists())
			commandFile.mkdirs();
		
		bot = new Bot(commandFile);
	}
	
}
