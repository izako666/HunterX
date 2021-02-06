package com.izako.hunterx.abilities.hatsus.leorio;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.entities.projectiles.ArmEntity;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.NenType;

import net.minecraft.entity.LivingEntity;

public class EmitterPunchAbility extends Ability {

	public EmitterPunchAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.ONUSE).setNenType(NenType.EMITTER).setMaxCooldown(4 * 20);
	}
	@Override
	public void use(LivingEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		if(p.world.isRemote())
			return;
		
		if(data.hasActiveAbility(ModAbilities.LOCKON_ABILITY) && ((LockOnAbility)data.getActiveAbility(ModAbilities.LOCKON_ABILITY, p)).lockOn != null) {
			if(Helper.consumeAura(20, p, this)) {
			LivingEntity Lockon = ((LockOnAbility)data.getActiveAbility(ModAbilities.LOCKON_ABILITY, p)).lockOn;
		
			ArmEntity arm = new ArmEntity(ArmEntity.TYPE,p.world);
			
		
			float finalX = (float) (Lockon.getPosX() - 3);
			float finalY = (float) Lockon.getPosYEye();
			float finalZ = (float) (Lockon.getPosZ() - 3);
			
			arm.setPosition(finalX, finalY, finalZ);
			arm.setOwner(p);
	        double motionX = (Lockon.getPosX() - arm.getPosX()) / 20;
	        double motionY =  0;
	        double motionZ = (Lockon.getPosZ() - arm.getPosZ()) / 20;

	        
	        arm.setMotion(motionX, motionY, motionZ);			
			p.world.addEntity(arm);
			}

		}
		super.use(p);
	}
	@Override
	public String getId() {
		return "emitter_punch";
	}

	@Override
	public String getName() {
		return "Emitter Punch";
	}

	@Override
	public String getDesc() {
		return "Emit your arm near your enemy.";
	}

}
