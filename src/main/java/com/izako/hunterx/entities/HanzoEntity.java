package com.izako.hunterx.entities;

import javax.annotation.Nullable;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.entities.goals.hanzo.DartThrowGoal;
import com.izako.hunterx.entities.goals.hanzo.DashGoal;
import com.izako.hunterx.entities.goals.hanzo.ShurikenThrowGoal;
import com.izako.hunterx.init.ModItems;
import com.izako.hunterx.init.ModQuests;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class HanzoEntity extends ZombieEntity{

	PlayerEntity source;
	public HanzoEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public HanzoEntity(EntityType<? extends ZombieEntity> type, World worldIn,PlayerEntity p) {
		super(type, worldIn);
		this.source = p;		
	}



	@SuppressWarnings("unchecked")
	public static EntityType<HanzoEntity> TYPE = (EntityType<HanzoEntity>) EntityType.Builder
			.<HanzoEntity>create(HanzoEntity::new, EntityClassification.MONSTER).setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true).size(1f, 2f).setUpdateInterval(1).build("hanzo")
			.setRegistryName(Main.MODID, "hanzo");


	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, true));

		this.goalSelector.addGoal(1, new ShurikenThrowGoal(this));
		this.goalSelector.addGoal(1, new DashGoal(this));
		this.goalSelector.addGoal(2, new DartThrowGoal(this));


	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);
	}


	@Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		 if(difficulty.getAdditionalDifficulty() == Difficulty.HARD.getId()) {
			 this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(ModItems.HANZO_CHESTPLATE));
			 this.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(ModItems.HANZO_BOOTS));
			 this.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(ModItems.HANZO_LEGGINGS));

		 }
		   }
	@Override
	public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {

		return true;
	}

	@Override
	public void tick() {
		if(this.isAggressive()) {
			this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.HANZO_SWORD));
		}
		if(!this.isAggressive()) {
			if(this.hasItemInSlot(EquipmentSlotType.MAINHAND)) {
				this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.AIR));
			}
		}
		super.tick();
	}
	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) 
	{
		spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
		
		this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(ModItems.HANZO_CHESTPLATE));
		this.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(ModItems.HANZO_LEGGINGS));
		this.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(ModItems.HANZO_BOOTS));
		
		return spawnData;
	}

	@Override
	protected boolean shouldBurnInDay() {
		return false;
	}

	@Override
	public void setBreakDoorsAItask(boolean enabled) {

	}

	@Override
	public void setChild(boolean childZombie) {

	}

	@Override
	protected ResourceLocation getLootTable() {

		return null;
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
	public void swingArm(Hand hand) {

		super.swingArm(hand);
	}

	@Override
	public void onDeath(DamageSource cause) {
	
		if(cause.getTrueSource() instanceof PlayerEntity) {
			
			PlayerEntity p = (PlayerEntity) cause.getTrueSource();
			IHunterData data = HunterDataCapability.get(p);
		if(data.hasQuest(ModQuests.HUNTEREXAM04) && !data.getQuest(ModQuests.HUNTEREXAM04).isFinished()) {

			data.getQuest(ModQuests.HUNTEREXAM04).setProgress(100);
			
			this.entityDropItem(new ItemStack(ModItems.BADGE));
		}
		}
		if(!cause.getTrueSource().isEntityEqual(source) && !source.world.isRemote()) {
			IHunterData sourceData = HunterDataCapability.get(source);
			sourceData.getQuest(ModQuests.HUNTEREXAM04).removeQuest(source);
		}
		
		super.onDeath(cause);
	}



}
