package com.gravypod.PodBot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class Listeners extends ListenerAdapter {
	
	BotStartup botStartup;
	CommandParse commandParse;
	
	public Listeners(BotStartup _botStartup, CommandParse _commandParse) {
		
		botStartup = _botStartup;
		commandParse = _commandParse;
		
	}

	public void onMessage(MessageEvent event) throws Exception {
		
		commandParse.commandFind(event.getChannel(), event.getUser(), event.getUser().getHostmask(), event.getMessage());

		PodBot.logger.Log(event.getUser().getNick(), event.getChannel().getName(), event.getMessage());
		
	}
	
}
