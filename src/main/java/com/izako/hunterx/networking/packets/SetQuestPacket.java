package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.init.ModQuests;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SetQuestPacket {



	
	public SetQuestPacket() {}

	String ID;
	boolean give;
	public SetQuestPacket(String id, boolean give) {

		this.ID = id;
		this.give = give;
	}
	
	public void encode(PacketBuffer buf) {

		buf.writeString(ID);
		buf.writeBoolean(give);
	}
	
	public static SetQuestPacket decode(PacketBuffer buf) {
		SetQuestPacket msg = new SetQuestPacket();
		msg.ID = buf.readString();
		msg.give = buf.readBoolean();
		return msg;
	}
	public static void handle(SetQuestPacket msg,  final Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity p = ctx.get().getSender();
				if(msg.give) {
				ModQuests.getInstance(msg.ID).giveQuest(p);
				} else {
					ModQuests.getInstance(msg.ID).finishQuest(p);
				}
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
