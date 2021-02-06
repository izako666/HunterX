package com.izako.hunterx.abilities.basic_hatsus;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.entities.projectiles.AuraBlastProjectileEntity;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.ChargeableAbility;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.StringTextComponent;

public class AuraBlastAbility extends ChargeableAbility implements ITrainable {

	public AuraBlastAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.CHARGING).setMaxCharging(100).setNenType(NenType.EMITTER).setMaxCooldown(4 * 20);
	}
	@Override
	public String getId() {
		return "aura_blast";
	}

	@Override
	public String getName() {
		return "Aura Blast";
	}

	@Override
	public String getDesc() {
		return "Manifest your aura into one strong and effective blast.";
	}
	@Override
	public void onEndCharging(LivingEntity p) {
		double damage = Helper.fromRangeToRange(0, this.props.maxCharging, 1, 10, this.getChargingTimer());
		double scale = Helper.fromRangeToRange(0, this.props.maxCharging, 0.5d, 3d, this.getChargingTimer());
		damage = Helper.getTrueValue((float)damage, this, p);
		AuraBlastProjectileEntity entity = new AuraBlastProjectileEntity(p.world);
		entity.damage = damage;
		entity.scale = scale;
		entity.setPosition(p.getPosX(), p.getPosYEye(), p.getPosZ());
		entity.setOwner(p);
		entity.shoot(p, p.rotationPitch, p.rotationYaw, 1f, 1.5f, 0f);
		entity.rotationPitch = p.rotationPitch;
		if(!p.world.isRemote()) {
			p.world.addEntity(entity);
		}
		super.onEndCharging(p);
	
		Helper.consumeAura(25, p, this);
		
	}
	@Override
	public void setXp(double xp, LivingEntity p) {
		super.setXp(xp, p);
		
		IHunterData data = HunterDataCapability.get(p);
		if(data.hasQuest(ModQuests.BASIC_EMITTER) && data.getQuest(ModQuests.BASIC_EMITTER).getProgress() <100) {
			data.getQuest(ModQuests.BASIC_EMITTER).setProgress((xp + 50) * 2);
		}
	}
	

}
