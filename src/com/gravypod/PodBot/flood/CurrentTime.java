package com.gravypod.PodBot.flood;

/**
 * 
 * @author gravypod
 * 
 */
public class CurrentTime {

	/**
	 * 
	 * Gets the current time in seconds.
	 * 
	 * @return
	 * 
	 */
	public static long inSeconds() {

		return System.currentTimeMillis() / 1000L;
	}

}
