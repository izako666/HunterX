package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.networking.packets.AbilityUsePacket.ClientHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SetActiveAbilityPacket {

	
	public SetActiveAbilityPacket() {}

	int activeAbility;
	public SetActiveAbilityPacket(int activeAbility) {

		this.activeAbility = activeAbility;
	}
	
	public void encode(PacketBuffer buf) {

		buf.writeInt(activeAbility);
	}
	
	public static SetActiveAbilityPacket decode(PacketBuffer buf) {
		SetActiveAbilityPacket msg = new SetActiveAbilityPacket();
			msg.activeAbility = buf.readInt();
		
		return msg;
	}
	public static void handle(SetActiveAbilityPacket msg,  final Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity p = ctx.get().getSender();
				IAbilityData data = AbilityDataCapability.get(p);
				data.setActiveAbility(msg.activeAbility);
			});
		} 
		ctx.get().setPacketHandled(true);
	}
	

}
