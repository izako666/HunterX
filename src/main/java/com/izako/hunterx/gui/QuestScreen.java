package com.izako.hunterx.gui;

import java.awt.Color;
import java.util.Arrays;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.izapi.IZAHelper;
import com.izako.hunterx.izapi.quest.IQuestGiver;
import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.izapi.quest.Quest.QuestScreenEndReturnType;
import com.izako.hunterx.networking.ModidPacketHandler;
import com.izako.hunterx.networking.packets.SetQuestPacket;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.config.GuiUtils;

public class QuestScreen extends Screen {

	public static final ResourceLocation CHATBOX = new ResourceLocation(Main.MODID, "textures/gui/chatprompt.png");
	public static final ResourceLocation QUESTACCEPT = new ResourceLocation(Main.MODID, "textures/gui/questaccept.png");
	public int height;
	public int width;
	public PlayerEntity p;
	public IQuestGiver qgiver;
	public SequencedString[] sequencedStrings = null;
	public SequencedString currentString = null;
	public int stringIndex = 0;
	public Quest currentQuest;
	public boolean hasFinalStringEnded = false;
	public boolean renderQuestAcceptanceScreen = false;
	public static final int defaultChatboxStringLength = 240;

	public QuestScreen(IQuestGiver qgiver) {
		super(new StringTextComponent(""));
		this.qgiver = qgiver;
		this.minecraft = Minecraft.getInstance();

	}

	@Override
	public void render(int mouseX, int mouseY, float p_render_3_) {

		this.renderBackground();
		if(!this.renderQuestAcceptanceScreen) {
		this.getMinecraft().getTextureManager().bindTexture(CHATBOX);
		GuiUtils.drawTexturedModalRect(width / 2 - 128, height - 50, 0, 200, 256, 80, 0);
		if (this.currentString != null) {
			this.currentString.render(width / 2 + 10 - 128, height - 30);
		}
		} else {
			this.renderQuestAcceptScreen(mouseX,mouseY,p_render_3_);
		}
		this.tick();
	}

	@Override
	public void init(Minecraft mc, int p_init_2_, int p_init_3_) {
		height = mc.mainWindow.getScaledHeight();
		width = mc.mainWindow.getScaledWidth();
		p = mc.player;
		if(currentQuest == null) {
		currentQuest = qgiver.getSpeech().getQuests(p)[IZAHelper.getCurrentQuest(qgiver.getSpeech().getQuests(p), p)];
		}
		if (sequencedStrings == null) {
			sequencedStrings = qgiver.getSpeech().getSpeechFromState(p);
		}

		if (currentString == null && sequencedStrings != null && sequencedStrings[this.stringIndex] != null) {
			currentString = IZAHelper.getNewSqStringInstance(sequencedStrings[this.stringIndex]);
			currentString.event = this::onStringEnd;
		}
	}

	public void onStringEnd() {
		this.stringIndex++;
		this.currentString = null;
	}

	public void tick() {
		if (currentString == null && sequencedStrings != null) {
			if (sequencedStrings.length > this.stringIndex && sequencedStrings[this.stringIndex] != null) {
				currentString = IZAHelper.getNewSqStringInstance(sequencedStrings[this.stringIndex]);
				currentString.event = this::onStringEnd;
			} 
			 if(sequencedStrings.length == this.stringIndex && !hasFinalStringEnded) {
				hasFinalStringEnded  = true;
				this.onFinalStringEnd();
			}
		}

	}
	public void onFinalStringEnd() {
		QuestScreenEndReturnType returnType = currentQuest.applyQuestScreenEndLogic(this);
		if(returnType == QuestScreenEndReturnType.NULL) {
			this.onClose();
		} else if(returnType == QuestScreenEndReturnType.QUEST) {
			this.renderQuestAcceptanceScreen = true;
			this.addQuestButtons();
		} else {
			sequencedStrings = currentQuest.getAdditionalMessage(this);
			this.hasFinalStringEnded = false;
			this.stringIndex = 0;
		}
	}
	@Override
	public void onClose() {
		IHunterData data = HunterDataCapability.get(this.minecraft.player);
		ModidPacketHandler.INSTANCE.sendToServer(new StatsUpdatePacket(data,false));
	      this.minecraft.displayGuiScreen((Screen)null);

	}
	public void addQuestButtons() {
		if(this.renderQuestAcceptanceScreen) {
		this.addButton(new QuestChoiceButton(width/ 2 + 64, height / 2 + 87, 32, 32, "questaccept", this::acceptQuest));
	    this.addButton(new QuestChoiceButton(width / 2 + 32, height / 2 + 87, 32, 32, "questdecline", this::declineQuest));
		}
		}
	
	public void renderQuestAcceptScreen(int x, int y, float v) {
		this.getMinecraft().getTextureManager().bindTexture(QUESTACCEPT);
		int baseX = width /2 - 128;
		int baseY = height /2 - 128;
		GuiUtils.drawTexturedModalRect(width/2 - 128, height/2 - 128, 0, 0, 256, 256, 0);
		this.buttons.forEach((b) -> {
			b.renderButton(x, y, v);
		});
		if(this.currentQuest != null) {
	this.drawString(this.getMinecraft().fontRenderer, this.currentQuest.getName(), baseX + 22, baseY + 15, Color.WHITE.getRGB());
	this.currentQuest.renderDesc(baseX + 22, baseY + 80);
	this.drawString(this.getMinecraft().fontRenderer, "Will you take this quest?", baseX + 22, baseY + 215, Color.WHITE.getRGB());
		}
	
	}
	
	private void acceptQuest(Button but) {
		if(this.currentQuest != null) {
		this.currentQuest.giveQuest(p);
		ModidPacketHandler.INSTANCE.sendToServer(new SetQuestPacket(this.currentQuest.getId(), true));
		this.onClose();
		}
	}
	private void declineQuest(Button but) {
		this.onClose();
	}
}
