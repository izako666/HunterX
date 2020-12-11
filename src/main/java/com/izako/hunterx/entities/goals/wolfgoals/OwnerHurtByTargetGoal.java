package com.izako.hunterx.entities.goals.wolfgoals;

import java.util.EnumSet;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;

public class OwnerHurtByTargetGoal extends TargetGoal {

	 private final AnimalEntity animal;
	   private LivingEntity attacker;
	   private int timestamp;
	   
	   private LivingEntity owner;

	   public OwnerHurtByTargetGoal(AnimalEntity defender, LivingEntity owner) {
	      super(defender, false);
	      this.animal = defender;
	      this.owner = owner;
	      this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
	   }

	   /**
	    * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
	    * method as well.
	    */
	   public boolean shouldExecute() {
	         LivingEntity livingentity = this.owner;
	         if (livingentity == null) {
	            return false;
	         } else {
	            this.attacker = livingentity.getRevengeTarget();
	            int i = livingentity.getRevengeTimer();
	            return i != this.timestamp && this.isSuitableTarget(this.attacker, EntityPredicate.DEFAULT);
	         }
	      
	   }

	   /**
	    * Execute a one shot task or start executing a continuous task
	    */
	   public void startExecuting() {
	      this.goalOwner.setAttackTarget(this.attacker);
	      LivingEntity livingentity = this.owner;
	      if (livingentity != null) {
	         this.timestamp = livingentity.getRevengeTimer();
	      }

	      super.startExecuting();
	   }
}
