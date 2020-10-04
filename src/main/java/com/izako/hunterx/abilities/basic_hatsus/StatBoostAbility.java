package com.izako.hunterx.abilities.basic_hatsus;

import java.util.List;

import com.izako.hunterx.abilities.basics.KenAbility;
import com.izako.hunterx.abilities.basics.KoAbility;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.AbilityTags;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.PassiveAbility;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;

public class StatBoostAbility extends PassiveAbility implements ITrainable {


	@Override
	public String getId() {
		return "stat_boost";
	}

	@Override
	public String getName() {
		return "Aura Enhancement";
	}

	@Override
	public void onStartPassive(LivingEntity p) {
		
		super.onStartPassive(p);
	}

	@Override
	public void duringPassive(LivingEntity p) {
		super.duringPassive(p);
	}

	@Override
	public void onEndPassive(LivingEntity p) {
		super.onEndPassive(p);
	}


	@Override
	public double getPowerScale() {
		return 1.5d;
	}
}
