package com.izako.hunterx.abilities.hatsus.killua;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModEffects;
import com.izako.hunterx.init.ModParticleTypes;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PunchAbility;
import com.izako.wypi.WyHelper;
import com.izako.wypi.particles.GenericParticleData;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;

public class LightningPalmAbility extends PunchAbility {

	public LightningPalmAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxPassive(Integer.MAX_VALUE).setNenType(NenType.TRANSMUTER).setMaxCooldown(2 * 20);
	}
	@Override
	public String getId() {
		return "lightning_palm";
	}

	@Override
	public String getName() {
		return "Lightning Palm";
	}

	@Override
	public String getDesc() {
		return "Shocks the opponent through electricity built up in the users palm.";
	}



	@Override
	public void duringPassive(LivingEntity p) {
		super.duringPassive(p);
		IAbilityData abilityData = AbilityDataCapability.get(p);
		if(!p.world.isRemote() && p.ticksExisted % 8 == 0) {
			double xRange = (p.world.getRandom().nextDouble()  -0.5) / 2;
			double yRange = (p.world.getRandom().nextDouble())/ 2;
			double zRange = (p.world.getRandom().nextDouble() -0.5) / 2;

			GenericParticleData data = new GenericParticleData(ModParticleTypes.LIGHTNING);
			data.setMotion(-xRange / 20, -yRange/8, -zRange/20);
			data.setLife(20);
			data.setSize(0.15f);
			data.setColor(1f, 1f, 1f, 0.9f);
			 WyHelper.spawnParticles(data, (ServerWorld) p.world, p.getPosX()+ Helper.getRotatedX(0.5f, p.rotationYaw + 180), p.getPosY()+ 1.1 + yRange, p.getPosZ()+ Helper.getRotatedZ(0.5f, p.rotationYaw + 180));

		}

	}


	@Override
	public float onPunch(PlayerEntity p, LivingEntity target) {
		target.addPotionEffect(new EffectInstance(ModEffects.PARALYSIS_EFFECT, 40, 1));
		Helper.endAbilitySafe(p, this);
		Helper.consumeAura(20, p, this);
		return Helper.getTrueValue(15, this, p);
	}
}
