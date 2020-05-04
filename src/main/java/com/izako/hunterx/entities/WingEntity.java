package com.izako.hunterx.entities;

import java.util.List;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModQuests;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class WingEntity extends CreatureEntity {

	@SuppressWarnings("unchecked")
	public static EntityType<WingEntity> type = (EntityType<WingEntity>) EntityType.Builder
			.<WingEntity>create(WingEntity::new, EntityClassification.CREATURE).setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true).size(1f, 2f).setUpdateInterval(1).build("wing")
			.setRegistryName(Main.MODID, "wing");

	protected WingEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
	}

   @Override
	public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {

	  List<VillagerEntity> list = worldIn.getEntitiesWithinAABB(VillagerEntity.class, new AxisAlignedBB(this.posX - 30, this.posY - 30, this.posZ - 30, this.posX + 30, this.posY + 30, this.posZ + 30));
  
	  if(!list.isEmpty()) {
		  return false;
	  }
	  return true;
   }
   @Override
   protected boolean processInteract(PlayerEntity player, Hand hand) {
	      if(!ModQuests.HUNTEREXAM01.hasQuest(player)) {
	    	  if(world.isRemote) {
	    	  player.sendMessage(new StringTextComponent("Oh hello young traveler"));
	    	  player.sendMessage(new StringTextComponent("You seem pretty strong, maybe you'd want to"));
	    	  player.sendMessage(new StringTextComponent("apply for the hunter exam."));
	    	  player.sendMessage(new StringTextComponent("too bad its registration will be over in 2 days..."));
	    	  player.sendMessage(new StringTextComponent("Get to the hunter exam in 40 minutes.").applyTextStyle(TextFormatting.BLUE));
	    	  }
	    	  IHunterData data = HunterDataCapability.get(player);
	    	  data.giveQuest(ModQuests.HUNTEREXAM01.getId(), 0);
	    	  return true;


	      }
	      return false;
	   }

	@Override
	public boolean canDespawn(double d) {
		return true;
	}
}