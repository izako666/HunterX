package com.izako.hunterx.izapi.quest;

import java.util.List;

import net.minecraft.entity.player.PlayerEntity;

public interface IMultipleChoiceQuest {

	List<Quest> getChoices(PlayerEntity p);
	
	boolean mustFinishAllQuests();
}
