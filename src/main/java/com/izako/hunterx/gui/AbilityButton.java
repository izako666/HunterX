package com.izako.hunterx.gui;

import com.izako.hunterx.izapi.ability.Ability;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class AbilityButton extends Button {


String abilityName;
ResourceLocation ablTexture;
Ability ability;

	public AbilityButton(int widthIn, int heightIn, int p_i51141_3_, int p_i51141_4_, String text, 
			IPressable onPress, Ability abl) {
		super(widthIn, heightIn, p_i51141_3_, p_i51141_4_, text, onPress);
		abilityName = abl.getName();
		ablTexture = abl.props.tex;
		ability = abl;
	}
}
