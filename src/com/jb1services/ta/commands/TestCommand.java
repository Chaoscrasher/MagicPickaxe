package com.jb1services.ta.commands;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.jb1services.ta.items.MagicPickaxeLore;

import net.md_5.bungee.api.ChatColor;

public class TestCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (sender.isOp())
		{
			if (args.length == 2)
			{
				if (args[0].equalsIgnoreCase("drop"))
				{
					if (args[1].equals("magic-pickaxe"))
					{
						if (sender instanceof Player)
						{
							((Player) sender).getInventory().addItem(makeRandomMagicPickaxe());
						}
						else
							sender.sendMessage(ChatColor.RED + "Sorry, but only players can do this!");
						return true;
					}
				}
			}
		}
		return false;
	}

	public static ItemStack makeRandomMagicPickaxe()
	{
		Random rnd = new Random();
		int rnum = rnd.nextInt(3);
		ItemStack is = new ItemStack(rnum == 0 ? Material.WOODEN_PICKAXE : rnum == 1 ? Material.STONE_PICKAXE : rnum == 3 ? Material.IRON_PICKAXE : Material.DIAMOND_PICKAXE);
		is.getItemMeta().setLore(new MagicPickaxeLore(true).toLore());
		return is;
	}
}
