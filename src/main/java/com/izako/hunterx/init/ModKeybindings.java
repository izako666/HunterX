package com.izako.hunterx.init;

import org.lwjgl.glfw.GLFW;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.gui.CharacterCreatorScreen;
import com.izako.hunterx.gui.HunterScreen;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.AbilityUpdatePacket;
import com.izako.hunterx.networking.packets.AbilityUsePacket;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class ModKeybindings {

	public static final KeyBinding HUNTER_KEYBINDING = new KeyBinding("key.huntergui",GLFW.GLFW_KEY_R, "key.categories.hntrx");
	public static final KeyBinding ACTIVATE = new KeyBinding("key.activate",GLFW.GLFW_KEY_GRAVE_ACCENT, "key.categories.hntrx");
	public static final KeyBinding SLOT1 = new KeyBinding("key.slot.one",GLFW.GLFW_KEY_1, "key.categories.hntrx");
	public static final KeyBinding SLOT2 = new KeyBinding("key.slot.two",GLFW.GLFW_KEY_2, "key.categories.hntrx");
	public static final KeyBinding SLOT3 = new KeyBinding("key.slot.three",GLFW.GLFW_KEY_3, "key.categories.hntrx");
	public static final KeyBinding SLOT4 = new KeyBinding("key.slot.four",GLFW.GLFW_KEY_4, "key.categories.hntrx");
	public static final KeyBinding SLOT5 = new KeyBinding("key.slot.five",GLFW.GLFW_KEY_5, "key.categories.hntrx");
	public static final KeyBinding SLOT6 = new KeyBinding("key.slot.six",GLFW.GLFW_KEY_6, "key.categories.hntrx");
	public static final KeyBinding SLOT7 = new KeyBinding("key.slot.seven",GLFW.GLFW_KEY_7, "key.categories.hntrx");
	public static final KeyBinding SLOT8 = new KeyBinding("key.slot.eight",GLFW.GLFW_KEY_8, "key.categories.hntrx");

	public static KeyBinding[] ABILITYSLOTS = new KeyBinding[] {
			SLOT1, SLOT2, SLOT3, SLOT4, SLOT5, SLOT6, SLOT7, SLOT8
	};
	public static void init()
	{
		ClientRegistry.registerKeyBinding(HUNTER_KEYBINDING);	
		ClientRegistry.registerKeyBinding(ACTIVATE);	
		ClientRegistry.registerKeyBinding(SLOT1);	
		ClientRegistry.registerKeyBinding(SLOT2);	
		ClientRegistry.registerKeyBinding(SLOT3);	
		ClientRegistry.registerKeyBinding(SLOT4);	
		ClientRegistry.registerKeyBinding(SLOT5);	
		ClientRegistry.registerKeyBinding(SLOT6);	
		ClientRegistry.registerKeyBinding(SLOT7);	
		ClientRegistry.registerKeyBinding(SLOT8);	

	}

	@SubscribeEvent
	public static void onKeyInput(KeyInputEvent event)
	{

		if(ModKeybindings.ACTIVATE.isKeyDown()) {
			
			for(KeyBinding kb : Minecraft.getInstance().gameSettings.keyBindsHotbar) {
				kb.bind(InputMappings.getInputByCode(GLFW.GLFW_KEY_UNKNOWN, 0));
			}
		} if(event.getKey() == ModKeybindings.ACTIVATE.getKey().getKeyCode() && event.getAction() == 2) {
			for(KeyBinding kb : Minecraft.getInstance().gameSettings.keyBindsHotbar) {
				kb.bind(kb.getDefault());
			}
		}
		
		for(KeyBinding kb : ModKeybindings.ABILITYSLOTS) {
			if(ModKeybindings.ACTIVATE.isKeyDown() && kb.isPressed()) {
				PlayerEntity p = Minecraft.getInstance().player;
				IAbilityData abldata = AbilityDataCapability.get(Minecraft.getInstance().player);
				if(abldata.getAbilityInSlot(ModKeybindings.getSlotFromBinding(kb)) != null) {
					abldata.getAbilityInSlot(ModKeybindings.getSlotFromBinding(kb)).onUse(p);
					PacketHandler.INSTANCE.sendToServer(new AbilityUsePacket(ModKeybindings.getSlotFromBinding(kb)));
				}
			}
		}
		
		if(Minecraft.getInstance().currentScreen == null) {
		if(event.getKey() == HUNTER_KEYBINDING.getKey().getKeyCode()) {
		    IHunterData data = HunterDataCapability.get(Minecraft.getInstance().player);
		    IAbilityData abilitydata = AbilityDataCapability.get(Minecraft.getInstance().player);
		    if(!data.isCharacterMade()) {
		    	Minecraft.getInstance().displayGuiScreen(new CharacterCreatorScreen());
		    } else {
		    PacketHandler.INSTANCE.sendToServer(new AbilityUpdatePacket(abilitydata, true));
			PacketHandler.INSTANCE.sendToServer(new StatsUpdatePacket(data, true));
			Minecraft.getInstance().displayGuiScreen(new HunterScreen());
		    }
		}
		}
	
	}




 
	public static int getSlotFromBinding(KeyBinding kb) {
		if(kb.getKey().getKeyCode() == ModKeybindings.SLOT1.getKey().getKeyCode()) {
			return 0;
		} 
		else if (kb.getKey().getKeyCode() == ModKeybindings.SLOT2.getKey().getKeyCode()) {
			return 1;
		}
		else if (kb.getKey().getKeyCode() == ModKeybindings.SLOT3.getKey().getKeyCode()) {
			return 2;
		}
		else if (kb.getKey().getKeyCode() == ModKeybindings.SLOT4.getKey().getKeyCode()) {
			return 3;
		}
		else if (kb.getKey().getKeyCode() == ModKeybindings.SLOT5.getKey().getKeyCode()) {
			return 4;
		}
		else if (kb.getKey().getKeyCode() == ModKeybindings.SLOT6.getKey().getKeyCode()) {
			return 5;
		}
		else if (kb.getKey().getKeyCode() == ModKeybindings.SLOT7.getKey().getKeyCode()) {
			return 6;
		}
		else if (kb.getKey().getKeyCode() == ModKeybindings.SLOT8.getKey().getKeyCode()) {
			return 7;
		}

		return -1;
	}


	public static KeyBinding getKeybindFromSlot(int slot) {
		switch(slot) {
		case(0):
			return ModKeybindings.SLOT1;
		case(1):
			return ModKeybindings.SLOT2;
		case(2):
			return ModKeybindings.SLOT3;
		case(3):
			return ModKeybindings.SLOT4;
		case(4):
			return ModKeybindings.SLOT5;
		case(5):
			return ModKeybindings.SLOT6;
		case(6):
			return ModKeybindings.SLOT7;
		case(7):
			return ModKeybindings.SLOT8;
		}
		return null;
	}

}
