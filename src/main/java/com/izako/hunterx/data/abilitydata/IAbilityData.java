package com.izako.hunterx.data.abilitydata;

import java.util.List;

import javax.annotation.Nullable;

import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.NenType;

public interface IAbilityData {

	List<Ability> getAbilities();
	void setAbilities(List<Ability> list);
	Ability getAbility(@Nullable Ability abl);
	void giveAbility(Ability abl);
	void putAbilityInSlot(Ability abl,int slot);
	void removeAbilityInSlot(int slot);
	Ability[] getSlotAbilities();
	void setSlotAbilities(Ability[] abll);
	Ability getAbilityInSlot(int slot);
	int getSlotForAbility(Ability abl);
	void removeAbility(Ability abl);
	
	//general nen data
	int getNenCapacity();
	void setNenCapacity(int val);
	void setCurrentNen(int val);
	int getCurrentNen();
	NenType getNenType();
	void setNenType(NenType type);
	
}
