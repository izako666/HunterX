package com.izako.hunterx.entities.projectiles;

import java.util.List;

import com.izako.hunterx.Main;
import com.izako.hunterx.entities.ProjectileEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class AuraBlastProjectileEntity extends ProjectileEntity {

	@SuppressWarnings("unchecked")
	public static EntityType<AuraBlastProjectileEntity> TYPE = (EntityType<AuraBlastProjectileEntity>) EntityType.Builder
	.<AuraBlastProjectileEntity>create(AuraBlastProjectileEntity::new, EntityClassification.MISC).setTrackingRange(128)
	.setShouldReceiveVelocityUpdates(true).size(1, 1).setUpdateInterval(1)
	.setCustomClientFactory(AuraBlastProjectileEntity::new).build("aura_blast_projectile_entity").setRegistryName(Main.MODID, "aura_blast_projectile_entity");

	public AuraBlastProjectileEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.onImpactEntity = this::onImpactEntity;
	}

	public AuraBlastProjectileEntity(World worldIn) {
		this(TYPE, worldIn);
		
	}
	
	public AuraBlastProjectileEntity(FMLPlayMessages.SpawnEntity pkt, World worldIn) {
		this(TYPE, worldIn);
	}
	
	private void onImpactEntity(List<LivingEntity> entities, ProjectileEntity proj) {
		
		for(LivingEntity entity : entities) {
			entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.owner), (float) this.damage);
		}
		
	}

	 public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
	      float f = -MathHelper.sin(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
	      float f1 = -MathHelper.sin(pitch * ((float)Math.PI / 180F));
	      float f2 = MathHelper.cos(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
	      this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
	      this.setMotion(this.getMotion().add(shooter.getMotion().x, shooter.onGround ? 0.0D : shooter.getMotion().y, shooter.getMotion().z));
	   }

	   /**
	    * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
	    */
	   public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
	      Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy, this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy, this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy).scale((double)velocity);
	      this.setMotion(vec3d);
	      float f = MathHelper.sqrt(horizontalMag(vec3d));
	      this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180F / (float)Math.PI));
	      this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, (double)f) * (double)(180F / (float)Math.PI));
	      this.prevRotationYaw = this.rotationYaw;
	      this.prevRotationPitch = this.rotationPitch;
	   }

	
}
