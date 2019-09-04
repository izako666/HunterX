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
		this.str = str;
	}

	public String getID() {
		return str;

	}

	public boolean canStart() {
		return true;
	}

	public void startAbility(EntityPlayer player) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		boolean isPassiveActive = stats.isPassiveActive(this.getID());
		boolean isOnCooldown = stats.isOnCooldown(this.getID());
		if (!isOnCooldown) {
			if (!isPassiveActive) {
				stats.setIsPassiveActive(true, this.getID());
			} else {
				this.endAbility(player);
			}
			
		}
	}

	public void duringAbility(EntityPlayer player, int passiveTimer) {
		if (passiveTimer <= 0) {
			this.endAbility(player);
		}
	}

	public void endAbility(EntityPlayer player) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);

		stats.setIsPassiveActive(false, this.getID());
	}

	public ResourceLocation getAbilityTexture() {
		return null;
	}

	public void setAbilityToSlot(EntityPlayer player, int index) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		stats.setAbilityToSlot(index, this);
		if (player instanceof EntityPlayerMP) {
			ModidPacketHandler.INSTANCE.sendTo(new AbilityPacketSync(this, index, 1), (EntityPlayerMP) player);
		}
		ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(this, index, 1));
	}

	public void giveAbility(EntityPlayer player) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		stats.removeAbility(this);
		stats.giveAbility(this);
		if (player instanceof EntityPlayerMP) {
			ModidPacketHandler.INSTANCE.sendTo(new AbilityPacketSync(this, 1, 3), (EntityPlayerMP) player);
		}
		ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(this, 1, 3));

	}

}
