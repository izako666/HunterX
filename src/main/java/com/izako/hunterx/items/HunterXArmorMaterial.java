package com.izako.hunterx.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class HunterXArmorMaterial  implements IArmorMaterial{

	private String name;
	private int durability;
	private int[] damageReductionAmount;
	private int enchantability;
	 private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
	public HunterXArmorMaterial(String name, int durability, int[] damageReductionAmount, int enchantability) {
	this.name = name;	
	this.durability = durability;
	this.damageReductionAmount = damageReductionAmount;
	this.enchantability = enchantability;
	}
	@Override
	public int getDurability(EquipmentSlotType slotIn) {
		// TODO Auto-generated method stub
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * durability;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		// TODO Auto-generated method stub
		return damageReductionAmount[slotIn.getIndex()];
	}

	@Override
	public int getEnchantability() {
		// TODO Auto-generated method stub
		return enchantability;
	}

	@Override
	public SoundEvent getSoundEvent() {
		// TODO Auto-generated method stub
		return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
	}

	@Override
	public Ingredient getRepairMaterial() {
		// TODO Auto-generated method stub
		return Ingredient.EMPTY;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public float getToughness() {
		// TODO Auto-generated method stub
		return 0;
	}

}
