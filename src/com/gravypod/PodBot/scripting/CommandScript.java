package com.gravypod.PodBot.scripting;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import sleep.bridges.io.BufferObject;
import sleep.bridges.io.IOObject;
import sleep.interfaces.Variable;
import sleep.runtime.ScriptInstance;
import sleep.runtime.SleepUtils;

public class CommandScript {
	
	private final ScriptInstance instance;
	
	private final BufferObject buffer = new BufferObject(); // Create a buffer to hold the print/printf info
	
	public CommandScript(ScriptInstance instance) {
	
		this.instance = instance;
		buffer.allocate(2048);
		IOObject.setConsole(instance.getScriptEnvironment(), buffer);
	}
	
	public String execute(String[] message) {
	
		final BufferObject buffer = new BufferObject(); // Create a buffer to hold the print/printf info
		
		buffer.allocate(2048); // init the size at 2048 
		
		IOObject.setConsole(instance.getScriptEnvironment(), buffer); // set the console
		final Variable globals = instance.getScriptVariables().getGlobalVariables();
		globals.putScalar("@__ARGS__", SleepUtils.getArrayWrapper(Arrays.asList(message)));
		instance.runScript();
		
		return new String(((ByteArrayOutputStream) buffer.getSource()).toByteArray());
		
	}
	
}
