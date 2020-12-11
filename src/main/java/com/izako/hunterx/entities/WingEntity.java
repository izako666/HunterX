package com.izako.hunterx.entities;

import java.util.List;

import com.izako.hunterx.Main;
import com.izako.hunterx.abilities.basics.InAbility;
import com.izako.hunterx.abilities.basics.RenAbility;
import com.izako.hunterx.abilities.basics.TenAbility;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.entities.goals.UseRenGoal;
import com.izako.hunterx.entities.goals.WingMeleeGoal;
import com.izako.hunterx.izapi.NPCSpeech;
import com.izako.hunterx.izapi.quest.IQuestGiver;
import com.izako.hunterx.quests.speech.WingSpeech;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WingEntity extends CreatureEntity implements IQuestGiver{

	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		
		IAbilityData data = AbilityDataCapability.get(this);
		data.setIsNenUser(true);
		new RenAbility().give(this);
		new TenAbility().give(this);
		new InAbility().give(this);
		data.setNenCapacity(1000);
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@SuppressWarnings("unchecked")
	public static EntityType<WingEntity> TYPE = (EntityType<WingEntity>) EntityType.Builder
			.<WingEntity>create(WingEntity::new, EntityClassification.CREATURE).setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true).size(1f, 2f).setUpdateInterval(1).build("wing")
			.setRegistryName(Main.MODID, "wing");

	protected WingEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(1, new WingMeleeGoal(this, 1.0D, true));
		this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, true));
		this.goalSelector.addGoal(2, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(-1, new UseRenGoal(this));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10);
	}

   @Override
	public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {

	  List<VillagerEntity> list = worldIn.getEntitiesWithinAABB(VillagerEntity.class, new AxisAlignedBB(this.getPosX() - 30, this.getPosY() - 30, this.getPosZ() - 30, this.getPosX() + 30, this.getPosY() + 30, this.getPosZ() + 30));
  
	  if(!list.isEmpty()) {
		  return true;
	  }
	  return false;
   }

	@Override
	public boolean canDespawn(double d) {
		return true;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public NPCSpeech getSpeech() {
		return new WingSpeech();
	}
}
