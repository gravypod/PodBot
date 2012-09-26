package com.gravypod.PodBot;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;

/**
 * 
 * Bot startup class.
 * 
 * @author gravypod
 * 
 */
public class BotStartup extends PircBotX {

	protected List<String> channelList;
	protected CommandParse commandParse;
	public static BotStartup botInstance;
	String hostName;
	int port = 6667;
	String pass;
	String[] channels;

	public BotStartup() {

		hostName = PropLoader.getServer();

		port = Integer.parseInt(PropLoader.getPort());

		pass = PropLoader.getPassword();

		channelList = new ArrayList<String>();

		channels = PropLoader.getChannel().split(",");

		commandParse = new CommandParse();

		botInstance = this;
		
		this.getListenerManager().addListener(new Listeners(this));
		
		this.setName(PropLoader.getNick());

		try {
			this.connect(hostName, port, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setVerbose(true);

		this.identify(PropLoader.getPassword());
		sendMessage("nickserv", "identify " + PropLoader.getNick() + " " + PropLoader.getPassword());

		try {
			Thread.sleep(10L);
		} catch (InterruptedException e) {
		}

		for (String channel : channels) {

			channelList.add(channel);
			joinChannel(channel);

		}

	}

	public void onMessage(Channel channel, String sender, String hostname, String message) {

		commandParse.commandFind(channel, sender, hostname, message);

		PodBot.logger.Log(sender, channel.getName(), message);

	}


}
