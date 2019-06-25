package com.izako.HunterX.items.armor;



import com.izako.HunterX.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorBase extends ItemArmor{

	public ArmorBase(String name,ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModItems.HunterX);
		
		ModItems.ITEMS.add(this);
	}
	
	
	
	

}
