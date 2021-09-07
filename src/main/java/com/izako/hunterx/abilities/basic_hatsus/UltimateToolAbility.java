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
import com.izako.hunterx.izapi.ability.Ability.AuraConsumptionType;
import com.izako.hunterx.izapi.ability.Ability.IAuraConsumption;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Attributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class UltimateToolAbility extends EquipAbility implements ITrainable {

	public UltimateToolAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxPassive(Integer.MAX_VALUE).setNenType(NenType.CONJURER).setMaxCooldown(6 * 20);
	}
	@Override
	public ItemStack createItem(LivingEntity p) {
		ItemStack stack = new ItemStack(ModItems.BASIC_CONJURER_TOOL);
		double speedVal = Helper.fromRangeToRange(0, this.getMaxXP(), 0, 8, this.getXp());

		AttributeModifier mod = new AttributeModifier("attackmod", Helper.getTrueValue((float) 14, this, p), Operation.ADDITION);
		AttributeModifier speedMod = new AttributeModifier("speedmod", Helper.getTrueValue((float)speedVal, this, p), Operation.ADDITION);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), mod, null);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED.getName(), speedMod, null);

		Helper.consumeAura(40, p, this);

		return stack;
	}

	@Override
	public void duringPassive(LivingEntity p) {
		super.duringPassive(p);
		
		if(p.ticksExisted % 20 == 0) {
			Helper.consumeAura(1, p, this);
		}
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
