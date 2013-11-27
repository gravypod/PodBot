package com.gravypod.PodBot.scripting;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import sleep.bridges.io.BufferObject;
import sleep.bridges.io.IOObject;
import sleep.interfaces.Variable;
import sleep.runtime.ScriptInstance;
import sleep.runtime.SleepUtils;

public class CommandScript implements Command {
	
	private final ScriptInstance instance;
	
	private final BufferObject buffer = new BufferObject(); // Create a buffer to hold the print/printf info
	
	public CommandScript(ScriptInstance instance) {
	
		this.instance = instance;
		buffer.allocate(2048);
		IOObject.setConsole(instance.getScriptEnvironment(), buffer);
	}
	@Override
	public String execute(String[] message) {
	
		final BufferObject buffer = new BufferObject(); // Create a buffer to hold the print/printf info
		
		buffer.allocate(2048); // init the size at 2048 
		
		IOObject.setConsole(instance.getScriptEnvironment(), buffer); // set the console
		final Variable globals = instance.getScriptVariables().getGlobalVariables();
		globals.putScalar("@__ARGS__", SleepUtils.getArrayWrapper(Arrays.asList(message)));
		instance.runScript();
		String text = new String(((ByteArrayOutputStream) buffer.getSource()).toByteArray());
		text = text.replaceAll("\\r\\n|\\r|\\n", " ");
		return text;
		
	}
	
}
