package com.izako.hunterx.init;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.abilities.ExampleAbility;
import com.izako.hunterx.izapi.ability.Ability;

public class ModAbilities {

	public static final ExampleAbility EXAMPLEABILITY = new ExampleAbility();
	public static List<Ability> ABILITY_INSTANCES = new ArrayList<>();
 public	static void register(){
		ABILITY_INSTANCES.add(EXAMPLEABILITY);
		System.out.println("ye");
	}
	public static Ability getNewInstanceFromId(String id) {
		Ability tempAbl = null;
		for(Ability abl : ABILITY_INSTANCES) {
			if(abl.getId().equalsIgnoreCase(id)) {
				System.out.println("WTF");
				try {
					tempAbl = abl.getClass().getConstructor().newInstance();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return tempAbl;
	}
}
