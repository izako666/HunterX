package com.izako.hunterx.abilities.hatsus.kalluto;

import com.izako.hunterx.entities.projectiles.PaperProjectileEntity;
import com.izako.hunterx.izapi.ability.Ability;

import net.minecraft.entity.LivingEntity;

public class MeanderingDanceAbility extends Ability {

	public MeanderingDanceAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.ONUSE);
	}
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

		PaperProjectileEntity proj = new PaperProjectileEntity(p.world);
		proj.setPosition(p.getPosX(), p.getPosYEye(), p.getPosZ());
		proj.setOwner(p);
		proj.shoot(p, p.rotationPitch, p.rotationYaw, 1f, 1.5f, 0f);
		if(!p.world.isRemote()) {
		p.world.addEntity(proj);
		}
		super.onUse(p);
	}

}
