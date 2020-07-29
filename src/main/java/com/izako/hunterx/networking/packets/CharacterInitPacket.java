package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class CharacterInitPacket {



	
	public CharacterInitPacket() {}

	CompoundNBT hData;
	CompoundNBT ablData;
	public CharacterInitPacket(IHunterData hData, IAbilityData ablData) {

		this.hData = (CompoundNBT) HunterDataCapability.INSTANCE.getStorage().writeNBT(HunterDataCapability.INSTANCE, hData, null);
		this.ablData = (CompoundNBT) AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, ablData, null);
	}
	
	public void encode(PacketBuffer buf) {

		buf.writeCompoundTag(hData);
		buf.writeCompoundTag(ablData);
	}
	
	public static CharacterInitPacket decode(PacketBuffer buf) {
		CharacterInitPacket msg = new CharacterInitPacket();

		msg.hData = buf.readCompoundTag();
		msg.ablData = buf.readCompoundTag();
		return msg;
	}
	public static void handle(CharacterInitPacket msg,  final Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity p = ctx.get().getSender();
                IHunterData hunterData = HunterDataCapability.get(p);
                IAbilityData data = AbilityDataCapability.get(p);
                hunterData.setIsCharacterMade(msg.hData.getBoolean("ischarmade"));
				data.setAuraColor(msg.ablData.getInt("auracolor"));
				System.out.println("yuh");
			
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
