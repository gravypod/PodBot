package com.gravypod.PodBot.commands;

import com.gravypod.PodBot.BotStartup;
import com.gravypod.PodBot.CommandClass;
import com.gravypod.PodBot.CommandParse;
import com.gravypod.PodBot.PodBot;
import com.gravypod.PodBot.TimedTask;

public class timer extends CommandClass {
	
	public timer() {
		
		TimedTask task = new TimedTask(CommandParse.channel, CommandParse.sender.getNick(), arrayToString(CommandParse.args, " "));
		PodBot.timer.schedule(task, 180000L);
		BotStartup.botInstance.sendMessage(CommandParse.channel, CommandParse.sender + ": you reminder has been set!");
		
	}
	
}
