package com.izako.hunterx.abilities.basics;

import java.util.Arrays;
import java.util.List;

import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IBlacklistPassive;
import com.izako.hunterx.izapi.ability.IOnPlayerRender;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PassiveAbility;

public class InAbility extends PassiveAbility implements IBlacklistPassive, IOnPlayerRender {

	public InAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxPassive(20 * 15).setNenType(NenType.UNKNOWN);
	}
	@Override
	public String getId() {
		return "in";
	}

	@Override
	public String getName() {
		return "In";
	}
	@Override
	public List<Ability> getBlackList() {
	
		return Arrays.asList(ModAbilities.ZETSU_ABILITY);
	}
	
	@Override
	public String getDesc() {
		return "In hides your aura from anyone not using gyo.";
	}

}
