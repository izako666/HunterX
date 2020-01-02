package com.izako.HunterX.stats.events;

import java.util.UUID;

import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.EntityStatsClientSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AuraEvent {
	private int counter = 0;
	private AttributeModifier auraModifier;
	public static UUID aura_attributemodifier_uuid = UUID.fromString("aa18be1b-b1d0-4917-a168-42dd240d000d");
	
    @SubscribeEvent
	public void auraRegen(TickEvent.PlayerTickEvent e) {
    	IEntityStats stats = e.player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
    	
    	
    	if (stats.getAura() >= stats.getAuraCapacity()) {
    		
    		
    	}else {
    		counter++;
    		if(counter >= 60) {
    			
    			stats.setAura(stats.getAura()+1);
        		
        		if(e.player instanceof EntityPlayerMP) {
    				ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getAura(), 2, false), (EntityPlayerMP) e.player);
        		}
    			auraModifier = new AttributeModifier(aura_attributemodifier_uuid, "defenseStatIncrease", stats.getAura(), 0)
    					.setSaved(true);
    			counter = 0;
    			
    		}
    		
    		
    			
    		
    	}
		
	}
	

	
	

}
