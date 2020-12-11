package com.izako.hunterx.gui;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.gui.AnimatedButton.SpriteTemplate;
import com.izako.hunterx.gui.ListSlider.Entry;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.NPCSpeech;
import com.izako.hunterx.izapi.quest.IMultipleChoiceQuest;
import com.izako.hunterx.izapi.quest.IQuestGiver;
import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.izapi.quest.Quest.QuestScreenEndReturnType;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.ChoiceQuestActivateEntryPacket;
import com.izako.hunterx.networking.packets.SetQuestPacket;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;
import com.izako.wypi.WyHelper;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

@OnlyIn(Dist.CLIENT)
public class QuestScreen extends Screen {

	public static final ResourceLocation CHATBOX = new ResourceLocation(Main.MODID, "textures/gui/chatprompt.png");
	public static final ResourceLocation QUESTACCEPT = new ResourceLocation(Main.MODID, "textures/gui/questaccept.png");
	public static final ResourceLocation MULTIPLE_CHOICE = new ResourceLocation(Main.MODID, "textures/gui/quest_choice_gui.png");
	
	public int height;
	public int width;
	public PlayerEntity p;
	public IQuestGiver qgiver;
	public SequencedString[] sequencedStrings = null;
	public SequencedString currentString = null;
	public int stringIndex = 0;
	public Quest currentQuest;
	public boolean hasFinalStringEnded = false;
	public int guiState = 0;
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

		switch (guiState) {

		case 0:
			this.getMinecraft().getTextureManager().bindTexture(CHATBOX);
			GuiUtils.drawTexturedModalRect(width / 2 - 128, height - 50, 0, 200, 256, 80, 0);
			if (this.currentString != null) {
				this.currentString.render(width / 2 + 10 - 128, height - 30);
				this.skipString.render(width / 2 + 30, height - 10);
			}
			break;
		case 1:
			this.renderQuestAcceptScreen(mouseX, mouseY, p_render_3_);
			break;
		case 2:
			this.renderChoicesScreen(mouseX, mouseY, p_render_3_);
			break;
		}
		this.tick();
	}

	private void renderChoicesScreen(int mouseX, int mouseY, float p_render_3_) {

		RenderSystem.pushMatrix();
		RenderSystem.translated(-(this.width * 0.1), -(this.height * 0.1), 0);
		RenderSystem.scaled(1.2d, 1.2d, 1d);
		Helper.drawIMG(MULTIPLE_CHOICE, width / 2 - 160, 40, 0, 0, MathHelper.floor(47 * 1.5),MathHelper.floor( 124 * 1.5), 0, 47, 124);
		Helper.drawIMG(MULTIPLE_CHOICE, width / 2 - 80, 40, 48, 0, MathHelper.floor(120 * 1.5),MathHelper.floor( 108 * 1.5), 0, 120, 108);
		RenderSystem.scaled(1d, 1d, 1d);
		RenderSystem.popMatrix();

		this.buttons.forEach(b -> {
			
			b.renderButton(mouseX, mouseY, p_render_3_);
			if(b instanceof ListSlider) {
				ListSlider slider = (ListSlider) b;
					
					font.drawString(slider.selectedEntry.name, width / 2 - 90, 42, Color.BLACK.getRGB());
				
					List<String> strings = WyHelper.splitString(font, slider.selectedEntry.desc, width / 2 - 70, 76, 160);
				
					for(int i = 0; i < strings.size(); i++) {
						String str = strings.get(i);
						font.drawString( str, width/2-90, 76 + (20 * i), Color.black.getRGB());
					
				
				}
			}
			});

	}

	@Override
	public void init(Minecraft mc, int p_init_2_, int p_init_3_) {
		height = mc.getMainWindow().getScaledHeight();
		width = mc.getMainWindow().getScaledWidth();
		p = mc.player;
		IHunterData data = HunterDataCapability.get(p);
		if (currentQuest == null) {
			currentQuest = this.speech.getQuests(p)[Helper.getCurrentQuest(this.speech.getQuests(p), p)];
		}
		if (sequencedStrings == null) {
			sequencedStrings = this.speech.getSpeechFromState(p);
		}

		if (this.guiState == 0 && currentString == null && sequencedStrings != null
				&& sequencedStrings[this.stringIndex] != null) {
			currentString = Helper.getNewSqStringInstance(sequencedStrings[this.stringIndex]);
			currentString.event = this::onStringEnd;
		}
		if (skipString == null) {
			skipString = new FlickeringString("click anywhere to skip", 140).setScale(0.7f);

		}
		this.font = Minecraft.getInstance().fontRenderer;
		this.buttons.forEach(b -> {
			if(b instanceof ListSlider) {
				ListSlider slider = (ListSlider) b;
				slider.entries.forEach(entry -> {
					entry.posX = (entry.posX - slider.x) + width / 2 - 186;
				});
				b.x = width / 2 - 186;
				b.y = 31;
			}
			if(b instanceof AnimatedButton) {
				b.x = width /2 + 90;
				b.y = 196;
			}
		});
	}

	public void onStringEnd() {
		this.stringIndex++;
		this.currentString = null;
	}

	public void tick() {
		if (currentString == null && sequencedStrings != null) {
			if (sequencedStrings.length > this.stringIndex && sequencedStrings[this.stringIndex] != null) {
				currentString = Helper.getNewSqStringInstance(sequencedStrings[this.stringIndex]);
				currentString.event = this::onStringEnd;
			}
			if (sequencedStrings.length == this.stringIndex && !hasFinalStringEnded) {
				hasFinalStringEnded = true;
				this.onFinalStringEnd();
			}
		}

	}

	public void onFinalStringEnd() {
		IHunterData data = HunterDataCapability.get(p);
		if (data.hasQuest(this.currentQuest) && this.currentQuest instanceof IMultipleChoiceQuest) {
			this.guiState = 2;
			this.addChoicesButtons((IMultipleChoiceQuest) data.getQuest(this.currentQuest));
		} else if (!data.hasQuest(this.currentQuest)) {
			this.guiState = 1;
			this.addQuestButtons();
		} else {
			QuestScreenEndReturnType returnType = data.getQuest(this.currentQuest).finishedTalkingEvent(this);
			if (returnType == QuestScreenEndReturnType.NULL) {
				this.onClose();
			} else {
				sequencedStrings = currentQuest.getAdditionalMessage(this);
				this.hasFinalStringEnded = false;
				this.stringIndex = 0;
			}
		}
	}

	@Override
	public void onClose() {
		IHunterData data = HunterDataCapability.get(this.minecraft.player);
		PacketHandler.INSTANCE.sendToServer(new StatsUpdatePacket(data, false));
		this.minecraft.displayGuiScreen((Screen) null);

	}

	public void addQuestButtons() {
		if (this.guiState == 1) {
			this.addButton(
					new QuestChoiceButton(width / 2 + 64, height / 2 + 87, 32, 32, "questaccept", this::acceptQuest));
			this.addButton(
					new QuestChoiceButton(width / 2 + 32, height / 2 + 87, 32, 32, "questdecline", this::declineQuest));
		}

	}

	public void addChoicesButtons(IMultipleChoiceQuest q) {
		if (this.guiState == 2) {
			List<Quest> choices = q.getChoices(p);
		    IHunterData data = HunterDataCapability.get(p);
		    Iterator<Quest> iterator = choices.iterator();
		    
		    while(iterator.hasNext()) {
		    	Quest quest = iterator.next();
		    	if(data.hasQuest(quest)) {
		    		iterator.remove();
		    	}
		    }
			ListSlider slider =new ListSlider(width / 2 - 186, 31, 10, 100, 0, choices.size() * 10, Entry.fromQuests(choices));
		
			slider.setOnActivateEntry((e,s) -> {
				q.onActivateEntry(e, s,p);
				PacketHandler.INSTANCE.sendToServer(new ChoiceQuestActivateEntryPacket(((Quest)q).getId(), e.id));
				Minecraft.getInstance().currentScreen.onClose();
			});
			this.addButton(slider);
		
			
			SpriteTemplate temp1 = new SpriteTemplate(MULTIPLE_CHOICE, 0, 125, 2);
			SpriteTemplate temp2 = new SpriteTemplate(MULTIPLE_CHOICE, 0, 138, 2);

			this.addButton(new AnimatedButton(width/2+90, 196, 26, 13, "", new Button.IPressable() {
				
				@Override
				public void onPress(Button but) {
					QuestScreen scr = (QuestScreen) Minecraft.getInstance().currentScreen;
				     if(scr.currentQuest != null) {
				    	 IHunterData data = HunterDataCapability.get(scr.p);
				    	 if(data.hasQuest(scr.currentQuest))
				 		 data.getQuest(scr.currentQuest).setProgress(101);
				    	 scr.onClose();
 				     }
				}
			}, temp1, temp2));
		}
	}

	public void renderQuestAcceptScreen(int x, int y, float v) {
		this.getMinecraft().getTextureManager().bindTexture(QUESTACCEPT);
		int baseX = width / 2 - 128;
		int baseY = height / 2 - 128;
		GuiUtils.drawTexturedModalRect(width / 2 - 128, height / 2 - 128, 0, 0, 256, 256, 0);
		this.buttons.forEach((b) -> {
			b.renderButton(x, y, v);
		});
		if (this.currentQuest != null) {
			this.drawString(this.getMinecraft().fontRenderer, this.currentQuest.getName(), baseX + 22, baseY + 15,
					Color.WHITE.getRGB());
			String desc = this.currentQuest.getDescription();
			List<String> descs = WyHelper.splitString(font, desc, baseX + 22, 0, 228);
			for (int i = 0; i < descs.size(); i++) {
				this.drawString(font, descs.get(i), (baseX + 22), baseY + 80 + 20 * i, 16777215);
			}

			this.drawString(this.getMinecraft().fontRenderer, "Will you take this quest?", baseX + 22, baseY + 215,
					Color.WHITE.getRGB());
		}

	}

	private void acceptQuest(Button but) {
		if (this.currentQuest != null) {
			ModQuests.newInstance(this.currentQuest.getId()).giveQuest(p);
			PacketHandler.INSTANCE.sendToServer(new SetQuestPacket(this.currentQuest.getId(), true));
			this.onClose();
		}
	}

	private void declineQuest(Button but) {
		this.onClose();
	}

	@Override
	public boolean mouseClicked(double x, double y, int p_mouseClicked_5_) {
		if (this.currentString != null && this.guiState == 0) {
			if (this.currentString.ticksExisted >= this.currentString.maxTicks) {
				this.currentString.ticksExisted = this.currentString.maxTicks + this.currentString.delayTicks;
			} else {
				this.currentString.ticksExisted = this.currentString.maxTicks;
			}
		}
		return super.mouseClicked(x, y, p_mouseClicked_5_);
	}

}
