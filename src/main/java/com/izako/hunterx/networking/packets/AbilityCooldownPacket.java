package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class AbilityCooldownPacket {

	public AbilityCooldownPacket() {
	}

	int slot;
	int cooldown;
	public AbilityCooldownPacket(int slot, int cooldown) {

		this.slot = slot;
		this.cooldown = cooldown;
	}

	public void encode(PacketBuffer buf) {

		buf.writeInt(slot);
		buf.writeInt(cooldown);
	}

	public static AbilityCooldownPacket decode(PacketBuffer buf) {
		AbilityCooldownPacket msg = new AbilityCooldownPacket();
		msg.slot = buf.readInt();
		msg.cooldown = buf.readInt();
		return msg;
	}

	public static void handle(AbilityCooldownPacket msg, final Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
			ctx.get().enqueueWork(() -> {
				ClientHandler.handle(msg);
			});
			ctx.get().setPacketHandled(true);
		}
	}

	public static class ClientHandler {
		@OnlyIn(Dist.CLIENT)
		public static void handle(AbilityCooldownPacket msg) {
			ClientPlayerEntity p = Minecraft.getInstance().player;

			IAbilityData data = AbilityDataCapability.get(p);
			data.getAbilityInSlot(msg.slot).setCooldown(msg.cooldown);

		}
	}

}
