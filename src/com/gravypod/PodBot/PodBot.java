package com.gravypod.PodBot;

import java.io.File;
import java.util.regex.Pattern;

/**
 * 
 * The main class of PodBot
 * 
 * @author gravypod
 * 
 */
public class PodBot {
	
	/** The path separator for the OS java is running on */
	public final static String pathSep = System.getProperty("file.separator");
	
	/** The line separator for the OS java is running on */
	public final static String lineSep = System.getProperty("line.separator");
	
	/** Pattern to match all urls */
	public static Pattern urlPattern = Pattern.compile("^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
	
	public static Bot bot;
	
	public static String PASS = "", NICK = "PodBot", SERVER = "irc.netchat.io", SERVER_PASSWORD = "";
	
	public static int TIME_TO_KICK = 11, MESSAGES_TO_KICK = 9, PORT = 6667;
	
	public static String[] ADMIN = new String[] {
		"gravypod"
	},
	CHANNELS = new String[] {
		"#podbot"
		
	};
	
	public static void main(String[] args) {
	
		
		File commandFile = new File("commands");
		
		if (!commandFile.exists())
			commandFile.mkdirs();
		
		bot = new Bot(commandFile);
	}
	
}
