package com.izako.hunterx.quests.basic_hatsus;

import com.izako.hunterx.izapi.quest.Quest;

public class BasicHatsuQuests {

	
	public static class Enhancer extends Quest {

		@Override
		public String getId() {
			return "basic_enhancer";
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
		}}
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
		}}
	public static class Conjurer extends Quest {

		@Override
		public String getId() {
			return "basic_conjurer";
		}

		@Override
		public String getName() {
			return "Utility Set";
		}

		@Override
		public String getDescription() {
			return "Use this ability until you master it.";
		}}
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
		}}

}
