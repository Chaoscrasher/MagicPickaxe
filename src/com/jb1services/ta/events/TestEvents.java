package com.jb1services.ta.events;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class TestEvents implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{
		Block block = event.getBlock();
		event.getPlayer().sendMessage("That block is of type " + block.getType());
	}

	public void onBlockBreak(BlockBreakEvent event)
	{
		Block block = event.getBlock();
		event.getPlayer().sendMessage("That block is of type " + block.getType());
	}
}
