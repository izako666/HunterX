package com.izako.hunterx.items.entities;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class NeedleEntity extends ProjectileItemEntity{

	@SuppressWarnings("unchecked")
	public static EntityType<NeedleEntity> TYPE = (EntityType<NeedleEntity>) EntityType.Builder
			.<NeedleEntity>create(NeedleEntity::new, EntityClassification.MISC).setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true).size(1, 1).setUpdateInterval(1)
			.setCustomClientFactory(NeedleEntity::new).build("needle").setRegistryName(Main.MODID, "needle");


	public NeedleEntity(EntityType<? extends NeedleEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public NeedleEntity(EntityType<? extends NeedleEntity> type, LivingEntity throwerIn, World worldIn) {
		super(type, throwerIn, worldIn);

		owner = throwerIn;
	}

	public NeedleEntity(EntityType<? extends NeedleEntity> type, double x, double y, double z, World worldIn) {
		super(type, x, y, z, worldIn);
	}
	
	public NeedleEntity(FMLPlayMessages.SpawnEntity packet, World worldIn) {
		super(TYPE, worldIn);
	}


	@Override
	protected Item getDefaultItem() {
		// TODO Auto-generated method stub
		return ModItems.NEEDLE;
	}


	@Override
	protected void onImpact(RayTraceResult result) {
		// TODO Auto-generated method stub
		if (!this.world.isRemote) {

			if (!this.world.isRemote) {

				if (result.getType() == RayTraceResult.Type.ENTITY) {
					if (((EntityRayTraceResult) result).getEntity() != null
							&& ((EntityRayTraceResult) result).getEntity() != owner) {

						Entity e = ((EntityRayTraceResult) result).getEntity();
						e.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 6f);

						this.remove();
					}

				}

			}

		}

		if (this.ticksExisted > 1) {
			this.setMotion(0, 0, 0);
		}
		

	}

	@Override
	public void tick() {
		super.tick();

		if (this.ticksExisted >= 120) {
			this.remove();
		}
	}
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);

	}

}
