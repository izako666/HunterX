package com.izako.hunterx.events.quests;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.quests.hunterexam.HunterExam03;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class HunterExamEvents {

	@SubscribeEvent
	public static void tick(LivingUpdateEvent e) {
		if (e.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity p = (PlayerEntity) e.getEntityLiving();
			IHunterData data = HunterDataCapability.get(p);
			if (data.hasQuest(ModQuests.HUNTEREXAM01)) {

				if (data.getQuest(ModQuests.HUNTEREXAM01).getProgress() < 100) {

					if (e.getEntityLiving().ticksExisted % 480 == 0) {
						data.getQuest(ModQuests.HUNTEREXAM01).setProgress(data.getQuest(ModQuests.HUNTEREXAM01).getProgress() + 1);
					}
				}

			}

			if (data.hasQuest(ModQuests.HUNTEREXAM03)) {
				if (data.getQuest(ModQuests.HUNTEREXAM03).getProgress() < 100) {
					if (e.getEntityLiving().ticksExisted % 24 == 0) {
						Quest q = data.getQuest(ModQuests.HUNTEREXAM03);
						q.setProgress(q.getProgress() + 1);
					}
					
				}
				if (data.getQuest(ModQuests.HUNTEREXAM03).getProgress() == 100) {
					if (e.getEntityLiving().world instanceof ServerWorld) {
						HunterExam03 quest = (HunterExam03) data.getQuest(ModQuests.HUNTEREXAM03);
						BlockPos sourcePos = new BlockPos(quest.posX, quest.posY, quest.posZ);

						double distance = Math.sqrt(sourcePos.distanceSq(p.getPosition()));
						if (distance > 100 ) {
							quest.finishQuest(p);
							p.sendMessage(
									new StringTextComponent("You've passed the third stage, go back to your examiner")
											.applyTextStyle(TextFormatting.BLUE));
						}
						if (distance < 100) {
							data.removeQuest(ModQuests.HUNTEREXAM03);
							p.sendMessage(new StringTextComponent("You have failed the hunter exam")
									.applyTextStyle(TextFormatting.BLUE));

						}
					}
				}
			}
		}
	}
	

}
