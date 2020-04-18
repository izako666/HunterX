package com.izako.hunterx.data.abilitydata;

import com.izako.hunterx.Main;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityDataCloneEvent {

	@SubscribeEvent
	public static void onClone(PlayerEvent.Clone e) {
		
		PlayerEntity oldP = e.getOriginal();
		PlayerEntity p = e.getPlayer();
		IAbilityData oldData = AbilityDataCapability.get(oldP);
		IAbilityData data = AbilityDataCapability.get(p);
		data.setAbilities(oldData.getAbilities());
		data.setSlotAbilities(oldData.getSlotAbilities());
		data.setNenCapacity(oldData.getNenCapacity());
		data.setCurrentNen(oldData.getCurrentNen());
		data.setNenType(oldData.getNenType());
	}
}
