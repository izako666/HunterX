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
import com.izako.hunterx.abilities.hatsus.genthru.CountdownAbility;
import com.izako.hunterx.abilities.hatsus.genthru.LittleFlowerAbility;
import com.izako.hunterx.abilities.hatsus.gon.JajankenGuAbility;
import com.izako.hunterx.abilities.hatsus.gon.JajankenPaAbility;
import com.izako.hunterx.abilities.hatsus.gon.JajankenSciAbility;
import com.izako.hunterx.abilities.hatsus.kalluto.MeanderingDanceAbility;
import com.izako.hunterx.abilities.hatsus.kalluto.PaperMarkAbility;
import com.izako.hunterx.abilities.hatsus.killua.LightningPalmAbility;
import com.izako.hunterx.abilities.hatsus.killua.LightningSpeedAbility;
import com.izako.hunterx.abilities.hatsus.killua.ThunderboltAbility;
import com.izako.hunterx.abilities.hatsus.killua.WhirlwindAbility;
import com.izako.hunterx.abilities.hatsus.leorio.EmitterPunchAbility;
import com.izako.hunterx.abilities.hatsus.leorio.LockOnAbility;
import com.izako.hunterx.abilities.hatsus.leorio.PantsStealAbility;
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
	public static final Ability JAJANKEN_SCI_ABILITY = new JajankenSciAbility();
	public static final Ability LIGHTNING_PALM_ABILITY = new LightningPalmAbility();
	public static final Ability LIGHTNING_SPEED_ABILITY = new LightningSpeedAbility();
	public static final Ability LIGHTNING_BOLT_ABILITY = new ThunderboltAbility();
	public static final Ability WHIRLWIND_ABILITY = new WhirlwindAbility();
	public static final Ability LOCKON_ABILITY = new LockOnAbility();
	public static final Ability EMITTER_PUNCH_ABILITY = new EmitterPunchAbility();
	public static final Ability PANTS_STEALER_ABILITY = new PantsStealAbility();
	public static final Ability LITTLE_FLOWER_ABILITY = new LittleFlowerAbility();
  public static final Ability COUNTDOWN_ABILITY = new CountdownAbility();
	public static final Ability MEANDERING_DANCE_ABILITY = new MeanderingDanceAbility();
	public static final Ability PAPER_MARK_ABILITY = new PaperMarkAbility();
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
     ABILITY_INSTANCES.add(JAJANKEN_SCI_ABILITY);
     ABILITY_INSTANCES.add(LIGHTNING_PALM_ABILITY);
     ABILITY_INSTANCES.add(LIGHTNING_SPEED_ABILITY);
     ABILITY_INSTANCES.add(LIGHTNING_BOLT_ABILITY);
     ABILITY_INSTANCES.add(WHIRLWIND_ABILITY);
     ABILITY_INSTANCES.add(LOCKON_ABILITY);
     ABILITY_INSTANCES.add(EMITTER_PUNCH_ABILITY);
     ABILITY_INSTANCES.add(PANTS_STEALER_ABILITY);
     ABILITY_INSTANCES.add(LITTLE_FLOWER_ABILITY);

     ABILITY_INSTANCES.add(COUNTDOWN_ABILITY);

     ABILITY_INSTANCES.add(PAPER_MARK_ABILITY);
     ABILITY_INSTANCES.add(MEANDERING_DANCE_ABILITY);

     
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
