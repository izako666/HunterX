package com.izako.hunterx.abilities.hatsus.leorio;

import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PassiveAbility;
import com.izako.wypi.WyHelper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.EntityRayTraceResult;

public class LockOnAbility extends PassiveAbility {

	public LivingEntity lockOn = null;
	public LockOnAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxPassive(Integer.MAX_VALUE).setMaxCooldown(0).setNenType(NenType.UNKNOWN);
	}
	@Override
	public String getId() {
		return "lock_on";
	}

	@Override
	public String getName() {
		return "Lock On";
	}

	@Override
	public String getDesc() {
		return "A lock on ability for leorios moves.";
	}

	@Override
	public void onStartPassive(LivingEntity p) {
		
		EntityRayTraceResult ray = WyHelper.rayTraceEntities(p, Helper.getTrueValue(100, this, p),p);
		
		if(!(ray.getEntity() instanceof LivingEntity)) {
			this.endAbility(p);
			return;
		} else {
			
			this.lockOn = (LivingEntity) ray.getEntity();
		}
		super.onStartPassive(p);
	}

	@Override
	public void onEndPassive(LivingEntity p) {
		this.lockOn = null;
		super.onEndPassive(p);
	}
	@Override
	public void duringPassive(LivingEntity p) {
		if(!p.world.isRemote()) {
			if(this.lockOn == null) {
				this.endAbility(p);
			} else {
				if(!this.lockOn.isAlive()) {
					this.endAbility(p);
				}
			}
		}
	}

}
