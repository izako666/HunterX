package com.izako.hunterx.abilities.basic_hatsus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IBlacklistPassive;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PassiveAbility;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class StatBoostAbility extends PassiveAbility implements ITrainable, IBlacklistPassive {

	public static final UUID ATTACK = UUID.fromString("3eb3e20b-b5c0-474d-a7b2-b6ea3fb0a9ac");
	public static final UUID DEFENSE = UUID.fromString("6e085274-52f9-42c1-a3f7-1688d201464f");
	
	public StatBoostAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxPassive(Integer.MAX_VALUE).setNenType(NenType.ENHANCER);
	}
	@Override
	public String getId() {
		return "stat_boost";
	}

	@Override
	public String getName() {
		return "Aura Enhancement";
	}
	@Override
	public String getDesc() {
		return "Aura Enhancement as the name suggests enhances your aura which gives you fortitude and strength in exchange for aura usage.";
	}
	@Override
	public void onStartPassive(LivingEntity p) {

		p.sendMessage(new StringTextComponent("You're aura got stronger.").applyTextStyle(TextFormatting.RED));
		AttributeModifier aMod = new AttributeModifier(ATTACK, "statboost_attack", Helper.getTrueValue(10, this, p), Operation.ADDITION);
		AttributeModifier dMod = new AttributeModifier(DEFENSE, "statboost_defense", Helper.getTrueValue(15, this, p), Operation.ADDITION);
		try {
		p.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(dMod);
		p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(aMod);
		} catch(Exception e) {
			e.printStackTrace();
		}
		super.onStartPassive(p);
	}

	@Override
	public void duringPassive(LivingEntity p) {
		super.duringPassive(p);
	}

	@Override
	public void onEndPassive(LivingEntity p) {
		
		p.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier(DEFENSE);
		p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(ATTACK);

		super.onEndPassive(p);
	}

	@Override
	public double getPowerScale() {
		return 1.5d;
	}

	@Override
	public List<Ability> getBlackList() {
		return new ArrayList<>();
	}
	@Override
	public void setXp(double xp, LivingEntity p) {
		super.setXp(xp, p);
		
		IHunterData data = HunterDataCapability.get(p);
		if(data.hasQuest(ModQuests.BASIC_ENHANCER) && data.getQuest(ModQuests.BASIC_ENHANCER).getProgress() <100) {
			data.getQuest(ModQuests.BASIC_ENHANCER).setProgress((this.getXp() + 50) * 2);
		}
	}

}
