package com.izako.hunterx.init;

import com.izako.hunterx.Main;
import com.izako.hunterx.izapi.HunterEffect;
import com.izako.hunterx.registerers.ModEventSubscriber;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEffects {

	public static final Effect BLEEDING_EFFECT = new HunterEffect(EffectType.HARMFUL, 10357504);
	public static final Effect PARALYSIS_EFFECT = new HunterEffect(EffectType.HARMFUL, 3195619);

	@SubscribeEvent
	public static void register(RegistryEvent.Register<Effect> evt) {
		
		evt.getRegistry().registerAll(ModEventSubscriber.setup(BLEEDING_EFFECT, "bleeding")
				,ModEventSubscriber.setup(PARALYSIS_EFFECT, "paralysis"));
		
	}
}
