package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class AbilityUsePacket {


	
	public AbilityUsePacket() {}

	int slot;
	public AbilityUsePacket(int slot) {

		this.slot = slot;
	}
	
	public void encode(PacketBuffer buf) {

		buf.writeInt(slot);
	}
	
	public static AbilityUsePacket decode(PacketBuffer buf) {
		AbilityUsePacket msg = new AbilityUsePacket();
			msg.slot = buf.readInt();
		
		return msg;
	}
	public static void handle(AbilityUsePacket msg,  final Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity p = ctx.get().getSender();
				IAbilityData data = AbilityDataCapability.get(p);
				if(data.getAbilityInSlot(msg.slot) != null) {
					data.getAbilityInSlot(msg.slot).onUse(p);
				}
			});
		} else if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
		 ClientHandler.handle(msg, ctx);	
		}
		ctx.get().setPacketHandled(true);
	}
	
	@OnlyIn(Dist.CLIENT)
	static
	class ClientHandler {
		
		@OnlyIn(Dist.CLIENT)
		public static void handle(AbilityUsePacket msg, final Supplier<NetworkEvent.Context> ctx) {
			Minecraft.getInstance().enqueue(() -> {
				PlayerEntity p = Minecraft.getInstance().player;
				IAbilityData data = AbilityDataCapability.get(p);
				if(data.getAbilityInSlot(msg.slot) != null) {
					data.getAbilityInSlot(msg.slot).onUse(p);
				}

			});
		}
	}
}
