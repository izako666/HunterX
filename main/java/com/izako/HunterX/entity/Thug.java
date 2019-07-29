package com.izako.HunterX.entity;

import com.izako.HunterX.entity.AI.RangedGunAI;
import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.items.entities.EntityBullet;
import com.izako.HunterX.items.entities.EntityCard;
import com.mojang.authlib.GameProfile;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIZombieAttack;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerInteractionManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Thug extends EntityZombie implements IRangedAttackMob {
	
	private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.<Boolean>createKey(AbstractSkeleton.class, DataSerializers.BOOLEAN);
	
	
	public Thug(World worldIn) {
		super(worldIn);

	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		
		
		
		
	}

	protected void initEntityAI() {
		
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new RangedGunAI(this, 0.4D, 40, 40.0F));
		this.tasks.addTask(2, new EntityAIZombieAttack(this, 1.0D, false));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.applyEntityAI();
	}

	protected void applyEntityAI() {
		this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] { EntityPigZombie.class }));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));

	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
	}

	@Override
	protected boolean isValidLightLevel() {

		return true;
	}

	@Override
	public boolean getCanSpawnHere() {

		return this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
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

		return super.getAmbientSound();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {

		return super.getHurtSound(source);
	}

	@Override
	protected SoundEvent getDeathSound() {

		return super.getDeathSound();
	}
	
	 

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		
		 EntityBullet bullet = new EntityBullet(this.world, this);
	        double d0 = target.posX - this.posX;
	        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - bullet.posY;
	        double d2 = target.posZ - this.posZ;
	        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
	        bullet.shoot(d0, d1 + d3 *0.05, d2, 4F, 0);
	        
	        this.world.spawnEntity(bullet);
		
	}
	
	@SideOnly(Side.CLIENT)
    public boolean isSwingingArms()
    {
        return ((Boolean)this.dataManager.get(SWINGING_ARMS)).booleanValue();
    }

	@Override
	public void setSwingingArms(boolean swingingArms) {
		this.dataManager.set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
		
	}

}
