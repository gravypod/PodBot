package com.gravypod.PodBot;

import org.pircbotx.Channel;
import org.pircbotx.User;

/**
 * 
 * CommandParse class. Finds commands and starts classes that use them. Used in
 * most message handling.
 * 
 * @author gravypod
 * 
 */
public class CommandParse extends CommandClass {

	public static Channel channel;
	public static User sender;
	public static String hostname, message, command;

	public static String[] args;

	private String[] prefixes = { ".", "!", "|" };

	/**
	 * Find if a command exists in a message.
	 * 
	 * @param channel
	 * @param sender
	 * @param login
	 * @param hostname
	 * @param message 
	 */
	public void commandFind(Channel channel, User sender, String hostname, String message) {

		String command = null;

		boolean isCommand = false;

		for (String prefix : prefixes) {

			if (message.startsWith(prefix)) {

				isCommand = true;

				command = message.replace(prefix, "");

				continue;

			}

		}

		for (String s : message.split(" ")) {
			
			if (isURL(s)) {
				sendDirectResponce(channel, "That link is " + PodBot.linkReader.webRead(s));
			}
			
		}

		if (!isCommand)
			return;

		String cmd = command.split(" ")[0].toLowerCase().trim();

		String path = "com.gravypod.PodBot.commands." + cmd + ".class";

		CommandParse.channel = channel;

		CommandParse.sender = sender;

		CommandParse.hostname = hostname;

		CommandParse.message = message;

		CommandParse.command = cmd;

		CommandParse.args = command.replace(cmd, "").split(" ");

		if (PodBot.getCommands().contains(path)) {

			try {

				Class.forName("com.gravypod.PodBot.commands." + cmd).newInstance();
				return;

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (FileFind.fileExist(cmd)) {

			sendArrayResponce(args, command, channel, FileFind.ArrayFileContent(cmd, sender.getNick(), channel.getName()));

			return;

		}

		BotStartup.botInstance.sendNotice(sender, "That is not a valid command");
		
		
	}

}
