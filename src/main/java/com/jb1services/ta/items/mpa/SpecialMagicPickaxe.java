package com.jb1services.ta.items.mpa;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.jb1services.ta.items.MagicPickaxe;
import com.jb1services.ta.items.mpa.SpecialMagicPickaxeType;

public class SpecialMagicPickaxe extends MagicPickaxe
{
	public static boolean debug1 = true;
	public static boolean debug2 = false;

	private SpecialMagicPickaxeType type;

	public SpecialMagicPickaxe()
	{

	}

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
		if (lore != null && lore.size() >= 1 && lore.size() <= 2)
		{
			super.setLoreData(lore);
			if (lore.size() == 2)
			{
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
		}
		else
			throw new IllegalArgumentException("lore is null or not of length 1 to 2!");
	}

	@Override
	public List<String> toLore()
	{
		if (type != null)
		{
			List<String> lore = super.toLore();
			lore.add("special: [" + type.name() + "]: "+ type.getDescription());
			return lore;
		}
		else
			return super.toLore();
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

	/**
	 * Randomizes the SpecialMagicPickaxeType of this object.
	 * Calls randomizeType(float, Map) with null as second parameter.
	 * @param typeChangeChance The chance with which the type change actually happens (can be 1.0, and 0.0).
	 */
	private SpecialMagicPickaxe randomizeType(float typeChangeChance)
	{
		return randomizeType(typeChangeChance, null);
	}

	/**
	 * Randomizes the SpecialMagicPickaxeType of this object.
	 * @param typeChangeChance The chance with which the type change actually happens (can be 1.0, and 0.0).
	 * @param typePropabilities The probability with which a certain type should be chosen. If a type is not given
	 * a specific probability, the default is 1.0.
	 */
	private SpecialMagicPickaxe randomizeType(float typeChangeChance, Map<SpecialMagicPickaxeType, Float> typePropabilities)
	{
		boolean noTypePropabilities = typePropabilities == null || typePropabilities.isEmpty();
		if (typeChangeChance >= 0.0 && typeChangeChance <= 1.0)
		{
			List<SpecialMagicPickaxeType> invalidProbabilities;
			if (noTypePropabilities || (invalidProbabilities = typePropabilities.keySet().stream().filter(key -> typePropabilities.get(key) <= 0.0 || typePropabilities.get(key) > 1.0).collect(Collectors.toList())).isEmpty())
			{
				Random rnd = new Random();
				if (rnd.nextFloat() < typeChangeChance)
				{
					boolean changed = false;
					while (!changed)
					{
						SpecialMagicPickaxeType type = SpecialMagicPickaxeType.values()[rnd.nextInt(SpecialMagicPickaxeType.values().length)];
						if (noTypePropabilities || !typePropabilities.containsKey(type) || rnd.nextFloat() < typePropabilities.get(type))
						{
							setType(type);
							changed = true;
						}
					}
				}
			}
			else
				throw new IllegalArgumentException("All typePropabilities need to be > 0.0 and <= 1.0. Invalid probabilities: " + invalidProbabilities.stream().map(type -> type.name() + ": " + typePropabilities.get(type)).collect(Collectors.joining(", ")));
		}
		else
			throw new IllegalArgumentException("typeChangeChance needs to be > 0.0 & <= 1.0, but is " + typeChangeChance);

		return this;
	}

	public SpecialMagicPickaxe randomize(int successIncreaseX, int successIncreaseY, int successIncreaseZ, float successChanceX, float successChanceY, float successChanceZ, int maxValueX, int maxValueY, int maxValueZ, float typeChangeChance, Map<SpecialMagicPickaxeType, Float> typeProbabilities)
	{
		super.randomize(successIncreaseX, successIncreaseY, successIncreaseZ, successChanceX, successChanceY, successChanceZ, maxValueX, maxValueY, maxValueZ);
		return this.randomizeType(typeChangeChance, typeProbabilities);
	}

	public SpecialMagicPickaxe randomize(int successIncreaseX, int successIncreaseY, int successIncreaseZ, float successChanceX, float successChanceY, float successChanceZ, int maxValueX, int maxValueY, int maxValueZ, float typeChangeChance)
	{
		super.randomize(successIncreaseX, successIncreaseY, successIncreaseZ, successChanceX, successChanceY, successChanceZ, maxValueX, maxValueY, maxValueZ);
		return this.randomizeType(typeChangeChance);
	}

	public SpecialMagicPickaxe randomize(int successIncreases, float successChances, int maxValues, float typeChangeChance, Map<SpecialMagicPickaxeType, Float> typeProbabilities)
	{
		super.randomize(successIncreases, successChances, maxValues);
		return this.randomizeType(typeChangeChance, typeProbabilities);
	}

	public SpecialMagicPickaxe randomize(int successIncreases, float successChances, int maxValues, float typeChangeChance)
	{
		super.randomize(successIncreases, successChances, maxValues);
		if (debug2) System.out.println("super.randomize() done!");
		return this.randomizeType(typeChangeChance);
	}

	@Override
	public ItemStack make(int material)
	{
		if (ID_MATERIAL_ASSOCIATION.containsKey(material))
		{
			ItemStack is = new ItemStack(ID_MATERIAL_ASSOCIATION.get(material));
			ItemMeta im = is.getItemMeta();
			im.setLore(this.toLore());
			is.setItemMeta(im);
			return is;
		}
		else
			throw new IllegalArgumentException("Material id is not contained in ID_MATERIAL_ASSOCIATION. Valid ids: " + ID_MATERIAL_ASSOCIATION.keySet().stream().map(key -> key + ": " + ID_MATERIAL_ASSOCIATION.get(key).name()).collect(Collectors.joining(", ")));
	}

	public static ItemStack makeSpecialMagicPickaxeByLore(SpecialMagicPickaxe smpl)
	{
		Random rnd = new Random();
		return smpl.make(rnd.nextInt(4));
	}

	public static ItemStack makeTrulyRandomSpecialMagicPickaxe()
	{
		Random rnd = new Random();
		return new SpecialMagicPickaxe().randomize(5, 0.5f, 25, 0.5f).make(rnd.nextInt(5));
	}
		
	@Override
	public boolean equals(Object that)
	{
		if (this == that)
			return true;
		
		if (that == null)
			return false;
		
		if (this.getClass().equals(that.getClass()))
		{
			SpecialMagicPickaxe thato = (SpecialMagicPickaxe) that;
			return this.xBreak == thato.xBreak && this.yBreak == thato.yBreak && this.zBreak == thato.zBreak && this.type == thato.type;
		}
		return false;
	}
}
