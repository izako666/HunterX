package com.izako.hunterx.data.abilitydata;

import java.awt.Color;
import java.util.List;

import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.Ability.AbilityType;
import com.izako.hunterx.izapi.ability.NenType;

import net.minecraft.entity.LivingEntity;

public interface IAbilityData {

	List<Ability> getAbilities();
	void setAbilities(List<Ability> list);
	Ability getAbility(Ability abl);
	void giveAbility(Ability abl);
	void putAbilityInSlot(Ability abl,int slot);
	void removeAbilityInSlot(int slot);
	Ability[] getSlotAbilities();
	void setSlotAbilities(Ability[] abll);
	Ability getAbilityInSlot(int slot);
	int getSlotForAbility(Ability abl);
	Ability getSlotAbility(Ability abl);
	void removeAbility(Ability abl);
	int getActiveAbility();
	void setActiveAbility(int slot);
	Ability getActiveAbility(Ability abl, LivingEntity owner);

	boolean hasActiveAbility(Ability abl);
	List<Ability> getAbilitiesOfType(AbilityType type);
	
	//general nen data
	int getNenCapacity();
	void setNenCapacity(int val);
	void setCurrentNen(int val);
	int getCurrentNen();
	NenType getNenType();
	void setNenType(NenType type);
	void setAuraColor(int r, int g, int b);
	void setAuraColor(int rgb);
	Color getAuraColor();
	
	boolean isNenUser();
	void setIsNenUser(boolean val);
}
