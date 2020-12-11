package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.gui.ListSlider;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.quest.IMultipleChoiceQuest;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class ChoiceQuestActivateEntryPacket {


	public ChoiceQuestActivateEntryPacket() {}
	String questId;
	String entryId;
	public ChoiceQuestActivateEntryPacket(String questId, String entryId) {
		
		this.questId = questId;
		this.entryId = entryId;
	}
	
	public void encode(PacketBuffer buf) {
		buf.writeInt(questId.length());
		buf.writeString(questId);
		buf.writeInt(entryId.length());
		buf.writeString(entryId);
	}
	
	public static ChoiceQuestActivateEntryPacket decode(PacketBuffer buf) {
		ChoiceQuestActivateEntryPacket msg = new ChoiceQuestActivateEntryPacket();
		int length = buf.readInt();
		msg.questId = buf.readString(length);
		int lengthE = buf.readInt();
		msg.entryId = buf.readString(lengthE);
		return msg;
	}
	
	public static void handle(ChoiceQuestActivateEntryPacket msg,  final Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity p = ctx.get().getSender();
				IHunterData data = HunterDataCapability.get(p);
				Quest q = ModQuests.getInstance(msg.questId);
				Quest entryQ = ModQuests.getInstance(msg.entryId);
				Ability entryA = ModAbilities.getAbilityOfId(msg.entryId);
				ListSlider.Entry entry;
				if(entryQ == null) {
				 entry = new ListSlider.Entry(entryA);
				} else {
					entry = new ListSlider.Entry(entryQ);
				}
				if(q != null && data.hasQuest(q)) {
					((IMultipleChoiceQuest) q).onActivateEntry(entry,null,p);
				}
			});
		}
		ctx.get().setPacketHandled(true);
	}

}
