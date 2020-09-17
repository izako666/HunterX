package com.izako.hunterx.abilities.basics;

import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IOnPlayerRender;
import com.izako.hunterx.izapi.ability.PassiveAbility;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class GyoAbility extends PassiveAbility implements IOnPlayerRender{

	public GyoAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxPassive(40 * 20);
	}
	@Override
	public String getId() {
		return "gyo";
	}

	@Override
	public void onEndPassive(LivingEntity p) {
		if(this.getPassiveTimer() == 0) {
			p.addPotionEffect(new EffectInstance(Effects.NAUSEA, 15, 1));
		}
	}
	@Override
	public String getName() {
		return "Gyo";
	}

}
