package com.izako.hunterx.networking;

import com.izako.hunterx.networking.packets.AbilityChargingEndPacket;
import com.izako.hunterx.networking.packets.AbilityUpdatePacket;
import com.izako.hunterx.networking.packets.AbilityUsePacket;
import com.izako.hunterx.networking.packets.HanzoSwordPacket;
import com.izako.hunterx.networking.packets.ModifierUpdatePacket;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ModidPacketHandler {

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
	    new ResourceLocation("hntrx", "main"),
	    () -> PROTOCOL_VERSION,
	    PROTOCOL_VERSION::equals,
	    PROTOCOL_VERSION::equals
	);
	
	public static void registerPackets() {
		int packet = 0;
		INSTANCE.registerMessage(packet++, HanzoSwordPacket.class, HanzoSwordPacket::encode, HanzoSwordPacket::decode, HanzoSwordPacket::handle);
		INSTANCE.registerMessage(packet++, StatsUpdatePacket.class, StatsUpdatePacket::encode, StatsUpdatePacket::decode, StatsUpdatePacket::handle);
		INSTANCE.registerMessage(packet++, ModifierUpdatePacket.class, ModifierUpdatePacket::encode, ModifierUpdatePacket::decode, ModifierUpdatePacket::handle);
        INSTANCE.registerMessage(packet++, AbilityUsePacket.class, AbilityUsePacket::encode, AbilityUsePacket::decode, AbilityUsePacket::handle);
        INSTANCE.registerMessage(packet++, AbilityUpdatePacket.class, AbilityUpdatePacket::encode, AbilityUpdatePacket::decode, AbilityUpdatePacket::handle);
        INSTANCE.registerMessage(packet++, AbilityChargingEndPacket.class, AbilityChargingEndPacket::encode, AbilityChargingEndPacket::decode, AbilityChargingEndPacket::handle);
	}
}
