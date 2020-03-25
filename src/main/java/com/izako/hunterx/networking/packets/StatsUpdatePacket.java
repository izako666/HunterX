package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class StatsUpdatePacket {

	
	public StatsUpdatePacket() {}
	double healthStat;
	double speedStat;
	double attackStat;
	double defenseStat;
	public StatsUpdatePacket(IHunterData data) {
		
		this.healthStat = data.getHealthStat();
		this.speedStat = data.getSpeedStat();
		this.attackStat = data.getAttackStat();
		this.defenseStat = data.getDefenseStat();
	}
	
	public void encode(PacketBuffer buf) {
		buf.writeDouble(healthStat);
		buf.writeDouble(speedStat);
		buf.writeDouble(attackStat);
		buf.writeDouble(defenseStat);

	}
	
	public static StatsUpdatePacket decode(PacketBuffer buf) {
		StatsUpdatePacket msg = new StatsUpdatePacket();
		msg.healthStat = buf.readDouble();
		msg.speedStat = buf.readDouble();
		msg.attackStat = buf.readDouble();
		msg.defenseStat = buf.readDouble();
		return msg;
	}
	public static void handle(StatsUpdatePacket msg,  final Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
			
				PlayerEntity p = ctx.get().getSender();
				IHunterData data = HunterDataCapability.get(p);
                data.setHealthStat(msg.healthStat);
                data.setSpeedStat(msg.speedStat);
                data.setAttackStat(msg.attackStat);
                data.setDefenseStat(msg.defenseStat);

			
			
			});
		} else if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity p = Minecraft.getInstance().player;
				IHunterData data = HunterDataCapability.get(p);
                data.setHealthStat(msg.healthStat);
                data.setSpeedStat(msg.speedStat);
                data.setAttackStat(msg.attackStat);
                data.setDefenseStat(msg.defenseStat);

			});
		}
	}
}
