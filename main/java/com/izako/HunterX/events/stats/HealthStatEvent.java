package com.izako.HunterX.events.stats;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HealthStatEvent {

	double needToIncreaseHealth = 0;
	private static double healthStat = 0;
	private static AttributeModifier healthModifier;
	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		Entity playerIn = event.getEntity();

		if (event.getEntity() instanceof EntityPlayer && healthStat <= 20.0D) {

			// this event is for the healthstat every 40 hits modifier is increased by one,
			// max is 20
			needToIncreaseHealth = needToIncreaseHealth + 1;
			healthStat = needToIncreaseHealth / 2;
			IAttributeInstance attribute = ((EntityLivingBase) playerIn)
					.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
			 healthModifier = new AttributeModifier(playerIn.getUniqueID(), "HealthStatIncrease", healthStat,
					0).setSaved(true);
			attribute.removeModifier(healthModifier);
			attribute.applyModifier(healthModifier);

		} else if (event.getEntity() instanceof EntityPlayer && healthStat > 20.0D) {
			IAttributeInstance attribute = ((EntityLivingBase) playerIn)
					.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
			AttributeModifier modifier = new AttributeModifier(playerIn.getUniqueID(), "HealthStatIncrease", 20.0D, 0)
					.setSaved(true);
			attribute.removeModifier(modifier);
			attribute.applyModifier(modifier);
			playerIn.sendMessage(new TextComponentString(Double.toString(healthStat)));
		}
	}
	public static AttributeModifier getHealthModifier() {
		return healthModifier;
	}
	
}
