package com.izako.hunterx.izapi.quest;

import java.util.List;

import com.izako.hunterx.gui.ListSlider;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;

public interface IMultipleChoiceQuest {

	List<Quest> getChoices(PlayerEntity p);
	
	boolean mustFinishAllQuests();
	
	/*
	 * slider is null on server side.
	 */
	default void onActivateEntry(ListSlider.Entry entry, ListSlider slider,PlayerEntity p) {};
	

}
