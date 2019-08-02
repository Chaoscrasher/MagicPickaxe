package com.jb1services.ta.items.mpa;

import java.util.function.Predicate;

import org.bukkit.Material;

public enum SpecialMagicPickaxeType
{
	NON_ORE_BREAKING("doesn't break ore blocks", mat -> mat.toString().contains("STONE") ||
			mat.toString().contains("DIORITE") ||
			mat.toString().contains("ANDESITE") ||
			mat.toString().contains("BRICK") ||
			mat.toString().contains("GRANITE") ||
			mat.toString().contains("PRISMARINE"));

	private String description;
	private Predicate<Material> breakingPredicate;

	private SpecialMagicPickaxeType(String description, Predicate<Material> breakingPredicate)
	{
		this.description = description;
		this.breakingPredicate = breakingPredicate;
	}

	public String getDescription()
	{
		return description;
	}

	public Predicate<Material> getBreakingPredicate()
	{
		return breakingPredicate;
	}
	
}
