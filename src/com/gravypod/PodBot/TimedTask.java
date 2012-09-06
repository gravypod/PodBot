package com.gravypod.PodBot;

import java.util.TimerTask;

public class TimedTask extends TimerTask {

	BotStartup bot = BotStartup.botInstance;
	String channel, sender, message;

	/**
	 * 
	 * Creates a new timed message to be sent by the TimedTask
	 * 
	 * @param _channel
	 * @param _sender
	 * @param _message
	 * 
	 */
	public TimedTask(String _channel, String _sender, String _message) {

		channel = _channel;
		sender = _sender;
		message = _message;

	}

	@Override
	public void run() {

		bot.sendMessage(channel, sender + ": Your timer is done.");
		bot.sendMessage(channel, sender + ":" + message);

	}

}
