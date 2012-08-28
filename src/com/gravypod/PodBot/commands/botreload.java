package com.gravypod.PodBot.commands;

import com.gravypod.PodBot.CommandClass;
import com.gravypod.PodBot.CommandParse;
import com.gravypod.PodBot.PodBot;

public class botreload extends CommandClass {
	
	public botreload() {
		
		if (!isUserOp(CommandParse.channel, CommandParse.sender))
			return;
		
		try {
			PodBot.reloadCommands();
		} catch (Exception e) {
			sendResponce(CommandParse.args, CommandParse.command, CommandParse.channel, "An exception was thrown. Please restart the bot.");
		} finally {
			sendResponce(CommandParse.args, CommandParse.command, CommandParse.channel, "Reload of commands is finished.");
		}
		
		
		
		
	}
	
	
}
