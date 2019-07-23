package com.chaoscrasher.commands;

public class TPACommand extends ChaosCommandExecutor {
	
	public boolean onCommand()
	{
		if (sender.isOp())
		{
			if (args.length > 3)
			{
				if (args.length == 3)
				{
					teleportCommandSenderSameWorld(0);
					return true;
				}
				else if (args.length == 4)
				{
					teleportCommandSenderAllWorldsOrTargetPlayerSameWorld(0);
					return true;
				}
				else if (args.length == 5)
				{
					teleportTargetPlayerTargetWorld(0);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String getCommandShorthand()
	{
		return "tpall";
	}
}