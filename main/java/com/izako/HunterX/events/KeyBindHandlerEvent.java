package com.izako.HunterX.events;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import com.izako.HunterX.Main;
import com.izako.HunterX.init.ListAbilities;
import com.izako.HunterX.init.ListKeybinds;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.AbilityPacketSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyBindingMap;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyBindHandlerEvent {

	@SubscribeEvent
	public void onPress(KeyInputEvent e) {
		
		int posX = Minecraft.getMinecraft().displayWidth;
		int posY = Minecraft.getMinecraft().displayHeight;

		EntityPlayerSP p = Minecraft.getMinecraft().player;
		IEntityStats stats = p.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		if(ListKeybinds.activate.isKeyDown()) {
			for(KeyBinding kb : Minecraft.getMinecraft().gameSettings.keyBindsHotbar)
			{
				kb.setKeyCode(0);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_0)) {
				ListAbilities.ABILITYTEN.giveAbility(p);
				System.out.println("success");
			}
		} else if(!ListKeybinds.activate.isKeyDown()) {
			for(KeyBinding kb : Minecraft.getMinecraft().gameSettings.keyBindsHotbar) {
				kb.setKeyCode(kb.getKeyCodeDefault());
			}
		}

		if (ListKeybinds.openSelection.isPressed()) {
			p.openGui(Main.instance, 0, Minecraft.getMinecraft().world, (int) p.posX, (int) p.posY, (int) p.posZ);
		}
		if (ListKeybinds.one.isPressed() && ListKeybinds.activate.isKeyDown()) {

			if (stats.getSlotsList()[0] != null) {
				ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(0), 0, 2, false));
			}
		}
		if (ListKeybinds.two.isPressed() && ListKeybinds.activate.isKeyDown()) {
			if (stats.getSlotsList()[1] != null) {
				ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(1), 1, 2, false));
			}

		}
		if (ListKeybinds.three.isPressed() && ListKeybinds.activate.isKeyDown()) {
			if (stats.getSlotsList()[2] != null) {

			
				ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(2), 2, 2, false));
			}
			}

			if (ListKeybinds.four.isPressed() && ListKeybinds.activate.isKeyDown()) {
				if (stats.getSlotsList()[3] != null) {

					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(3), 3, 2, false));
				}
			}
			if (ListKeybinds.five.isPressed() && ListKeybinds.activate.isKeyDown()) {
				if (stats.getSlotsList()[4] != null) {
					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(4), 4, 2, false));
				}
			}
			if (ListKeybinds.six.isPressed() && ListKeybinds.activate.isKeyDown()) {
				if (stats.getSlotsList()[5] != null) {
					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(5), 5, 2, false));
				}
			}
			if (ListKeybinds.seven.isPressed() && ListKeybinds.activate.isKeyDown()) {
				if (stats.getSlotsList()[6] != null) {
					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(6), 6, 2, false));
				}
			}
			if (ListKeybinds.eight.isPressed() && ListKeybinds.activate.isKeyDown()) {
				if (stats.getSlotsList()[7] != null) {
					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(7), 7, 2, false));
				}
			}
			if (ListKeybinds.nine.isPressed() && ListKeybinds.activate.isKeyDown()) {
				if (stats.getSlotsList()[8] != null) {

					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(stats.getAbilityNonNull(8), 8, 2, false));
				}
			}
		}
	}
	

