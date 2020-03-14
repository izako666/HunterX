package com.izako.hunterx.items.tiers;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class HunterXItemTier implements IItemTier{

	private int harvestLevel;
	private int maxUses;
	private float efficiency;
	private float attackDamage;
	private int enchantability;
	public HunterXItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability ) {
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		
	}
	@Override
	public int getMaxUses() {
		// TODO Auto-generated method stub
		return this.maxUses;
	}

	@Override
	public float getEfficiency() {
		// TODO Auto-generated method stub
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		// TODO Auto-generated method stub
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		// TODO Auto-generated method stub
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		// TODO Auto-generated method stub
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		// TODO Auto-generated method stub
		return null;
	}

}
