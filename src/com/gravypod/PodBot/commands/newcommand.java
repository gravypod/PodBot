package com.gravypod.PodBot.commands;

import com.gravypod.PodBot.CommandClass;
import com.gravypod.PodBot.CommandParse;
import com.gravypod.PodBot.NewCommand;

public class newcommand extends CommandClass {
	
	public newcommand() {
		
		if (isUserOp(CommandParse.channel, CommandParse.sender)) {
			String fileName = CommandParse.args[1].trim();
			String message = CommandParse.message.replace(fileName, "").replace("." + CommandParse.command, "").trim();
			NewCommand.NewCommands(CommandParse.channel, CommandParse.sender, fileName, message);
		}
		
	}
	
}
