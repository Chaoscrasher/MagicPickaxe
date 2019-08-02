package com.jb1services.ta.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import com.jb1services.ta.commands.TestCommand;
import com.jb1services.ta.events.MagicPickaxeBreakEvent;
import com.jb1services.ta.events.TestEvents;

public class TeachingExamplePlugin extends JavaPlugin {

	public static TeachingExamplePlugin instance;
	
	@Override
	public void onEnable()
	{
		instance = this;
		System.out.println("TeachingExample loaded!");
		Bukkit.getPluginCommand("cmd").setExecutor(new TestCommand());
		Bukkit.getPluginManager().registerEvents(new TestEvents(), this);
		Bukkit.getPluginManager().registerEvents(new MagicPickaxeBreakEvent(), this);
		this.saveConfig();
	}

	@Override
	public void onDisable()
	{

	}
}
