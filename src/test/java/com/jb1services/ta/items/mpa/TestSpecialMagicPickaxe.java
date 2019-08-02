package com.jb1services.ta.items.mpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.junit.jupiter.api.Test;

import com.jb1services.ta.items.MagicPickaxe;
import com.jb1services.ta.main.TeachingExamplePlugin;

import static org.mockito.Mockito.*;

class TestSpecialMagicPickaxe
{
	
	@Test
	void test()
	{
		Random rnd = new Random();	
		int rval = rnd.nextInt(MagicPickaxe.ID_MATERIAL_ASSOCIATION.size());
		
		Server server = mock(Server.class);
		Logger logger = mock(Logger.class);
		when(server.getLogger()).thenReturn(logger);
		Bukkit.setServer(server);
		
		ItemFactory ift = mock(ItemFactory.class);
		when(server.getItemFactory()).thenReturn(ift);
		ItemMeta im = new MyItemMeta();
		when(ift.getItemMeta(MagicPickaxe.ID_MATERIAL_ASSOCIATION.get(rval))).thenReturn(im);
		//Bukkit bukkit = mock(Bukkit.class);
		
		
		
		SpecialMagicPickaxe smp = new SpecialMagicPickaxe().randomize(5, 0.5f, 25, 0.5f);
		ItemStack is;	
		try
		{
			is = smp.make(-1);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			try
			{
				is = smp.make(MagicPickaxe.ID_MATERIAL_ASSOCIATION.size());
				fail();
			}
			catch (IllegalArgumentException e2)
			{
				is = smp.make(rval);
				List<String> lore = is.getItemMeta().getLore();
				assertTrue(!lore.isEmpty());
				SpecialMagicPickaxe smp2 = new SpecialMagicPickaxe(lore);
				assertEquals(smp2, smp);
			}
		}
	}

}
