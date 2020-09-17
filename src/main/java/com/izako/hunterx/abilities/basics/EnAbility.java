package com.izako.hunterx.abilities.basics;

import com.izako.hunterx.entities.EnEntity;
import com.izako.hunterx.izapi.ability.ChargeablePassiveAbility;
import com.izako.hunterx.izapi.ability.ITrainable;

import net.minecraft.entity.LivingEntity;

public class EnAbility extends ChargeablePassiveAbility implements ITrainable {

	@Override
	public String getId() {
		return "en";
	}

	@Override
	public String getName() {
		return "En";
	}

	@Override
	public void onStartPassive(LivingEntity p) {
		if(!p.world.isRemote()) {
		EnEntity entity = new EnEntity(EnEntity.TYPE, p.world,p);
		entity.setPosition(p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getZ());
		entity.renderScale = 50 * this.getCurrentPowerScale();
		p.world.addEntity(entity);
		}
	}

	@Override
	public double getPowerScale() {
		return 2.0;
	}
}
