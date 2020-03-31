package com.izako.hunterx.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.data.world.HunterWorldData;
import com.izako.hunterx.init.ModQuests;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class QuestEvents {

	public static int tick = 0;
	@SubscribeEvent
	public static void tick(LivingUpdateEvent e) {
		if(e.getEntityLiving() instanceof PlayerEntity) {
			tick++;
			PlayerEntity p = (PlayerEntity) e.getEntityLiving();
			IHunterData data = HunterDataCapability.get(p);
			if(ModQuests.HUNTEREXAM01.hasQuest(p)) {

				if(data.getProgress(ModQuests.HUNTEREXAM01.getId()) < 100) {
					
					if(tick % 480 == 0) {
						data.setProgress(ModQuests.HUNTEREXAM01.getId(), data.getProgress(ModQuests.HUNTEREXAM01.getId()) + 1);
					}
				}
			
			}
			
			if(ModQuests.HUNTEREXAM03.hasQuest(p)) {
				if(data.getProgress(ModQuests.HUNTEREXAM03.getId()) < 100) {
					if(tick % 24 == 0) {
						data.setProgress(ModQuests.HUNTEREXAM03.getId(), data.getProgress(ModQuests.HUNTEREXAM03.getId()) + 1);
					}
				} 
				if(data.getProgress(ModQuests.HUNTEREXAM03.getId()) == 100) {
					if(e.getEntityLiving().world instanceof ServerWorld) {
						HunterWorldData worlddata = HunterWorldData.get((ServerWorld) e.getEntityLiving().world);
					int distance =	(int) e.getEntityLiving().getDistanceSq(worlddata.getPos().getX(), worlddata.getPos().getY(), worlddata.getPos().getZ());
					
					if(distance > 100) {
						ModQuests.HUNTEREXAM03.finishQuest(p);
	                    data.giveQuest(ModQuests.HUNTEREXAM04.getId(), 0);
						p.sendMessage(new StringTextComponent("You've passed the third stage, go back to your examiner").applyTextStyle(TextFormatting.BLUE));
					}
					if(distance < 100) {
						data.removeQuest(ModQuests.HUNTEREXAM03.getId());
						p.sendMessage(new StringTextComponent("You have failed the hunter exam").applyTextStyle(TextFormatting.BLUE));

					}
					}
				}
			}
		}
	}
}
