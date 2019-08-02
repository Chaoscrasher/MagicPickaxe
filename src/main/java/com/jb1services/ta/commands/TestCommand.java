package com.jb1services.ta.commands;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.jb1services.ta.items.MagicPickaxe;
import com.jb1services.ta.items.mpa.SpecialMagicPickaxe;
import com.jb1services.ta.items.mpa.SpecialMagicPickaxeType;

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
				if (args[0].equalsIgnoreCase("make"))
				{
					if (args[1].equals("magic-pickaxe"))
					{
						if (sender instanceof Player)
						{
							p.sendMessage(ChatColor.GREEN + "Here's your truly random magic pickaxe!");
							((Player) sender).getInventory().addItem(SpecialMagicPickaxe.makeTrulyRandomSpecialMagicPickaxe());
						}
						else
							sender.sendMessage(ChatColor.RED + "Sorry, but only players can do this!");
						return true;
					}
				}
			}
			else if (args.length == 3)
			{
				if (args[0].equalsIgnoreCase("make"))
				{
					if (args[1].equals("magic-pickaxe"))
					{
						if (args[2].equals("test"))
						{
							if (sender instanceof Player)
							{
								((Player) sender).getInventory().addItem(MagicPickaxe.makeTestMagicPickaxe());
							}
							else
								sender.sendMessage(ChatColor.RED + "Sorry, but only players can do this!");
							return true;
						}
						else if (SpecialMagicPickaxeType.valueOf(args[2]) != null)
						{
							SpecialMagicPickaxeType smpt = SpecialMagicPickaxeType.valueOf(args[2]);
							p.getInventory().addItem(SpecialMagicPickaxe.makeSpecialMagicPickaxeByLore(new SpecialMagicPickaxe(smpt)));
						}
						else
							sender.sendMessage("Unknown subcommand for make magic-pickaxe '" + args[2] + "'!");
						return true;
					}
				}
			}
			else if (args.length == 5)
			{
				if (args[0].equalsIgnoreCase("make"))
				{
					if (args[1].equals("magic-pickaxe"))
					{
						try
						{
							if (sender instanceof Player)
							{
								((Player) sender).getInventory().addItem(makeMagicPickaxeByLore(new MagicPickaxe(Integer.valueOf(args[2]), Integer.valueOf(args[3]), Integer.valueOf(args[4]))));
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

	public static ItemStack makeMagicPickaxeByLore(MagicPickaxe mpl)
	{
		Random rnd = new Random();
		int rnum = rnd.nextInt(3);
		ItemStack is = new ItemStack(rnum == 0 ? Material.WOODEN_PICKAXE : rnum == 1 ? Material.STONE_PICKAXE : rnum == 3 ? Material.IRON_PICKAXE : Material.DIAMOND_PICKAXE);
		ItemMeta im = is.getItemMeta();
		im.setLore(mpl.toLore());
		is.setItemMeta(im);
		return is;
	}
}
