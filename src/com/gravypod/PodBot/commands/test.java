package com.gravypod.PodBot.commands;

import com.gravypod.PodBot.CommandClass;
import com.gravypod.PodBot.CommandParse;

public class test extends CommandClass {

	public test() {

		Command(CommandParse.channel, CommandParse.sender, CommandParse.login, CommandParse.hostname, CommandParse.message);

	}

	private void Command(String channel, String sender, String login, String hostname, String message) {

		sendResponce(CommandParse.args, CommandParse.command, channel, "Just ping me mo fo!");

	}

}
