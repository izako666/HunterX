package com.izako.hunterx.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModEffects;

import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class BleedingEvent {

	@SubscribeEvent
	public static void tick(LivingUpdateEvent evt) {
		if(evt.getEntityLiving().ticksExisted % 100 == 0) {
			
			if(evt.getEntityLiving().isPotionActive(ModEffects.BLEEDING_EFFECT)) {
				evt.getEntityLiving().attackEntityFrom(DamageSource.MAGIC, evt.getEntityLiving().getActivePotionEffect(ModEffects.BLEEDING_EFFECT).getAmplifier());
			}
		}
	}
}
