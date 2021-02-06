package com.izako.hunterx.init;

import org.lwjgl.glfw.GLFW;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.gui.CharacterCreatorScreen;
import com.izako.hunterx.gui.HunterScreen;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.Ability.AbilityType;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.AbilityUpdatePacket;
import com.izako.hunterx.networking.packets.AbilityUsePacket;
import com.izako.hunterx.networking.packets.SetActiveAbilityPacket;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class ModKeybindings {

	public static final KeyBinding HUNTER_KEYBINDING = new KeyBinding("key.huntergui",GLFW.GLFW_KEY_R, "key.categories.hntrx");
	public static final KeyBinding ACTIVATE_ABILITY_SCROLLER = new KeyBinding("key.activatescroller",GLFW.GLFW_KEY_G, "key.categories.hntrx");
	public static final KeyBinding USE_ABILITY = new KeyBinding("key.useability",GLFW.GLFW_KEY_GRAVE_ACCENT, "key.categories.hntrx");

	public static void init()
	{
		ClientRegistry.registerKeyBinding(HUNTER_KEYBINDING);	
		ClientRegistry.registerKeyBinding(ACTIVATE_ABILITY_SCROLLER);
		ClientRegistry.registerKeyBinding(USE_ABILITY);
	}

	@SubscribeEvent
	public static void onKeyInput(KeyInputEvent event)
	{

		if(Minecraft.getInstance().currentScreen != null)
			return;
		if(event.getKey() == ACTIVATE_ABILITY_SCROLLER.getKey().getKeyCode() && event.getAction() == 0) {
		    IHunterData data = HunterDataCapability.get(Minecraft.getInstance().player);
		    IAbilityData abilitydata = AbilityDataCapability.get(Minecraft.getInstance().player);

			data.setSelectingAbility(!data.isSelectingAbility());
		}
 
		if(event.getKey() == USE_ABILITY.getKey().getKeyCode()) {
		    IHunterData data = HunterDataCapability.get(Minecraft.getInstance().player);
		    IAbilityData abilitydata = AbilityDataCapability.get(Minecraft.getInstance().player);
			Ability abl = abilitydata.getSlotAbilities()[abilitydata.getActiveAbility()];
			if(abl != null) {
				if(abl.props.type == AbilityType.CHARGING || abl.props.type  == AbilityType.CHARGING_PASSIVE) {
					if(event.getAction() == 1) {
						PacketHandler.INSTANCE.sendToServer(new AbilityUsePacket(abilitydata.getActiveAbility(),true));
					}
				} else if(event.getAction() == 0) {
				PacketHandler.INSTANCE.sendToServer(new AbilityUsePacket(abilitydata.getActiveAbility(),true));
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



	@SubscribeEvent
	public static void scrollEvent(InputEvent.MouseScrollEvent evt) {
		
		PlayerEntity p = Minecraft.getInstance().player;
		IAbilityData data = AbilityDataCapability.get(p);
		IHunterData hunterData = HunterDataCapability.get(p);
		double scrollDirection = evt.getScrollDelta();
		if(hunterData.isSelectingAbility()) {
			if(evt.getScrollDelta() == 0.0) {
				return;
			} else if(scrollDirection > 0.0) {
				scrollDirection = -1;
				
			} else {
				scrollDirection = 1;
			}

		int newActiveAbility = (int) (data.getActiveAbility() + scrollDirection);
		
		if(newActiveAbility > 4) {
			newActiveAbility = 0;
		} else if(newActiveAbility < 0) {
			newActiveAbility = 4;
		}
		data.setActiveAbility(newActiveAbility);
		PacketHandler.INSTANCE.sendToServer(new SetActiveAbilityPacket(newActiveAbility));
		evt.setCanceled(true);
		} 
	}

}
