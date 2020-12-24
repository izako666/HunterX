package com.izako.hunterx.abilities.hatsus.killua;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.Ability.AbilityType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class WhirlwindAbility extends LightningSpeedAbility {

	public WhirlwindAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxPassive(20 * 20).setMaxCooldown(60 * 20).setNenType(NenType.TRANSMUTER);
	}

	@Override
	public void onStartPassive(LivingEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		float amount =(float) (data.getNenCapacity() * 0.8);
		
		if(data.getCurrentNen() >= amount) {
			data.setCurrentNen((int) (data.getCurrentNen() - amount));
		} else {
			this.endAbility(p);
		}
		super.onStartPassive(p);
	}

	@Override
	public String getId() {
		return "whirlwind";
	}

	@Override
	public String getName() {
		return "GodSpeed: Whirlwind";
	}

	@Override
	public String getDesc() {
		return "for 20 seconds you immediately counter any attack and dodge any attack that comes to you.";
	}

	@SubscribeEvent
	public static void damage(LivingDamageEvent evt) {
		IAbilityData data = AbilityDataCapability.get(evt.getEntityLiving());
		
		if(data.hasActiveAbility(ModAbilities.WHIRLWIND_ABILITY)) {
			if(evt.getSource().getImmediateSource() == evt.getSource().getTrueSource()) {
				evt.getSource().getTrueSource().attackEntityFrom(DamageSource.causeMobDamage(evt.getEntityLiving()), evt.getAmount() / 2);
			}
			evt.setCanceled(true);
		}
	}
}
