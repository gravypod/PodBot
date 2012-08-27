package com.gravypod.PodBot;


public class CommandParse extends CommandClass {
	
	public static String channel, sender, login, hostname, message, command;

	public static String[] args;

	private String[] prefixes = {".", "!", "|"};
	
	public void commandFind(String channel, String sender, String login, String hostname, String message) {
		
		String command = null;
		
		boolean isCommand = false;
		
		for (String prefix : prefixes) {
			
			if (message.startsWith(prefix)) {
				
				isCommand = true;
			
				command = message.replace(prefix, "");
			
				continue;
				
			}
			
		}
		
		if (!isCommand)
			return;
		
		String cmd = command.split(" ")[0].toLowerCase().trim();
		String path = "com.gravypod.PodBot.commands." + cmd + ".class";
		
		CommandParse.channel = channel; 
		
		CommandParse.sender = sender; 
		
		CommandParse.login = login; 
		
		CommandParse.hostname = hostname; 
		
		CommandParse.message = message;
		
		CommandParse.command = cmd;
		
		CommandParse.args = command.replace(cmd, "").split(" ");
		
		if (PodBot.getCommands().contains(path)) {
			
			try {
				
				Class.forName("com.gravypod.PodBot.commands." + cmd).newInstance();
				return;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		if (FileFind.doesFileExist(cmd)) {
			
			for (String toSend : FileFind.ArrayFileContent(command, sender, channel)) {
			
				System.out.println(toSend);
				sendResponce(args, command, channel, toSend);
			}
			
			return;
			
		} 

		BotStartup.botInstance.sendNotice(sender, "That is not a valid command");
		
	}
	
}
