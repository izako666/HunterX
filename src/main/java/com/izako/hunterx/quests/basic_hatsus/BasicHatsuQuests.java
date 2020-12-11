package com.izako.hunterx.quests.basic_hatsus;

import com.izako.hunterx.abilities.basic_hatsus.AuraBlastAbility;
import com.izako.hunterx.abilities.basic_hatsus.LoyaltyCurseAbility;
import com.izako.hunterx.abilities.basic_hatsus.SharpenAuraAbility;
import com.izako.hunterx.abilities.basic_hatsus.StatBoostAbility;
import com.izako.hunterx.abilities.basic_hatsus.UltimateToolAbility;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class BasicHatsuQuests {

	
	public static class Enhancer extends Quest {

		@Override
		public String getId() {
			return "basic_enhancer";
		}

	

		@Override
		public void giveQuest(PlayerEntity p) {
			StatBoostAbility abl = new StatBoostAbility();
			abl.setXp(-50,p);
			abl.give(p);
			super.giveQuest(p);
		}

		@Override
		public String getName() {
			return "Strengthen Aura";
		}

		@Override
		public String getDescription() {
			return "Use this ability until you master it.";
		}}
	
	public static class Emitter extends Quest {

		@Override
		public String getId() {
			return "basic_emitter";
		}

		@Override
		public String getName() {
			return "Aura Blast";
		}

		@Override
		public String getDescription() {
			return "Use this ability until you master it.";
		}
		
		@Override
		public void giveQuest(PlayerEntity p) {
			AuraBlastAbility abl = new AuraBlastAbility();
			abl.setXp(-50,p);
			abl.give(p);
			super.giveQuest(p);
		}

	}
	
	public static class Manipulator extends Quest {

		@Override
		public String getId() {
			return "basic_manipulator";
		}

		@Override
		public String getName() {
			return "Loyalty Curse";
		}

		@Override
		public String getDescription() {
			return "Use this ability until you master it.";
		}
		
		@Override
		public void giveQuest(PlayerEntity p) {
			LoyaltyCurseAbility abl = new LoyaltyCurseAbility();
			abl.setXp(-50,p);
			abl.give(p);
			super.giveQuest(p);
		}

	}
	public static class Conjurer extends Quest {

		@Override
		public String getId() {
			return "basic_conjurer";
		}

		@Override
		public String getName() {
			return "Ultimate Tool";
		}

		@Override
		public String getDescription() {
			return "Use this ability until you master it.";
		}
		
		@Override
		public void giveQuest(PlayerEntity p) {
			UltimateToolAbility abl = new UltimateToolAbility();
			abl.setXp(-50,p);
			abl.give(p);
			super.giveQuest(p);
		}

	}
	public 	static class Transmuter extends Quest {

		@Override
		public String getId() {
			return "basic_transmuter";
		}

		@Override
		public String getName() {
			return "Sharpen and Harden";
		}

		@Override
		public String getDescription() {
			return "Use this ability until you master it.";
		}
		@Override
		public void giveQuest(PlayerEntity p) {
			SharpenAuraAbility abl = new SharpenAuraAbility();
			abl.setXp(-50,p);
			abl.give(p);
			super.giveQuest(p);
		}
}

}
