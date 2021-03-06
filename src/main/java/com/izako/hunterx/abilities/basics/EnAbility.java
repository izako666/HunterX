package com.izako.hunterx.abilities.basics;

import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.entities.EnEntity;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.ChargeablePassiveAbility;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;

import net.minecraft.entity.LivingEntity;

public class EnAbility extends ChargeablePassiveAbility implements ITrainable {

	public EnEntity entity;
	
	public List<LivingEntity> clientEntities = new ArrayList<>();
	public EnAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.CHARGING_PASSIVE).setMaxPassive(Integer.MAX_VALUE).setMaxCharging(100).setNenType(NenType.UNKNOWN).setMaxCooldown(8 * 20);
	}
	
	@Override
	public void duringPassive(LivingEntity p) {
		if(p.ticksExisted % 20 == 0) {
			Helper.consumeAura(4, p, this);

		}
	}

	@Override
	public String getId() {
		return "en";
	}

	@Override
	public String getName() {
		return "En";
	}
	
	@Override
	public String getDesc() {
		return "En creates a dome around you to detect and notice enemies, if you charge it up to the max you can see your enemies in the form of a gui.";
	}

	@Override
	public void onStartPassive(LivingEntity p) {
		if(!p.world.isRemote()) {
		EnEntity entity = new EnEntity(EnEntity.TYPE, p.world,p);
		entity.setPosition(p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getZ());
		entity.renderScale = (50 * this.getCurrentPowerScale()) * (this.getChargingTimer() / 50.0) *this.getStrength();
		p.world.addEntity(entity);
		this.entity = entity;
		}
	}

	@Override
	public void onEndPassive(LivingEntity p) {
		if(!p.world.isRemote()) {
		this.entity.remove();
		}
		super.onEndPassive(p);
	}

	@Override
	public double getPowerScale() {
		return 2.0;
	}
}
