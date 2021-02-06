package com.izako.hunterx.events;

import java.util.Arrays;
import java.util.List;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IOnPlayerRender;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.SyncAbilityRenderingPacket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityRenderSyncEvents {

	@SubscribeEvent
	public static void onStartTrack(PlayerEvent.StartTracking evt) {
	
		if(!(evt.getTarget() instanceof LivingEntity))
			return;

		PlayerEntity tracker = evt.getPlayer();
		LivingEntity tracked = (LivingEntity) evt.getTarget();
		if(!tracker.world.isRemote()) {
		IAbilityData data = AbilityDataCapability.get(tracked);
		List<Ability> list = data.getAbilities();
		if(tracked instanceof PlayerEntity) {
			list = Arrays.asList(data.getSlotAbilities());
		}
		 for(Ability abl : list) {
			 if(abl instanceof IOnPlayerRender) {
				 if(data.hasActiveAbility(ModAbilities.IN_ABILITY))
					 return;
				 PacketHandler.INSTANCE.sendTo(new SyncAbilityRenderingPacket(abl.getId(), tracked.getEntityId(), abl.isActive(),data.getAuraColor()), ((ServerPlayerEntity)tracker).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
			 }
		 }
		}
	}
}
