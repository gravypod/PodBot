package com.gravypod.PodBot;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 
 * The main class of PodBot
 * 
 * @author gravypod
 *
 */
public class PodBot {
	
	private static List<String> commands = null;
	
	/** The date */
	public static Date date = new java.util.Date();
	
	/** The path separator for the OS java is running on */
	public final static String pathSep = System.getProperty("file.separator");
	
	/** The line separator for the OS java is running on */
	public final static String lineSep = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		
		new Thread() {
			
			@Override
			public void run() {
				
				
				PropLoader.PropCheck();
				PropLoader.PropLoad();
				
				File commandFile = new File("commands");
				
				if (!commandFile.exists())
					commandFile.mkdir();
					
				
				reloadCommands();
		
				for (String s : commands) {
					System.out.println(s);
				}
		
		
				new BotStartup();
			}
			
		}.start();
		
	}
	
	/**
	 * 
	 * Reload the list of commands that are in the command path.
	 * Soon to also populate the list with commands inside the 'plugin' folder
	 * 
	 */
	public static void reloadCommands() {
		
		commands = null;
		
		commands = ListClasses.getClasseNamesInPackage(jarLocation(), "com.gravypod.PodBot.commands.");
		
	}
	
	/**
	 * 
	 * Get the list of commands.
	 * 
	 */
	public static List<String> getCommands() {
		
		return commands;
		
	}
	
	/**
	 * 
	 * @return the location of the jar file.
	 * 
	 */
	public static String jarLocation() {
		
		return PodBot.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		
	}
	
}
