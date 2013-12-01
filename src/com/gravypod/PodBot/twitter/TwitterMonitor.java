package com.gravypod.PodBot.twitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.gravypod.PodBot.PodBot;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


public class TwitterMonitor {
	private final static Twitter twitter = TwitterFactory.getSingleton();
    private final static Timer time = new Timer();
    private final static List<Long> sendMessages = new ArrayList<Long>();
    
    public static void start(final String user, final String channel) {
    	time.schedule(new TimerTask() {
			
			@Override
			public void run() {
                try {
                	List<Status> statuses = twitter.getUserTimeline(user);
                	for (Status status : statuses) {
                		
                		if (sendMessages.size() >= 40) {
                			sendMessages.remove(0);
                		}
                		long id = status.getId();
                		if (!sendMessages.contains(id)) {
                			PodBot.bot.sendMessage(channel, "(Twitter) " + user + ": " + status.getText());
                		}
                	}
                } catch (TwitterException e) {
	                e.printStackTrace();
                }
			}
		}, 100l, 100l);
    }
    
    
}
