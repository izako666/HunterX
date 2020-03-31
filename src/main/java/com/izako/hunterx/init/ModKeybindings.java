package com.izako.hunterx.init;

import org.lwjgl.glfw.GLFW;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.gui.HunterScreen;
import com.izako.hunterx.networking.ModidPacketHandler;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class ModKeybindings {

	public static final KeyBinding HUNTER_KEYBINDING = new KeyBinding("key.huntergui",GLFW.GLFW_KEY_R, "key.categories.hntrx");

	public static void init()
	{
		ClientRegistry.registerKeyBinding(HUNTER_KEYBINDING);	
	}

	@SubscribeEvent
	public static void onKeyInput(KeyInputEvent event)
	{

		if(Minecraft.getInstance().currentScreen == null) {
		if(event.getKey() == HUNTER_KEYBINDING.getKey().getKeyCode()) {
		    IHunterData data = HunterDataCapability.get(Minecraft.getInstance().player);
			ModidPacketHandler.INSTANCE.sendToServer(new StatsUpdatePacket(data, true));
			Minecraft.getInstance().displayGuiScreen(new HunterScreen());
		}
		}
	
	}








}
