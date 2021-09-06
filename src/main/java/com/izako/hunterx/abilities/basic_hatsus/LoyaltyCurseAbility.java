package com.izako.hunterx.abilities.basic_hatsus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.entities.goals.wolfgoals.FollowOwnerGoal;
import com.izako.hunterx.entities.goals.wolfgoals.MeleeAttackGoal;
import com.izako.hunterx.entities.goals.wolfgoals.OwnerHurtByTargetGoal;
import com.izako.hunterx.entities.goals.wolfgoals.OwnerHurtTargetGoal;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IBlacklistPassive;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PassiveAbility;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class LoyaltyCurseAbility extends PassiveAbility implements ITrainable, IBlacklistPassive {

	public AnimalEntity animal;
	public FollowOwnerGoal followGoal;
	public OwnerHurtTargetGoal ownerHurtGoal;
	public OwnerHurtByTargetGoal ownerHurtByGoal;
	public MeleeAttackGoal meleeGoal;
	
	public LoyaltyCurseAbility() {
		this.props = new Ability.Properties(this).setMaxPassive(Integer.MAX_VALUE).setAbilityType(AbilityType.PASSIVE).setNenType(NenType.MANIPULATOR).setMaxCooldown(1 * 20);
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

		Optional<Entity> entity = Helper.getTargetEntity(p, 50);
		
		if(!entity.isPresent() || !(entity.get() instanceof AnimalEntity)) {
			if(p.world.isRemote()) {
			p.sendMessage(new StringTextComponent("Not an animal!").applyTextStyle(TextFormatting.RED));
			}
			this.endAbility(p);
			return;
		}
		
		if(!p.world.isRemote()) {
			this.animal = (AnimalEntity) entity.get();
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

		if(p.ticksExisted % 60 == 0) {
			Helper.consumeAura(1, p, this);

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
	@Override
	public List<Ability> getBlackList() {
		return Arrays.asList(ModAbilities.ZETSU_ABILITY);
	}

	
}
