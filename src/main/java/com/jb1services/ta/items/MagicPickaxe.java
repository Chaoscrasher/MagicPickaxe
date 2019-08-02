package com.jb1services.ta.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

import org.bukkit.Material;

public class MagicPickaxe
{
	public static boolean debug1 = false;

	protected Predicate<Material> breakingPredicate = mat -> mat.toString().contains("STONE") ||
			mat.toString().contains("DIORITE") ||
			mat.toString().contains("ANDESITE") ||
			mat.toString().contains("BRICK") ||
			mat.toString().contains("GRANITE") ||
			mat.toString().contains("PRISMARINE") ||
			mat.toString().contains("ORE");

	protected Integer xBreak;
	protected Integer yBreak;
	protected Integer zBreak;

	protected MagicPickaxe()
	{

	}

	public MagicPickaxe(int xRand, int yRand, int zRand, boolean uselessBoolean)
	{
		Random rnd = new Random();
		this.xBreak = rnd.nextInt(xRand) * (rnd.nextBoolean() ? -1 : 1);
		this.yBreak = rnd.nextInt(yRand) * (rnd.nextBoolean() ? -1 : 1);
		this.zBreak = rnd.nextInt(zRand) * (rnd.nextBoolean() ? -1 : 1);
	}

	public MagicPickaxe(int allRandomLimit)
	{
		Random rnd = new Random();
		this.xBreak = rnd.nextInt(allRandomLimit) * (rnd.nextBoolean() ? -1 : 1);
		this.yBreak = rnd.nextInt(allRandomLimit) * (rnd.nextBoolean() ? -1 : 1);
		this.zBreak = rnd.nextInt(allRandomLimit) * (rnd.nextBoolean() ? -1 : 1);
	}

	public MagicPickaxe(int xBreak,
			int yBreak, int zBreak)
	{
		super();
		this.xBreak = xBreak;
		this.yBreak = yBreak;
		this.zBreak = zBreak;
	}

	public MagicPickaxe (List<String> lore)
	{
		if (lore != null && lore.size() >= 1)
		{
			setLoreData(lore);
		}
		else
			throw new IllegalArgumentException("Lore has to be != null and of size 1!");
	}

	protected void setLoreData(List<String> lore)
	{
		String lb = lore.get(0);
		this.xBreak = Integer.valueOf(lb.substring(lb.indexOf("x=")+2, lb.indexOf(" y")));
		this.yBreak = Integer.valueOf(lb.substring(lb.indexOf("y=")+2, lb.indexOf(" z")));
		this.zBreak = Integer.valueOf(lb.substring(lb.indexOf("z=")+2));
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

	public static Optional<MagicPickaxe> isValidMagicPickaxeLore(List<String> lore)
	{
		try
		{
			return Optional.of(new MagicPickaxe(lore));
		}
		catch (IllegalArgumentException e)
		{
			return Optional.empty();
		}
	}

	public Predicate<Material> getBreakableDefinition()
	{
		return breakingPredicate;
	}

	public List<String> toLore()
	{
		List<String> list = new ArrayList<>();
		String loreb = "x=" + xBreak + " y=" + yBreak + " z=" + zBreak;
		list.add(loreb);
		if (debug1) System.out.println("Returning correct lore!: " + list);
		return list;
	}
}
