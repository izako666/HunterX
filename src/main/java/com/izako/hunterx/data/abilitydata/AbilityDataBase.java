package com.izako.hunterx.data.abilitydata;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.Ability.AbilityType;
import com.izako.hunterx.izapi.ability.NenType;

public class AbilityDataBase implements IAbilityData {

	private List<Ability> ABILITIES = new ArrayList<>();
	private Ability[] SLOT_ABILITIES = new Ability[8];
	private int nenCapacity = 100;
	private int currentNen = 0;
	private Color aura = Color.WHITE;
	private NenType type = null;
	private boolean isNenUser = true;
	@Override
	public List<Ability> getAbilities() {
		return ABILITIES;
	}

	@Override
	public void setAbilities(List<Ability> list) {
		ABILITIES = list;
	}

	@Override
	public Ability getAbility(Ability abl) {
		if(abl != null) {
		for(int i = 0; i < ABILITIES.size(); i++) {
			if(ABILITIES.get(i).getId() == abl.getId()) {
				return ABILITIES.get(i);
			}
		}
		}
		return null;
	}

	@Override
	public void giveAbility(Ability abl) {
		ABILITIES.remove(abl);
		ABILITIES.add(abl);
		
	}

	@Override
	public void putAbilityInSlot(Ability abl, int slot) {
		SLOT_ABILITIES[slot] = abl;
		if(abl != null) {
		abl.setSlot(slot);
		}
	}

	@Override
	public void removeAbilityInSlot(int slot) {
		SLOT_ABILITIES[slot] = null;
	}

	@Override
	public Ability[] getSlotAbilities() {
		return SLOT_ABILITIES;
	}

	@Override
	public void setSlotAbilities(Ability[] abll) {
		// TODO Auto-generated method stub
		SLOT_ABILITIES = abll;
	}

	@Override
	public Ability getAbilityInSlot(int slot) {
		return SLOT_ABILITIES[slot];
	}

	@Override
	public int getSlotForAbility(Ability abl) {
		for(int i = 0; i < SLOT_ABILITIES.length; i++) {
			if(SLOT_ABILITIES[i] == abl) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void removeAbility(Ability abl) {
		for(int i = 0; i< ABILITIES.size(); i++) {
			if(ABILITIES.get(i).getId() == abl.getId()) {
				ABILITIES.remove(i);
			}
		}
	}

	@Override
	public int getNenCapacity() {
		return this.nenCapacity;
	}

	@Override
	public void setNenCapacity(int val) {
		this.nenCapacity = val;
	}

	@Override
	public void setCurrentNen(int val) {
		this.currentNen = val;
	}

	@Override
	public int getCurrentNen() {
		return this.currentNen;
	}

	@Override
	public NenType getNenType() {
		return this.type;
	}

	@Override
	public void setNenType(NenType type) {
		this.type = type;
	}

	@Override
	public void setAuraColor(int r, int g, int b) {
		this.aura = new Color(r, g, b);
	}

	@Override
	public Color getAuraColor() {
		return this.aura;
	}

	@Override
	public void setAuraColor(int rgb) {

		this.aura = new Color(rgb);
	}

	@Override
	public Ability getSlotAbility(Ability abl) {
		for(Ability abil : this.SLOT_ABILITIES) {
			if(abil != null && abl != null) {
			if(abil.equals(abl)) {
				return abil;
			}
			}
		}
		return null;
	}

	@Override
	public boolean isNenUser() {
		return this.isNenUser;
	}

	@Override
	public void setIsNenUser(boolean val) {

		this.isNenUser = val;
		
	}

	@Override
	public List<Ability> getAbilitiesOfType(AbilityType type) {
		List<Ability> abilities = new ArrayList<>();
		
		for(int i = 0; i < this.SLOT_ABILITIES.length; i++) {
			if(this.SLOT_ABILITIES[i] != null && this.SLOT_ABILITIES[i].props.type == type) {
				abilities.add(this.SLOT_ABILITIES[i]);
			}
		}
		return abilities;
	}

}
