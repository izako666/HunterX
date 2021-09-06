package com.izako.hunterx.quests.hunterexam;

import com.izako.hunterx.abilities.basics.KoAbility;
import com.izako.hunterx.abilities.basics.RenAbility;
import com.izako.hunterx.abilities.basics.TenAbility;
import com.izako.hunterx.abilities.basics.ZetsuAbility;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.entities.HanzoEntity;
import com.izako.hunterx.gui.QuestScreen;
import com.izako.hunterx.gui.SequencedString;
import com.izako.hunterx.init.ModItems;
import com.izako.hunterx.izapi.NPCSpeech.QuestState;
import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.SetQuestPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HunterExam04 extends Quest{

	public static final String failMessage = "You must get his badge";
	public static final String passMessage = "Congratulations on passing the Hunter Exam!";
	@Override
	public String getId() {
		return "hunterexam04";
	}

	@Override
	public String getName() {
		return "Badge Collection";
	}

	@Override
	public String getDescription() {
		return "The final stage to the hunter exam is getting the required badge from an exam participant, Hanzo is your target.";
	}


	@Override
	public void giveQuest(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		data.giveQuest(this);
		if(!p.world.isRemote) {
		HanzoEntity boss = new HanzoEntity(HanzoEntity.TYPE, p.world,p);
	    boss.setPosition(p.getPosX() + 2, p.getPosY(), p.getPosZ());
	    p.world.addEntity(boss);
		}
	}

	@Override
	public QuestScreenEndReturnType finishedTalkingEvent(QuestScreen scr) {
		IHunterData data = HunterDataCapability.get(scr.p);
		QuestState state = scr.qgiver.getSpeech().getStateFromQuest(data.getQuest(scr.currentQuest), scr.p);
		switch(state) {
		case NOTFULFILLED:
			return QuestScreenEndReturnType.NULL;
		case FULFILLED:
			if(scr.p.inventory.hasItemStack(new ItemStack(ModItems.BADGE))) {
				if(scr.sequencedStrings[0].string != HunterExam04.passMessage ) {
					this.finishQuest(scr.p);
					PacketHandler.INSTANCE.sendToServer(new SetQuestPacket(this.getId(), false));
				return QuestScreenEndReturnType.MESSAGE;
				}
				return QuestScreenEndReturnType.NULL;
			} 
			if(scr.sequencedStrings[0].string != HunterExam04.failMessage) {
			return QuestScreenEndReturnType.MESSAGE;
			}
			return QuestScreenEndReturnType.NULL;
		}
		return QuestScreenEndReturnType.NULL;
		
	}
	@Override
	@OnlyIn(Dist.CLIENT)
	public SequencedString[] getAdditionalMessage(QuestScreen scr) {
		if(scr.p.inventory.hasItemStack(new ItemStack(ModItems.BADGE))) {
		return new SequencedString[] {new SequencedString("Congratulations on passing the Hunter Exam!", QuestScreen.defaultChatboxStringLength, 0).setTicksFromLength(true)};
		}
		return new SequencedString[] {new SequencedString("You must get his badge", QuestScreen.defaultChatboxStringLength, 0).setTicksFromLength(true)};
	}

	@Override
	public void finishQuest(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		IAbilityData abilityData = AbilityDataCapability.get(p);
		this.setProgress(101);
		ItemStack stack = new ItemStack(ModItems.HUNTER_LICENSE);
		stack.setDisplayName(new StringTextComponent(p.getDisplayName().getFormattedText() + "'s License"));
		if(!p.addItemStackToInventory(stack)) {
			p.entityDropItem(stack);
		}
		data.setIsHunter(true);
	}

}
