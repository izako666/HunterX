package com.izako.hunterx.quests.basics;

import com.izako.hunterx.izapi.quest.Quest;

public class ZetsuQuest extends Quest{

	@Override
	public String getId() {
		return "zetsuquest";
	}

	@Override
	public String getName() {
		return "Learn Zetsu";
	}

	@Override
	public String getDescription() {
		return "Defeat 10 mobs without aggroing them and you'll be ready to learn zetsu.";
	}

}
