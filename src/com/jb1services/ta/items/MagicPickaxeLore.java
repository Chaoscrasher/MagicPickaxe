package com.jb1services.ta.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MagicPickaxeLore
{
	private boolean breaksDirt;
	private boolean breaksWood;
	private boolean breaksSand;
	private boolean breaksStone;

	private Integer xBreak;
	private Integer yBreak;
	private Integer zBreak;

	public MagicPickaxeLore(List<String> lore)
	{
		if (lore != null && lore.size() == 2)
		{
			String la = lore.get(0);
			String vals = la.substring(la.indexOf("[")+1, la.length()-1);
			String[] svals = vals.split(", ");
			for (String sval : svals)
			{
				if (sval.contains("dirt"))
				{
					breaksDirt = true;
				}
				if (sval.contains("wood"))
				{
					breaksWood = true;
				}
				if (sval.contains("sand"))
				{
					breaksSand = true;
				}
				if (sval.contains("stone"))
				{
					breaksStone = true;
				}
			}

			String lb = lore.get(1);
			this.xBreak = Integer.valueOf(lb.substring(lb.indexOf("x=")+2, lb.indexOf(" y")));
			this.yBreak = Integer.valueOf(lb.substring(lb.indexOf("y=")+2, lb.indexOf(" z")));
			this.xBreak = Integer.valueOf(lb.substring(lb.indexOf("z=")+2));
		}
		else
			throw new IllegalArgumentException("Lore has to be != null and of size 2!");
	}

	public boolean isBreaksDirt()
	{
		return breaksDirt;
	}

	public boolean isBreaksWood()
	{
		return breaksWood;
	}

	public boolean isBreaksSand()
	{
		return breaksSand;
	}

	public boolean isBreaksStone()
	{
		return breaksStone;
	}

	public Integer getxBreak()
	{
		return xBreak;
	}

	public Integer getyBreak()
	{
		return yBreak;
	}

	public Integer getzBreak()
	{
		return zBreak;
	}

	public static Optional<MagicPickaxeLore> isValidMagicPickaxeLore(List<String> lore)
	{
		try
		{
			return Optional.of(new MagicPickaxeLore(lore));
		}
		catch (Exception e)
		{
			return Optional.empty();
		}
	}

	public MagicPickaxeLore(boolean allRandom)
	{
		Random rnd = new Random();
		this.breaksDirt = Math.random() > 0.5;
		this.breaksWood = Math.random() > 0.5;
		this.breaksStone = Math.random() > 0.5;
		this.breaksSand = Math.random() > 0.5;

		this.xBreak = rnd.nextInt(10) * (rnd.nextBoolean() ? -1 : 1);
		this.yBreak = rnd.nextInt(10) * (rnd.nextBoolean() ? -1 : 1);
		this.zBreak = rnd.nextInt(10) * (rnd.nextBoolean() ? -1 : 1);
	}

	public MagicPickaxeLore(boolean breaksStone, boolean breaksWood, boolean breaksDirt, boolean breaksSand, int xBreak,
			int yBreak, int zBreak)
	{
		super();
		this.breaksStone = breaksStone;
		this.breaksWood = breaksWood;
		this.breaksDirt = breaksDirt;
		this.breaksSand = breaksSand;
		this.xBreak = xBreak;
		this.yBreak = yBreak;
		this.zBreak = zBreak;
	}

	public List<String> toLore()
	{
		if (breaksDirt || breaksWood || breaksSand || breaksStone)
		{
			String lorea = "breaks: [" + (breaksDirt ? "dirt, " : "") +
										 (breaksWood ? "wood, " : "") +
										 (breaksSand ? "sand, " : "") +
										 (breaksStone ? "stone" : "") + "]";
			String loreb = "x=" + xBreak + " y=" + yBreak + " z=" + zBreak;
			return Arrays.asList(lorea, loreb);
		}
		return new ArrayList<>();
	}
}
