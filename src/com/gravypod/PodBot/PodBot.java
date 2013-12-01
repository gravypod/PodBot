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
	
	/** Pattern to match all urls */
	public static Pattern urlPattern = Pattern.compile("^(http?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
	
	public static Bot bot;
	
	public static String PASS = "";
	
	public static String NICK = "PodBot";
	
	public static String SERVER = "irc.netchat.io";
	
	public static String SERVER_PASSWORD = "";
	
	public static int PORT = 6667;
	
	public static String[] CHANNELS = new String[] { "#podbot" };
	
	public static int TIME_TO_KICK = 11;
	
	public static int MESSAGES_TO_KICK = 9;
	
	public static String COMMAND_INITIATOR = ".";
	
	public static String[] ADMIN = new String[] { "gravypod" };
	
	public static void main(String[] args) {
	
		File commandFile = new File("commands");
		
		if (!commandFile.exists())
			commandFile.mkdirs();
		
		bot = new Bot(commandFile);
	}
	
}
