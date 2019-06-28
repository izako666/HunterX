package com.izako.HunterX.stats.events;

import com.izako.HunterX.stats.capabilties.EntityStatsProvider;
import com.izako.HunterX.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityEvent {
	


	public static final ResourceLocation ENTITY_STAT = new ResourceLocation(Reference.MOD_ID, "healthStat");
	@SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if(!(event.getObject() instanceof EntityPlayer)) 
        {
            return;
        }

        event.addCapability(ENTITY_STAT, new EntityStatsProvider());
    }
}
