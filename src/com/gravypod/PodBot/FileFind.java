package com.gravypod.PodBot;

import java.io.File;

/**
 * 
 * Finds command files.
 * 
 * @author gravypod
 * 
 */
public class FileFind extends CommandClass {

	/**
	 * 
	 * Check to see if a command file exists in the commands folder.
	 * 
	 * @param fileName
	 *            - command name that was run
	 * @return
	 * 
	 */
	public static boolean fileExist(String fileName) {

		return new File("commands" + PodBot.pathSep + fileName + ".txt").exists();

	}

	/**
	 * 
	 * Find the contents of a file. If it is multi-line use ArrayFileContent!
	 * 
	 * @param fileName
	 *            - command name that was run.
	 * @param sender
	 *            - Sender of the command.
	 * @param channel
	 *            - Channel the command was sent in.
	 * @return
	 * 
	 */
	public static String FindContent(String fileName, String sender, String channel) {

		return FileReader.convertFileToString(new File("commands" + PodBot.pathSep + fileName + ".txt"));

	}

	/**
	 * 
	 * Returns an array list of lines that were in a command file. It is split
	 * at what ever PodBot.lineSep is.
	 * 
	 * @param fileName
	 *            - command name that was run.
	 * @param sender
	 *            - Sender of the command.
	 * @param channel
	 *            - Channel the command was sent in.
	 * @return
	 * 
	 */
	public static String[] ArrayFileContent(String fileName, String sender, String channel) {

		return FindContent(fileName, sender, channel).split(PodBot.lineSep);

	}

}
