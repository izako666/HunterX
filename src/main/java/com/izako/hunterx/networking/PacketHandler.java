package com.izako.hunterx.networking;

import com.izako.hunterx.networking.packets.AbilityChargingEndPacket;
import com.izako.hunterx.networking.packets.AbilityCooldownPacket;
import com.izako.hunterx.networking.packets.AbilityUpdatePacket;
import com.izako.hunterx.networking.packets.AbilityUsePacket;
import com.izako.hunterx.networking.packets.ActivateAbilityPacket;
import com.izako.hunterx.networking.packets.ActivateComputerPacket;
import com.izako.hunterx.networking.packets.CGiveItemStackPacket;
import com.izako.hunterx.networking.packets.CharacterInitPacket;
import com.izako.hunterx.networking.packets.ChoiceQuestActivateEntryPacket;
import com.izako.hunterx.networking.packets.EnUpdatePacket;
import com.izako.hunterx.networking.packets.HanzoSwordPacket;
import com.izako.hunterx.networking.packets.LockOnPacket;
import com.izako.hunterx.networking.packets.ModifierUpdatePacket;
import com.izako.hunterx.networking.packets.OpenQuestGuiPacket;
import com.izako.hunterx.networking.packets.PunchAbilityPacket;
import com.izako.hunterx.networking.packets.QuestProgressPacket;
import com.izako.hunterx.networking.packets.QuestUpdatePacket;
import com.izako.hunterx.networking.packets.RefreshNenPacket;
import com.izako.hunterx.networking.packets.SetActiveAbilityPacket;
import com.izako.hunterx.networking.packets.SetQuestPacket;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;
import com.izako.hunterx.networking.packets.SyncAbilityRenderingPacket;
import com.izako.hunterx.networking.packets.UpdatePCStockPacket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

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
	    INSTANCE.registerMessage(packet++, OpenQuestGuiPacket.class, OpenQuestGuiPacket::encode, OpenQuestGuiPacket::decode, OpenQuestGuiPacket::handle);
        INSTANCE.registerMessage(packet++, SetQuestPacket.class, SetQuestPacket::encode, SetQuestPacket::decode, SetQuestPacket::handle);
        INSTANCE.registerMessage(packet++, CharacterInitPacket.class, CharacterInitPacket::encode, CharacterInitPacket::decode, CharacterInitPacket::handle);
        INSTANCE.registerMessage(packet++, SyncAbilityRenderingPacket.class, SyncAbilityRenderingPacket::encode, SyncAbilityRenderingPacket::decode, SyncAbilityRenderingPacket::handle);
	    INSTANCE.registerMessage(packet++, PunchAbilityPacket.class, PunchAbilityPacket::encode, PunchAbilityPacket::decode, PunchAbilityPacket::handle);
	    INSTANCE.registerMessage(packet++, QuestProgressPacket.class, QuestProgressPacket::encode, QuestProgressPacket::decode, QuestProgressPacket::handle);
	    INSTANCE.registerMessage(packet++, EnUpdatePacket.class, EnUpdatePacket::encode, EnUpdatePacket::decode, EnUpdatePacket::handle);
	    INSTANCE.registerMessage(packet++, QuestUpdatePacket.class, QuestUpdatePacket::encode, QuestUpdatePacket::decode, QuestUpdatePacket::handle);
	    INSTANCE.registerMessage(packet++, ChoiceQuestActivateEntryPacket.class, ChoiceQuestActivateEntryPacket::encode, ChoiceQuestActivateEntryPacket::decode, ChoiceQuestActivateEntryPacket::handle);
	    INSTANCE.registerMessage(packet++, SetActiveAbilityPacket.class, SetActiveAbilityPacket::encode, SetActiveAbilityPacket::decode, SetActiveAbilityPacket::handle);
	    INSTANCE.registerMessage(packet++, AbilityCooldownPacket.class, AbilityCooldownPacket::encode, AbilityCooldownPacket::decode, AbilityCooldownPacket::handle);
	    INSTANCE.registerMessage(packet++, RefreshNenPacket.class, RefreshNenPacket::encode, RefreshNenPacket::decode, RefreshNenPacket::handle);
        INSTANCE.registerMessage(packet++, ActivateAbilityPacket.class, ActivateAbilityPacket::encode, ActivateAbilityPacket::decode, ActivateAbilityPacket::handle);
	    INSTANCE.registerMessage(packet++, LockOnPacket.class, LockOnPacket::encode, LockOnPacket::decode, LockOnPacket::handle);
	    INSTANCE.registerMessage(packet++, ActivateComputerPacket.class, ActivateComputerPacket::encode, ActivateComputerPacket::decode, ActivateComputerPacket::handle);
	    INSTANCE.registerMessage(packet++, CGiveItemStackPacket.class, CGiveItemStackPacket::encode, CGiveItemStackPacket::decode, CGiveItemStackPacket::handle);
	    INSTANCE.registerMessage(packet++, UpdatePCStockPacket.class, UpdatePCStockPacket::encode, UpdatePCStockPacket::decode, UpdatePCStockPacket::handle);



	}
	
	public static <MSG>  void sendToTracking(LivingEntity tracked, MSG message) {
		 PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> tracked), message);
	}
}
