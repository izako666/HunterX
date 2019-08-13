package com.izako.HunterX.entity.AI;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRanged;

public class RangedGunAI extends EntityAIAttackRanged {

	/** The entity the AI instance has been applied to */
    private final EntityLiving entityHost;
    /** The entity (as a RangedAttackMob) the AI instance has been applied to. */
    private final IRangedAttackMob rangedAttackEntityHost;
    private EntityLivingBase attackTarget;
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

	public RangedGunAI(IRangedAttackMob attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn) {
		this(attacker, movespeed, maxAttackTime, maxAttackTime, maxAttackDistanceIn);
		
	}
	
	public RangedGunAI(IRangedAttackMob attacker, double movespeed, int p_i1650_4_, int maxAttackTime, float maxAttackDistanceIn)
    {
		
		super(attacker, movespeed, p_i1650_4_, maxAttackTime, maxAttackDistanceIn);
        this.rangedAttackTime = -1;

        if (!(attacker instanceof EntityLivingBase))
        {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        }
        else
        {
            this.rangedAttackEntityHost = attacker;
            this.entityHost = (EntityLiving)attacker;
            this.entityMoveSpeed = movespeed;
            this.attackIntervalMin = p_i1650_4_;
            this.maxRangedAttackTime = maxAttackTime;
            this.attackRadius = maxAttackDistanceIn;
            this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
            this.setMutexBits(3);
        }
    }
	
	
	
	 
	
	@Override
	public boolean shouldExecute() {
		
		EntityLivingBase entitylivingbase = this.entityHost.getAttackTarget();

        if (entitylivingbase == null)
        {
            return false;
        }
        else
        {
        	this.attackTarget = entitylivingbase;
		
		double disx = entityHost.posX - attackTarget.posX; 
		double disz =entityHost.posZ - attackTarget.posZ;

		if(disx < 0) {
			   disx = disx*-1;
		}

		if(disz < 0) {
			  disz = disz*-1;
		}
		 
		if(disz+disx <= 10) {
			   return false;
		} else {
			return super.shouldExecute();
		}
		
        }
		
		
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		
		
		return super.shouldContinueExecuting();
	}

}

