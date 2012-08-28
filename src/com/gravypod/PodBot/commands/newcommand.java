package com.gravypod.PodBot.commands;

import com.gravypod.PodBot.CommandClass;
import com.gravypod.PodBot.CommandParse;
import com.gravypod.PodBot.NewCommand;

public class newcommand extends CommandClass {
	
	public newcommand() {
		
		if (isUserOp(CommandParse.channel, CommandParse.sender))
			NewCommand.NewCommands(CommandParse.channel, CommandParse.sender, CommandParse.args);
		
	}
	
}
