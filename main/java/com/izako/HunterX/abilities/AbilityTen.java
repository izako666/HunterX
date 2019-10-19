package com.izako.HunterX.abilities;

import com.izako.HunterX.Main;
import com.izako.HunterX.entity.particles.EntityTenParticle;
import com.izako.HunterX.izapi.abilities.Ability;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.PacketParticles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class AbilityTen extends Ability {

	public AbilityTen(String str) {
		super(str);
	}
	@Override
	public void startAbility(EntityPlayer player) {

		System.out.println(this.isPassiveActive(player));
	
		super.startAbility(player);
		
	}

}
