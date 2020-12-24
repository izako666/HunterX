package com.izako.hunterx.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class ParalysisEvent {

	@SubscribeEvent
	public static void keyInput(InputUpdateEvent e) {
		
        ClientPlayerEntity p = Minecraft.getInstance().player;
		if(!p.isPotionActive(ModEffects.PARALYSIS_EFFECT))
		     return;
		

		e.getMovementInput().moveForward = 0;
		e.getMovementInput().moveStrafe = 0;
		e.getMovementInput().jump = false;
		e.getMovementInput().sneaking = false;

	}
}
