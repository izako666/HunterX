package com.izako.hunterx.gui;

import com.izako.hunterx.Main;
import com.izako.hunterx.izapi.IZAHelper;
import com.izako.hunterx.izapi.quest.IQuestGiver;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.config.GuiUtils;

public class QuestScreen extends Screen{

	public static final ResourceLocation CHATBOX = new ResourceLocation(Main.MODID, "textures/gui/chatprompt.png");
	public int height;
	public int width;
	public PlayerEntity p;
	public IQuestGiver qgiver;
	public SequencedString[] sequencedStrings = null;
	public SequencedString currentString = null;
	public int stringIndex = 0;
	public QuestScreen(IQuestGiver qgiver) {
		super(new StringTextComponent(""));
		this.qgiver = qgiver;
		this.minecraft = Minecraft.getInstance();
		
	}

	@Override
	   public void render(int mouseX, int mouseY, float p_render_3_) {

		this.renderBackground();
		this.getMinecraft().getTextureManager().bindTexture(CHATBOX);
		GuiUtils.drawTexturedModalRect(width / 2 - 128, height - 50, 0, 200, 256, 80, 0);
		this.tick();
		if(this.currentString != null) {
		this.currentString.render(width / 2 + 10 - 128, height - 30);
		}
		   }

	@Override
	   public void init(Minecraft mc, int p_init_2_, int p_init_3_) {
		height = mc.mainWindow.getScaledHeight();
		width = mc.mainWindow.getScaledWidth();
		p = mc.player;
		
		if(sequencedStrings == null) {
		sequencedStrings = qgiver.getSpeech().getSpeechFromState(p);
		}
		
		if(currentString == null && sequencedStrings != null &&sequencedStrings[this.stringIndex] != null) {
			currentString = IZAHelper.getNewSqStringInstance(sequencedStrings[this.stringIndex]);
			currentString.event = this::onStringEnd;
		}
	   }
	
	public void onStringEnd() {
		this.stringIndex++;
		this.currentString = null;
	}
	public void tick() {
		if(currentString == null && sequencedStrings != null) {
			if(sequencedStrings.length > this.stringIndex && sequencedStrings[this.stringIndex] != null) {
			currentString = IZAHelper.getNewSqStringInstance(sequencedStrings[this.stringIndex]);
			currentString.event = this::onStringEnd;
			}
		}

	}
}
