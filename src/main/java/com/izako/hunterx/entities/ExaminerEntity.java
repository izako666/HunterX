package com.izako.hunterx.entities;

import com.izako.hunterx.Main;
import com.izako.hunterx.izapi.NPCSpeech;
import com.izako.hunterx.izapi.quest.IQuestGiver;
import com.izako.hunterx.quests.speech.ExaminerSpeech;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ExaminerEntity extends CreatureEntity implements IQuestGiver {

	@SuppressWarnings("unchecked")
	public static EntityType<ExaminerEntity> TYPE = (EntityType<ExaminerEntity>) EntityType.Builder
			.<ExaminerEntity>create(ExaminerEntity::new, EntityClassification.CREATURE).setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true).size(1f, 2f).setUpdateInterval(1).build("examiner")
			.setRegistryName(Main.MODID, "examiner");

	protected ExaminerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
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

	public int getSlotForStack(ItemStack stack, PlayerEntity p) {
	  for(int i = 0; i <= p.inventory.getSizeInventory(); i++) {
		  if(p.inventory.getStackInSlot(i).getItem() == stack.getItem()) {
			  return i;
		  }
	  }
	  return -1;
	}


	@Override
	public boolean canDespawn(double d) {
		return false;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public NPCSpeech getSpeech() {
		return new ExaminerSpeech();
	}

		
}
