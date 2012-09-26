package com.gravypod.PodBot;

import java.util.regex.Matcher;

import org.pircbotx.Channel;

/**
 * This is my main command class. Any classes than deal with Commands /should
 * extend this. The CommandClass is a util class for all commands. All messages
 * should be send from this class.
 * 
 * @author gravypod
 * 
 */
public abstract class CommandClass {

	boolean notice = false;
	boolean ping = false;

	/**
	 * 
	 * Gets a pointer (A user name or command name)
	 * 
	 * @param args
	 *            - Args that were used in this command.
	 * @param commandName
	 *            - name of command that was used
	 * @return
	 * 
	 */
	public String getPointer(String[] args, String commandName) {

		String pointedMessage = commandName;

		if (args.length > 0)
			for (String argument : args) {

				pointedMessage = argument;

				if (pointedMessage.endsWith("@@")) {

					notice = true;
					continue;

				} else if (pointedMessage.endsWith("@")) {

					ping = true;
					continue;

				}

				pointedMessage = commandName;

			}

		if (ping) {
			pointedMessage.replace("@", "");
		}

		if (notice) {
			pointedMessage.replace("@@", "");
		}

		return pointedMessage;

	}

	/**
	 * 
	 * Send a message to a pointer.
	 * 
	 * @param args
	 *            - The args that were used in this message
	 * @param commandName
	 *            - The command that was used in this message
	 * @param channel
	 *            - Channel were the message was sent in
	 * @param message
	 *            - The message you want to send.
	 * 
	 */
	public void sendResponce(String[] args, String commandName, Channel channel, String message) {

		String pointer = getPointer(args, commandName);

		if (!(pointer == null))
			if (!pointer.isEmpty() && pointer.contains("@"))
				pointer = pointer.replaceAll("@", "");

		if (notice) {
			
			BotStartup.botInstance.sendNotice(pointer, pointer + ": " + message);
			BotStartup.botInstance.sendNotice(CommandParse.sender, pointer + ": " + message);

		} else {

			BotStartup.botInstance.sendMessage(channel, pointer + ": " + message);

		}

		notice = false;
		ping = false;

	}

	public void sendDirectResponce(Channel channel, String message) {

		BotStartup.botInstance.sendMessage(channel, message);

	}

	/**
	 * 
	 * Send an array of messages to a pointer.
	 * 
	 * @param args
	 *            - The args that were used in this message
	 * @param commandName
	 *            - The command that was used in this message
	 * @param channel
	 *            - Channel were the message was sent in
	 * @param messages
	 *            - String[] of messages that you want to send
	 * 
	 */
	public void sendArrayResponce(String[] args, String commandName, Channel channel, String[] messages) {

		for (String message : messages) {
			sendResponce(args, commandName, channel, message);
		}

	}

	public boolean isURL(String s) {

		try {

			Matcher matcher = PodBot.urlPattern.matcher(s);
			return matcher.matches();

		} catch (RuntimeException e) {
			return false;
		}

	}

	/**
	 * 
	 * Checks to see if a user is opped.
	 * 
	 * @param channel
	 * @param username
	 * @return
	 * 
	 */
	public boolean isUserOp(Channel channel, String username) {
		
		
		if (channel.hasVoice(BotStartup.botInstance.getUser(username)))
			return true;

		sendResponce(CommandParse.args, CommandParse.command, channel, "You do not have permission to use that command!");

		return false;

	}

	/**
	 * 
	 * Checks to see if a user is voiced.
	 * 
	 * @param channel
	 * @param username
	 * @return
	 * 
	 */
	public boolean isUserVoice(Channel channel, String username) {

		if (channel.hasVoice(BotStartup.botInstance.getUser(username)))
			return true;

		sendResponce(CommandParse.args, CommandParse.command, channel, "You do not have permission to use that command!");

		return false;
	}
	
	public boolean isMD5(String s) {
		
		try {

			Matcher matcher = PodBot.md5.matcher(s);
			return matcher.matches();

		} catch (RuntimeException e) {
			return false;
		}
		
	}
	
	public static String arrayToString(String[] a, String separator) {
	    StringBuffer result = new StringBuffer();
	    if (a.length > 0) {
	        result.append(a[0]);
	        for (int i=1; i<a.length; i++) {
	            result.append(separator);
	            result.append(a[i]);
	        }
	    }
	    return result.toString();
	}

}
