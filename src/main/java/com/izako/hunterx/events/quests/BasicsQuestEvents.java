package com.izako.hunterx.events.quests;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.init.ModQuests;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class BasicsQuestEvents {

	
	@SubscribeEvent
	public static void tick(LivingUpdateEvent e) {
		if(e.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity p = (PlayerEntity) e.getEntityLiving();
			if(p.ticksExisted % 240 == 0) {
				IHunterData data = HunterDataCapability.get(p);
				IAbilityData ablData = AbilityDataCapability.get(p);
				if(data.hasQuest(ModQuests.RENQUEST) && !data.getQuest(ModQuests.RENQUEST).canFinish() && ablData.getSlotAbility(ModAbilities.TEN_ABILITY) != null && ablData.getSlotAbility(ModAbilities.TEN_ABILITY).isInPassive()) {
					data.getQuest(ModQuests.RENQUEST).setProgress(data.getQuest(ModQuests.RENQUEST).getProgress() + 1);
				} 
			}
		}
	}
}
