package com.izako.hunterx.entities.AI;

import java.util.EnumSet;

import com.izako.hunterx.init.ModItems;

import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.inventory.EquipmentSlotType;

public class GunAI extends RangedAttackGoal{

	
	 /** The entity the AI instance has been applied to */
   private final MobEntity entityHost;
   /** The entity (as a RangedAttackMob) the AI instance has been applied to. */
   private final IRangedAttackMob rangedAttackEntityHost;
   private LivingEntity attackTarget;
   /**
    * A decrementing tick that spawns a ranged attack once this value reaches 0. It is then set back to the
    * maxRangedAttackTime.
    */
   private int rangedAttackTime;
   private final double entityMoveSpeed;
   private int seeTime;
   private final int attackIntervalMin;
   /** The maximum time the AI has to wait before peforming another ranged attack. */
   private final int maxRangedAttackTime;
   private final float attackRadius;
   private final float maxAttackDistance;

	public GunAI(IRangedAttackMob attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn) {
		this(attacker, movespeed, maxAttackTime, maxAttackTime, maxAttackDistanceIn);
		
	}
	
	public GunAI(IRangedAttackMob attacker, double movespeed, int p_i1650_4_, int maxAttackTime, float maxAttackDistanceIn)
   {
		super(attacker, movespeed, maxAttackTime, maxAttackDistanceIn);
		
       this.rangedAttackTime = -1;

       if (!(attacker instanceof MobEntity))
       {
           throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
       }
       else
       {
           this.rangedAttackEntityHost = attacker;
           this.entityHost = (MobEntity) attacker;
           this.entityMoveSpeed = movespeed;
           this.attackIntervalMin = p_i1650_4_;
           this.maxRangedAttackTime = maxAttackTime;
           this.attackRadius = maxAttackDistanceIn;
           this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
           this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE));
       }
   }
	
	@Override
	public boolean shouldContinueExecuting() {
		
		if(this.entityHost.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() != ModItems.PISTOL) {
			return false;
		}
		
		LivingEntity entitylivingbase = this.entityHost.getAttackTarget();

       if (entitylivingbase == null)
       {
           return false;
       }
       else
       {
           this.attackTarget = entitylivingbase;
           
           
           double disX = this.attackTarget.posX - this.entityHost.posX;
   		double disZ = this.attackTarget.posZ - this.entityHost.posZ;
   		
   		if(disX <0) {
   			disX = -disX;
   		}
   		
   		if(disZ <0) {
   			disZ = -disZ;
   		}
   		double distance = Math.sqrt(disX*disX + disZ*disZ);
   		
   		if(distance <= 10) {
   			return false;
   		}
   		
           return true;
       }
		
		
		
		
		
	}
}
