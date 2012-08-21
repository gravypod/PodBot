package com.gravypod.PodBot;


public class CommandParse {
	
	public static String channel, sender, login, hostname, message;

	public static String[] args;
	
	private String[] prefixes = {".", "!", "|"};
	
	public void commandFind(String channel, String sender, String login, String hostname, String message) {
		
		String command = null;
		
		boolean isCommand = false;
		
		for (String prefix : prefixes) {
			
			if (message.startsWith(prefix)) {
				
				isCommand = true;
			
				command = message.replace(prefix, "").trim();
			
				continue;
				
			}
			
		}
		
		if (!isCommand)
			return;
		
		String argument = command.split(" ")[0].toLowerCase();
		
		String classPath = "com.gravypod.PodBot.commands.";
		
		if (PodBot.getCommands().contains(classPath + argument + ".class")) {
			
			try {
				
				CommandParse.channel = channel; 
				
				CommandParse.sender = sender; 
				
				CommandParse.login = login; 
				
				CommandParse.hostname = hostname; 
				
				CommandParse.message = message;
				
				CommandParse.args = command.replace(argument, "").split(" ", 2);
				
				Class.forName(classPath + argument).newInstance();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if (!FileFind.FindFile(message.split(" ")[0], sender, channel)) {
			BotStartup.botInstance.sendNotice(sender, "That is not a valid command");
		}
		
	}
	
}
