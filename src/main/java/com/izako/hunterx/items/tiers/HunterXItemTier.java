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
		return this.maxUses;
	}

	@Override
	public float getEfficiency() {
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.EMPTY;
	}

}
