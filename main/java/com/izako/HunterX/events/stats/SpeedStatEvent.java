package com.izako.HunterX.events.stats;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class SpeedStatEvent {

	int needToIncreaseSpeed = 0;
	private static double actualSpeedStat = 0.0D;
	private static AttributeModifier speedModifier;
	private static AttributeModifier speedModifierMax;

	@SubscribeEvent
	public void onSprintEvent(TickEvent.PlayerTickEvent event) {
		
		//This event is for increasing speed every 2000 ticks of ongoing sprinting translates into .01 modifier
		EntityPlayer player = event.player;
		if (player.isSprinting() && actualSpeedStat < 0.25D) {
			needToIncreaseSpeed = needToIncreaseSpeed + 1;
			double speedStat = needToIncreaseSpeed / 20;
			actualSpeedStat = speedStat / 100;
			IAttributeInstance attribute = ((EntityLivingBase) player)
					.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
			  AttributeModifier speedModifier = new AttributeModifier(player.getUniqueID(), "SpeedStatIncrease",
					actualSpeedStat, 0).setSaved(true);
			attribute.removeModifier(speedModifier);
			attribute.applyModifier(speedModifier);
			//maximum speed modifier is .25 
		} else if (actualSpeedStat >= 0.25D) {
			IAttributeInstance attribute = ((EntityLivingBase) player)
					.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
			 speedModifierMax = new AttributeModifier(player.getUniqueID(), "SpeedStatIncrease", 0.5D, 0)
					.setSaved(true);
			attribute.removeModifier(speedModifierMax);
			attribute.applyModifier(speedModifierMax);

		}

	}

	public static AttributeModifier getSpeedModifier() {
		return speedModifier;
	}
	public static AttributeModifier getSpeedModifierMax() {
		return speedModifierMax;
	}
}
