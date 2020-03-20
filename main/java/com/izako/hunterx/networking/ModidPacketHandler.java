package com.izako.hunterx.networking;

import com.izako.hunterx.networking.packets.HanzoSwordPacket;

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
	}
}
