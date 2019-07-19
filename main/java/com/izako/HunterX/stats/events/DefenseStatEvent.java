package com.izako.HunterX.stats.events;

import java.util.UUID;

import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.EntityStatsClientSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DefenseStatEvent {

	private AttributeModifier defenseModifier;
	UUID attribute_uuid = UUID.randomUUID();

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {

		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer) event.getEntity();

			IAttributeInstance attribute = ((EntityLivingBase) playerIn)
					.getEntityAttribute(SharedMonsterAttributes.ARMOR);
			IEntityStats stats = playerIn.getCapability(EntityStatsProvider.ENTITY_STATS, null);
			double defenseStatCap = stats.getDefenseStat();
			if (defenseStatCap < 10.0D) {
				stats.setDefenseStat(defenseStatCap + 0.02);
				defenseStatCap = stats.getDefenseStat();
				if(playerIn instanceof EntityPlayerMP) {
					ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getDefenseStat(), 2), (EntityPlayerMP) playerIn);
				}
				defenseModifier = new AttributeModifier(attribute_uuid, "defenseStatIncrease", defenseStatCap, 0)
						.setSaved(true);
				attribute.removeModifier(defenseModifier);
				attribute.applyModifier(defenseModifier);

			} else if (defenseStatCap >= 10.0D) {
				defenseModifier = new AttributeModifier(attribute_uuid, "defenseStatIncrease", 10, 0)
						.setSaved(true);
				attribute.removeModifier(defenseModifier);
				attribute.applyModifier(defenseModifier);
			}
		}

	}
}
