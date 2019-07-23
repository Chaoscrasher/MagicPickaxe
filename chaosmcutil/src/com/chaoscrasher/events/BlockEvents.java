package com.chaoscrasher.events;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEvents implements Listener {


	public static Random rand = new Random();
	public static final String USE_PERMISSION = "fieldore.use";

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent event)
	{

	}
}
