package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
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

public class StatsUpdatePacket {

	public StatsUpdatePacket() {
	}

	boolean get;
	INBT data;

	public StatsUpdatePacket(IHunterData data, boolean get) {

		this.data = HunterDataCapability.INSTANCE.getStorage().writeNBT(HunterDataCapability.INSTANCE,data, null);
	    this.get = get;
	}

	public void encode(PacketBuffer buf) {
		buf.writeBoolean(get);
		buf.writeCompoundTag((CompoundNBT) data);

	}

	public static StatsUpdatePacket decode(PacketBuffer buf) {
		StatsUpdatePacket msg = new StatsUpdatePacket();
		msg.get = buf.readBoolean();
		msg.data = buf.readCompoundTag();
		return msg;
	}

	public static void handle(StatsUpdatePacket msg, final Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				if(msg.get == false) {
				PlayerEntity p = ctx.get().getSender();
				IHunterData data = HunterDataCapability.get(p);
				HunterDataCapability.INSTANCE.getStorage().readNBT(HunterDataCapability.INSTANCE,data, null, msg.data);
				} else {

					PlayerEntity p = ctx.get().getSender();
					IHunterData data = HunterDataCapability.get(p);
					PacketHandler.INSTANCE.sendTo(new StatsUpdatePacket(data, true), ((ServerPlayerEntity) p).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
				}

			});
		} else if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
			ctx.get().enqueueWork(() -> {
				ClientHandler.handle(msg);
			});
		}
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler{
		@OnlyIn(Dist.CLIENT)
		public static void handle(StatsUpdatePacket msg) {
			ClientPlayerEntity p = Minecraft.getInstance().player;
			IHunterData data = HunterDataCapability.get(p);
			HunterDataCapability.INSTANCE.getStorage().readNBT(HunterDataCapability.INSTANCE,data, null, msg.data);

		}
	}
}
