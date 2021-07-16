package com.izako.hunterx.gui;

import com.izako.hunterx.Main;
import com.izako.wypi.WyHelper;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class QuestChoiceButton  extends Button{

	public static final ResourceLocation QUEST_DEFAULT = new ResourceLocation(Main.MODID, "textures/gui/questdefaultbutton.png");
	public static final ResourceLocation QUEST_DECLINE = new ResourceLocation(Main.MODID, "textures/gui/questdeclinebutton.png");
	public static final ResourceLocation QUEST_ACCEPT = new ResourceLocation(Main.MODID, "textures/gui/questacceptbutton.png");

	public QuestChoiceButton(int widthIn, int heightIn, int p_i51141_3_, int p_i51141_4_, String text, IPressable onPress) {
		super(widthIn, heightIn, p_i51141_3_, p_i51141_4_, text, onPress);
	}

	@Override
	public void renderButton(int mouseX, int mouseY, float p_renderButton_3_) {
		
		if(this.getMessage().equalsIgnoreCase("questaccept") && this.isMouseOver(mouseX, mouseY)) {

			WyHelper.drawIcon(QUEST_ACCEPT, this.x, this.y, this.width + 2, this.height + 2,1);
		} else if(this.getMessage().equalsIgnoreCase("questdecline") && this.isMouseOver(mouseX, mouseY)) {
			WyHelper.drawIcon(QUEST_DECLINE, this.x, this.y, this.width + 2, this.height  + 2,1);
		} else {
			WyHelper.drawIcon(QUEST_DEFAULT, this.x, this.y, this.width, this.height,1);
		}
	}
}
