package com.izako.HunterX.events;

import com.izako.HunterX.Main;
import com.izako.HunterX.init.ListAbilities;
import com.izako.HunterX.init.ListKeybinds;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.AbilityPacketSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyBindHandlerEvent {

	@SubscribeEvent
	public void onPress(KeyInputEvent e) {
		
		EntityPlayerSP p = Minecraft.getMinecraft().player;
		IEntityStats stats = p.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		
		if(ListKeybinds.openSelection.isPressed()) {
			p.openGui(Main.instance, 0, Minecraft.getMinecraft().world, (int)p.posX, (int)p.posY, (int)p.posZ);
		}
		if(ListKeybinds.one.isPressed() && ListKeybinds.activate.isPressed()) {

			if(stats.getSlotsList()[0] != null) {
				ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(0), 0, 2));
			}
		}
		if(ListKeybinds.two.isPressed() && ListKeybinds.activate.isPressed()) {
			ListAbilities.ABILITY_JAJANKEN.giveAbility(p);
			if(stats.getSlotsList()[1] != null) {
				ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(1), 1, 2));
			}

		}
		if(ListKeybinds.three.isPressed() && ListKeybinds.activate.isPressed()) {
			if(stats.getSlotsList()[2] != null) {

				ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(2), 2, 2));
			}
		
		if(ListKeybinds.four.isPressed() && ListKeybinds.activate.isPressed()) {
			if(stats.getSlotsList()[3] != null) {

				ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(3), 3, 2));
			}
		}
		if(ListKeybinds.five.isPressed() && ListKeybinds.activate.isPressed()) {
			if(stats.getSlotsList()[4] != null) {
				ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(4), 4, 2));
			}
		}
		if(ListKeybinds.six.isPressed() && ListKeybinds.activate.isPressed()) {
			if(stats.getSlotsList()[5] != null) {
				ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(5), 5, 2));
			}
		}
		if(ListKeybinds.seven.isPressed() && ListKeybinds.activate.isPressed()) {
			if(stats.getSlotsList()[6] != null) {
				ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(6), 6, 2));
			}
		}
		if(ListKeybinds.eight.isPressed() && ListKeybinds.activate.isPressed()) {
			if(stats.getSlotsList()[7] != null) {
				ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(7), 7, 2));
			}
		}
		if(ListKeybinds.nine.isPressed() && ListKeybinds.activate.isPressed()) {
			if(stats.getSlotsList()[8] != null) {
				ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(8), 8, 2));
			}
		}
}
	}}
