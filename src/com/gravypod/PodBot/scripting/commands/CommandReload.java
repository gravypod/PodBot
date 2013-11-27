package com.gravypod.PodBot.scripting.commands;

import com.gravypod.PodBot.PodBot;
import com.gravypod.PodBot.scripting.Command;


public class CommandReload implements Command {
	@Override
	public String execute(String[] message) {
		PodBot.bot.loadCommands();
	    return "Commands reloaded";
	}
}
