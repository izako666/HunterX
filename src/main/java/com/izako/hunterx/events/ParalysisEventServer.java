package com.izako.hunterx.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModEffects;

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ParalysisEventServer {

	@SubscribeEvent
	public static void tick(LivingUpdateEvent evt) {
		if(evt.getEntityLiving().isPotionActive(ModEffects.PARALYSIS_EFFECT)) {
			evt.getEntityLiving().setMotion(0, 0, 0);
		}
	}
}
