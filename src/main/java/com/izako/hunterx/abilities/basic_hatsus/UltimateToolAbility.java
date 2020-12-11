package com.izako.hunterx.abilities.basic_hatsus;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModItems;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.EquipAbility;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.item.ItemStack;

public class UltimateToolAbility extends EquipAbility implements ITrainable {

	public UltimateToolAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxPassive(Integer.MAX_VALUE).setNenType(NenType.CONJURER);
	}
	@Override
	public ItemStack createItem(LivingEntity p) {
		ItemStack stack = new ItemStack(ModItems.BASIC_CONJURER_TOOL);
		double val = Helper.fromRangeToRange(0, this.getMaxXP(), 0, 6, this.getXp());
		double speedVal = Helper.fromRangeToRange(0, this.getMaxXP(), 0, 4, this.getXp());

		AttributeModifier mod = new AttributeModifier("attackmod", Helper.getTrueValue((float)val, this, p), Operation.ADDITION);
		AttributeModifier speedMod = new AttributeModifier("speedmod", Helper.getTrueValue((float)speedVal, this, p), Operation.ADDITION);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), mod, null);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED.getName(), speedMod, null);

		return stack;
	}

	@Override
	public String getId() {
		return "ultimate_tool";
	}

	@Override
	public String getName() {
		return "Ultimate Tool";
	}

	@Override
	public String getDesc() {
		return "Create the ultimate most useful tool, 4 in 1!";
	}
	@Override
	public void setXp(double xp, LivingEntity p) {
		super.setXp(xp, p);
		
		IHunterData data = HunterDataCapability.get(p);
		if(data.hasQuest(ModQuests.BASIC_CONJURER) && data.getQuest(ModQuests.BASIC_CONJURER).getProgress() <100) {
			data.getQuest(ModQuests.BASIC_CONJURER).setProgress((xp + 50) * 2);
		}
	}

}
