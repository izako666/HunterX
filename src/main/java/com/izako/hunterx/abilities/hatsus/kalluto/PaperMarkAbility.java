package com.izako.hunterx.abilities.hatsus.kalluto;

import java.util.Optional;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModParticleTypes;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PassiveAbility;
import com.izako.hunterx.izapi.ability.Ability.AbilityType;
import com.izako.wypi.WyHelper;
import com.izako.wypi.particles.GenericParticleData;

import net.minecraft.client.MinecraftGame;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.server.ServerWorld;

public class PaperMarkAbility extends PassiveAbility {

	private static final ResourceLocation PAPER = new ResourceLocation(Main.MODID, "textures/particles/paper.png");
	BlockPos origin;
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
			this.origin = entity.get().getPosition();
			
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
			this.endAbility(p);
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
		
		Vec3d vecOrigin = new Vec3d(this.origin).add(0.5,0,0.5);
		Vec3d vecOrigin2 = new Vec3d(this.origin).add(0.5, 2.7, 0.5);
		
			if(!p.world.isRemote()) {
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

			
		
		super.duringPassive(p);
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
}
