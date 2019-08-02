package com.jb1services.ta.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.jb1services.ta.items.MagicPickaxe;
import com.jb1services.ta.items.mpa.SpecialMagicPickaxe;
import com.jb1services.ta.main.TeachingExamplePlugin;

public class MagicPickaxeBreakEvent implements Listener {


	public static boolean debug1 = true;
	public static boolean debug2 = false;

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		Player p = event.getPlayer();
		Block block = event.getBlock();
		ItemMeta im = p.getInventory().getItemInMainHand().getItemMeta();
		if (im != null)
		{
			List<String> lore = im.getLore();
			if (debug2) p.sendMessage("lore: " + lore);
			Optional<SpecialMagicPickaxe> smplo = SpecialMagicPickaxe.isValidSpecialMagicPickaxeLore(lore);
			Optional<MagicPickaxe> mplo = MagicPickaxe.isValidMagicPickaxeLore(lore);
			MagicPickaxe mpl = smplo.isPresent() ? smplo.get() : mplo.isPresent() ? mplo.get() : null;
			if (mpl != null)
			{
				if (debug1) p.sendMessage("special magic pickaxe: " + (mpl instanceof SpecialMagicPickaxe));
				Runnable r = () ->
				{
					int xs = block.getX() + 0, ys = block.getY(), zs = block.getZ();
					if (debug2) p.sendMessage("Detected your magic pickaxe block break! Read lore: " + mpl.toLore() + "");
					List<ItemStack> items = new ArrayList<>();
					List<Material> notBroken = new ArrayList<>();
					int blocks = 0;
					for (int x = xs; x != xs + mpl.getxBreak(); x+=(mpl.getxBreak() >= 0 ? 1 : -1))
					{
						for (int y = ys; y != ys + mpl.getyBreak(); y+=(mpl.getyBreak() >= 0 ? 1 : -1))
						{
							for (int z = zs; z != zs + mpl.getzBreak(); z+=(mpl.getzBreak() >= 0 ? 1 : -1))
							{
								Location loc = new Location(block.getWorld(), x, y, z);
								if (mpl.getBreakableDefinition().test(loc.getBlock().getType()))
								{
									//p.getInventory().addItem(loc.getBlock().getDrops(p.getInventory().getItemInMainHand()).toArray(new ItemStack[] {}));
									//block.breakNaturally(p.getInventory().getItemInMainHand());
									//System.out.println("Breaking block " + x + " " + y + " " + z);
									items.addAll(loc.getBlock().getDrops(p.getInventory().getItemInMainHand()));
									if (!loc.getBlock().getType().equals(Material.AIR))
										blocks++;

									loc.getBlock().setType(Material.AIR);
								}
								else if (!notBroken.contains(loc.getBlock().getType()))
								{
									notBroken.add(loc.getBlock().getType());
								}
							}
						}
					}

					Map<ItemStack, Integer> map = new HashMap<>();
					for (ItemStack is : items)
					{
						if (map.containsKey(is))
						{
							map.put(is, map.get(is) + 1);
						}
						else
						{
							map.put(is, 1);
						}
					}

					if (debug1) p.sendMessage("Broke " + blocks + " blocks to get " + items.size() + " items ("+map.size()+" kv pairs)!");
					for (ItemStack is : map.keySet())
					{
						if (debug1) p.sendMessage(is + ": x" + map.get(is) + " " +
								((is.getItemMeta().getLore() == null || is.getItemMeta().getLore().isEmpty()) ? "normal" : "special!") + "\n");
					}

					if (debug1) p.sendMessage("Materials not broken: " + notBroken.stream().map(mat -> mat.toString()).collect(Collectors.joining(", ")));

					Location loc = block.getLocation();
					loc.setY(mpl.getyBreak() > 0 ? block.getY() : block.getY() + mpl.getyBreak());
					int cnt = 0;
					for (ItemStack mat : map.keySet())
					{
						mat.setAmount(map.get(mat)*mat.getAmount());
						loc.getWorld().dropItem(loc, mat);
						cnt++;
						/*
						if (loc.getBlockX() < block.getX() + mpl.getxBreak())
						{
							loc.add(mpl.getxBreak() >= 0 ? 1 : -1, 0, 0);
						}
						else if (loc.getBlockZ() < block.getZ() + mpl.getzBreak())
						{
							loc.setX(block.getX());
							loc.add(0, 0, (mpl.getzBreak() >= 0 ? 1 : -1));
						}*/
					}


					/*
					int cnt = 0;
					Location loc = block.getLocation();
					for (ItemStack is : items)
					{
						if (cnt % 64 == 0)
						{
							if (loc.getBlockX() < block.getX() + mpl.getxBreak())
							{
								loc.add(mpl.getxBreak() >= 0 ? 1 : -1, 0, 0);
							}
							else if (loc.getBlockZ() < block.getZ() + mpl.getzBreak())
							{
								loc.setX(block.getX());
								loc.add(0, 0, (mpl.getzBreak() >= 0 ? 1 : -1));
							}
						}
						loc.getWorld().dropItem(loc, is);
						cnt++;
					}
					*/
				};
				Bukkit.getScheduler().scheduleSyncDelayedTask(TeachingExamplePlugin.instance, r);
			}
		}
	}
}
