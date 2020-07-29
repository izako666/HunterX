package com.izako.hunterx.izapi;

import java.util.function.Function;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.gui.SequencedString;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class IZAHelper {

	public static final Function<ResourceLocation, RenderType> entityModelFunc = s -> {
		return RenderType.getEntityCutoutNoCull(s);};

	public static int getCurrentQuest(Quest[] q, PlayerEntity p) {
	      
		for(int i = 0; i < q.length; i++) {
			if(!q[i].hasQuest(p) || !q[i].isFinished(p)) {
				return i;
			}
		}
		return -1;
	}
	
	public static Ability addSlotAbility(Ability abl, PlayerEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		Ability ability = null;
		for(int i = 0; i < data.getSlotAbilities().length; i++) {
			if(data.getSlotAbilities()[i] == null) {
				data.putAbilityInSlot(abl, i);
				ability = abl;
				break;
			} 
		}
		if(ability == null) {
			data.putAbilityInSlot(abl, 0);
			ability = abl;
		}
		return ability;
	}
	public static SequencedString getNewSqStringInstance(SequencedString str) {
		SequencedString newstr = new SequencedString(str.string, str.maxLength, str.maxTicks);
		newstr.color = str.color;
		newstr.delayTicks = str.delayTicks;
		
		return newstr;
	}
	
	public static SequencedString[] getNewSqStringInstance(SequencedString[] sqstr) {
		SequencedString[] newstr = new SequencedString[sqstr.length];
		for(int i = 0; i < sqstr.length; i++) {
			SequencedString tempsq = new SequencedString(sqstr[i].string, sqstr[i].maxLength, sqstr[i].maxTicks);
			tempsq.color = sqstr[i].color;
			tempsq.delayTicks = sqstr[i].delayTicks;
			newstr[i] = tempsq;
		}
		return newstr;
	}
}
