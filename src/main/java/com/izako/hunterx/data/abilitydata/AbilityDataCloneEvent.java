package com.izako.hunterx.data.abilitydata;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.networking.ModidPacketHandler;
import com.izako.hunterx.networking.packets.AbilityUpdatePacket;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;

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
		data.setAuraColor(oldData.getAuraColor().getRGB());
		data.setIsNenUser(oldData.isNenUser());
		
		//ModidPacketHandler.INSTANCE.sendTo(new AbilityUpdatePacket(data, false), ((ServerPlayerEntity) p).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
	}
	
	@SubscribeEvent
	public static void onDeath(PlayerEvent.PlayerRespawnEvent evt) {
		PlayerEntity p = evt.getPlayer();
		IAbilityData data = AbilityDataCapability.get(p);
		IHunterData hData = HunterDataCapability.get(p);
		if(!p.world.isRemote) {
			ModidPacketHandler.INSTANCE.sendTo(new AbilityUpdatePacket(data, false), ((ServerPlayerEntity) p).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
			ModidPacketHandler.INSTANCE.sendTo(new StatsUpdatePacket(hData, false), ((ServerPlayerEntity) p).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
		}
	}
	@SubscribeEvent
	public static void onJoinWorld(PlayerEvent.PlayerLoggedInEvent e) {
		PlayerEntity p = e.getPlayer();
		if(!p.world.isRemote) {
			IAbilityData data = AbilityDataCapability.get(p);
		ModidPacketHandler.INSTANCE.sendTo(new AbilityUpdatePacket(data, false), ((ServerPlayerEntity) p).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
		}
	}
	
	@SubscribeEvent
	public static void changeDimensionEvent(PlayerEvent.PlayerChangedDimensionEvent evt) {
		PlayerEntity p = evt.getPlayer();
		if(!p.world.isRemote) {
			IAbilityData data = AbilityDataCapability.get(p);
		ModidPacketHandler.INSTANCE.sendTo(new AbilityUpdatePacket(data, false), ((ServerPlayerEntity) p).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
		}
	}
}
