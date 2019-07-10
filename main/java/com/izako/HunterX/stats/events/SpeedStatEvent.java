package com.izako.HunterX.stats.events;

import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class SpeedStatEvent {

	double needToIncreaseSpeed = 0;
	private double actualSpeedStat = 0.0D;
	private AttributeModifier speedModifier;
	private AttributeModifier speedModifierMax;

	@SubscribeEvent
	public void onSprintEvent(TickEvent.PlayerTickEvent event) {

		// This event is for increasing speed every 2000 ticks of ongoing sprinting
		// translates into .01 modifier
		EntityPlayer player = event.player;
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		if (player.isSprinting() && actualSpeedStat < 0.2D) {
			actualSpeedStat = stats.getSpeedStat();
			stats.setSpeedStat(actualSpeedStat + 0.00002);
			IAttributeInstance attribute = ((EntityLivingBase) player)
					.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
			 speedModifier = new AttributeModifier(player.getUniqueID(), "SpeedStatIncrease",
					actualSpeedStat, 0).setSaved(true);
			attribute.removeModifier(speedModifier);
			attribute.applyModifier(speedModifier);
			// maximum speed modifier is .25
		} else if (actualSpeedStat >= 0.2D) {
			IAttributeInstance attribute = ((EntityLivingBase) player)
					.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
			speedModifierMax = new AttributeModifier(player.getUniqueID(), "SpeedStatIncrease", 0.2D, 0).setSaved(true);
			attribute.removeModifier(speedModifier);
			attribute.removeModifier(speedModifierMax);
			attribute.applyModifier(speedModifierMax);

		}

	}

}
