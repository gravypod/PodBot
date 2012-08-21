package com.gravypod.PodBot;

import org.jibble.pircbot.User;

public class Util {

	static BotStartup bot;
	
	public static void init(BotStartup _bot) {
		
		bot = _bot;
		
	}
	
	public static String matchUser(String channel, String user) {
		
		String person = user;
		
		for (User users : bot.getUsers(channel)) {
			
			if (users.getNick().startsWith(user)) {
				
				person = users.getNick();
				continue;
				
			}
			
		}
		
		return person;
		
	}
	
}
