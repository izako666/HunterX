package com.izako.hunterx.abilities.basics;

import java.util.UUID;

import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IOnPlayerRender;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.PassiveAbility;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;

public class KenAbility extends PassiveAbility implements IOnPlayerRender,ITrainable {
	public static final UUID attackModifierID = UUID.fromString("ee8542a1-beff-4fb5-a264-1f5c9c338840");
	public static final UUID defenseModifierID = UUID.fromString("9dd5b8a2-a138-45d8-b245-ff0a6e709dfb");

	public KenAbility() {
		super();
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE)
				.setConsumptionType(AuraConsumptionType.PERCENTAGE).setAuraConsumption(this::auraConsumptionEvent)
				.setMaxPassive(Integer.MAX_VALUE).setMaxCooldown(20 * 15);
	}

	@Override
	public String getId() {
		return "ken";
	}

	@Override
	public String getName() {
		return "Ken";
	}

	public void onStartPassive(LivingEntity p) {
		AttributeModifier defenseMod = new AttributeModifier(defenseModifierID, "kenmodifier",
				30 * this.getCurrentPowerScale(), Operation.ADDITION);
		AttributeModifier attackMod = new AttributeModifier(attackModifierID, "kenmodifierattack",
				30 * this.getCurrentPowerScale(), Operation.ADDITION);
		if (p.getAttribute(SharedMonsterAttributes.ARMOR).getModifier(defenseModifierID) == null) {
			p.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(defenseMod);
		}
		if (p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(attackModifierID) == null) {
			p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(attackMod);
		}
	}

	@Override
	public void duringPassive(LivingEntity p) {
		AttributeModifier defenseMod = new AttributeModifier(defenseModifierID, "kenmodifier",
				30 * this.getCurrentPowerScale(), Operation.ADDITION);
		AttributeModifier attackMod = new AttributeModifier(attackModifierID, "kenmodifierattack",
				30 * this.getCurrentPowerScale(), Operation.ADDITION);
		if (p.getAttribute(SharedMonsterAttributes.ARMOR).getModifier(defenseModifierID) == null) {
			p.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(defenseMod);
		}
		if (p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(attackModifierID) == null) {
			p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(attackMod);
		}


	}

	@Override
	public void onEndPassive(LivingEntity p) {
		p.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier(defenseModifierID);
		p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(attackModifierID);

	}

	private int auraConsumptionEvent() {
		if (this.getPassiveTimer() % 20 == 0) {
			return (int) (10 * this.getCurrentAuraConScale());
		}
		return 0;
	}

}
