package com.izako.HunterX.entity;

import java.awt.List;
import java.util.ArrayList;

import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowGolem;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.ai.EntityAIVillagerInteract;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.village.Village;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityStats extends EntityVillager implements IEntityAdditionalSpawnData {
	java.util.List<EntityStats> list;
	
	double str;
	double spe;
	double def;
	double hea;
	
	
	
	
	public EntityStats(World worldIn) {
		super(worldIn);
		
		
	}
	
	
	
	@Override
	protected void initEntityAI() {
		
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        
	}
	
	//method for getting entities in a area
	
	public java.util.List<EntityStats> getEnt(World w,double x, double z) {
		return w.getEntitiesWithinAABB(EntityStats.class, new AxisAlignedBB(x-100,0,z-100,x+100,257,z+100));
		
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		 try {
			 list = getEnt(this.world, this.posX, this.posZ);
			 
		} catch (NullPointerException n) {
			
		}
		if (list.size() != 0) {
			this.setDead();
		}
		
		return super.onInitialSpawn(difficulty, livingdata);
	}
	

	
	
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}
	
	
	
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		
		
		
		return false;
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		
		
		
	}
	
	//Interaction wit the examiner
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		
		if(!world.isRemote) {
			
			
		}
		
		
		
		
		
		
		if (world.isRemote) {
			
			 IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
				
				str = stats.getAttackStat();
				spe = stats.getSpeedStat();
				def = stats.getDefenseStat();
				hea = stats.getHealthStat();
			
		player.sendMessage(new TextComponentString("Oh who are you?"));
		player.sendMessage(new TextComponentString("Ah so you want to become stronger?"));
		player.sendMessage(new TextComponentString("Well let me see how strong you are then!"));
		player.sendMessage(new TextComponentString(""));
		
       
		
		//Speed stat text
		
		if(spe < 0.05) {
			player.sendMessage(new TextComponentString("you're not very fast are you...?"));
		}
		
		if(spe < 0.1 && spe > 0.05) {
			player.sendMessage(new TextComponentString("Huh, you're quite nimble on your feet."));
		}
		
		if(spe <= 0.2 && spe > 0.1) {
			player.sendMessage(new TextComponentString("I have never seen someone as fast as you are."));
		}
		
		//Strength stat text
		
		if(str < 5) {
			player.sendMessage(new TextComponentString("you're not exactly strong......"));
		}
		
		if(str < 7 && str > 5) {
			player.sendMessage(new TextComponentString("You're really strong, I can tell."));
		}
		
		if(str <= 10 && str > 7) {
			player.sendMessage(new TextComponentString("Wow. your strength is immeasurable"));
		}
		
		//Defense stat text
		
		if(def < 5) {
			player.sendMessage(new TextComponentString("you can't take that much damage"));
		}
		
		if(def < 7 && def > 5) {
			player.sendMessage(new TextComponentString("your endurance.... is acceptable."));
		}
		
		if(def <= 10 && def > 7) {
			player.sendMessage(new TextComponentString("Like a wall, you can take immense damage."));
		}
		
		//Health stat text
		
		if(hea < 5) {
			player.sendMessage(new TextComponentString("you're not very healthy are you?"));
		}
		
		if(hea < 7 && hea > 5) {
			player.sendMessage(new TextComponentString("atleast you're fit."));
		}
		
		if(hea <= 10 && hea > 7) {
			player.sendMessage(new TextComponentString("I can see you're of immense health."));
		}
		
		player.sendMessage(new TextComponentString(""+str));
		player.sendMessage(new TextComponentString(""+spe));
		player.sendMessage(new TextComponentString(""+def));
		player.sendMessage(new TextComponentString(""+hea));
		}
		
		
		

		return true;
	}
	
	
	//spawning in villages only
	@Override
	public boolean getCanSpawnHere() {
		
		 if(world.villageCollection.getVillageList().iterator().hasNext() == false && world.villageCollection.getNearestVillage(new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 10) == null)
	        {
	         return false;
	        }
		 if(world.villageCollection.getNearestVillage(new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 10) == null && world.villageCollection.getNearestVillage(new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 10) == null) {
			 
			return false; 
		 }
		 
		 
		 
	     return true;
	}
	
	@Override
	protected boolean canDespawn() {
		return false;
	}



	@Override
	public void writeSpawnData(ByteBuf buffer) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void readSpawnData(ByteBuf additionalData) {
		// TODO Auto-generated method stub
		
	}

	
	
   

}
