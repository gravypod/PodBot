package com.gravypod.PodBot;

import java.util.Date;
import java.util.List;

public class PodBot {
	
	private static List<String> commands = null;
	
	public static Date date = new java.util.Date();
	
	public final static String pathSep = System.getProperty("file.separator");
	
	public static void main(String[] args) {
		
		PropLoader.PropCheck();
		PropLoader.PropLoad();
		
		commands = ListClasses.getClasseNamesInPackage(jarLocation(), "com.gravypod.PodBot.commands.");
		
		for (String s : commands)
			System.out.println(s);
		
		new BotStartup();
		
	}
	
	public static List<String> getCommands() {
		
		return commands;
		
	}
	
	public static String jarLocation() {
		
		return PodBot.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	}
}
