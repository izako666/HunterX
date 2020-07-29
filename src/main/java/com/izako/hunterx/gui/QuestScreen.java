package com.izako.hunterx.gui;

import java.awt.Color;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.izapi.IZAHelper;
import com.izako.hunterx.izapi.NPCSpeech;
import com.izako.hunterx.izapi.quest.IQuestGiver;
import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.izapi.quest.Quest.QuestScreenEndReturnType;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.SetQuestPacket;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

@OnlyIn(Dist.CLIENT)
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
	public NPCSpeech speech;
	public FlickeringString skipString = null;

	public QuestScreen(IQuestGiver qgiver) {
		super(new StringTextComponent(""));
		this.qgiver = qgiver;
		this.minecraft = Minecraft.getInstance();
		this.speech = qgiver.getSpeech();

	}

	@Override
	public void render(int mouseX, int mouseY, float p_render_3_) {

		this.renderBackground();
		if(!this.renderQuestAcceptanceScreen) {
		this.getMinecraft().getTextureManager().bindTexture(CHATBOX);
		GuiUtils.drawTexturedModalRect(width / 2 - 128, height - 50, 0, 200, 256, 80, 0);
		if (this.currentString != null) {
			this.currentString.render(width / 2 + 10 - 128, height - 30);
			this.skipString.render(width / 2 + 30 , height - 10);
		}
		} else {
			this.renderQuestAcceptScreen(mouseX,mouseY,p_render_3_);
		}
		this.tick();
	}

	@Override
	public void init(Minecraft mc, int p_init_2_, int p_init_3_) {
		height = mc.getMainWindow().getScaledHeight();
		width = mc.getMainWindow().getScaledWidth();
		p = mc.player;
		if(currentQuest == null) {
		currentQuest = this.speech.getQuests(p)[IZAHelper.getCurrentQuest(this.speech.getQuests(p), p)];
		}
		if (sequencedStrings == null) {
			sequencedStrings = this.speech.getSpeechFromState(p);
		}

		if (currentString == null && sequencedStrings != null && sequencedStrings[this.stringIndex] != null) {
			currentString = IZAHelper.getNewSqStringInstance(sequencedStrings[this.stringIndex]);
			currentString.event = this::onStringEnd;
		}
		if(skipString == null) {
			skipString = new FlickeringString("click anywhere to skip", 140).setScale(0.7f);

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
		PacketHandler.INSTANCE.sendToServer(new StatsUpdatePacket(data,false));
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
		PacketHandler.INSTANCE.sendToServer(new SetQuestPacket(this.currentQuest.getId(), true));
		this.onClose();
		}
	}
	private void declineQuest(Button but) {
		this.onClose();
	}

	@Override
	public boolean mouseClicked(double x, double y, int p_mouseClicked_5_) {
		if(this.currentString != null && !this.renderQuestAcceptanceScreen) {
		if(this.currentString.ticksExisted >= this.currentString.maxTicks) {
			this.currentString.ticksExisted = this.currentString.maxTicks + this.currentString.delayTicks;
		} else {
			this.currentString.ticksExisted = this.currentString.maxTicks;
		}
		}
		return super.mouseClicked(x, y, p_mouseClicked_5_);
	}
	
	}
