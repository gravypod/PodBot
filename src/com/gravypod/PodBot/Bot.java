package com.gravypod.PodBot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jibble.pircbot.PircBot;

import sleep.error.YourCodeSucksException;
import sleep.runtime.ScriptInstance;
import sleep.runtime.ScriptLoader;

import com.gravypod.PodBot.config.PropLoader;
import com.gravypod.PodBot.scripting.CommandScript;

/**
 * 
 * Bot startup class.
 * 
 * @author gravypod
 * 
 */
public class Bot extends PircBot {
	
	private final List<String> channelList;
	
	private final String hostName;
	
	private final String COMMAND_INITIATOR = ".";
	
	private final int port;
	
	private final String pass;
	
	private final String[] channels;
	
	private final File commandDir;
	
	private final HashMap<String, CommandScript> commands = new HashMap<String, CommandScript>();
	
	private final ScriptLoader loader = new ScriptLoader();
	
	private final TimeUnit timeUnit = TimeUnit.MICROSECONDS;
	
	private final ArrayBlockingQueue<Runnable> threadQueue = new ArrayBlockingQueue<Runnable>(100);
	
	private final ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 100, 10, timeUnit, threadQueue);
	
	public Bot(File commandDir) {
	
		this.commandDir = commandDir;
		hostName = PropLoader.getServer();
		
		port = Integer.parseInt(PropLoader.getPort());
		
		pass = PropLoader.getPassword();
		
		channelList = new ArrayList<String>();
		
		channels = PropLoader.getChannel().split(",");
		
		this.setName(PropLoader.getNick());
		
		try {
			this.connect(hostName, port, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setVerbose(true);
		
		this.identify(PropLoader.getPassword());
		
		for (String channel : channels) {
			
			channelList.add(channel);
			joinChannel(channel);
			
		}
		
	}
	
	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
	
		if (sender.equals(PropLoader.getNick()))
			channelList.add(channel);
		
	}
	
	@Override
	protected void onMessage(final String channel, final String sender, final String login, final String hostname, final String message) {
	
		if (message.startsWith(COMMAND_INITIATOR)) {
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
				
					String[] command = message.substring(1).split(" ");
					
					if (!commands.containsKey(command[0])) {
						
						File f = new File(commandDir, command[0] + ".sl");
						
						if (f.exists()) {
							ScriptInstance instance = null;
							try {
								instance = loader.loadScript(f);
							} catch (YourCodeSucksException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
							if (instance == null) {
								PodBot.bot.sendMessage(channel, "error loading script");
								return;
							}
							CommandScript script = new CommandScript(instance);
							commands.put(command[0], script);
							String responce = script.execute(Arrays.copyOfRange(command, 1, message.length()));
							if (responce != null && responce.length() > 0) {
								PodBot.bot.sendMessage(channel, responce);
							}
						}
						
					} else {
						
						CommandScript script = commands.get(command[0]);
						
						String responce = script.execute(Arrays.copyOfRange(command, 1, message.length()));
						if (responce != null && responce.length() > 0) {
							PodBot.bot.sendMessage(channel, responce);
						}
					}
				}
			});
			
		}
		
	}
	
}
