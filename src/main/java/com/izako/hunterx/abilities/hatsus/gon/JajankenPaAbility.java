package com.izako.hunterx.abilities.hatsus.gon;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.entities.projectiles.AuraBlastProjectileEntity;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.ChargeableAbility;
import com.izako.hunterx.izapi.ability.IHandOverlay;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.wypi.WyHelper;
import com.izako.wypi.particles.GenericParticleData;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;

public class JajankenPaAbility extends ChargeableAbility implements ITrainable,IHandOverlay{

	public JajankenPaAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.CHARGING).setMaxCharging(100).setNenType(NenType.EMITTER);

	}
	@Override
	public String getId() {
		return "jajankenpa";
	}

	@Override
	public String getName() {
		return "Jajanken:Pa";
	}

	@Override
	public String getDesc() {
		return "Shoots strong aura blast.";
	}

	@Override
	public void onEndCharging(LivingEntity p) {
		double damage = Helper.fromRangeToRange(0, this.props.maxCharging, 10, 50, this.getChargingTimer());
		double scale = Helper.fromRangeToRange(0, this.props.maxCharging, 0.5d, 3d, this.getChargingTimer());
		damage = Helper.getTrueValue((float)damage, this, p);
		AuraBlastProjectileEntity entity = new AuraBlastProjectileEntity(p.world);
		entity.damage = damage;
		entity.scale = scale;
		entity.setPosition(p.getPosX(), p.getPosY() + 1.5, p.getPosZ());
		entity.setOwner(p);
		entity.shoot(p, p.rotationPitch, p.rotationYaw, 1f, 1.5f, 0f);
		entity.rotationPitch = p.rotationPitch;
		if(!p.world.isRemote()) {
			p.world.addEntity(entity);
		}
		super.onEndCharging(p);
	
	}
	@Override
	public boolean isFullArm() {
		return false;
	}

	@Override
	public void duringCharging(LivingEntity p) {
		super.duringCharging(p);
		p.addPotionEffect(new EffectInstance(Effects.SLOWNESS,20,2,false,false));
	
		IAbilityData abilityData = AbilityDataCapability.get(p);
		if(!p.world.isRemote() && p.ticksExisted % 8 == 0) {
			double xRange = (p.world.getRandom().nextDouble()  -0.5) / 2;
			double yRange = (p.world.getRandom().nextDouble())/ 2;
			double zRange = (p.world.getRandom().nextDouble() -0.5) / 2;

			GenericParticleData data = new GenericParticleData();
			data.setTexture(new ResourceLocation(Main.MODID, "textures/particles/genericaura2.png"));
			data.setMotion(-xRange / 20, -yRange/8, -zRange/20);
			data.setLife(20);
			data.setSize(0.15f);
			data.setColor(abilityData.getAuraColor().getRed() / 255.0f, abilityData.getAuraColor().getGreen() / 255.0f, abilityData.getAuraColor().getBlue() / 255.0f, 0.7f);
			 WyHelper.spawnParticles(data, (ServerWorld) p.world, p.getPosX()+ Helper.getRotatedX(0.5f, p.rotationYaw + 180), p.getPosY()+ 1.1 + yRange, p.getPosZ()+ Helper.getRotatedZ(0.5f, p.rotationYaw + 180));

		}
	}
}
