package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.gui.ComputerScreen;
import com.izako.hunterx.gui.SequencedString;
import com.izako.hunterx.izapi.quest.IQuestGiver;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class ActivateComputerPacket {



	public ActivateComputerPacket() {
	}



	public void encode(PacketBuffer buf) {

	}

	public static ActivateComputerPacket decode(PacketBuffer buf) {
		ActivateComputerPacket msg = new ActivateComputerPacket();
		return msg;
	}

	public static void handle(ActivateComputerPacket msg, final Supplier<NetworkEvent.Context> ctx) {

		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
			ctx.get().enqueueWork(() -> {
				ClientHandler.handle(msg);
			});
		}
		 ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler{
		@OnlyIn(Dist.CLIENT)
		public static void handle(ActivateComputerPacket msg) {
			Minecraft.getInstance().displayGuiScreen(new ComputerScreen());
			
		}
	}
}
