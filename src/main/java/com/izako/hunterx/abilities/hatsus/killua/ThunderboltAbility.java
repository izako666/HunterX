package com.izako.hunterx.abilities.hatsus.killua;

import java.util.List;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModEffects;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.wypi.WyHelper;
import com.izako.wypi.particles.GenericParticleData;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;

public class ThunderboltAbility extends Ability {

	public ThunderboltAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.ONUSE).setNenType(NenType.TRANSMUTER);
	}
 	boolean thunderBoltTime = false;
	@Override
	public String getId() {
		return "thunderbolt";
	}

	@Override
	public String getName() {
		return "Thunderbolt";
	}

	@Override
	public String getDesc() {
		return "First use causes a jump, second use spawns a thunderbolt.";
	}

	@Override
	public void use(LivingEntity p) {
		if(!thunderBoltTime) {
			if(!p.world.isRemote()) {
			p.setMotion(p.getMotion().add(0, 1.5, 0));
			p.velocityChanged = true;
			p.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 36, 20,true,true));
			}
			thunderBoltTime = true;
		} else {
			if(!p.world.isRemote()) {
			List<LivingEntity> entities = p.world.getEntitiesWithinAABB(LivingEntity.class, p.getBoundingBox().expand(1, -10, 1));
			entities.remove(p);
			for(int i = 1; i < 21; i++) {
				float posx = (float) (p.getPosX() + Math.cos(Math.toRadians(i * 18)));
				float posy = (float) (p.getPosY() - (i / 2));
				float posz = (float) (p.getPosZ() + Math.sin(Math.toRadians(i * 18)));
				
				GenericParticleData data = new GenericParticleData();
				data.setTexture(new ResourceLocation(Main.MODID, "textures/particles/lightning.png"));
				data.setMotion(0, 0, 0);
				data.setLife(20);
				data.setSize(0.15f);
				data.setColor(1f, 1f, 1f, 0.9f);
				 WyHelper.spawnParticles(data, (ServerWorld) p.world, posx, posy,posz);

			}
			LightningBoltEntity bolt = new LightningBoltEntity(p.world, p.getPosX(), p.getPosY() - 10, p.getPosZ(), false);
			for(LivingEntity entity : entities) {
				entity.attackEntityFrom(DamageSource.causeThrownDamage(p, bolt), Helper.getTrueValue(20, this, p));
				entity.addPotionEffect(new EffectInstance(ModEffects.PARALYSIS_EFFECT, 100, 1));
				
				System.out.println(entity.getHealth());
			}
			}
			thunderBoltTime = false;
		}
		super.use(p);
	}

}
