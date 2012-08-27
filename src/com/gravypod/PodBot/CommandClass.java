package com.gravypod.PodBot;

import org.jibble.pircbot.User;

public abstract class CommandClass {
	
	boolean notice = false;
	boolean ping = false;
	
	public String getPointer(String[] args, String commandName) {
		

		String pointedMessage = commandName;
		
		if (args.length > 0)
		for (String argument : args) {
			
			pointedMessage = argument;
			
			if (pointedMessage.endsWith("@@")) {
				
				notice = true;
				continue;
				
			} else if (pointedMessage.endsWith("@")) {
				
				ping = true;
				continue;
				
			}
			
			pointedMessage = commandName;
			
		}
		
		if (ping) {
			pointedMessage.replace("@", "");
		} 
		
		if (notice) {
			pointedMessage.replace("@@", "");
		} 

		return pointedMessage;
		
	}
	
	public void sendResponce(String[] args, String commandName, String channel, String message) {
		
		String pointer = getPointer(args, commandName);
		
		BotStartup bst = BotStartup.botInstance;
		
		if (notice) {
			
			bst.sendNotice(pointer, pointer + ": " + message);
			
		} else
			bst.sendMessage(channel, pointer + ": " + message);
		
	}
	
	public boolean isUserOp(String channel, String username) {
		
		for (User user : BotStartup.botInstance.getUsers(channel))
			if (user.getNick().equalsIgnoreCase(username) && user.isOp())
				return true;
		
		sendResponce(CommandParse.args, CommandParse.command, channel, "You do not have permission to use that command!");
		
		return false;
		
	}
	
	public boolean isUserVoice(String channel, String username) {

		for (User user : BotStartup.botInstance.getUsers(channel))
			if (user.getNick().equalsIgnoreCase(username) && user.hasVoice())
				return true;
		
		sendResponce(CommandParse.args, CommandParse.command, channel, "You do not have permission to use that command!");
		
		return false;
	}

	
}
