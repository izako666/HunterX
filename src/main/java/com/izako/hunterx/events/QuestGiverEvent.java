package com.izako.hunterx.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.quest.IQuestGiver;
import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.OpenQuestGuiPacket;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class QuestGiverEvent {

	@SubscribeEvent
	public static void interact(PlayerInteractEvent.EntityInteract e) {
		
		if(e.getTarget() instanceof IQuestGiver) {
			
			e.getTarget().setRotationYawHead(e.getPlayer().rotationYawHead + 180f);
			if(!e.getPlayer().world.isRemote) {
			IHunterData data = HunterDataCapability.get(e.getPlayer());
			IQuestGiver giver = (IQuestGiver) e.getTarget();
		
			
			
			PacketHandler.INSTANCE.sendTo(new StatsUpdatePacket(data,false), ((ServerPlayerEntity) e.getPlayer()).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
			PacketHandler.INSTANCE.sendTo(new OpenQuestGuiPacket(e.getTarget()), ((ServerPlayerEntity) e.getPlayer()).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
			
			}
		}
	}
}
