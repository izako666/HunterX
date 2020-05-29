package com.izako.hunterx.abilities.basics;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.PassiveAbility;

import net.minecraft.entity.player.PlayerEntity;

public class ZetsuAbility extends PassiveAbility{

	public ZetsuAbility() {

		super();
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxCooldown(10).setMaxPassive(Integer.MAX_VALUE);
	}

	@Override
	public String getId() {
		return "zetsu";
	}

	@Override
	public String getName() {
		return "Zetsu";
	}


	@Override
	public void duringPassive(PlayerEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		data.setCurrentNen(data.getCurrentNen() - data.getNenCapacity() / 100);
	}

}
