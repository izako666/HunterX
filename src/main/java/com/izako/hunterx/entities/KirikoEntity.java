package com.izako.hunterx.entities;


import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModQuests;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class KirikoEntity extends ZombieEntity{


	@SuppressWarnings("unchecked")
	public static EntityType<KirikoEntity> TYPE = (EntityType<KirikoEntity>) EntityType.Builder
			.<KirikoEntity>create(KirikoEntity::new, EntityClassification.MONSTER).setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true).size(1.2f, 2.4f).setUpdateInterval(1).build("kiriko")
			.setRegistryName(Main.MODID, "kiriko");

	public KirikoEntity(EntityType<? extends KirikoEntity> type, World worldIn) {
		super(type, worldIn);

	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, true));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
	}

	
	
	@Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		 
		   }

	@Override
	protected ResourceLocation getLootTable() {

		return null;
	}

	@Override
	protected boolean shouldBurnInDay() {
		return false;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {

		return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {

		return null;
	}

	@Override
	protected SoundEvent getDeathSound() {

		return null;
	}

	@Override
	public void onDeath(DamageSource cause) {
	
		if(cause.getTrueSource() instanceof PlayerEntity) {
			PlayerEntity p = (PlayerEntity) cause.getTrueSource();
			IHunterData data = HunterDataCapability.get(p);
			if(data.hasQuest(ModQuests.HUNTEREXAM02) && !data.getQuest(ModQuests.HUNTEREXAM02).isFinished()) {
				data.getQuest(ModQuests.HUNTEREXAM02).setProgress(100);
			}
		}
	}

}
