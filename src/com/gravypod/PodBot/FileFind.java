package com.gravypod.PodBot;

import java.io.File;

public class FileFind extends CommandClass {
	
	public static boolean doesFileExist(String fileName) {
		
		return new File("commands" + PodBot.pathSep + fileName + ".txt").exists();
		
	}
	
	public static String FindContent(String fileName, String sender, String channel) {
		
		return FileReader.convertFileToString(new File("commands" + PodBot.pathSep + fileName + ".txt"));
		
	}
	
	public static String[] ArrayFileContent(String fileName, String sender, String channel) {
		
		return FindContent(fileName, sender, channel).split(System.getProperty("line.separator"));
		
	}
	
}
