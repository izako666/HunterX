package com.izako.HunterX.izapi.abilities;

import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.AbilityPacketSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class Ability {

	private String str;
	public Ability(String str) {
		this.str= str;
	}
	public String getID() {
		return str;
		
	}
	
	public boolean canStart() {
		return true;
		}
	
	public void startAbility(EntityPlayer player) {}
	public void duringAbility(EntityPlayer player) {}
	public void endAbility(EntityPlayer player) {}
	
	public ResourceLocation getAbilityTexture() {
		return null;
	}
	
	public void setAbilityToSlot(EntityPlayer player, int index) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		stats.setAbilityToSlot(index, this);
		if(player instanceof EntityPlayerMP) {
		ModidPacketHandler.INSTANCE.sendTo(new AbilityPacketSync(this, index, 1), (EntityPlayerMP) player);
		}
		ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(this, index, 1));
	}

}
