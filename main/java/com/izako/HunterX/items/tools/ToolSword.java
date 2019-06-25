package com.izako.HunterX.items.tools;




import com.izako.HunterX.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ToolSword extends ItemSword{
	
	public ToolSword(String name, ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModItems.HunterX);
		
		ModItems.ITEMS.add(this);
		
	}
	
	
	
	

}
