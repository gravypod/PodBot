package com.gravypod.PodBot.commands;

import com.gravypod.PodBot.CommandClass;
import com.gravypod.PodBot.CommandParse;
import com.gravypod.PodBot.NewCommand;

public class copycommand extends CommandClass {

	public copycommand() {

		if (isUserOp(CommandParse.channel, CommandParse.sender))
			NewCommand.AltCommand(CommandParse.channel, CommandParse.sender, CommandParse.args);

	}

}
