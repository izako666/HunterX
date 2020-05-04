package com.izako.hunterx.data.abilitydata;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModKeybindings;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.Ability.AbilityType;
import com.izako.hunterx.izapi.ability.ChargeableAbility;
import com.izako.hunterx.izapi.ability.PassiveAbility;
import com.izako.hunterx.networking.ModidPacketHandler;
import com.izako.hunterx.networking.packets.AbilityChargingEndPacket;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityDataUpdateEvent {

	@SubscribeEvent
	public static void livingUpdate(LivingUpdateEvent e) {
		if (e.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity p = (PlayerEntity) e.getEntityLiving();
			IAbilityData data = AbilityDataCapability.get(p);
			for (int i = 0; i < 8; i++) {
				Ability a = data.getAbilityInSlot(i);
				if (a != null) {
					if (a.getCooldown() > 0) {
						a.setCooldown(a.getCooldown() - 1);
					}
					if (a.isCharging()) {
						for (KeyBinding kb : ModKeybindings.ABILITYSLOTS) {
							if (kb.getKey().getKeyCode() == ModKeybindings.getKeybindFromSlot(a.getSlot()).getKey()
									.getKeyCode()) {
								if (!kb.isKeyDown()) {
									a.setCharging(false);
									((ChargeableAbility) a).onEndCharging(p);
									a.setCooldown(a.getMaxCooldown());
									a.setChargingTimer(0);
									ModidPacketHandler.INSTANCE.sendToServer(new AbilityChargingEndPacket(a.getSlot()));
								} else if(a.getChargingTimer() >= a.getMaxCharging()) {

									((ChargeableAbility) a).onEndCharging(p);
									a.setCooldown(a.getMaxCooldown());
									a.setCharging(false);
									a.setChargingTimer(0);
									ModidPacketHandler.INSTANCE.sendToServer(new AbilityChargingEndPacket(a.getSlot()));

								}
							}
						}
						if(a.getChargingTimer() < a.getMaxCharging()) {
							a.setChargingTimer(a.getChargingTimer() + 1);
							((ChargeableAbility) a).duringCharging(p);
						}
					}
					if (a.getType() == AbilityType.PASSIVE) {
						if (a.isInPassive()) {
							if (a.getPassiveTimer() > 0) {
								a.setPassiveTimer(a.getPassiveTimer() - 1);
							}
							if (a.getPassiveTimer() == 0) {
								a.setInPassive(false);
								((PassiveAbility) a).onEndPassive(p);
								a.setCooldown(a.getMaxCooldown());
								a.setPassiveTimer(a.getMaxPassive());
								continue;

							}
							((PassiveAbility) a).duringPassive(p);
						}
					}
				}
			}
		}
		e.setResult(Result.DEFAULT);
	}
}
