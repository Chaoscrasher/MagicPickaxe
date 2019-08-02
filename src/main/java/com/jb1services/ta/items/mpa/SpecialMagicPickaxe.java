package com.jb1services.ta.items.mpa;

import java.util.List;
import java.util.Optional;

import com.jb1services.ta.items.MagicPickaxe;

public class SpecialMagicPickaxe extends MagicPickaxe
{
	public static boolean debug1 = true;
	public static boolean debug2 = true;

	private SpecialMagicPickaxeType type;

	public SpecialMagicPickaxe(int xBreak, int yBreak, int zBreak, SpecialMagicPickaxeType type)
	{
		super(xBreak, yBreak, zBreak);
		setType(type);
	}

	public SpecialMagicPickaxe(SpecialMagicPickaxeType type)
	{
		super(25, 25, 25, true);
		super.yBreak = super.yBreak >= 0 ? super.yBreak : super.yBreak*-1;
		setType(type);
	}

	public SpecialMagicPickaxe(List<String> lore)
	{
		if (lore != null && lore.size() == 2)
		{
			super.setLoreData(lore);
			String slore = lore.get(1);
			if (debug1) System.out.println("slore = " + slore);
			if (slore.matches("special: \\[[A-Z_]+\\]: [\\w'!.? ]+"))
			{
				String iddesc = slore.substring(slore.indexOf("special: [") + "special: [".length());
				type = SpecialMagicPickaxeType.valueOf(iddesc.substring(0, iddesc.indexOf("]")));
				this.breakingPredicate = type.getBreakingPredicate();
			}
			else
				throw new IllegalArgumentException("lore is not well-formed!");
		}
		else
			throw new IllegalArgumentException("lore is null or not of length 2!");
	}

	@Override
	public List<String> toLore()
	{
		List<String> lore = super.toLore();
		lore.add("special: [" + type.name() + "]: "+ type.getDescription());
		return lore;
	}

	public static Optional<SpecialMagicPickaxe> isValidSpecialMagicPickaxeLore(List<String> lore)
	{
		try
		{
			return Optional.of(new SpecialMagicPickaxe(lore));
		}
		catch (IllegalArgumentException e)
		{
			if (debug1) e.printStackTrace();
			return Optional.empty();
		}
	}

	private void setType(SpecialMagicPickaxeType type)
	{
		this.type = type;
		this.breakingPredicate = type.getBreakingPredicate();
	}
}
