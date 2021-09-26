package com.izako.hunterx.entities;

import java.util.List;

import com.izako.hunterx.Main;

import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
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

public class ProjectileEntity extends Entity implements IEntityAdditionalSpawnData {

	public double scale = 1d;
	public double damage = 1d;
	public LivingEntity owner;
	public OnImpactBlock onImpactBlock = (b, p) -> {
	};
	public OnImpactEntity onImpactEntity = (e, p) -> {
	};

	@SuppressWarnings("unchecked")
	public static EntityType<ProjectileEntity> TYPE = (EntityType<ProjectileEntity>) EntityType.Builder
			.<ProjectileEntity>create(ProjectileEntity::new, EntityClassification.MISC).setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true).size(1, 1).setUpdateInterval(1)
			.setCustomClientFactory(ProjectileEntity::new).build("projectile_entity")
			.setRegistryName(Main.MODID, "projectile_entity");

	public ProjectileEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);

	}

	public ProjectileEntity(FMLPlayMessages.SpawnEntity pkt, World worldIn) {
		this(ProjectileEntity.TYPE, worldIn);
	}

	public ProjectileEntity(World world, LivingEntity owner) {
		this(ProjectileEntity.TYPE, world);
		this.owner = owner;
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

	public void setOwner(LivingEntity owner) {
		this.owner = owner;
	}

	@Override
	protected void onInsideBlock(BlockState state) {
		if(!canBeCollidedWith())
			return;
		
		if (!(state.getBlock().getBlock() instanceof AirBlock)) {
			this.onImpactBlock.onImpact(state, this);
			this.remove();
		}
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public void tick() {
		if (owner == null) {
			this.remove();
			return;
		}
		this.setPosition(this.getPosX() + this.getMotion().x, this.getPosY() + this.getMotion().y,
				this.getPosZ() + this.getMotion().z);
		if(this.canBeCollidedWith()) {
		List<LivingEntity> entities = this.world.getEntitiesWithinAABB(LivingEntity.class,
				this.getBoundingBox().grow(0.5));
		if (owner != null) {
			entities.remove(owner);
		}
		if (entities.size() > 0) {
			this.onImpactEntity.onImpact(entities, this);
			this.remove();
		}
		}
		super.tick();

	}

	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn) {
		super.onCollideWithPlayer(entityIn);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox() {
		return this.getBoundingBox().shrink(0.1);
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entityIn) {
		return this.getBoundingBox().shrink(0.1);
	}

	@Override
	public float getCollisionBorderSize() {
		return (float) (this.getBoundingBox().maxX - this.getBoundingBox().minX);
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		AxisAlignedBB bb;
		if(!this.canBeCollidedWith()) {
			bb = new AxisAlignedBB(0, 0, 0, 0, 0, 0);
		} else {
		 bb = super.getBoundingBox().grow(scale / 8);
		}
		return bb;
	}

	@Override
	protected AxisAlignedBB getBoundingBox(Pose p_213321_1_) {
		return this.getBoundingBox();
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return this.getBoundingBox();
	}

	public interface OnImpactBlock {

		void onImpact(BlockState state, ProjectileEntity proj);
	}

	public interface OnImpactEntity {

		void onImpact(List<LivingEntity> entities, ProjectileEntity proj);
	}

	@Override
	public void writeSpawnData(PacketBuffer buffer) {

		buffer.writeDouble(scale);
		buffer.writeDouble(damage);
		if (owner != null) {
			buffer.writeInt(owner.getEntityId());
		}
	}

	@Override
	public void readSpawnData(PacketBuffer additionalData) {

		this.scale = additionalData.readDouble();
		this.damage = additionalData.readDouble();
		ClientHandler.handle(additionalData, this);
	}

	public static class ClientHandler {
		@OnlyIn(Dist.CLIENT)
		public static void handle(PacketBuffer buf, ProjectileEntity e) {

			try {
				e.owner = (LivingEntity) Minecraft.getInstance().world.getEntityByID(buf.readInt());
			} catch (Exception exception) {

			}
		}
	}

}
