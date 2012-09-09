package com.gravypod.PodBot.commands;

import com.gravypod.PodBot.BotStartup;
import com.gravypod.PodBot.CommandClass;
import com.gravypod.PodBot.CommandParse;


public class join extends CommandClass {
	
	
	public join() {
		
		if (this.isUserOp(CommandParse.channel, CommandParse.sender))
			BotStartup.botInstance.joinChannel(CommandParse.args[1]);
		
	}
	
}
