package com.izako.hunterx.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.izako.hunterx.Main;
import com.izako.hunterx.entities.AI.GunAI;
import com.izako.hunterx.init.ModItems;
import com.izako.hunterx.items.entities.BulletEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class ThugEntity extends ZombieEntity implements IRangedAttackMob{

	@SuppressWarnings("unchecked")
	public static EntityType<ThugEntity> type = (EntityType<ThugEntity>) EntityType.Builder
			.<ThugEntity>create(ThugEntity::new, EntityClassification.MONSTER).setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true).size(1f, 2f).setUpdateInterval(1).build("thug")
			.setRegistryName(Main.MODID, "thug");


	private static final DataParameter<Integer> TEXTURE_ID = EntityDataManager.createKey(ThugEntity.class, DataSerializers.VARINT);
	public ThugEntity(EntityType<? extends ThugEntity> type, World worldIn) {
		super(type, worldIn);
		;

	}

	@Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		 
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

		this.goalSelector.addGoal(1, new GunAI(this, 1, 60, 50));
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
	protected void registerData() {
		super.registerData();
		this.getDataManager().register(TEXTURE_ID, 0);
	}
	@Override
	public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {

		return this.world.getDifficulty() != Difficulty.PEACEFUL;
	}
	@Override
	public void writeAdditional(CompoundNBT nbt)
	{
		super.writeAdditional(nbt);
		nbt.putInt("texture", this.getTextureId());
	}

	@Override
	public void readAdditional(CompoundNBT nbt)
	{
		super.readAdditional(nbt);
		this.setTextureId(nbt.getInt("texture"));
	}

	public int getTextureId() {
		return this.getDataManager().get(TEXTURE_ID);
	}

	public void setTextureId(int val) {
		this.getDataManager().set(TEXTURE_ID, val);
	}
	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) 
	{
		spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
		
		int randVal = rand.nextInt(3);
		
		this.setTextureId(randVal);
		
		if(this.getTextureId() == 1) {
			this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.PISTOL));
		}
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
		// TODO Auto-generated method stub

		super.swingArm(hand);
	}

	private static int randomWithRange(int min, int max) {
		return new Random().nextInt(max + 1 - min) + min;
	}

	private boolean canBeDropped(Item item) {
		return item != ModItems.BADGE && item != ModItems.CARD && item != ModItems.HANZO_SWORD && item != ModItems.HUNTER_LICENSE && item != ModItems.KIRIKO_EGG && item != ModItems.PISTOL && item != ModItems.THUG_EGG && item != ModItems.WING_EGG;
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

		if (chance <= 3) {
			int items = ITEMS.size() - 1;
			ItemStack dropItem = new ItemStack(ITEMS.get(randomWithRange(1, items)));

			if (this.canBeDropped(dropItem.getItem())) {
				this.entityDropItem(dropItem, 1);
			}
		}
		super.onDeath(cause);
	}

	@Override
	public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
		// TODO Auto-generated method stub
		
		BulletEntity bullet = new BulletEntity(BulletEntity.type, this, world);
       
        double d0 = target.posX - this.posX;
        double d1 = target.getBoundingBox().minY + (double)(target.getHeight() / 3.0F) - bullet.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        bullet.shoot(d0, d1 ,d2, 4F, 0);
        this.world.addEntity(bullet);
		
	}



}
