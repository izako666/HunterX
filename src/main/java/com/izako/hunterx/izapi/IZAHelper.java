package com.izako.hunterx.izapi;

import com.izako.hunterx.gui.SequencedString;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class IZAHelper {

	public static int getCurrentQuest(Quest[] q, PlayerEntity p) {
	      
		for(int i = 0; i < q.length; i++) {
			if(!q[i].hasQuest(p) || !q[i].isFinished(p)) {
				return i;
			}
		}
		return -1;
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
