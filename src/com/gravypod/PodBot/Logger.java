package com.gravypod.PodBot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Log file maker
 * 
 * @author gravypod
 * 
 */
public class Logger {

	private static Map<String, PrintWriter> printWriterStreams;

	public Logger(String channels) {

		printWriterStreams = new HashMap<String, PrintWriter>();

		for (String channel : channels.split(",")) {

			try {

				File logFile = new File(channel + "." + PropLoader.getLogfile() + "." + PropLoader.getLogExtention());

				if (!logFile.exists())
					logFile.createNewFile();

				printWriterStreams.put(channel, new PrintWriter(new FileWriter(logFile, true)));

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * 
	 * Log a message into a log file.
	 * 
	 * @param sender
	 * @param channel
	 * @param message
	 * 
	 */
	public void Log(String sender, String channel, String message) {

		PrintWriter out;

		try {

			if (!printWriterStreams.containsKey(channel)) {

				File logFile = new File(channel + "." + PropLoader.getLogfile() + "." + PropLoader.getLogExtention());

				if (!logFile.exists())
					logFile.createNewFile();
				out = new PrintWriter(new FileWriter(logFile, true));
				printWriterStreams.put(channel, out);

			} else {
				out = printWriterStreams.get(channel);
			}

			out.println(PodBot.date.toString() + " <" + sender + ">" + ": " + message);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
