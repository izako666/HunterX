package com.izako.hunterx.abilities.basic_hatsus;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.entities.goals.wolfgoals.FollowOwnerGoal;
import com.izako.hunterx.entities.goals.wolfgoals.MeleeAttackGoal;
import com.izako.hunterx.entities.goals.wolfgoals.OwnerHurtByTargetGoal;
import com.izako.hunterx.entities.goals.wolfgoals.OwnerHurtTargetGoal;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PassiveAbility;
import com.izako.wypi.WyHelper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class LoyaltyCurseAbility extends PassiveAbility implements ITrainable {

	AnimalEntity animal;
	FollowOwnerGoal followGoal;
	OwnerHurtTargetGoal ownerHurtGoal;
	OwnerHurtByTargetGoal ownerHurtByGoal;
	MeleeAttackGoal meleeGoal;
	
	public LoyaltyCurseAbility() {
		this.props = new Ability.Properties(this).setMaxPassive(Integer.MAX_VALUE).setAbilityType(AbilityType.PASSIVE).setNenType(NenType.MANIPULATOR);
	}
	@Override
	public String getId() {
		return "loyalty_curse";
	}

	@Override
	public String getName() {
		return "Loyalty Curse";
	}

	@Override
	public String getDesc() {
		return "Makes any animal you cast it on fight for you. Only one at a time.";
	}

	@Override
	public void onStartPassive(LivingEntity p) {
		
		EntityRayTraceResult ray = WyHelper.rayTraceEntities(p, 100,p);
		
		if(!(ray.getEntity() instanceof AnimalEntity)) {
			if(p.world.isRemote()) {
			p.sendMessage(new StringTextComponent("Not an animal!").applyTextStyle(TextFormatting.RED));
			}
			this.endAbility(p);
			return;
		}
		
		System.out.println(ray.getEntity());
		if(!p.world.isRemote()) {
			this.animal = (AnimalEntity) ray.getEntity();
            this.followGoal = new FollowOwnerGoal(this.animal, 1.0D, 10.0F, 2.0F, false,p);
            this.ownerHurtGoal = new OwnerHurtTargetGoal(this.animal, p);
            this.ownerHurtByGoal = new OwnerHurtByTargetGoal(this.animal, p);
            this.meleeGoal = new MeleeAttackGoal(animal, 1.0D, true, p);
			this.animal.goalSelector.addGoal(1, this.followGoal);
		    this.animal.goalSelector.addGoal(0, this.ownerHurtByGoal);
		    this.animal.goalSelector.addGoal(0, this.ownerHurtGoal);
		    this.animal.goalSelector.addGoal(4, this.meleeGoal);

		}
		
		Helper.consumeAura(40, p, this);
		super.onStartPassive(p);
	}

	@Override
	public void duringPassive(LivingEntity p) {
		if(this.getPassiveTimer() > (Integer.MAX_VALUE - Helper.getTrueValue(100 * 20, this, p)) && Helper.getTrueValue(100 * 20, this,p) < 100 * 20) {
			this.endAbility(p);
		}
	}
	@Override
	public void onEndPassive(LivingEntity p) {
		
		if(this.animal != null && !p.world.isRemote()) {
			this.animal.goalSelector.removeGoal(this.followGoal);
			this.animal.goalSelector.removeGoal(this.ownerHurtGoal);
			this.animal.goalSelector.removeGoal(this.ownerHurtByGoal);
			this.animal.goalSelector.removeGoal(this.meleeGoal);

		}
		super.onEndPassive(p);
	}
	@Override
	public void setXp(double xp, LivingEntity p) {
		super.setXp(xp, p);
		
		IHunterData data = HunterDataCapability.get(p);
		if(data.hasQuest(ModQuests.BASIC_MANIPULATOR) && data.getQuest(ModQuests.BASIC_MANIPULATOR).getProgress() <100) {
			data.getQuest(ModQuests.BASIC_MANIPULATOR).setProgress((xp + 50) * 2);
		}
	}

}
