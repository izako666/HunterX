package com.izako.hunterx.entities.goals;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class WingMeleeGoal extends MeleeAttackGoal {

	public WingMeleeGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
	}

	@Override
	public void tick() {

		if(this.attacker.getAttackTarget() == null)
			return;
		
		
		super.tick();
	}

}
