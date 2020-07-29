package com.izako.wypi.data.ability;

import java.util.List;

import com.izako.wypi.APIConfig.AbilityCategory;
import com.izako.wypi.abilities.Ability;

public interface IAbilityData
{
	boolean addUnlockedAbility(Ability abl);
	boolean setUnlockedAbility(int slot, Ability abl);
	boolean removeUnlockedAbility(Ability abl);
	boolean hasUnlockedAbility(Ability abl);
	<T extends Ability> T getUnlockedAbility(T abl);
	<T extends Ability> T getUnlockedAbility(int slot);
	List<Ability> getUnlockedAbilities(AbilityCategory category);
	void clearUnlockedAbilities(AbilityCategory category);
	void clearUnlockedAbilityFromList(AbilityCategory category, List<Ability> list); 
	int countUnlockedAbilities(AbilityCategory category);

	boolean addEquippedAbility(Ability abl);
	boolean setEquippedAbility(int slot, Ability abl);
	boolean removeEquippedAbility(Ability abl);
	boolean hasEquippedAbility(Ability abl);
	<T extends Ability> T getEquippedAbility(T abl);
	<T extends Ability> T getEquippedAbility(int slot);
	<T extends Ability> T[] getEquippedAbilities();
	<T extends Ability> T[] getEquippedAbilities(AbilityCategory category);
	void clearEquippedAbilities(AbilityCategory category);
	void clearEquippedAbilityFromList(AbilityCategory category, List<Ability> list); 
	int countEquippedAbilities(AbilityCategory category);
	
	<T extends Ability> T getPreviouslyUsedAbility();
	void setPreviouslyUsedAbility(Ability abl);
}
