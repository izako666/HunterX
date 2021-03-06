package com.izako.hunterx.abilities.basics;

import java.util.UUID;

import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IOnPlayerRender;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PassiveAbility;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;

public class RyuOffenseAbility extends PassiveAbility implements ITrainable, IOnPlayerRender {

	public static final UUID attackModifierID = UUID.fromString("4df81c69-0f33-4edf-9282-e1497c28fb1e");
	public static final UUID defenseModifierID = UUID.fromString("045c0a8e-04f4-495e-a396-a2c3a5bfcb6f");

	public RyuOffenseAbility() {
		super();
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE)
				.setConsumptionType(AuraConsumptionType.PERCENTAGE).setAuraConsumption(this::auraConsumptionEvent)
				.setMaxPassive(Integer.MAX_VALUE).setMaxCooldown(20 * 15).setNenType(NenType.UNKNOWN);
	}

	@Override
	public String getId() {
		return "ryu_offense";
	}

	@Override
	public String getName() {
		return "Ryu: Offense";
	}

	@Override
	public String getDesc() {
		return "Ryu: Offense, a variant of Ryu, focuses your aura on your attack and the rest on defense.";
	}
	public void onStartPassive(LivingEntity p) {
		AttributeModifier defenseMod = new AttributeModifier(defenseModifierID, "ryuoffensemod",
				Helper.getTrueValue(15, this, p), Operation.ADDITION);
		AttributeModifier attackMod = new AttributeModifier(attackModifierID, "ryuoffensemodattack",
				Helper.getTrueValue(30, this, p), Operation.ADDITION);
		if (p.getAttribute(SharedMonsterAttributes.ARMOR).getModifier(defenseModifierID) == null) {
			p.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(defenseMod);
		}
		if (p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(attackModifierID) == null) {
			p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(attackMod);
		}
	}

	@Override
	public void duringPassive(LivingEntity p) {
		AttributeModifier defenseMod = new AttributeModifier(defenseModifierID, "ryuoffensemod",
				Helper.getTrueValue(15, this, p), Operation.ADDITION);
		AttributeModifier attackMod = new AttributeModifier(attackModifierID, "ryuoffensemodattack",
				Helper.getTrueValue(30, this, p), Operation.ADDITION);
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
