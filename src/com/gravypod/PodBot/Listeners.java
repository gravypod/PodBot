package com.gravypod.PodBot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;

public class Listeners extends ListenerAdapter {
	
	BotStartup botStartup;
	
	public Listeners(BotStartup _botStartup) {
		
		botStartup = _botStartup;
		
	}

	public void onMessage(MessageEvent event) throws Exception {
		botStartup.onMessage(event.getChannel(), event.getUser().getNick(), event.getUser().getHostmask(), event.getMessage());
	}
	
}
