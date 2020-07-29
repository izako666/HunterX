package com.izako.hunterx.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IOnPlayerRender;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.SyncAbilityRenderingPacket;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityRenderSyncEvents {

	public static void onStartTrack(PlayerEvent.StartTracking evt) {
		if(!(evt.getTarget() instanceof PlayerEntity))
			return;
		
		
		PlayerEntity tracker = evt.getPlayer();
		PlayerEntity tracked = (PlayerEntity)evt.getTarget();
		if(!tracker.world.isRemote()) {
		IAbilityData data = AbilityDataCapability.get(tracked);
		 for(Ability abl : data.getSlotAbilities()) {
			 if(abl instanceof IOnPlayerRender) {
				 PacketHandler.INSTANCE.sendTo(new SyncAbilityRenderingPacket(abl.getId(), tracked.getUniqueID(), abl.isActive()), ((ServerPlayerEntity)tracker).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
			 }
		 }
		}
	}
}
