package com.chaoscrasher.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class DiagCommand extends ChaosCommandExecutor {

	@Override
	public boolean onCommand()
	{
		if (sender.isOp())
		{
			if (args.length > 1)
			{
				if (args.length == 2)
				{
					if (fst.equalsIgnoreCase("world-exists"))
					{
						World world = Bukkit.getWorld(snd);
						sendGreen("World " + snd + (world != null ? " exists!" : " doesn't exist!"));
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public String getCommandShorthand()
	{
		return "diag";
	}
}