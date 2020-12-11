package com.izako.hunterx.abilities.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.init.ModEffects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class SharpenAuraEvents {

	@SubscribeEvent
	public static void damage(LivingDamageEvent evt) {
		if(evt.getSource().getImmediateSource() == null || evt.getSource().getTrueSource() == null)
			return;
		
		if(evt.getSource().getImmediateSource().getEntityId() == evt.getSource().getTrueSource().getEntityId()) {
			IAbilityData data = AbilityDataCapability.get(evt.getEntityLiving());
			if(data.hasActiveAbility(ModAbilities.SHARPEN_AURA_ABILITY)) {
				if(evt.getSource().getTrueSource() instanceof LivingEntity) {
					((LivingEntity)evt.getSource().getTrueSource()).addPotionEffect(new EffectInstance(ModEffects.BLEEDING_EFFECT, 60 * 20, 3, false, false));
				}
			}
		}	}
}
