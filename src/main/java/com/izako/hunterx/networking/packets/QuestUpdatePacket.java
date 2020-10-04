package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class QuestUpdatePacket {

	public QuestUpdatePacket() {}
	String questId;
	CompoundNBT nbt;
	public QuestUpdatePacket(Quest q) {
		
		this.questId = q.getId();
		nbt = q.writeData();
	}
	
	public void encode(PacketBuffer buf) {
		buf.writeInt(questId.length());
		buf.writeString(questId);
		buf.writeCompoundTag(nbt);
	}
	
	public static QuestUpdatePacket decode(PacketBuffer buf) {
		QuestUpdatePacket msg = new QuestUpdatePacket();
		int length = buf.readInt();
		msg.questId = buf.readString(length);
		msg.nbt = buf.readCompoundTag();
		return msg;
	}
	
	public static void handle(QuestUpdatePacket msg,  final Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity p = ctx.get().getSender();
				IHunterData data = HunterDataCapability.get(p);
				Quest q = ModQuests.getInstance(msg.questId);
				Quest dataQuest = data.getQuest(q);
				if(dataQuest != null) {
					dataQuest.readData(msg.nbt);
				}
			});
		}
		ctx.get().setPacketHandled(true);
	}

}
