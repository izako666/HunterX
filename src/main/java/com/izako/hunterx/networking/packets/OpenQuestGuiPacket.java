package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.gui.QuestScreen;
import com.izako.hunterx.izapi.quest.IQuestGiver;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class OpenQuestGuiPacket {


	public OpenQuestGuiPacket() {
	}


	public int entity;
	public OpenQuestGuiPacket(Entity qgiver) {

		this.entity = qgiver.getEntityId();
	}

	public void encode(PacketBuffer buf) {

		buf.writeInt(entity);
	}

	public static OpenQuestGuiPacket decode(PacketBuffer buf) {
		OpenQuestGuiPacket msg = new OpenQuestGuiPacket();
		msg.entity = buf.readInt();
		return msg;
	}

	public static void handle(OpenQuestGuiPacket msg, final Supplier<NetworkEvent.Context> ctx) {

		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
			ctx.get().enqueueWork(() -> {
				ClientHandler.handle(msg);
			});
		}
		 ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler{
		@OnlyIn(Dist.CLIENT)
		public static void handle(OpenQuestGuiPacket msg) {
			PlayerEntity p = Minecraft.getInstance().player;

			Entity e = Minecraft.getInstance().world.getEntityByID(msg.entity);
			IQuestGiver qgiver = (IQuestGiver) e;
			Minecraft.getInstance().displayGuiScreen(new QuestScreen(qgiver));
		}
	}
}
