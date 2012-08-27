package com.gravypod.PodBot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class PropLoader {
	
	private static String nick;
	private static String channel;
	private static String server;
	private static String logfile;
	private static String owner;
	private static String password;
	private static String LogExtention;
	private static String Admins;
	private static String port;
	private static boolean antiFlood;
	
	public static void PropCheck() {
		File config = new File(PodBot.rootLocation() + "config.ini");
		if (!config.exists()) {
			System.out.println("Missing config.ini Making one now");
			FileWriter outFile;
			try {
				outFile = new FileWriter(config);
				PrintWriter out = new PrintWriter(outFile);
				out.println("port=6667");
				out.println("Server=irc.esper.net");
				out.println("NickName=PodBot2");
				out.println("Channels=#PersonalWorlds");
				out.println("LogFile=chatlog");
				out.println("LogExtentions=log");
				out.println("Owner=");
				out.println("Password=");
				out.println("Admins=");
				out.println("Antiflood=true");

				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void PropLoad() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config.ini"));
			setPort(prop.getProperty("port"));
			setNick(prop.getProperty("NickName"));
			setChannel(prop.getProperty("Channels"));
			setServer(prop.getProperty("Server"));
			setLogfile(prop.getProperty("LogFile"));
			setOwners(prop.getProperty("Owner"));
			setPassword(prop.getProperty("Password"));
			setLogExtention(prop.getProperty("LogExtentions"));
			setAdmins(prop.getProperty("Admins"));
			setAntiFlood(Boolean.parseBoolean(prop.getProperty("Antiflood")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getLogExtention() {
		return LogExtention;
	}

	public static void setLogExtention(String string) {
		LogExtention = string;
	}

	public static String getOwner() {
		return owner;
	}

	public static void setOwners(String string) {
		owner = string;
	}

	public static String getNick() {
		return nick;
	}

	public static void setNick(String string) {
		nick = string;
	}

	public static String getChannel() {
		return channel;
	}

	public static void setChannel(String string) {
		channel = string;
	}

	public static String getServer() {
		return server;
	}

	public static void setServer(String string) {
		server = string;
	}

	public static String getLogfile() {
		return logfile;
	}

	public static void setLogfile(String string) {
		logfile = string;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String string) {
		password = string;
	}
	
	public static String[] getAdmins() {
		String[] AdminArray = Admins.split(",");
		return AdminArray;
	}

	public static void setAdmins(String admins) {
		Admins = admins;
	}

	public static String getPort() {
		return port;
	}

	public static void setPort(String port) {
		PropLoader.port = port;
	}

	public static boolean getAntiFlood() {
		return antiFlood;
	}

	public static void setAntiFlood(boolean antiFlood) {
		PropLoader.antiFlood = antiFlood;
	}
}
