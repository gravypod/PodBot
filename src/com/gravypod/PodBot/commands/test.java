package com.gravypod.PodBot.commands;

import com.gravypod.PodBot.CommandClass;
import com.gravypod.PodBot.CommandParse;

public class test extends CommandClass {

	String channel;
	String sender;
	String login;
	String hostname;
	String message;
	String command;
	String[] args;
	
	public test() {
		
		channel = CommandParse.channel;
		sender = CommandParse.sender;
		login = CommandParse.login;
		hostname = CommandParse.hostname;
		message = CommandParse.message;
		command = CommandParse.command;
		args = CommandParse.args;
		
		Command(channel, sender, login, hostname, message);
		
	}
	
	private void Command(String channel, String sender, String login, String hostname, String message) {
		
		sendResponce(args, command, channel, "Just ping me mo fo!");
		
	}
	
	

}
