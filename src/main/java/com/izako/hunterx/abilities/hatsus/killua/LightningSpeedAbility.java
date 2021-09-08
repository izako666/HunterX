package com.izako.hunterx.abilities.hatsus.killua;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModParticleTypes;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PassiveAbility;
import com.izako.wypi.WyHelper;
import com.izako.wypi.particles.GenericParticleData;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;

public class LightningSpeedAbility extends PassiveAbility {

	public LightningSpeedAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setNenType(NenType.TRANSMUTER).setMaxPassive(Integer.MAX_VALUE).setMaxCooldown(4 * 20);
	}
	@Override
	public String getId() {
		return "lightning_speed_ability";
	}

	@Override
	public String getName() {
		return "GodSpeed: Speed of Lightning";
	}

	@Override
	public String getDesc() {
		return "Outputs great amounts of electricity around the user to speed him up.";
	}
	@Override
	public void duringPassive(LivingEntity p) {
		p.addPotionEffect(new EffectInstance(Effects.SPEED,20,3,true,true));
		
		 if(!p.world.isRemote()) {
			int chance = p.world.getRandom().nextInt(100);
			if(chance > 80) {
				IAbilityData abilityData = AbilityDataCapability.get(p);
				double xRange = p.world.getRandom().nextDouble()  -0.5;
				double yRange = p.world.getRandom().nextDouble() * 2;
				double zRange = p.world.getRandom().nextDouble() -0.5;
				GenericParticleData data = new GenericParticleData(ModParticleTypes.LIGHTNING);
				data.setMotion(0, 0, 0);
				data.setLife(10);
				data.setSize(0.15f);
				data.setColor(1f, 1f, 1f, 0.9f);
			 WyHelper.spawnParticles(data, (ServerWorld) p.world, p.getPosX() + xRange, p.getPosY() + yRange, p.getPosZ() + zRange);
			} 
		} 

		 if(p.ticksExisted % 20 == 0) {
			 Helper.consumeAura(2, p, this);
		 }
		super.duringPassive(p);
	}

}
