package com.gravypod.PodBot;

import java.io.File;

public class FileFind {
	
	
	public static boolean FindFile(String fileName, String sender, String channel) {
		
		File file = new File(PodBot.jarLocation() + PodBot.pathSep + "commands" + PodBot.pathSep + fileName);
		
		if (!file.exists())
			return false;
		
		BotStartup.botInstance.sendMessage(channel, sender + ": " + FileReader.convertFileToString(file));
		
		return true;
		
	}
	
}
