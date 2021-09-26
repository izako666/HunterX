package com.izako.hunterx.abilities.hatsus.kalluto;

import com.izako.hunterx.entities.projectiles.PaperProjectileEntity;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class MeanderingDanceAbility extends Ability {

	public MeanderingDanceAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.ONUSE);
	}
	@Override
	public String getId() {
		return "meandering_dance";
	}

	@Override
	public String getName() {
		return "Meandering Dance";
	}

	@Override
	public String getDesc() {
		return "A projectile is shot made up of condensed confetti at the marked target. Use in correlation with Paper Mark.";
	}

	@Override
	public void onUse(LivingEntity p) {

		PaperProjectileEntity proj = new PaperProjectileEntity(p.world, p);
		proj.setPosition(p.getPosX(), p.getPosY(), p.getPosZ());
		proj.setOwner(p);
		proj.damage = 25;
		if(!p.world.isRemote()) {
		p.world.addEntity(proj);
		}
		super.onUse(p);
	}

	public static Vec3d getPositionInCircle(Vec3d origin, double rotation, float increment,float radius) {
		float radianRotation = (float) Helper.fromRangeToRange(0, 360, 0, Math.PI, rotation);
		float finalIncrement = radianRotation + increment;
		if(finalIncrement > Math.PI) {
			finalIncrement -= Math.PI;
		}
		double posX = origin.getX() + (radius * Math.cos(finalIncrement*2));
		double posY = origin.getY();
		double posZ = origin.getZ() + (radius * Math.sin(finalIncrement*2));

		Vec3d vec3d = new Vec3d(posX,posY,posZ);
		
		return vec3d;
	}

}
