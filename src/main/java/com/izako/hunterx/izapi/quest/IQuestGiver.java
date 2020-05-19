package com.izako.hunterx.izapi.quest;

import com.izako.hunterx.izapi.NPCSpeech;

import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraftforge.api.distmarker.Dist;

public interface IQuestGiver {

	@OnlyIn(Dist.CLIENT)
	public NPCSpeech getSpeech();
}
