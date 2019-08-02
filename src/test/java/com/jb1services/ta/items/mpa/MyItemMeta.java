package com.jb1services.ta.items.mpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.persistence.PersistentDataContainer;

import com.google.common.collect.Multimap;

public class MyItemMeta implements ItemMeta
{
	private List<String> list = new ArrayList<>();
	
	@Override
	public Map<String, Object> serialize()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistentDataContainer getPersistentDataContainer()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasDisplayName()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDisplayName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDisplayName(String name)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasLocalizedName()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getLocalizedName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocalizedName(String name)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasLore()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getLore()
	{
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public void setLore(List<String> lore)
	{
		this.list = lore;
	}

	@Override
	public boolean hasCustomModelData()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCustomModelData()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCustomModelData(Integer data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasEnchants()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasEnchant(Enchantment ench)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getEnchantLevel(Enchantment ench)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<Enchantment, Integer> getEnchants()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addEnchant(Enchantment ench, int level, boolean ignoreLevelRestriction)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeEnchant(Enchantment ench)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasConflictingEnchant(Enchantment ench)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addItemFlags(ItemFlag... itemFlags)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeItemFlags(ItemFlag... itemFlags)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<ItemFlag> getItemFlags()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasItemFlag(ItemFlag flag)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUnbreakable()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setUnbreakable(boolean unbreakable)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasAttributeModifiers()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<AttributeModifier> getAttributeModifiers(Attribute attribute)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAttributeModifier(Attribute attribute, AttributeModifier modifier)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAttributeModifiers(Multimap<Attribute, AttributeModifier> attributeModifiers)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeAttributeModifier(Attribute attribute)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAttributeModifier(EquipmentSlot slot)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAttributeModifier(Attribute attribute, AttributeModifier modifier)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CustomItemTagContainer getCustomTagContainer()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVersion(int version)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemMeta clone()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spigot spigot()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
