package com.izako.hunterx.abilities.hatsus.kalluto;

import java.util.List;
import java.util.Optional;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModParticleTypes;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PassiveAbility;
import com.izako.wypi.WyHelper;
import com.izako.wypi.particles.GenericParticleData;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;

public class PaperMarkAbility extends PassiveAbility {

	private static final ResourceLocation PAPER = new ResourceLocation(Main.MODID, "textures/particles/paper.png");
	BlockPos origin;
	public LivingEntity marked;
	int timeToStart = 0;
	float rotator = 0f;
	public PaperMarkAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxPassive(Integer.MAX_VALUE).setMaxCooldown(0).setNenType(NenType.MANIPULATOR);
	}
	@Override
	public String getId() {
		return "paper_mark";
	}

	@Override
	public String getName() {
		return "Paper Mark";
	}

	@Override
	public String getDesc() {
		return "Bombards the target with a flurry of paper scraps and tries to stick a paper on to said target.";
	}

	@Override
	public void onStartPassive(LivingEntity p) {
		Optional<Entity> entity = Helper.getTargetEntity(p, 50);

		if(entity.isPresent()) {
			Vec3d vecOrigin = entity.get().getPositionVec();
			this.origin = PaperMarkAbility.getPosFromVec(vecOrigin);
			if(!p.world.isRemote()) {
			for(int i = 0; i< 25; i++) {
				GenericParticleData particle = new GenericParticleData(ModParticleTypes.PAPER);
				int time = 20 + this.rand.nextInt(15);
				float xOffset = (float) ((this.rand.nextFloat() - 0.5));
				float yOffset = (float) ((this.rand.nextFloat() - 0.5));
				float zOffset = (float) ((this.rand.nextFloat() - 0.5));

				this.timeToStart = time;
				particle.setLife(time);
				particle.setSize(0.12f);
				Vec3d path  = p.getEyePosition(1.0f).subtract(new Vec3d(this.origin));
				particle.setMotion((float)(-path.getX() / time), (float)(-path.getY() /(float)(time)), (float)(-path.getZ() / (float)(time)));

				WyHelper.spawnParticles(particle, (ServerWorld)p.world, p.getEyePosition(1).getX() + xOffset, p.getEyePosition(1).getY() + yOffset, p.getEyePosition(1).getZ() + zOffset);
			}
			}
		} else {

			Helper.endAbilitySafe(p, this);
		}
		this.rotator = 0f;
		super.onStartPassive(p);
	}

	@Override
	public void duringPassive(LivingEntity p) {
		if(this.origin == null)
			return;

		if(this.getPassiveTimer() > this.props.maxPassive - timeToStart)
			return;
		GenericParticleData particle = new GenericParticleData(ModParticleTypes.PAPER);
		particle.setLife(15);
		particle.setSize(0.12f);
		particle.setMotion(0f, 0.15f,0f);
		

		GenericParticleData particle2 = new GenericParticleData(ModParticleTypes.PAPER);
		particle2.setLife(13);
		particle2.setSize(0.12f);
		particle2.setMotion(0f, -0.15f,0f);

		
		this.rotator += 11.25;
		if(this.rotator >= 360) {
			this.rotator = 0;
		}
		float rotator2 = this.rotator + 180;
		if(rotator2 > 360) {
			rotator2 -= 360;
		}
		float rotator3 = this.rotator + 90;
		float rotator4 = this.rotator - 90;
		if(rotator3 > 360) {
			rotator3 -= 360;
		}
		if(rotator4 > 360) {
			rotator4 -= 360;
		}
		float xOffset = 0.5f;
		float zOffset = 0.5f;
		if(this.origin.getX() < 0) {
			xOffset = -0.5f;
		}
		if(this.origin.getZ() < 0) {
			zOffset = -0.5f;
		}
		

		
		Vec3d vecOrigin = new Vec3d(this.origin).add(xOffset,0,zOffset);
		Vec3d vecOrigin2 = new Vec3d(this.origin).add(xOffset, 2.7, zOffset);
		
			if(!p.world.isRemote() && this.marked == null) {
					for(int i = 0; i< 5; i++) {
						float randomOffset = (float)(this.rand.nextInt(10) / 100.0f) - 0.05f;
						float randomYOffset = (float)(this.rand.nextInt(20) / 100.0f);
						Vec3d finalPosition = PaperMarkAbility.getPositionInCircle(vecOrigin, rotator, randomOffset);
						WyHelper.spawnParticles(particle, (ServerWorld)p.world, finalPosition.x, finalPosition.y + randomYOffset, finalPosition.z);

					}
					
					for(int i = 0; i< 5; i++) {
						float randomOffset = (float)(this.rand.nextInt(10) / 100.0f) - 0.05f;
						float randomYOffset = (float)(this.rand.nextInt(20) / 100.0f);
						Vec3d finalPosition = PaperMarkAbility.getPositionInCircle(vecOrigin, rotator2, randomOffset);
						WyHelper.spawnParticles(particle, (ServerWorld)p.world, finalPosition.x, finalPosition.y+randomYOffset, finalPosition.z);

					}
					
					for(int i = 0; i< 5; i++) {
						float randomOffset = (float)(this.rand.nextInt(10) / 100.0f) - 0.05f;
						float randomYOffset = (float)(this.rand.nextInt(20) / 100.0f);

						Vec3d finalPosition = PaperMarkAbility.getPositionInCircle(vecOrigin2, rotator3, randomOffset);
						WyHelper.spawnParticles(particle2, (ServerWorld)p.world, finalPosition.x, finalPosition.y - randomYOffset, finalPosition.z);

					}
					for(int i = 0; i< 5; i++) {
						float randomOffset = (float)(this.rand.nextInt(10) / 100.0f) - 0.05f;
						float randomYOffset = (float)(this.rand.nextInt(20) / 100.0f);

						Vec3d finalPosition = PaperMarkAbility.getPositionInCircle(vecOrigin2, rotator4, randomOffset);
						WyHelper.spawnParticles(particle2, (ServerWorld)p.world, finalPosition.x, finalPosition.y - randomYOffset, finalPosition.z);

					}



				


				}

			
			if(marked == null) {
				List<LivingEntity> mightMark = p.world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(origin.add(-2, 0, -2), origin.add(2, 1, 2)));
			    
				
				 if(!mightMark.isEmpty() && this.getPassiveTimer() % 20 == 0) {
						for(LivingEntity entity : mightMark) {
							int chance = this.rand.nextInt(9) + 1;
							if(chance <= 2) {
								this.marked = entity;
								this.marked.attackEntityFrom(DamageSource.causeMobDamage(p),1);
							}
						}
				}


				if(this.getPassiveTimer() <= this.props.maxPassive - 100 - timeToStart && this.marked == null) {

					Helper.endAbilitySafe(p, this);
					}
				if(this.marked != null && !this.marked.isAlive()) {
					Helper.endAbilitySafe(p, this);
				}

			}
		
			super.duringPassive(p);
	}

	@Override
	public void onEndPassive(LivingEntity p) {
		this.marked = null;
		this.origin = null;
		if(p.world.isRemote()) {
			System.out.println("client end");
		} else {
			System.out.println("server end");
		}
		super.onEndPassive(p);
	}
	/**
	 * @param origin the origin point to rotate around
	 * @param rotation the original rotation point to distinguish each usage of this method goes from 0 to 360
	 * @param increment an increment value to rotate the particle in the scale of pi
	 */
	private static Vec3d getPositionInCircle(Vec3d origin, double rotation, float increment) {
		float radianRotation = (float) Helper.fromRangeToRange(0, 360, 0, Math.PI, rotation);
		float finalIncrement = radianRotation + increment;
		if(finalIncrement > Math.PI) {
			finalIncrement -= Math.PI;
		}
		double posX = origin.getX() + (1.2f * Math.cos(finalIncrement*2));
		double posY = origin.getY();
		double posZ = origin.getZ() + (1.2f * Math.sin(finalIncrement*2));

		Vec3d vec3d = new Vec3d(posX,posY,posZ);
		
		return vec3d;
	}
	
	private static BlockPos getPosFromVec(Vec3d vec) {
		double x = vec.getX();
		double y = vec.getY();
		double z = vec.getZ();
		
		if(x < 0) {
			Minecraft.getInstance().mouseHelper.ungrabMouse();
			x = Math.ceil(x);
			Minecraft.getInstance().mouseHelper.grabMouse();

		} else {
			x = Math.floor(x);
		}
		
		if(y < 0) {
			y = Math.ceil(y);
		} else {
			y = Math.floor(y);
		}

		if(z < 0) {
			z = Math.ceil(z);
		} else {
			z = Math.floor(z);
		}

		return new BlockPos(x,y,z);
	}
}
