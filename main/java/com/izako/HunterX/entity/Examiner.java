package com.izako.HunterX.entity;

import java.io.Console;
import java.util.Timer;
import java.util.concurrent.DelayQueue;

import com.ibm.icu.text.MessagePattern.Part;
import com.izako.HunterX.init.EntityInit;
import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.izapi.Misc;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.EntityStatsServerSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

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
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
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
	public int getMaxSpawnedInChunk() {

		return 1;
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {

		return super.onInitialSpawn(difficulty, livingdata);
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
		if (cooldown <= 0) {

		} else {
			cooldown--;
		}
		super.onEntityUpdate();
	}

	// Interaction wit the examiner

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {

		EntityLivingBase boss = new BossExam(this.world);

		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		World world = player.getEntityWorld();
		if (!stats.isHunter()) {
			if (!stats.hasKilledKiriko()) {
				if (world.isRemote) {
					Misc.sendMsg(player, "Welcome to the Hunter Exam location site.", null);
					Misc.sendMsg(player, "So you want to participate in the hunter exam eh?", null);
					Misc.sendMsg(player,
							"I'm afraid you can't do this exam without being able to beat a measly kiriko.", null);
					Misc.sendMsg(player, TextFormatting.DARK_RED + "kill a kiriko first.", null);
				}
			}

			else if (stats.hasKilledKiriko() && !stats.hasStarted2ndPhase()) {
				if (world.isRemote) {
					stats.setHasStarted2ndPhase(true);
					ModidPacketHandler.INSTANCE
							.sendToServer(new EntityStatsServerSync(1D, 6, stats.hasStarted2ndPhase()));
					Misc.sendMsg(player, "Well first of all", null);
					Misc.sendMsg(player, "The first exam is a test of stamina", null);
					Misc.sendMsg(player, "You must run till your breath runs out.", null);
					Misc.sendMsg(player, TextFormatting.DARK_RED + "run for ten minutes.", null);

				}
			}
			if (stats.timeHasRun() > 50 && stats.hasStarted2ndPhase() && !stats.hasStarted3rdPhase()) {
				stats.setHasStarted3rdPhase(true);
				if (world.isRemote) {
					Misc.sendMsg(player, "Very well, the next exam is more about your... equipment.", null);
					Misc.sendMsg(player, "get some good equipment, without it you won't last.", null);
					Misc.sendMsg(player, TextFormatting.DARK_RED + "get a weapon from this mod.", null);
				}
			}
			else if (player.inventory.hasItemStack(new ItemStack(ModItems.HANZOS_SWORD))
					|| player.inventory.hasItemStack(new ItemStack(ModItems.KURAPIKAS_SWORD))
					|| player.inventory.hasItemStack(new ItemStack(ModItems.KURAPIKAS_SWORD_UNSHEATHED))
					|| player.inventory.hasItemStack(new ItemStack(ModItems.GONS_FISHING_ROD))
					|| player.inventory.hasItemStack(new ItemStack(ModItems.YOYO)) 
							) {
				if(!stats.hasKilledBoss() && stats.hasStarted3rdPhase()) {
					System.out.println(stats.hasStarted3rdPhase());
					System.out.println(stats.hasKilledBoss());
				if (world.isRemote) {
					Misc.sendMsg(player, "Fine then, this is your final challenge.", null);
					Misc.sendMsg(player, "beat your fellow comrade, hanzo in a one v one.", null);
				}
				if (!world.isRemote) {
					boss.setLocationAndAngles(this.posX + 1, this.posY, this.posZ + 1, this.rotationYaw, 0.0F);
					this.world.spawnEntity(boss);

				}
				}
			} else if (stats.hasKilledBoss()) {
			
					ItemStack stack = new ItemStack(ModItems.HUNTER_CARD);
					stack.setStackDisplayName(player.getName() + "'s License");
					int EmptySlot = player.inventory.getFirstEmptyStack();
					if (EmptySlot == -1) {
						if (world.isRemote) {
							Misc.sendMsg(player, TextFormatting.DARK_RED + "empty out your inventory please.", null);
						}
					} if(EmptySlot != -1) {
						if (world.isRemote) {
							Misc.sendMsg(player, "Congratulations, you are now a hunter.", null);
						}
						player.addItemStackToInventory(stack);
						stats.setIsHunter(true);
						System.out.println(stats.isHunter());

						ModidPacketHandler.INSTANCE.sendToServer(new EntityStatsServerSync(1D, 8, true));
					}

				}
			
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

		return true;
	}

	@Override
	public void setChild(boolean childZombie) {

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

}