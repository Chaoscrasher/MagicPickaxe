package com.chaoscrasher.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.chaoscrasher.commands.DiagCommand;
import com.chaoscrasher.commands.TPACommand;
import com.chaoscrasher.events.BlockEvents;

public class ChaosMCUtilPlugin extends JavaPlugin {

	public static ChaosMCUtilPlugin instance;

	@Override
	public void onEnable()
	{
		instance = this;
		System.out.println("ChaosMCUtil loaded!");
		new TPACommand();
		new DiagCommand();
		Bukkit.getPluginManager().registerEvents(new BlockEvents(), this);
		this.saveConfig();
	}

	@Override
	public void onDisable()
	{

	}
}
