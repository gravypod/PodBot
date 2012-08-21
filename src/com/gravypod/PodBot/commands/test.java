package com.gravypod.PodBot.commands;

import com.gravypod.PodBot.BotStartup;
import com.gravypod.PodBot.CommandParse;

public class test {

	String channel, sender, login, hostname, message;
	BotStartup botInstance;
	String[] args;
	
	public test() {
		
		channel = CommandParse.channel;
		sender = CommandParse.sender;
		login = CommandParse.login;
		hostname = CommandParse.hostname;
		message = CommandParse.message;
		botInstance = BotStartup.botInstance;
		args = CommandParse.args;
		
		Command(channel, sender, login, hostname, message);
		
	}
	
	private void Command(String channel, String sender, String login, String hostname, String message) {
		
		botInstance.sendMessage(channel, sender + ": Just ping me mo fo!");
		
	}
	
}
