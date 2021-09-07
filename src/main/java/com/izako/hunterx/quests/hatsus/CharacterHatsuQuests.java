package com.izako.hunterx.quests.hatsus;

import com.izako.hunterx.abilities.hatsus.genthru.CountdownAbility;
import com.izako.hunterx.abilities.hatsus.genthru.LittleFlowerAbility;
import com.izako.hunterx.abilities.hatsus.gon.JajankenGuAbility;
import com.izako.hunterx.abilities.hatsus.gon.JajankenPaAbility;
import com.izako.hunterx.abilities.hatsus.gon.JajankenSciAbility;
import com.izako.hunterx.abilities.hatsus.killua.LightningPalmAbility;
import com.izako.hunterx.abilities.hatsus.killua.LightningSpeedAbility;
import com.izako.hunterx.abilities.hatsus.killua.ThunderboltAbility;
import com.izako.hunterx.abilities.hatsus.killua.WhirlwindAbility;
import com.izako.hunterx.abilities.hatsus.leorio.EmitterPunchAbility;
import com.izako.hunterx.abilities.hatsus.leorio.LockOnAbility;
import com.izako.hunterx.abilities.hatsus.leorio.PantsStealAbility;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class CharacterHatsuQuests {

	public static class Gon extends Quest {

		@Override
		public String getId() {
			return "gon_quest";
		}

	

		@Override
		public void giveQuest(PlayerEntity p) {
			JajankenGuAbility jajankengu = new JajankenGuAbility();
			JajankenPaAbility jajankenpa = new JajankenPaAbility();
			JajankenSciAbility jajankensci = new JajankenSciAbility();
			jajankengu.give(p);
			jajankenpa.give(p);
			jajankensci.give(p);
			this.setProgress(101);
			
			IHunterData data = HunterDataCapability.get(p);
			data.getQuest(ModQuests.CHARACTER_HATSUS_QUEST).setProgress(101);
			super.giveQuest(p);
		}

		@Override
		public String getName() {
			return "Learn Gon's Hatsu";
		}

		@Override
		public String getDescription() {
			return "Learn Gon's Hatsu (By taking the abilities, you've already learnt them).";
		}}
	
	public static class Killua extends Quest {

		@Override
		public String getId() {
			return "killua_quest";
		}

	

		@Override
		public void giveQuest(PlayerEntity p) {
			
			LightningPalmAbility lightningPalm = new LightningPalmAbility();
			LightningSpeedAbility speed = new LightningSpeedAbility();
			ThunderboltAbility thunderbolt = new ThunderboltAbility();
			WhirlwindAbility whirlwind = new WhirlwindAbility();
			lightningPalm.give(p);
			speed.give(p);
			thunderbolt.give(p);
			whirlwind.give(p);
			this.setProgress(101);
			IHunterData data = HunterDataCapability.get(p);
			data.getQuest(ModQuests.CHARACTER_HATSUS_QUEST).setProgress(101);

			super.giveQuest(p);
		}

		@Override
		public String getName() {
			return "Learn Killua's Hatsu";
		}

		@Override
		public String getDescription() {
			return "Learn Killua's Hatsu (By taking the abilities, you've already learnt them).";
		}}
	public static class Leorio extends Quest {

		@Override
		public String getId() {
			return "leorio_quest";
		}

	

		@Override
		public void giveQuest(PlayerEntity p) {
			
			EmitterPunchAbility abl = new EmitterPunchAbility();
			LockOnAbility lock = new LockOnAbility();
			PantsStealAbility pants = new PantsStealAbility();
			abl.give(p);
			lock.give(p);
			pants.give(p);
			this.setProgress(101);
			IHunterData data = HunterDataCapability.get(p);
			data.getQuest(ModQuests.CHARACTER_HATSUS_QUEST).setProgress(101);

			super.giveQuest(p);
		}

		@Override
		public String getName() {
			return "Learn Leorio's Hatsu";
		}

		@Override
		public String getDescription() {
			return "Learn Leorio's Hatsu (By taking the abilities, you've already learnt them).";
		}}

	public static class Genthru extends Quest
	{
		@Override
		public String getId() {return "genthru_quest";}

		@Override
		public void giveQuest(PlayerEntity p)
		{
			LittleFlowerAbility littleFlower = new LittleFlowerAbility();
			CountdownAbility countdown = new CountdownAbility();
			littleFlower.give(p);
			// when done with countdown countdown.give(p);
			this.setProgress(101);
			IHunterData data = HunterDataCapability.get(p);
			data.getQuest(ModQuests.CHARACTER_HATSUS_QUEST).setProgress(101);

			super.giveQuest(p);
		}

		@Override
		public String getName() {return "Learn Genthru's Hatsu";}

		@Override
		public String getDescription()
		{
			return "Learn Genthru's Hatsu (By taking the abilities, you've already learnt them).";
		}
	}


}
