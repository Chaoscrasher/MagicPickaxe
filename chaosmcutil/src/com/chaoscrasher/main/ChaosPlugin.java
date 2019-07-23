package com.chaoscrasher.main;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class ChaosPlugin extends JavaPlugin {

	@Override
	public void onEnable()
	{
		this.onChaosEnable();
		this.saveConfig();
		runAfterLoadComplete();
	}

	@Override
	public void onDisable()
	{

	}

	public abstract void onChaosEnable();

	public abstract void onChaosDisable();

	/**
	 * Only load external data from within this method.
	 */
	public abstract void loadData();

	private final void runAfterLoadComplete()
	{
		getServer().getScheduler().scheduleSyncDelayedTask(this, this::loadData);
	}
}
