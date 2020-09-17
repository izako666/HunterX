package com.izako.hunterx.events.custom;

import com.izako.hunterx.izapi.ability.Ability;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

public class AbilityGiveEvent extends Event {

	LivingEntity p;
	Ability abl;

	public AbilityGiveEvent(Ability abl, LivingEntity p) {
		this.p = p;
		this.abl = abl;
	}

	public LivingEntity getPlayer() {
		return this.p;
	}

	public Ability getAbility() {

		return this.abl;
	}
}
