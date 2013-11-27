package com.gravypod.PodBot.flood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.gravypod.PodBot.config.PropLoader;

/**
 * Copyright (C) 2011-2012 Martin Foucek
 * 
 * @author <a href="https://github.com/Audio/bashoid">bashoid</a> edited by
 *         gravypod
 */
public class FloodChecker {

	private static final long Time_In_Sec;
	private static final long Max_Messages_Per_Time;
	static HashMap<String, ArrayList<Long>> records;
	public static int timeToKick = Integer.parseInt(PropLoader.getTimeToKick());
	public static int maxMessagesPerTime = Integer.parseInt(PropLoader.getMessagesToKick());

	static {
		Time_In_Sec = timeToKick; // in seconds
		Max_Messages_Per_Time = timeToKick;
		records = new HashMap<String, ArrayList<Long>>();
	}

	public static long maxMessagesPerTime() {

		return Max_Messages_Per_Time;
	}

	public static long TimeInSec() {

		return Time_In_Sec;
	}

	/**
	 * Boolean if the sender can be served
	 * 
	 * @param hostname
	 * @return
	 */
	public static boolean canBeServed(String hostname) {

		if (!isInLog(hostname))
			return true;

		ArrayList<Long> times = records.get(hostname);
		removeOldRecords(times);
		records.put(hostname, times);
		return (times.size() < Max_Messages_Per_Time);
	}

	/**
	 * Boolean to see if the user is in the logs
	 * 
	 * @param hostname
	 * @return
	 */
	private static boolean isInLog(String hostname) {

		return records.containsKey(hostname);
	}

	/**
	 * Get rid of all old logs
	 * 
	 * @param times
	 */
	private static void removeOldRecords(ArrayList<Long> times) {

		Iterator<Long> it = times.listIterator();
		while (it.hasNext()) {
			if (isOld(it.next())) {
				it.remove();
			}
		}
	}

	/**
	 * Finds if a log is old.
	 * 
	 * @param time
	 * @return
	 */
	private static boolean isOld(Long time) {

		return (CurrentTime.inSeconds() - time > Time_In_Sec);
	}

	/**
	 * Logger for logging host names and messages sent.
	 * 
	 * @param hostname
	 */
	public static void logServed(String hostname) {

		ArrayList<Long> times = getTimesServed(hostname);
		times.add(CurrentTime.inSeconds());
		records.put(hostname, times);
	}

	/**
	 * Get the times a hostname was served
	 * 
	 * @param hostname
	 * @return
	 */
	private static ArrayList<Long> getTimesServed(String hostname) {

		ArrayList<Long> times = records.get(hostname);
		return (times == null) ? new ArrayList<Long>() : times;
	}
}
