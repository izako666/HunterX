package com.izako.hunterx.abilities.basics;

import java.util.UUID;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IOnPlayerRender;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PassiveAbility;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.SyncAbilityRenderingPacket;
import com.izako.wypi.WyHelper;
import com.izako.wypi.particles.GenericParticleData;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;

public class RenAbility extends PassiveAbility implements IOnPlayerRender , ITrainable{

	public static final UUID attackModifierID = UUID.fromString("c199f71b-5e2b-447a-baca-c453bb287a0e");
	public static final UUID defenseModifierID = UUID.fromString("10abb734-49e0-4865-9383-19005b1bd92a");

	public RenAbility() {
		super();
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setConsumptionType(AuraConsumptionType.PERCENTAGE).setAuraConsumption(this::auraConsumptionEvent).setMaxPassive(Integer.MAX_VALUE).setMaxCooldown(20 * 15).setNenType(NenType.UNKNOWN); 
	}
	@Override
	public String getId() {
		return "ren";
	}

	@Override
	public String getName() {
		return "Ren";
	}

	
	@Override
	public String getDesc() {
		return "Ren explodes your aura output for attack and defense.";
	}
	public void onStartPassive(LivingEntity p) {
		AttributeModifier defenseMod = new AttributeModifier(defenseModifierID, "renmodifier", Helper.getTrueValue(15, this, p), Operation.ADDITION);
		AttributeModifier attackMod = new AttributeModifier(attackModifierID, "renmodifierattack", Helper.getTrueValue(30, this, p), Operation.ADDITION);
		if(p.getAttribute(SharedMonsterAttributes.ARMOR).getModifier(defenseModifierID) == null) {
		p.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(defenseMod);
		}
		if(p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(attackModifierID) == null) {
	   p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(attackMod);
		}
		}

	@Override
	public void duringPassive(LivingEntity p) {
		AttributeModifier defenseMod = new AttributeModifier(defenseModifierID, "renmodifier", Helper.getTrueValue(15, this, p), Operation.ADDITION);
		AttributeModifier attackMod = new AttributeModifier(attackModifierID, "renmodifierattack",Helper.getTrueValue(30, this, p), Operation.ADDITION);
		if(p.getAttribute(SharedMonsterAttributes.ARMOR).getModifier(defenseModifierID) == null) {
		p.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(defenseMod);
		}
		if(p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(attackModifierID) == null) {
	   p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(attackMod);
		}
		
		 if(!p.world.isRemote()) {
			int chance = p.world.getRandom().nextInt(100);
			if(chance > 97) {
				IAbilityData abilityData = AbilityDataCapability.get(p);
				double xRange = p.world.getRandom().nextDouble()  -0.5;
				double yRange = p.world.getRandom().nextDouble();
				double zRange = p.world.getRandom().nextDouble() -0.5;
				GenericParticleData data = new GenericParticleData();
				data.setTexture(new ResourceLocation(Main.MODID, "textures/particles/genericaura2.png"));
				data.setMotion(0, 0.05, 0);
				data.setLife(20);
				data.setSize(0.15f);
				data.setColor(abilityData.getAuraColor().getRed() / 255.0f, abilityData.getAuraColor().getGreen() / 255.0f, abilityData.getAuraColor().getBlue() / 255.0f, 0.7f);
			 WyHelper.spawnParticles(data, (ServerWorld) p.world, p.getPosX() + xRange, p.getPosY() + yRange, p.getPosZ() + zRange);
			} 
		} 
		
	}

	@Override
	public void onEndPassive(LivingEntity p) {
		p.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier(defenseModifierID);
		p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(attackModifierID);
		
	}
	
	private int auraConsumptionEvent() {
		if(this.getPassiveTimer() % 20 == 0) {
			return (int) (6 * this.getCurrentAuraConScale());
		}
		return 0;
	}
}
