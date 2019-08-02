package com.jb1services.ta.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MagicPickaxe
{
	public static boolean debug1 = false;

	@SuppressWarnings("serial")
	public static final Map<Integer, Material> ID_MATERIAL_ASSOCIATION = new HashMap<Integer, Material>()
	{{
		put(0, Material.WOODEN_PICKAXE);
		put(1, Material.STONE_PICKAXE);
		put(2, Material.IRON_PICKAXE);
		put(3, Material.GOLDEN_PICKAXE);
		put(4, Material.DIAMOND_PICKAXE);
	}};

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

	public MagicPickaxe randomize(int successIncreases, float successChances, int maxValues)
	{
		return randomize(successIncreases, successIncreases, successIncreases, successChances, successChances, successChances, maxValues, maxValues, maxValues);
	}

	public MagicPickaxe randomize(int successIncreaseX, int successIncreaseY, int successIncreaseZ, float successChanceX, float successChanceY, float successChanceZ, int maxValueX, int maxValueY, int maxValueZ)
	{
		if (successIncreaseX > 0 && successIncreaseY > 0 && successIncreaseZ > 0)
		{
			if (successChanceX > 0 && successChanceX <= 1.0 && successChanceY > 0 && successChanceY <= 1.0 && successChanceZ > 0 || successChanceZ <= 1.0)
			{
				if (maxValueX > 0 && maxValueY > 0 && maxValueZ > 0)
				{
					Random rnd = new Random();
					int xBreak = 0, yBreak = 0, zBreak = 0;
					int xDir = rnd.nextBoolean() ? 1 : -1, yDir = rnd.nextBoolean() ? 1 : -1, zDir = rnd.nextBoolean() ? 1 : -1;
					while (rnd.nextFloat() < successChanceX && Math.abs(xBreak) < maxValueX)
					{
						xBreak += successIncreaseX*xDir;
					}
					if (Math.abs(xBreak) > maxValueX) xBreak = maxValueX*xDir;
					if (xBreak == 0) xBreak = xDir;

					while (rnd.nextFloat() < successChanceY && Math.abs(yBreak) < maxValueY)
					{
						yBreak += successIncreaseY*yDir;
					}
					if (Math.abs(yBreak) > maxValueY) yBreak = maxValueY*yDir;
					if (yBreak == 0) yBreak = yDir;

					while (rnd.nextFloat() < successChanceZ && Math.abs(zBreak) < maxValueZ)
					{
						zBreak += successIncreaseZ*zDir;
					}
					if (Math.abs(zBreak) > maxValueZ) zBreak = maxValueZ*zDir;
					if (zBreak == 0) zBreak = zDir;
					
					this.xBreak = xBreak;
					this.yBreak = yBreak;
					this.zBreak = zBreak;
				}
				else throw new IllegalArgumentException("Max values need to be > 0, are x:" + maxValueX + ", y:" + maxValueY + ", z:" + maxValueZ);
			}
			else
				throw new IllegalArgumentException("success chances must be > 0 and <= 1.0, are x:"+successChanceX + ", y:" + successChanceY + ", z:" + successChanceZ);
		}
		else
			throw new IllegalArgumentException("success increases need to be < 0, are x:" + successIncreaseX + ", y:" + successChanceY + ", z:" + successIncreaseZ);

		return this;
	}

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

	public static ItemStack makeTrulyRandomMagicPickaxe()
	{
		Random rnd = new Random();
		return new MagicPickaxe().randomize(5, 0.5f, 25).make(rnd.nextInt(5));
	}

	public static ItemStack makeRandomMagicPickaxe()
	{
		Random rnd = new Random();
		return new MagicPickaxe(100).make(rnd.nextInt(4));
	}

	public static ItemStack makeTestMagicPickaxe()
	{
		return new MagicPickaxe(1, 2, 1).make(1);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breakingPredicate == null) ? 0 : breakingPredicate.hashCode());
		result = prime * result + ((xBreak == null) ? 0 : xBreak.hashCode());
		result = prime * result + ((yBreak == null) ? 0 : yBreak.hashCode());
		result = prime * result + ((zBreak == null) ? 0 : zBreak.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MagicPickaxe other = (MagicPickaxe) obj;
		if (breakingPredicate == null)
		{
			if (other.breakingPredicate != null)
				return false;
		} else if (!breakingPredicate.equals(other.breakingPredicate))
			return false;
		if (xBreak == null)
		{
			if (other.xBreak != null)
				return false;
		} else if (!xBreak.equals(other.xBreak))
			return false;
		if (yBreak == null)
		{
			if (other.yBreak != null)
				return false;
		} else if (!yBreak.equals(other.yBreak))
			return false;
		if (zBreak == null)
		{
			if (other.zBreak != null)
				return false;
		} else if (!zBreak.equals(other.zBreak))
			return false;
		return true;
	}
}
