package com.izako.hunterx.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModItems;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class ThugEntity extends ZombieEntity {

	@SuppressWarnings("unchecked")
	public static EntityType<ThugEntity> type = (EntityType<ThugEntity>) EntityType.Builder
			.<ThugEntity>create(ThugEntity::new, EntityClassification.MONSTER).setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true).size(1f, 2f).setUpdateInterval(1).build("thug")
			.setRegistryName(Main.MODID, "thug");

	public ThugEntity(EntityType<? extends ThugEntity> type, World worldIn) {
		super(type, worldIn);

	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new MeleeAttackGoal(this, 1f, true));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
	}

	@Override
	public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {

		return this.world.getDifficulty() != Difficulty.PEACEFUL;
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
		// TODO Auto-generated method stub

		super.swingArm(hand);
	}

	private static int randomWithRange(int min, int max) {
		return new Random().nextInt(max + 1 - min) + min;
	}

	@Override
	public void onDeath(DamageSource cause) {
		List<Item> ITEMS = new ArrayList<Item>();
		ForgeRegistries.ITEMS.getValues().forEach((i) -> {
			if (i.getRegistryName().getNamespace().contains("hntrx")) {

				ITEMS.add(i);

			}
		});
		int chance = randomWithRange(0, 10);

		if (chance <= 2) {
			int items = ITEMS.size() - 1;
			ItemStack dropItem = new ItemStack(ITEMS.get(randomWithRange(1, items)));

			if (!(dropItem.getItem() == ModItems.THUG_EGG)) {
				this.entityDropItem(dropItem, 1);
			}
		}
		super.onDeath(cause);
	}



}
