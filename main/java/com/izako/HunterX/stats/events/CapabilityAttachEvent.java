package com.izako.HunterX.stats.events;

import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityAttachEvent {

	@SubscribeEvent
	public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		ResourceLocation key = new ResourceLocation(Reference.MOD_ID + ":stats");
		if (event.getObject() instanceof EntityPlayer) {

			if (!event.getCapabilities().containsKey(key)) {
				event.addCapability(key, new EntityStatsProvider());
				System.out.println("attached");
			}

		}

	}
}
