package com.jb1services.ta.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.jb1services.ta.commands.TestCommand;
import com.jb1services.ta.events.TestEvents;

public class TeachingExamplePlugin extends JavaPlugin {

	public static TeachingExamplePlugin instance;

	@Override
	public void onEnable()
	{
		instance = this;
		System.out.println("TeachingExample loaded!");
		Bukkit.getPluginCommand("tbc").setExecutor(new TestCommand());
		Bukkit.getPluginManager().registerEvents(new TestEvents(), this);
		this.saveConfig();
	}

	@Override
	public void onDisable()
	{

	}
}
