package com.izako.HunterX.stats.events;

import java.util.UUID;

import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.EntityModifierServerChange;
import com.izako.HunterX.network.packets.EntityStatsServerSync;
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
	public static UUID speed_attribute_modifier = UUID.fromString("69082b90-7357-407c-9e82-7852b6925932");

	@SubscribeEvent
	public void onSprintEvent(TickEvent.PlayerTickEvent event) {

		
		
		EntityPlayer player = event.player;
		
		IAttributeInstance attribute = ((EntityLivingBase) player)
				.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		if (player.isSprinting()) {
			if (stats.getSpeedStat() < 0.1D) {
				
				stats.setSpeedStat(stats.getSpeedStat() + 0.000004);

				stats.getSpeedStat();
			
				ModidPacketHandler.INSTANCE.sendToServer(new EntityStatsServerSync(stats.getSpeedStat(), 3, false));
				ModidPacketHandler.INSTANCE.sendToServer(new EntityModifierServerChange(stats.getSpeedStat(), 2));
			} else if (stats.getSpeedStat() >= 0.15D) {
				// maximum speed modifier is .20
				ModidPacketHandler.INSTANCE.sendToServer(new EntityModifierServerChange(0.15, 2));

			}
		}

	}

}
