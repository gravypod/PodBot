package com.gravypod.PodBot.scripting.commands;

import com.gravypod.PodBot.PodBot;
import com.gravypod.PodBot.scripting.Command;


public class CommandJoin implements Command {
	@Override
	public String execute(String[] message) {
		if (message.length < 1) {
			if (message.length == 2) {
				PodBot.bot.joinChannel(message[0], message[1]);
				return "Joining";
			} else {
				PodBot.bot.joinChannel(message[0]);
				return "Joining";
			}
		}
	    return "Error, wrong usage. \".join (channel) (password [optional])\"";
	}
}
