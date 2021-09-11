package com.izako.hunterx.abilities.hatsus.kalluto;

import com.izako.hunterx.entities.projectiles.PaperProjectileEntity;
import com.izako.hunterx.izapi.ability.Ability;

import net.minecraft.entity.LivingEntity;

public class MeanderingDanceAbility extends Ability {

	@Override
	public String getId() {
		return "meandering_dance";
	}

	@Override
	public String getName() {
		return "Meandering Dance";
	}

	@Override
	public String getDesc() {
		return "A projectile is shot made up of condensed confetti at the marked target. Use in correlation with Paper Mark.";
	}

	@Override
	public void onUse(LivingEntity p) {

		if(!p.world.isRemote()) {
		PaperProjectileEntity proj = new PaperProjectileEntity(p.world);
		//proj.shoot(getXp(), getCurrentCooldownScale(), getCurrentAuraConScale(), getCooldown(), getChargingTimer());
		p.world.addEntity(proj);
		}
		super.onUse(p);
	}

}
