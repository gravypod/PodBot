package com.gravypod.PodBot;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jibble.pircbot.PircBot;

import sleep.error.YourCodeSucksException;
import sleep.runtime.ScriptInstance;
import sleep.runtime.ScriptLoader;

import com.gravypod.PodBot.scripting.Command;
import com.gravypod.PodBot.scripting.CommandScript;
import com.gravypod.PodBot.scripting.commands.CommandReload;

/**
 * 
 * Bot startup class.
 * 
 * @author gravypod
 * 
 */
public class Bot extends PircBot {
	
	private final String COMMAND_INITIATOR = ".";
	
	private final String[] channels;
	
	private final File commandDir;
	
	private final HashMap<String, Command> commands = new HashMap<String, Command>();
	
	private final ScriptLoader loader = new ScriptLoader();
	
	private final TimeUnit timeUnit = TimeUnit.MICROSECONDS;
	
	private final ArrayBlockingQueue<Runnable> threadQueue = new ArrayBlockingQueue<Runnable>(100);
	
	private final ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 100, 10, timeUnit, threadQueue);
	
	public Bot(File commandDir) {
		loadCommands();
		this.commandDir = commandDir;
		
		channels = PodBot.CHANNELS;
		
		this.setName(PodBot.NICK);
		
		try {
			this.connect(PodBot.SERVER, PodBot.PORT, PodBot.SERVER_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setVerbose(true);
		
		this.identify(PodBot.PASS);
		
		for (String channel : channels) {
			this.joinChannel(channel);
		}
		
	}
	
	public void loadCommands() {
		commands.clear();
		commands.put("reload", new CommandReload());
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
							instance.chdir(commandDir);
							CommandScript script = new CommandScript(instance);
							commands.put(command[0], script);
							String responce = script.execute(Arrays.copyOfRange(command, 1, message.length()));
							if (responce != null && responce.length() > 0) {
								PodBot.bot.sendMessage(channel, responce);
							}
						}
						
					} else {
						
						Command script = commands.get(command[0]);
						
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
