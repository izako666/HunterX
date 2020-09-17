package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class QuestProgressPacket {


	public QuestProgressPacket() {}
	String questId;
	int progress;
	public QuestProgressPacket(String questId, int progress) {
		
		this.questId = questId;
		this.progress = progress;
	}
	
	public void encode(PacketBuffer buf) {
		buf.writeInt(questId.length());
		buf.writeString(questId);
		buf.writeInt(progress);
	}
	
	public static QuestProgressPacket decode(PacketBuffer buf) {
		QuestProgressPacket msg = new QuestProgressPacket();
		int length = buf.readInt();
		msg.questId = buf.readString(length);
		msg.progress = buf.readInt();
		return msg;
	}
	
	public static void handle(QuestProgressPacket msg,  final Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity p = ctx.get().getSender();
				IHunterData data = HunterDataCapability.get(p);
				Quest q = ModQuests.getInstance(msg.questId);
				if(q != null && data.hasQuest(q)) {
				data.getQuest(q).setProgress(msg.progress);
				}
			});
		}
		ctx.get().setPacketHandled(true);
	}

}
