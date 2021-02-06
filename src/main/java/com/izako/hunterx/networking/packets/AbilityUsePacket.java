package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.networking.PacketHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class AbilityUsePacket {


	
	public AbilityUsePacket() {}

	int slot;
	boolean doubleUse;
	public AbilityUsePacket(int slot, boolean doubleUse) {

		this.slot = slot;
		this.doubleUse = doubleUse;
	}
	
	public void encode(PacketBuffer buf) {

		buf.writeInt(slot);
		buf.writeBoolean(doubleUse);
	}
	
	public static AbilityUsePacket decode(PacketBuffer buf) {
		AbilityUsePacket msg = new AbilityUsePacket();
			msg.slot = buf.readInt();
			msg.doubleUse = buf.readBoolean();
		
		return msg;
	}
	public static void handle(AbilityUsePacket msg,  final Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				ServerPlayerEntity p = ctx.get().getSender();
				IAbilityData data = AbilityDataCapability.get(p);
				if(data.getAbilityInSlot(msg.slot) != null) {
					if(msg.doubleUse && data.getAbilityInSlot(msg.slot).getCooldown() <= 0) {
						PacketHandler.INSTANCE.sendTo(new RefreshNenPacket(data.getCurrentNen()), p.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
						PacketHandler.INSTANCE.sendTo(new AbilityCooldownPacket(msg.slot, 0), p.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
						PacketHandler.INSTANCE.sendTo(new AbilityUsePacket(msg.slot, false), p.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
					}

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
