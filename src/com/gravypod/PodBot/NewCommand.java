package com.gravypod.PodBot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * Utilities for making new commands.
 * 
 * @author gravypod
 * 
 */
public class NewCommand {

	/** Instance of the Pirc bot */
	private static BotStartup bot = BotStartup.botInstance;

	/**
	 * Makes a new player set command
	 * 
	 * @param channel
	 * @param sender
	 * @param message
	 */
	public static void NewCommands(String channel, String sender, String fileName, String message) {

		try {
			String file = fileName.trim().toLowerCase();
			String text = message;

			File Command = new File("commands" + PodBot.pathSep + file + ".txt");
			FileWriter outFile = new FileWriter(Command, true);
			PrintWriter out = new PrintWriter(outFile);
			out.println(text);
			out.close();
			bot.sendMessage(channel, sender + ": Your command is done!");
		} catch (IOException e) {
			e.printStackTrace();
			bot.sendMessage(channel, sender + ": You Broke me!");
		}
	}

	/**
	 * Makes a copy of a player set command
	 * 
	 * @param channel
	 * @param sender
	 * @param message
	 */
	public static void AltCommand(String channel, String sender, String[] args) {

		try {
			String file = args[1];
			String text = args[2];
			File f1 = new File("commands" + PodBot.pathSep + file + ".txt");
			File f2 = new File("commands" + PodBot.pathSep + text + ".txt");
			FileWriter outFile = new FileWriter(f2, true);
			PrintWriter out = new PrintWriter(outFile);
			out.println(FileReader.convertFileToString(f1));
			out.close();
			bot.sendMessage(channel, sender + ": Your command has been dupped!");
		} catch (IOException e) {
			bot.sendMessage(channel, sender + ": You Broke me!");
		}
	}
}