package com.izako.hunterx.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModQuests;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class QuestEvents {

	@SubscribeEvent
	public static void tick(LivingUpdateEvent e) {
		if (e.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity p = (PlayerEntity) e.getEntityLiving();
			IHunterData data = HunterDataCapability.get(p);
			if (ModQuests.HUNTEREXAM01.hasQuest(p)) {

				if (data.getProgress(ModQuests.HUNTEREXAM01.getId()) < 100) {

					if (e.getEntityLiving().ticksExisted % 480 == 0) {
						data.setProgress(ModQuests.HUNTEREXAM01.getId(),
								data.getProgress(ModQuests.HUNTEREXAM01.getId()) + 1);
					}
				}

			}

			if (ModQuests.HUNTEREXAM03.hasQuest(p)) {
				if (data.getProgress(ModQuests.HUNTEREXAM03.getId()) < 100) {
					if (e.getEntityLiving().ticksExisted % 24 == 0) {
						data.setProgress(ModQuests.HUNTEREXAM03.getId(),
								data.getProgress(ModQuests.HUNTEREXAM03.getId()) + 1);
					}
					
				}
				if (data.getProgress(ModQuests.HUNTEREXAM03.getId()) == 100) {
					if (e.getEntityLiving().world instanceof ServerWorld) {
						CompoundNBT nbt = data.getOrCreateExtraQuestData(ModQuests.HUNTEREXAM03);
						BlockPos sourcePos = new BlockPos(nbt.getDouble("posx"), nbt.getDouble("posy"), nbt.getDouble("posz"));

						double distance = Math.sqrt(sourcePos.distanceSq(p.getPosition()));
						if (distance > 100) {
							ModQuests.HUNTEREXAM03.finishQuest(p);
							p.sendMessage(
									new StringTextComponent("You've passed the third stage, go back to your examiner")
											.applyTextStyle(TextFormatting.BLUE));
						}
						if (distance < 100) {
							data.removeQuest(ModQuests.HUNTEREXAM03.getId());
							p.sendMessage(new StringTextComponent("You have failed the hunter exam")
									.applyTextStyle(TextFormatting.BLUE));

						}
					}
				}
			}
		}
	}
	

}
