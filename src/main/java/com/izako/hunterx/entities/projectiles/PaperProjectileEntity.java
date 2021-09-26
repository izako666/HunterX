package com.izako.hunterx.entities.projectiles;

import java.util.List;

import com.izako.hunterx.Main;
import com.izako.hunterx.abilities.hatsus.kalluto.MeanderingDanceAbility;
import com.izako.hunterx.abilities.hatsus.kalluto.PaperMarkAbility;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.entities.ProjectileEntity;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.init.ModParticleTypes;
import com.izako.hunterx.izapi.Helper;
import com.izako.wypi.WyHelper;
import com.izako.wypi.particles.GenericParticleData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class PaperProjectileEntity extends ProjectileEntity {

	boolean canBeCollided = false;

	
	@SuppressWarnings("unchecked")
	public static EntityType<PaperProjectileEntity> TYPE = (EntityType<PaperProjectileEntity>) EntityType.Builder
			.<PaperProjectileEntity>create(PaperProjectileEntity::new, EntityClassification.MISC).setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true).size(1, 1).setUpdateInterval(1)
			.setCustomClientFactory(PaperProjectileEntity::new).build("paper_projectile")
			.setRegistryName(Main.MODID, "paper_projectile");

	
	public PaperProjectileEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.onImpactEntity = this::onImpactEntity;
	}

	public PaperProjectileEntity(World worldIn,LivingEntity owner) {
		this(TYPE, worldIn);

		this.owner = owner;
	}

	public PaperProjectileEntity(FMLPlayMessages.SpawnEntity pkt, World worldIn) {
		this(TYPE, worldIn);
	}

	private void onImpactEntity(List<LivingEntity> entities, ProjectileEntity proj) {


		for (LivingEntity entity : entities) {
			if (this.owner instanceof PlayerEntity) {

				entity.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) owner), (float) this.damage);
				System.out.println(entity.getHealth());
			} else {
				entity.attackEntityFrom(DamageSource.causeMobDamage(owner), (float) this.damage);
			}
		}

	}

	@Override
	public void tick() {

		super.tick();

		if(this.owner == null)
			return;
		if(!this.world.isRemote()) {
		if(this.ticksExisted < 150) {
		int rotation = this.ticksExisted * 8;
		while(rotation >= 360) {
			rotation -= 360;
		}
		float scale = 1f;
		if(this.ticksExisted > 100) {
		 scale = (float) Helper.fromRangeToRange(100, 150, 1, 5, this.ticksExisted);
		} 
		Vec3d vec = MeanderingDanceAbility.getPositionInCircle(this.owner.getPositionVec(), rotation, 0, (1.2f / scale));
		this.setPosition(vec.x, this.getPosY() + (double)(this.ticksExisted) / 5000d, vec.z);
		

		}
		for(int i = 0; i < 2; i++) {
		GenericParticleData particle = new GenericParticleData(ModParticleTypes.PAPER);
		particle.setLife(60);
		particle.setSize(0.12f);
		float randomXOffset = (this.rand.nextFloat() / 8f) - 0.125f;
		float randomYOffset = (this.rand.nextFloat() / 8f) - 0.125f;
		float randomZOffset = (this.rand.nextFloat() / 8f) - 0.125f;

		WyHelper.spawnParticles(particle, (ServerWorld)this.world, this.getPosX() + randomXOffset, this.getPosY() + randomYOffset, this.getPosZ() + randomZOffset);
		}

		if(this.ticksExisted == 150) {
			IAbilityData data = AbilityDataCapability.get(owner);
			if(data.hasActiveAbility(ModAbilities.PAPER_MARK_ABILITY) && ((PaperMarkAbility)data.getActiveAbility(ModAbilities.PAPER_MARK_ABILITY, owner)).marked != null) {
				this.canBeCollided = true;
				LivingEntity marked = ((PaperMarkAbility)data.getActiveAbility(ModAbilities.PAPER_MARK_ABILITY, owner)).marked;
				this.shoot(marked.getPosX(), marked.getPosY(), marked.getPosZ(), 20, 0);
			} else {
				this.remove();
			}
		}
		}
	}

	public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
		float f = -MathHelper.sin(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
		float f1 = -MathHelper.sin(pitch * ((float) Math.PI / 180F));
		float f2 = MathHelper.cos(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
		this.shoot((double) f, (double) f1, (double) f2, velocity, inaccuracy);
		this.setMotion(this.getMotion().add(shooter.getMotion().x, shooter.onGround ? 0.0D : shooter.getMotion().y,
				shooter.getMotion().z));
	}

	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z
	 * direction.
	 */
	public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
		Vec3d vec3d = new Vec3d((this.getPosX() - x) / velocity,(this.getPosY() -y) / velocity, (this.getPosZ() - z)/ velocity);
		this.setMotion(vec3d.mul(-1, -1,-1));
	}

	@Override
	public boolean canBeCollidedWith() {
		return canBeCollided;
	}
	
}
