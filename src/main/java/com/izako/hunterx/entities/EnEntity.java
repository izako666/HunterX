package com.izako.hunterx.entities;

import com.izako.hunterx.Main;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class EnEntity extends Entity implements IEntityAdditionalSpawnData {



 public	LivingEntity owner;
	public double renderScale  = 10;
	@SuppressWarnings("unchecked")
	public static EntityType<EnEntity> TYPE = (EntityType<EnEntity>) EntityType.Builder
	.<EnEntity>create(EnEntity::new, EntityClassification.MISC).setTrackingRange(128)
	.setShouldReceiveVelocityUpdates(true).size(1, 1).setUpdateInterval(1)
	.setCustomClientFactory(EnEntity::new).build("en_sphere").setRegistryName(Main.MODID, "en_sphere");
	public EnEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	public EnEntity(EntityType<?> entityTypeIn, World worldIn, LivingEntity owner) {
		super(entityTypeIn, worldIn);
		this.owner = owner;
		
	}
	public EnEntity(FMLPlayMessages.SpawnEntity packet, World worldIn) {
		super(TYPE, worldIn);
	}
	@Override
	protected void registerData() {
		
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
		
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
		
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void writeSpawnData(PacketBuffer buffer) {
	     buffer.writeDouble(renderScale);
	     if(owner != null) {
	     buffer.writeInt(owner.getEntityId());
	     }
	}

	@Override
	public void readSpawnData(PacketBuffer additionalData) {
		
		ClientHandler.handle(additionalData, this);
	}


	@Override
	public AxisAlignedBB getBoundingBox() {
		return super.getBoundingBox().grow(renderScale);
	}

	
	
	public static class ClientHandler {
		@OnlyIn(Dist.CLIENT)
		public static void handle(PacketBuffer buf,EnEntity e) {
			e.renderScale = buf.readDouble();
			try {
			e.owner = (LivingEntity) Minecraft.getInstance().world.getEntityByID(buf.readInt());
			} catch(Exception exception) {
				
			}
			}
	}
}
