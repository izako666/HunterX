package com.izako.hunterx.entities;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.data.world.HunterWorldData;
import com.izako.hunterx.init.ModItems;
import com.izako.hunterx.init.ModQuests;

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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ExaminerEntity extends CreatureEntity {

	@SuppressWarnings("unchecked")
	public static EntityType<ExaminerEntity> type = (EntityType<ExaminerEntity>) EntityType.Builder
			.<ExaminerEntity>create(ExaminerEntity::new, EntityClassification.CREATURE).setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true).size(1f, 2f).setUpdateInterval(1).build("examiner")
			.setRegistryName(Main.MODID, "examiner");

	protected ExaminerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
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

	public int getSlotForStack(ItemStack stack, PlayerEntity p) {
	  for(int i = 0; i <= p.inventory.getSizeInventory(); i++) {
		  if(p.inventory.getStackInSlot(i).getItem() == stack.getItem()) {
			  return i;
		  }
	  }
	  return -1;
	}
	@Override
	protected boolean processInteract(PlayerEntity p, Hand hand) {

		IHunterData data = HunterDataCapability.get(p);
		boolean bossSpawned = false;
		HanzoEntity boss = new HanzoEntity(HanzoEntity.type, this.world);

		if (ModQuests.HUNTEREXAM01.hasQuest(p) && !ModQuests.HUNTEREXAM01.isFinished(p)) {

			if (data.getProgress(ModQuests.HUNTEREXAM01.getId()) < 100) {
				if (world.isRemote) {
					p.sendMessage(new StringTextComponent("here exam02"));
				}
				ModQuests.HUNTEREXAM01.finishQuest(p);
				data.giveQuest(ModQuests.HUNTEREXAM02.getId(), 0);
				return true;
			}
			if (data.getProgress(ModQuests.HUNTEREXAM01.getId()) == 100) {
				p.sendMessage(new StringTextComponent("exam01 failed"));
				data.removeQuest(ModQuests.HUNTEREXAM01.getId());
				return true;
			}
		}
		if (ModQuests.HUNTEREXAM02.hasQuest(p) && !ModQuests.HUNTEREXAM02.isFinished(p)) {
			if (data.getProgress(ModQuests.HUNTEREXAM02.getId()) == 100) {
				if (world.isRemote) {
					p.sendMessage(new StringTextComponent("exam02 passed"));
				}
				ModQuests.HUNTEREXAM02.finishQuest(p);
				data.giveQuest(ModQuests.HUNTEREXAM03.getId(), 0);
				if (!world.isRemote) {
					HunterWorldData worlddata = HunterWorldData.get((ServerWorld) world);

					worlddata.setPos(this.getPosition());
					System.out.println("debug");
				}
				return true;
			}

		}
		if (ModQuests.HUNTEREXAM04.hasQuest(p) && !ModQuests.HUNTEREXAM04.isFinished(p)) {
			if (bossSpawned == false) {
				if (world.isRemote) {
					p.sendMessage(new StringTextComponent("here exam04"));
				}
				if (!world.isRemote) {
					boss.setLocationAndAngles(this.posX + 1, this.posY, this.posZ + 1, this.rotationYaw, 0.0F);
					this.world.addEntity(boss);

				}

				return true;
			}
			return false;
		}
		if (ModQuests.HUNTEREXAM04.hasQuest(p) && ModQuests.HUNTEREXAM04.isFinished(p) && !data.isHunter()) {
			if (p.inventory.hasItemStack(new ItemStack(ModItems.BADGE))) {
				p.sendMessage(new StringTextComponent("exam done"));
				int emptySlot = p.inventory.getFirstEmptyStack();
				if (emptySlot == -1) {
					if (world.isRemote) {
						p.sendMessage(new StringTextComponent("cant give license"));
					}
				} else {
					if (world.isRemote) {
						p.sendMessage(new StringTextComponent("u a hunter now."));
					}
					p.inventory.removeStackFromSlot(this.getSlotForStack(new ItemStack(ModItems.BADGE), p));
					ItemStack license = new ItemStack(ModItems.HUNTER_LICENSE);
					ITextComponent displayName = new StringTextComponent(p.getName().getUnformattedComponentText() + "'s License");
				    license.setDisplayName(displayName);
					p.addItemStackToInventory(license);

					data.setIsHunter(true);
				}
			}
		}
		if (!ModQuests.HUNTEREXAM01.hasQuest(p)) {
			if (world.isRemote) {
				p.sendMessage(new StringTextComponent("exams havent started yet"));
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean canDespawn(double d) {
		return false;
	}
}
