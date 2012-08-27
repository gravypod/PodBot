package com.gravypod.PodBot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class NewCommand {

	private static BotStartup bot = BotStartup.botInstance;

	/**
	 * Makes a new player set command
	 * 
	 * @param channel
	 * @param sender
	 * @param message
	 */
	public static void NewCommands(String channel, String sender, String[] args) {
		try {
			String file = args[1].trim().toLowerCase();
			String text = args[2].trim();
			File Command = new File(PodBot.rootLocation() + "commands" + PodBot.pathSep + file + ".txt");
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
			File f1 = new File(PodBot.rootLocation() + "commands" + PodBot.pathSep + file + ".txt");
			File f2 = new File(PodBot.rootLocation() + "commands" + PodBot.pathSep + text + ".txt");
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