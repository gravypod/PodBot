package com.gravypod.PodBot;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

public class BotStartup extends PircBot {
	
	public static Hashtable<String, User[]> channelUserList;
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
		
		channelUserList = new Hashtable<String, User[]>();
		
		commandParse = new CommandParse();
		
		botInstance = this;
		
		this.setName(PropLoader.getNick());
		
		try {
			this.connect(hostName, port, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setVerbose(true);
		
		this.identify(PropLoader.getPassword());
		
		try {
			Thread.sleep(10L);
		} catch (InterruptedException e) {
		}
		
		for (String channel : channels) {
			
			channelList.add(channel);
			joinChannel(channel);
			
		}
		
		
	}
	
	@Override
	protected void onConnect() {
		
	}
	
	@Override
	protected void onDisconnect() {
		
	}
	
	@Override
	protected void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
		
	}
	
	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		
		if (sender.equals(PropLoader.getNick()))
			channelList.add(channel);
		
	}
	
	@Override
	protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
	
		if (channelList.contains(channel)) {
			
			channelList.remove(channel);
			
			for (int i = 0; i < 10; i++)
				this.joinChannel(channel);
			
		}
		
	}
	
	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		
		
		commandParse.commandFind(channel, sender, login, hostname, message);
		
		Logger.Log(sender, channel, message);
		
	}
	
	@Override
	protected void onPrivateMessage(String sender, String login, String hostname, String message) {
		
	}
	
	@Override
	protected void onUserList(String channel, User[] users) {
		
	}
	
}
