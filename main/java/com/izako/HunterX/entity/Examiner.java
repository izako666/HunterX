package com.izako.HunterX.entity;

import java.io.Console;
import java.util.Timer;
import java.util.concurrent.DelayQueue;

import com.ibm.icu.text.MessagePattern.Part;
import com.izako.HunterX.init.EntityInit;

import akka.io.Tcp.Command;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandSummon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIZombieAttack;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Chat;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder.Spawn;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Examiner extends EntityZombie {
	int cooldown = 0;
	
	public Examiner(World worldIn) {
		super(worldIn);
		
	}

	@Override
	protected void initEntityAI() {
		 this.tasks.addTask(0, new EntityAISwimming(this));
	        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
	        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
	        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
	        this.tasks.addTask(8, new EntityAILookIdle(this));
	        this.applyEntityAI();
		
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		 this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
	        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
	        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);
	        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(100.0D);
	}
	
	@Override
	protected boolean shouldBurnInDay() {
		return false;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		
		return false;
	}
	
	@Override
	public void onEntityUpdate() {
		if(cooldown <= 0) {
			
		}else {
			cooldown--;
		}
		super.onEntityUpdate();
	}
	
	//Interaction wit the examiner
	
	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		
		EntityLivingBase boss = new BossExam(this.world);
		//cooldown for rightclick
		if(cooldown <= 0) {
		
		if (world.isRemote) {
		player.sendMessage(new TextComponentString("You have found me I see"));
		player.sendMessage(new TextComponentString("you have arrived at the finale of the hunter exam"));
		player.sendMessage(new TextComponentString("all you're hardship trough weather and monsters has brought you here"));
		player.sendMessage(new TextComponentString("you're final opponent is going to be Hanzo"));
		player.sendMessage(new TextComponentString("now... let the battle begin!!!"));
		}
		
		if(!world.isRemote) {
			boss.setLocationAndAngles(this.posX+1, this.posY, this.posZ+1, this.rotationYaw, 0.0F);
			this.world.spawnEntity(boss);
		}
		cooldown = 24000;
	}
		
		
		return true;
	}
	
	@Override
	protected boolean canDespawn() {
		return false;
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
	public void setChild(boolean childZombie) {
		
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


}
