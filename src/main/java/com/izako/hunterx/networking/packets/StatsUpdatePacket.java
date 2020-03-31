package com.izako.hunterx.networking.packets;

import java.util.HashMap;
import java.util.function.Supplier;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
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
					PlayerEntity serverP = ctx.get().getSender();
					IHunterData serverData = HunterDataCapability.get(serverP);

					PlayerEntity p = Minecraft.getInstance().player;
					IHunterData data = HunterDataCapability.get(p);
					data.setAttackStat(serverData.getAttackStat());
					data.setHealthStat(serverData.getHealthStat());
					data.setSpeedStat(serverData.getSpeedStat());
					data.setDefenseStat(serverData.getDefenseStat());
					data.setQuests(serverData.getQuests());

				}

			});
		} else if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
			ctx.get().enqueueWork(() -> {

			});
		}
	}
}
