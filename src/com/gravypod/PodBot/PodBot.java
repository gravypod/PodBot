package com.gravypod.PodBot;

import java.io.File;
import java.util.Date;
import java.util.List;

public class PodBot {
	
	private static List<String> commands = null;
	
	public static Date date = new java.util.Date();
	
	public final static String pathSep = System.getProperty("file.separator");
	
	public static void main(String[] args) {
		
		new Thread() {
			
			@Override
			public void run() {
				
				
				PropLoader.PropCheck();
				PropLoader.PropLoad();
				
				File commandFile = new File(PodBot.rootLocation() + "commands");
				
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
	
	public static void reloadCommands() {
		
		commands = null;
		
		commands = ListClasses.getClasseNamesInPackage(jarLocation(), "com.gravypod.PodBot.commands.");
		
	}
	
	public static List<String> getCommands() {
		
		return commands;
		
	}
	
	public static String jarLocation() {
		
		return PodBot.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		
	}
	
	public static String rootLocation() {
		
		return PodBot.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("PodBot.jar", "");
		
	}
}
