package com.izako.hunterx.izapi.ability;

import com.izako.hunterx.init.ModAbilities;

public class AbilityTags {

	public static final Tag BASICS = new Tag("basics", ModAbilities.TEN_ABILITY, 
			ModAbilities.REN_ABILITY,
			ModAbilities.KEN_ABILITY,
			ModAbilities.KO_ABILITY,
			ModAbilities.EN_ABILITY,
			ModAbilities.GYO_ABILITY,
			ModAbilities.IN_ABILITY,
			ModAbilities.RYU_DEFENSE_ABILITY,
			ModAbilities.RYU_OFFENSE_ABILITY,
			ModAbilities.SHU_ABILITY,
			ModAbilities.ZETSU_ABILITY);

	public static class Tag {

		public Ability[] ABILITIES;
		final String tag;

		public Tag(String tag, Ability... abilities) {
			this.tag = tag;
			this.ABILITIES = abilities;

		}

		public boolean hasTag(Ability abl) {

			for (int i = 0; i < this.ABILITIES.length; i++) {
				Ability abl1 = this.ABILITIES[i];
				if (abl1.equals(abl))
					return true;
			}
			return false;
		}
	}
}
