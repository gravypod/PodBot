package com.gravypod.PodBot;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;

import org.jibble.pircbot.PircBot;

import sleep.error.YourCodeSucksException;
import sleep.runtime.ScriptInstance;
import sleep.runtime.ScriptLoader;

import com.gravypod.PodBot.links.LinkReader;
import com.gravypod.PodBot.scripting.Command;
import com.gravypod.PodBot.scripting.CommandScript;
import com.gravypod.PodBot.scripting.commands.CommandJoin;
import com.gravypod.PodBot.scripting.commands.CommandReload;

/**
 * 
 * Bot startup class.
 * 
 * @author gravypod
 * 
 */
public class Bot extends PircBot {
	
	private final String[] channels;
	
	private final File commandDir;
	
	private final AtomicReference<HashMap<String, Command>> commands = new AtomicReference<HashMap<String, Command>>(new HashMap<String, Command>());
	
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
	
		commands.get().clear();
		commands.get().put("reload", new CommandReload());
		commands.get().put("join", new CommandJoin());
	}
	
	@Override
	protected void onMessage(final String channel, final String sender, final String login, final String hostname, final String message) {
	
		pool.execute(new Runnable() {
			
			@Override
			public void run() {
			
				if (message.startsWith(PodBot.COMMAND_INITIATOR)) {
					
					String[] command = message.substring(1).split(" ");
					
					if (!commands.get().containsKey(command[0])) {
						
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
							commands.get().put(command[0], script);
							String responce = script.execute(Arrays.copyOfRange(command, 1, message.length()));
							if (responce != null && responce.length() > 0) {
								PodBot.bot.sendMessage(channel, responce);
							}
						}
						
					} else {
						
						Command script = commands.get().get(command[0]);
						
						String responce = script.execute(Arrays.copyOfRange(command, 1, message.length()));
						if (responce != null && responce.length() > 0) {
							PodBot.bot.sendMessage(channel, responce);
						}
					}
					
				} else {
					Matcher match = PodBot.urlPattern.matcher(message);
					
					if (match.matches()) {
						
						String group = match.group();
						
						String title = LinkReader.webRead(group);
						PodBot.bot.sendMessage(channel, group + ": " + title);
					}
					
				}
			}
		});
	}
	
}
