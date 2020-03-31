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

public class BulletEntity extends ProjectileItemEntity{

	@SuppressWarnings("unchecked")
	public static EntityType<BulletEntity> type = (EntityType<BulletEntity>) EntityType.Builder
			.<BulletEntity>create(BulletEntity::new, EntityClassification.MISC).setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true).size(1, 1).setUpdateInterval(1)
			.setCustomClientFactory(BulletEntity::new).build("bullet").setRegistryName(Main.MODID, "bullet");


	public BulletEntity(EntityType<? extends BulletEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public BulletEntity(EntityType<? extends BulletEntity> type, LivingEntity throwerIn, World worldIn) {
		super(type, throwerIn, worldIn);

		owner = throwerIn;
	}

	public BulletEntity(EntityType<? extends BulletEntity> type, double x, double y, double z, World worldIn) {
		super(type, x, y, z, worldIn);
	}
	
	public BulletEntity(FMLPlayMessages.SpawnEntity packet, World worldIn) {
		super(type, worldIn);
	}


	@Override
	protected Item func_213885_i() {
		// TODO Auto-generated method stub
		return ModItems.PISTOL;
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

				this.remove();
			}

		}

		

	}
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);

	}

}
