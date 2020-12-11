package com.izako.hunterx.entities.goals;

import com.izako.hunterx.abilities.basics.RenAbility;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;

public class UseRenGoal extends Goal {

	CreatureEntity attacker;

	public UseRenGoal(CreatureEntity attacker) {
		this.attacker = attacker;
	}

	@Override
	public boolean shouldExecute() {
		return attacker.getAttackTarget() != null;
	}

	@Override
	public boolean shouldContinueExecuting() {
		IAbilityData data = AbilityDataCapability.get(attacker);
		return attacker.getAttackTarget() != null || attacker.getRevengeTarget() != null;
	}

	@Override
	public void startExecuting() {
		IAbilityData data = AbilityDataCapability.get(attacker);
		RenAbility ren = (RenAbility) data.getAbility(ModAbilities.REN_ABILITY);
		ren.onUse(attacker);
	}

	@Override
	public void resetTask() {
		IAbilityData data = AbilityDataCapability.get(attacker);
		data.getAbility(ModAbilities.REN_ABILITY).onUse(attacker);
	}

}
