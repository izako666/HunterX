package com.izako.hunterx.init;

import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.abilities.basics.KoAbility;
import com.izako.hunterx.abilities.basics.TenAbility;
import com.izako.hunterx.abilities.basics.ZetsuAbility;
import com.izako.hunterx.izapi.ability.Ability;

public class ModAbilities {

	public static final Ability TEN_ABILITY = new TenAbility();
	public static final Ability ZETSU_ABILITY = new ZetsuAbility();
	public static final Ability KO_ABILITY = new KoAbility();
	public static List<Ability> ABILITY_INSTANCES = new ArrayList<>();
 public	static void register(){
	 ABILITY_INSTANCES.add(TEN_ABILITY);
	 ABILITY_INSTANCES.add(ZETSU_ABILITY);
	 ABILITY_INSTANCES.add(KO_ABILITY);
	}
	public static Ability getNewInstanceFromId(String id) {
		Ability tempAbl = null;
		for(Ability abl : ABILITY_INSTANCES) {
			if(abl.getId().equalsIgnoreCase(id)) {
				
				try {
					tempAbl = abl.getClass().getConstructor().newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return tempAbl;
	}
}
