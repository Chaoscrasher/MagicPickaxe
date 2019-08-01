package com.jb1services.ta.commands;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.jb1services.ta.items.MagicPickaxeLore;

import net.md_5.bungee.api.ChatColor;

public class TestCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (sender.isOp())
		{
			Player p = sender instanceof Player ? (Player) sender : null;
			if (args.length == 1)
			{
				if (args[0].equals("clear-inv") && p != null)
				{
					p.getInventory().clear();
				}
			}
			else if (args.length == 2)
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
			else if (args.length == 3)
			{
				if (args[0].equalsIgnoreCase("drop"))
				{
					if (args[1].equals("magic-pickaxe"))
					{
						if (args[2].equals("test"))
						{
							if (sender instanceof Player)
							{
								((Player) sender).getInventory().addItem(makeTestMagicPickaxe());
							}
							else
								sender.sendMessage(ChatColor.RED + "Sorry, but only players can do this!");
							return true;
						}
						return true;
					}
				}
			}
			else if (args.length == 5)
			{
				if (args[0].equalsIgnoreCase("drop"))
				{
					if (args[1].equals("magic-pickaxe"))
					{
						try
						{
							if (sender instanceof Player)
							{
								((Player) sender).getInventory().addItem(makeMagicPickaxeByLore(new MagicPickaxeLore(true, true, true, true, Integer.valueOf(args[2]), Integer.valueOf(args[3]), Integer.valueOf(args[4]))));
							}
							else
								sender.sendMessage(ChatColor.RED + "Sorry, but only players can do this!");
						}
						catch (NumberFormatException e)
						{
							p.sendMessage(ChatColor.RED + "Sorry, but that's not an integer!");
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	public static ItemStack makeMagicPickaxeByLore(MagicPickaxeLore mpl)
	{
		Random rnd = new Random();
		int rnum = rnd.nextInt(3);
		ItemStack is = new ItemStack(rnum == 0 ? Material.WOODEN_PICKAXE : rnum == 1 ? Material.STONE_PICKAXE : rnum == 3 ? Material.IRON_PICKAXE : Material.DIAMOND_PICKAXE);
		ItemMeta im = is.getItemMeta();
		im.setLore(mpl.toLore());
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack makeRandomMagicPickaxe()
	{
		Random rnd = new Random();
		int rnum = rnd.nextInt(3);
		ItemStack is = new ItemStack(rnum == 0 ? Material.WOODEN_PICKAXE : rnum == 1 ? Material.STONE_PICKAXE : rnum == 3 ? Material.IRON_PICKAXE : Material.DIAMOND_PICKAXE);
		ItemMeta im = is.getItemMeta();
		im.setLore(new MagicPickaxeLore(true).toLore());
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack makeTestMagicPickaxe()
	{
		ItemStack is = new ItemStack(Material.STONE_PICKAXE);
		ItemMeta im = is.getItemMeta();
		im.setLore(new MagicPickaxeLore(true, true, true, false, 1, 2, 1).toLore());
		is.setItemMeta(im);
		return is;
	}
}
