package com.izako.hunterx.abilities.basic_hatsus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IBlacklistPassive;
import com.izako.hunterx.izapi.ability.IOnPlayerRender;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PassiveAbility;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;

public class SharpenAuraAbility extends PassiveAbility implements ITrainable, IBlacklistPassive, IOnPlayerRender {

	public SharpenAuraAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxPassive(Integer.MAX_VALUE).setNenType(NenType.TRANSMUTER).setMaxCooldown(4 * 20);
	}
	public static final UUID attackModID = UUID.fromString("716bf50a-ee28-4814-83e2-5db7539d25b9");
	@Override
	public String getId() {
		return "sharpen_aura";
	}

	@Override
	public String getName() {
		return "Sharpen Aura";
	}

	@Override
	public String getDesc() {
		return "Transmute your aura and make it sharper to make your enemies bleed in pain.";
	}

	@Override
	public void onStartPassive(LivingEntity p) {
		AttributeModifier mod = new AttributeModifier(attackModID, "attackmodsharpen", Helper.getTrueValue(8, this, p), Operation.ADDITION);
		
		p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(mod);
		
	}

	@Override
	public void duringPassive(LivingEntity p) {

		if(p.ticksExisted % 20 == 0) {
			Helper.consumeAura(1, p, this);

		}
		
	}

	@Override
	public void onEndPassive(LivingEntity p) {
		p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(attackModID);
	}

	@Override
	public List<Ability> getBlackList() {
		return new ArrayList<>(Arrays.asList(ModAbilities.ZETSU_ABILITY));
	}
	@Override
	public void setXp(double xp, LivingEntity p) {
		super.setXp(xp, p);
		
		IHunterData data = HunterDataCapability.get(p);
		if(data.hasQuest(ModQuests.BASIC_TRANSMUTER) && data.getQuest(ModQuests.BASIC_TRANSMUTER).getProgress() <100) {
			data.getQuest(ModQuests.BASIC_TRANSMUTER).setProgress((xp + 50) * 2);
		}
	}

}
