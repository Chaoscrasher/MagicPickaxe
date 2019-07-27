package com.jb1services.ta.events;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.jb1services.ta.items.MagicPickaxeLore;

public class SpecialPickaxe implements Listener {

	public void onBlockBreak(BlockBreakEvent event)
	{
		Player p = event.getPlayer();
		Block block = event.getBlock();
		Optional<MagicPickaxeLore> mplo = MagicPickaxeLore.isValidMagicPickaxeLore(p.getInventory().getItemInMainHand().getItemMeta().getLore());
		if (mplo.isPresent())
		{
			MagicPickaxeLore mpl = mplo.get();
			for (int x = block.getX(); x < x + mpl.getxBreak(); x+=(mpl.getxBreak() >= 0 ? 1 : -1))
			{
				for (int y = block.getY(); y < y + mpl.getyBreak(); y+=(mpl.getyBreak() >= 0 ? 1 : -1))
				{
					for (int z = block.getZ(); z < z + mpl.getzBreak(); z+=(mpl.getzBreak() >= 0 ? 1 : -1))
					{
						Location loc = new Location(block.getWorld(), x, y, z);
						loc.getBlock().breakNaturally(p.getInventory().getItemInMainHand());
					}
				}
			}
		}
	}
}
