package com.izako.hunterx.izapi.ability;

public interface ITrainable {

	default double getCooldownScale() {
		return 0.9;
	}
	default double getPowerScale() {
		return 1.1;
	}
	default double getAuraConsumptionScale() {
		return 0.5;
	}
	default double getXPOnUsage() {
		return 0.4;
	}
	
	default double getMaxXP() {
		return 100;
	}
}
