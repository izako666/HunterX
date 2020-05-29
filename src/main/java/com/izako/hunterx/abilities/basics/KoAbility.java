package com.izako.hunterx.abilities.basics;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IHandOverlay;
import com.izako.hunterx.izapi.ability.PunchAbility;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class KoAbility extends PunchAbility implements IHandOverlay{

	private boolean queuedAuraConsumption = false;
	public KoAbility() {
		this.props = new Ability.Properties(this).setConsumptionType(AuraConsumptionType.PERCENTAGE).setAuraConsumption(this::consumeAura).setAbilityType(AbilityType.PASSIVE).setMaxPassive(Integer.MAX_VALUE);
	}
	@Override
	public String getId() {
		return "ko";
	}

	@Override
	public String getName() {
		return "Ko";
	}

	@Override
	public float onPunch(PlayerEntity p, LivingEntity target) {
		IAbilityData data = AbilityDataCapability.get(p);
		this.queuedAuraConsumption = true;
		return (data.getNenCapacity() * 0.5f) / 5;
	}

	private int consumeAura() {
		if(queuedAuraConsumption) {
			queuedAuraConsumption = false;
			return 5;
		}
		return 0;
	}
}
