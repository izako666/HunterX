package com.izako.HunterX.stats.events;

import java.util.UUID;

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

	private AttributeModifier speedModifier;
	private AttributeModifier speedModifierMax;
	UUID attribute_uuid = UUID.randomUUID();

	@SubscribeEvent
	public void onSprintEvent(TickEvent.PlayerTickEvent event) {

		EntityPlayer player = event.player;
		IAttributeInstance attribute = ((EntityLivingBase) player)
				.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);

		if (player.isSprinting()) {
			if (stats.getSpeedStat() < 0.2D) {
				stats.setSpeedStat(stats.getSpeedStat() + 0.002);

				stats.getSpeedStat();
				speedModifier = new AttributeModifier(attribute_uuid, "SpeedStatIncrease", stats.getSpeedStat(), 0)
						.setSaved(true);
				attribute.removeModifier(speedModifier);
				attribute.applyModifier(speedModifier);
				player.sendMessage(new TextComponentString("speedcap" + Double.toString(stats.getSpeedStat())));
			} else if (stats.getSpeedStat() >= 0.2D) {
				// maximum speed modifier is .20
				speedModifier = new AttributeModifier(attribute_uuid, "SpeedStatIncrease", 0.2, 0).setSaved(true);
				attribute.removeModifier(speedModifier);
				attribute.applyModifier(speedModifier);

			}
		}

	}

}
