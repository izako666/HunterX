package com.izako.HunterX.stats.events;

import java.util.UUID;

import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.EntityModifierServerChange;
import com.izako.HunterX.network.packets.EntityStatsServerSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HealthStatEvent {

	private AttributeModifier healthModifier;
	public static UUID health_attributemodifier_uuid = UUID.fromString("135d510d-26c6-403e-8615-899862332e86");



	@SubscribeEvent
	public void onEaten(LivingEntityUseItemEvent.Finish e) {
		if (e.getItem().getItem() instanceof ItemFood && e.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.getEntityLiving();

			IAttributeInstance attribute = ((EntityLivingBase) player)
					.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
			IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
			Double healthStat = stats.getHealthStat();
			if (healthStat < 10) {


				stats.setHealthStat(healthStat + 0.10);


				healthStat = stats.getHealthStat();
				ModidPacketHandler.INSTANCE.sendToServer(new EntityStatsServerSync(stats.getHealthStat(), 1, false));
				ModidPacketHandler.INSTANCE.sendToServer(new EntityModifierServerChange(stats.getHealthStat(), 1) );
			} else if (healthStat >= 10) {

				

			}
		}

	}

}