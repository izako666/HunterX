package com.izako.hunterx.init;

import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.abilities.basic_hatsus.AuraBlastAbility;
import com.izako.hunterx.abilities.basic_hatsus.LoyaltyCurseAbility;
import com.izako.hunterx.abilities.basic_hatsus.SharpenAuraAbility;
import com.izako.hunterx.abilities.basic_hatsus.StatBoostAbility;
import com.izako.hunterx.abilities.basic_hatsus.UltimateToolAbility;
import com.izako.hunterx.abilities.basics.EnAbility;
import com.izako.hunterx.abilities.basics.GyoAbility;
import com.izako.hunterx.abilities.basics.InAbility;
import com.izako.hunterx.abilities.basics.KenAbility;
import com.izako.hunterx.abilities.basics.KoAbility;
import com.izako.hunterx.abilities.basics.RenAbility;
import com.izako.hunterx.abilities.basics.RyuDefenseAbility;
import com.izako.hunterx.abilities.basics.RyuOffenseAbility;
import com.izako.hunterx.abilities.basics.ShuAbility;
import com.izako.hunterx.abilities.basics.TenAbility;
import com.izako.hunterx.abilities.basics.ZetsuAbility;
import com.izako.hunterx.abilities.hatsus.gon.JajankenGuAbility;
import com.izako.hunterx.abilities.hatsus.gon.JajankenPaAbility;
import com.izako.hunterx.izapi.ability.Ability;

public class ModAbilities {

	public static final Ability TEN_ABILITY = new TenAbility();
	public static final Ability ZETSU_ABILITY = new ZetsuAbility();
	public static final Ability KO_ABILITY = new KoAbility();
	public static final Ability REN_ABILITY = new RenAbility();
	public static final Ability SHU_ABILITY = new ShuAbility();
	public static final Ability GYO_ABILITY = new GyoAbility();
	public static final Ability IN_ABILITY = new InAbility();
	public static final Ability KEN_ABILITY = new KenAbility();
	public static final Ability RYU_OFFENSE_ABILITY = new RyuOffenseAbility();
	public static final Ability RYU_DEFENSE_ABILITY = new RyuDefenseAbility();
	public static final Ability EN_ABILITY = new EnAbility();
	public static final Ability STAT_BOOST_ABILITY = new StatBoostAbility();
	public static final Ability AURA_BLAST_ABILITY = new AuraBlastAbility();
	public static final Ability LOYALTY_CURSE_ABILITY = new LoyaltyCurseAbility();
	public static final Ability ULTIMATE_TOOL_ABILITY = new UltimateToolAbility();
	public static final Ability SHARPEN_AURA_ABILITY = new SharpenAuraAbility();
	public static final Ability JAJANKEN_GU_ABILITY = new JajankenGuAbility();
	public static final Ability JAJANKEN_PA_ABILITY = new JajankenPaAbility();
	public static List<Ability> ABILITY_INSTANCES = new ArrayList<>();
 public	static void register(){
	 ABILITY_INSTANCES.add(TEN_ABILITY);
	 ABILITY_INSTANCES.add(ZETSU_ABILITY);
	 ABILITY_INSTANCES.add(KO_ABILITY);
	 ABILITY_INSTANCES.add(REN_ABILITY);
	 ABILITY_INSTANCES.add(SHU_ABILITY);
     ABILITY_INSTANCES.add(GYO_ABILITY);
     ABILITY_INSTANCES.add(IN_ABILITY);
     ABILITY_INSTANCES.add(KEN_ABILITY);
     ABILITY_INSTANCES.add(RYU_OFFENSE_ABILITY);
     ABILITY_INSTANCES.add(RYU_DEFENSE_ABILITY);
     ABILITY_INSTANCES.add(EN_ABILITY);
     ABILITY_INSTANCES.add(STAT_BOOST_ABILITY);
     ABILITY_INSTANCES.add(AURA_BLAST_ABILITY);
     ABILITY_INSTANCES.add(LOYALTY_CURSE_ABILITY);
     ABILITY_INSTANCES.add(ULTIMATE_TOOL_ABILITY);
     ABILITY_INSTANCES.add(SHARPEN_AURA_ABILITY);
     ABILITY_INSTANCES.add(JAJANKEN_GU_ABILITY);
     ABILITY_INSTANCES.add(JAJANKEN_PA_ABILITY);
     
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
	
	public static Ability getAbilityOfId(String id) {
		for(Ability abl : ABILITY_INSTANCES) {
			if(abl.getId().equalsIgnoreCase(id)) {
				return abl;
			}
		}
		return null;
	}
}
