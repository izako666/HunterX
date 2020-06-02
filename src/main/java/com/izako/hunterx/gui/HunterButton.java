package com.izako.hunterx.gui;

import com.izako.hunterx.init.ModQuests;

import net.minecraft.client.gui.widget.button.Button;

public class HunterButton extends Button {

	String name;
	String questID;

	public HunterButton(int widthIn, int heightIn, int p_i51141_3_, int p_i51141_4_, String text, boolean isQuest,
			IPressable onPress) {
		super(widthIn, heightIn, p_i51141_3_, p_i51141_4_, text, onPress);
		if (isQuest == true) {
			this.questID = text;
			this.name = ModQuests.getInstance(this.questID).getName();
		} else {
			this.name = text;
		}

	}
}
