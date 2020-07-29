package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.networking.PacketHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class AbilityUpdatePacket {

	public AbilityUpdatePacket() {
	}

boolean requestPacket;
INBT data;
	public AbilityUpdatePacket(IAbilityData data, boolean requestPacket) {

		this.data = AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, data, null);
		this.requestPacket = requestPacket;
	}

	public void encode(PacketBuffer buf) {

		buf.writeBoolean(requestPacket);
		buf.writeCompoundTag((CompoundNBT) this.data);
	}

	public static AbilityUpdatePacket decode(PacketBuffer buf) {
		AbilityUpdatePacket msg = new AbilityUpdatePacket();
		msg.requestPacket = buf.readBoolean();
		msg.data = buf.readCompoundTag();
		return msg;
	}

	public static void handle(AbilityUpdatePacket msg, final Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				if(msg.requestPacket == false) {
					PlayerEntity p = ctx.get().getSender();
					IAbilityData data = AbilityDataCapability.get(p);
					AbilityDataCapability.INSTANCE.getStorage().readNBT(AbilityDataCapability.INSTANCE, data, null, msg.data);
				} else {
					PlayerEntity p = ctx.get().getSender();
					IAbilityData data = AbilityDataCapability.get(p);
					PacketHandler.INSTANCE.sendTo(new AbilityUpdatePacket(data, true), ((ServerPlayerEntity) p).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
				}
			});
		} 
		else if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
			ctx.get().enqueueWork(() -> {
				ClientHandler.handle(msg);
			});
			ctx.get().setPacketHandled(true);
		}
	}

	public static class ClientHandler{
		@OnlyIn(Dist.CLIENT)
		public static void handle(AbilityUpdatePacket msg) {
			ClientPlayerEntity p = Minecraft.getInstance().player;

			IAbilityData data = AbilityDataCapability.get(p);
			AbilityDataCapability.INSTANCE.getStorage().readNBT(AbilityDataCapability.INSTANCE,data, null, msg.data);

			System.out.println("stuff");
		}
	}
}
