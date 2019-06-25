package com.izako.HunterX.items;




import com.izako.HunterX.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item{
	

	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		
		
		ModItems.ITEMS.add(this);
		
	}
	
	
}
