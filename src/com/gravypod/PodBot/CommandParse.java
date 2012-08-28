package com.gravypod.PodBot;

/**
 * 
 * CommandParse class. Finds commands and starts classes that use them.
 * Used in most message handling.
 * 
 * @author gravypod
 *
 */
public class CommandParse extends CommandClass {
	
	public static String channel, sender, login, hostname, message, command;

	public static String[] args;

	private String[] prefixes = {".", "!", "|"};
	
	/**
	 * Find if a command exists in a message.
	 * 
	 * @param channel
	 * @param sender
	 * @param login
	 * @param hostname
	 * @param message
	 */
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
		
		
		for (String s : message.split(" ")) {
			if (isURL(s)) {
				sendDirectResponce(channel, "That link is " + LinkReader.webRead(s));
			}
		}
		
		if (!isCommand)
			return;
		
		
		String cmd = message.split(" ")[0].toLowerCase().trim();
		
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
		
		
		if (FileFind.fileExist(cmd)) {
			
			sendArrayResponce(args, command, channel, FileFind.ArrayFileContent(cmd, sender, channel));
			
			return;
			
		} 

		BotStartup.botInstance.sendNotice(sender, "That is not a valid command");
		
	}
	
}
