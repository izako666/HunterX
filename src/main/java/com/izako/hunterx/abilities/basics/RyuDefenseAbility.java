package com.izako.hunterx.abilities.basics;

import java.util.UUID;

import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.PassiveAbility;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;

public class RyuDefenseAbility extends PassiveAbility {

	public static final UUID attackModifierID = UUID.fromString("387d6c90-1b2f-4c1b-9d99-0f4777a42d34");
	public static final UUID defenseModifierID = UUID.fromString("ca80dd72-7fef-4f41-9331-879d312eb550");

	public RyuDefenseAbility() {
		super();
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE)
				.setConsumptionType(AuraConsumptionType.PERCENTAGE).setAuraConsumption(this::auraConsumptionEvent)
				.setMaxPassive(Integer.MAX_VALUE).setMaxCooldown(20 * 15);
	}

	@Override
	public String getId() {
		return "ryu_defense";
	}

	@Override
	public String getName() {
		return "Ryu: Defense";
	}

	public void onStartPassive(LivingEntity p) {
		AttributeModifier defenseMod = new AttributeModifier(defenseModifierID, "ryudefensemod",
				40 * this.getCurrentPowerScale(), Operation.ADDITION);
		AttributeModifier attackMod = new AttributeModifier(attackModifierID, "ryudefensemodattack",
				20 * this.getCurrentPowerScale(), Operation.ADDITION);
		if (p.getAttribute(SharedMonsterAttributes.ARMOR).getModifier(defenseModifierID) == null) {
			p.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(defenseMod);
		}
		if (p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(attackModifierID) == null) {
			p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(attackMod);
		}
	}

	@Override
	public void duringPassive(LivingEntity p) {
		AttributeModifier defenseMod = new AttributeModifier(defenseModifierID, "ryudefensemod",
				40 * this.getCurrentPowerScale(), Operation.ADDITION);
		AttributeModifier attackMod = new AttributeModifier(attackModifierID, "ryudefensemodattack",
				20 * this.getCurrentPowerScale(), Operation.ADDITION);
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
			return (int) (6 * this.getCurrentAuraConScale());
		}
		return 0;
	}

}
