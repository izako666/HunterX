package com.izako.HunterX.init;

import java.util.ArrayList;
import java.util.List;

import com.izako.HunterX.abilities.AbilityJajanken;
import com.izako.HunterX.izapi.abilities.Ability;

public class ListAbilities {

	public static AbilityJajanken ABILITY_JAJANKEN = new AbilityJajanken("AbilityJajanken");
	
	public static List<Ability> Abilities = new ArrayList<Ability>();
	public static void registerAbilities() {
		Abilities.add(ABILITY_JAJANKEN);
		
		
	}
	
	public static Ability getAbilityFromID(String str) {
		for(int i = 0; i < Abilities.size(); i++) {
			if(Abilities.get(i).getID().equalsIgnoreCase(str)) {
				System.out.println("ability returned :)");
				return Abilities.get(i);
			}
		}
		System.out.println("Null returned :/");
		return null;
	}
}
