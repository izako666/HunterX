package com.izako.hunterx.abilities.basics;

import java.util.UUID;

import com.izako.hunterx.izapi.ability.IOnPlayerRender;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.PassiveAbility;

public class ShuAbility extends PassiveAbility implements IOnPlayerRender, ITrainable {

	public static final UUID SHU_UUID = UUID.fromString("b2bb389e-a2a9-4c42-85d2-0010c09901fc");

	
	@Override
	public String getId() {
		return "shu";
	}

	@Override
	public String getName() {
		return "Shu";
	}

	public double getEfficiencyModifier() {
		return this.getCurrentPowerScale();
	}

	@Override
	public double getPowerScale() {
		return 2.5;
	}
}
