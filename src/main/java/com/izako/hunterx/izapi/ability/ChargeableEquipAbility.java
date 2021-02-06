package com.izako.hunterx.izapi.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public abstract class ChargeableEquipAbility extends ChargeablePassiveAbility {


	
	public abstract ItemStack createItem(LivingEntity p);

	@Override
	public void onStartPassive(LivingEntity p) {
		ItemStack stack = this.createItem(p);
		stack.getOrCreateTag().putUniqueId("nenowner", p.getUniqueID());
	  if(p.getHeldItemMainhand().isEmpty()) {
		p.setHeldItem(Hand.MAIN_HAND, stack);
	  } else {
		  p.sendMessage(new StringTextComponent("Empty your main hand").applyTextStyle(TextFormatting.RED));
	  }
		super.onStartPassive(p);
	}

	@Override
	public void onEndPassive(LivingEntity p) {
		

		
		
		if(p.getItemStackFromSlot(EquipmentSlotType.MAINHAND).hasTag() && p.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getTag().contains("nenowner")) {
			if(p.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getTag().getUniqueId("nenowner").equals(p.getUniqueID())) {
			p.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
			}
		}
		if(p instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) p;
			
			for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
				ItemStack otherStack = player.inventory.getStackInSlot(i);
				
				if(otherStack.hasTag() && otherStack.getTag().hasUniqueId("nenowner")) {
					if(otherStack.getTag().getUniqueId("nenowner").equals(p.getUniqueID()) ) {
					player.inventory.removeStackFromSlot(i);
					}
				}
			}
		}
		super.onEndPassive(p);
	}
}
