package com.izako.hunterx.events.custom;

import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.Ability.AbilityType;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

public class AbilityActivateEvent extends Event{

	private Ability abl;
	private LivingEntity p;
	public AbilityActivateEvent(Ability ability, LivingEntity p) {
		this.abl = ability;
		this.p = p;
	}
	
	public LivingEntity getEntity() {
		return p;
	}
	
	public Ability getAbility() {
		return abl;
	}
	
	public AbilityType getType() {
		return abl.props.type;
	}
}
