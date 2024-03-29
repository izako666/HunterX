package com.izako.hunterx.items.entities;

import java.util.UUID;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class YoyoEntity extends ProjectileItemEntity implements IEntityAdditionalSpawnData {

	public UUID ownerID;
	public int entityId;
	private PlayerEntity owner;
	int cooldowncount = 0;
	private int maxticks = 15;
	private int count = 0;
	private boolean comeBack = false;
	private ItemStack itemStack;

	@SuppressWarnings("unchecked")
	public static EntityType<YoyoEntity> TYPE = (EntityType<YoyoEntity>) EntityType.Builder
			.<YoyoEntity>create(YoyoEntity::new, EntityClassification.MISC).setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true).size(1, 1).setUpdateInterval(1)
			.setCustomClientFactory(YoyoEntity::new).build("yoyo").setRegistryName(Main.MODID, "yoyo");

	public YoyoEntity(EntityType<? extends YoyoEntity> type, World worldIn) {
		super(type, worldIn);

	}

	public YoyoEntity(EntityType<? extends YoyoEntity> type, double x, double y, double z, World worldIn) {
		super(type, x, y, z, worldIn);
	}

	public YoyoEntity(EntityType<? extends YoyoEntity> type, LivingEntity livingEntityIn, World worldIn) {

		super(type, livingEntityIn, worldIn);
		owner = (PlayerEntity) livingEntityIn;
		
		ownerID = livingEntityIn.getUniqueID();
		
	}
	
	public YoyoEntity(EntityType<? extends YoyoEntity> type, LivingEntity livingEntityIn, World worldIn, ItemStack item) {

		super(type, livingEntityIn, worldIn);
		owner = (PlayerEntity) livingEntityIn;
		ownerID = livingEntityIn.getUniqueID();
		if(!item.hasTag()) {
			item.setTag(new CompoundNBT());
			item.getTag().putBoolean("cast", true);
		} else {
			item.getTag().putBoolean("cast", true);
		}
		itemStack = item;
		
	}


	public YoyoEntity(FMLPlayMessages.SpawnEntity packet, World worldIn) {
		super(TYPE, worldIn);
	}

	@Override
	protected void onImpact(RayTraceResult result) {

		if (!world.isRemote) {
			
			if(owner != null) {

			if (result.getType() != RayTraceResult.Type.ENTITY && comeBack == false) {

				this.count = this.ticksExisted;
				this.setMotion((this.owner.getPosX() - this.getPosX()) * 0.05,
						(this.owner.getPosY() + this.owner.getEyeHeight() - this.getPosY()) * 0.1,
						(this.owner.getPosZ() - this.getPosZ()) * 0.05);
				owner.getCooldownTracker().setCooldown(ModItems.YOYO, count);

				this.comeBack = true;
			} else if (result.getType() == RayTraceResult.Type.ENTITY) {
				if (((EntityRayTraceResult) result).getEntity() != owner && comeBack == false) {
					this.count = this.ticksExisted;
					this.setMotion((this.owner.getPosX() - this.getPosX()) * 0.05,
							(this.owner.getPosY() + this.owner.getEyeHeight() - this.getPosY()) * 0.1,
							(this.owner.getPosZ() - this.getPosZ()) * 0.05);

					((EntityRayTraceResult) result).getEntity().attackEntityFrom(DamageSource.causeThrownDamage(this, this.owner), 20);
					this.comeBack = true;
					owner.getCooldownTracker().setCooldown(ModItems.YOYO, count);

					
				} else {

				}
			}
		}
		}
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);

	}

	@Override
	protected Item getDefaultItem() {
		return ModItems.YOYO;
	}

	public PlayerEntity getOwner() {
		return owner;
	}

	@Override
	public void writeSpawnData(PacketBuffer buffer) {
		if(this.getOwner() != null) {
		buffer.writeInt(this.getOwner().getEntityId());

		}
	}

	@Override
	public void readSpawnData(PacketBuffer additionalData) {

		this.entityId = additionalData.readInt();
	}

	@Override
	public void tick() {

		if(this.ticksExisted > count*2 && count > 0) {
			this.remove();
			this.count = 0;
			owner.getCooldownTracker().setCooldown(ModItems.YOYO, 0);
			if(itemStack.hasTag()) {
				itemStack.getTag().putBoolean("cast", false);
			}
		}
			if(this.owner != null) {
			if(this.ticksExisted > maxticks && comeBack == false)  {
				this.setMotion((this.owner.getPosX() - this.getPosX()) * 0.05,
						(this.owner.getPosY() + this.owner.getEyeHeight() - this.getPosY()) * 0.1,
						(this.owner.getPosZ() - this.getPosZ()) * 0.05);

				this.count = this.ticksExisted;
				this.comeBack = true;
				owner.getCooldownTracker().setCooldown(ModItems.YOYO, count);
			} 
			
		}
		super.tick();
	}
}
