package com.chaoscrasher.commands;

import static net.md_5.bungee.api.ChatColor.GOLD;
import static net.md_5.bungee.api.ChatColor.GREEN;
import static net.md_5.bungee.api.ChatColor.RED;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public abstract class ChaosCommandExecutor implements CommandExecutor
{
	protected CommandSender sender;
	protected Player player;
	protected Command cmd;
	protected String label;
    protected String[] args;
    protected String fst;
    protected String snd;
    protected String trd;
    protected String fot;
    protected String fit;
    protected String sxt;
    protected String svt;

    public ChaosCommandExecutor()
    {
    	super();
    	System.out.println(this.getCommandShorthand());
    	this.register();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		this.sender = sender;
		if (sender instanceof Player)
		{
			this.player = (Player) sender;
		}
		this.cmd = cmd;
		this.label = label;
		this.args = args;
		fillShorthands();
		boolean toRet = onCommand();
		reset();
		return toRet;
	}

	public abstract boolean onCommand();

	public abstract String getCommandShorthand();

	public void register()
	{
		Bukkit.getPluginCommand(getCommandShorthand()).setExecutor(this);
	}

	private void fillShorthands()
	{
		if (args.length > 0)
		{
			fst = args[0];
		}
		if (args.length > 1)
		{
			snd = args[1];
		}
		if (args.length > 2)
		{
			trd = args[2];
		}
		if (args.length > 3)
		{
			fot = args[3];
		}
		if (args.length > 4)
		{
			fit = args[4];
		}
		if (args.length > 5)
		{
			sxt = args[5];
		}
		if (args.length > 6)
		{
			svt = args[6];
		}
	}

    public static void sendColor(CommandSender commandSender, String message, ChatColor chatColor)
    {
        commandSender.sendMessage(chatColor + message);
    }

    public static void sendRed(CommandSender commandSender, String message)
    {
        sendColor(commandSender, message, RED);
    }

    public static void sendGreen(CommandSender commandSender, String message)
    {
        sendColor(commandSender, message, GREEN);
    }

    public static void sendGold(CommandSender commandSender, String message)
    {
        sendColor(commandSender, message, GOLD);
    }

    protected void sendColor(String message, ChatColor chatColor)
    {
    	if (sender != null)
    	{
    		sender.sendMessage(chatColor + message);
    	}
    	else
    		throw new IllegalStateException("No commandSender existing!");
    }

    protected void sendRed(String message)
    {
        sendColor(message, RED);
    }

    protected void sendGreen(String message)
    {
        sendColor(message, GREEN);
    }

    protected void sendGold(String message)
    {
        sendColor(message, GOLD);
    }

    public static int[] getRangeFromString(String string)
    {
        String[] splits = string.split("-");
        if (splits != null && splits.length == 2)
        {
            try
            {
                return new int[] {Integer.valueOf(splits[0]), Integer.valueOf(splits[1])};
            }
            catch (NumberFormatException e) {}
        }
        return null;
    }

    public Location parseLocation(int start, int end)
    {
    	if (end - start == 3)
    	{
    		if (args.length >= start && args.length >= end-1)
    		{
    			if (player != null)
    			{
					return parseSameWorldLocation_unsave(start);
    			}
    			else
    				throw new IllegalArgumentException("Your arguments of size " + args.length + " is not big enough for command starting at " + start + "(incl.) and ending at " + end + "(excl)");
    		} else
    			throw new IllegalArgumentException("Your arguments of size " + args.length + " is not big enough for command starting at " + start + "(incl.) and ending at " + end + "(excl)");
    	} else if (end - start == 4)
    	{
    		if (args.length >= start && args.length >= end-1)
    		{
    			return parseWorldLocation_unsave(start);
    		} else
    		{
    			throw new IllegalArgumentException("Your arguments of size " + args.length + " is not big enough for command starting at " + start + "(incl.) and ending at " + end + "(excl)");
    		}
    	}
    	throw new IllegalArgumentException("You need at least 3 arguments to create a location.");
    }

    protected boolean isPlayer()
    {
    	return player != null;
    }

    private void reset()
    {
    	this.sender = null;
    	this.player = null;
    	this.cmd = null;
    	this.label = null;
    	this.args = null;
    	this.fst = null;
    	this.snd = null;
    	this.trd = null;
    	this.fot = null;
    	this.fit = null;
    	this.sxt = null;
    	this.svt = null;
    }

    protected Location parseSameWorldLocation_unsave(int start)
    {
    	double x, y, z;
		x = Integer.valueOf(args[start]);
		y = Integer.valueOf(args[start+1]);
		z = Integer.valueOf(args[start+2]);
		return new Location(player.getLocation().getWorld(), x, y, z);
    }

    protected Location parseWorldLocation_unsave(int start)
    {
    	double x, y, z;
		x = Integer.valueOf(args[start]);
		y = Integer.valueOf(args[start+1]);
		z = Integer.valueOf(args[start+2]);
		World world = Bukkit.getWorld(args[start+3]);
		return new Location(world, x, y, z);
    }

    protected void teleport(Location loc)
    {
    	if (player != null)
    	{
    		player.teleport(loc);
    	}
    	else
    		throw new IllegalArgumentException("Sorry, but that's only available to Players!");
    }

    protected void teleportCommandSenderSameWorld(int start)
	{
		if (isPlayer())
		{
			double x, y, z;
			try
			{
				Location loc = parseSameWorldLocation_unsave(start);
				teleport(loc);
				confirmTeleport(loc);
			}
			catch (NumberFormatException e)
			{
				sendRed("That's not a valid number!");
			}
		}
		else
			sendRed("Can't teleport you if you're not a player, dummy!");
	}

    protected void teleportCommandSenderAllWorldsOrTargetPlayerSameWorld(int start)
    {
    	try
		{
			Location loc = parseWorldLocation_unsave(start);
			teleport(loc);
			confirmTeleport(loc);
		}
		catch (NumberFormatException e)
		{
			Player player = Bukkit.getPlayer(fst);
			if (player != null)
			{
				try
				{
					Location loc = parseWorldLocation_unsave(start+1);
					player.teleport(loc);
					confirmTeleport(player, loc);
				}
				catch (NumberFormatException e2)
				{
					sendRed("That's not a valid number!");
				}
			}
			else
			{
				sendRed("'" + fst + "' is not an online player!");
			}
		}
    }

    protected void teleportTargetPlayerTargetWorld(int start)
	{
    	Player player = Bukkit.getPlayer(args[start]);
    	if (player != null)
    	{
			double x, y, z;
			try
			{
				Location loc = parseWorldLocation_unsave(start+1);
				teleport(loc);
				confirmTeleport(player, loc);
			}
			catch (NumberFormatException e)
			{
				sendRed("That's not a valid number!");
			}
    	}
    	else
    		sendRed("'" + args[start] + "' is not a valid player!");
	}

    protected void confirmTeleport(Location loc)
	{
		sendGreen("Teleported you to " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + (!player.getLocation().getWorld().equals(loc.getWorld()) ? (" " + loc.getWorld().getName()) : ""));
	}

	protected void confirmTeleport(Player tpTarget, Location loc)
	{
		sendGreen("Teleported "+tpTarget.getName()+" to " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + (!tpTarget.getLocation().getWorld().equals(loc.getWorld()) ? (" " + loc.getWorld().getName()) : ""));
	}
}
