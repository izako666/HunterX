package com.izako.hunterx.events.quests;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.events.custom.AbilityActivateEvent;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.QuestProgressPacket;
import com.izako.hunterx.networking.packets.QuestUpdatePacket;
import com.izako.hunterx.quests.basics.EnQuest;
import com.izako.hunterx.quests.basics.RyuQuest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class BasicsQuestEvents {

	@SubscribeEvent
	public static void tick(LivingUpdateEvent e) {
		if (e.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity p = (PlayerEntity) e.getEntityLiving();
			if (p.ticksExisted % 240 == 0) {
				IHunterData data = HunterDataCapability.get(p);
				IAbilityData ablData = AbilityDataCapability.get(p);
				if (data.hasQuest(ModQuests.RENQUEST) && !data.getQuest(ModQuests.RENQUEST).canFinish()
						&& ablData.getSlotAbility(ModAbilities.TEN_ABILITY) != null
						&& ablData.getSlotAbility(ModAbilities.TEN_ABILITY).isInPassive()
						&& !data.getQuest(ModQuests.RENQUEST).isFinished()) {
					data.getQuest(ModQuests.RENQUEST).setProgress(data.getQuest(ModQuests.RENQUEST).getProgress() + 1);
				}
			}
		}
	}

	@SubscribeEvent
	public static void killEntity(LivingDeathEvent e) {

		if (e.getEntityLiving() instanceof IMob && e.getSource().getTrueSource() instanceof PlayerEntity) {

			PlayerEntity p = (PlayerEntity) e.getSource().getTrueSource();
			MobEntity m = (MobEntity) e.getEntityLiving();

			IHunterData data = HunterDataCapability.get(p);
			if (data.hasQuest(ModQuests.ZETSUQUEST) && !data.getQuest(ModQuests.ZETSUQUEST).isFinished()) {
				if (m.getAttackTarget() != p && data.getQuest(ModQuests.ZETSUQUEST).getProgress() < 100) {
					data.getQuest(ModQuests.ZETSUQUEST)
							.setProgress(data.getQuest(ModQuests.ZETSUQUEST).getProgress() + 10);
				}
			}
		}
	}
	@SubscribeEvent
	public static void breakBlock(BlockEvent.BreakEvent e) {
		PlayerEntity p = e.getPlayer();
		if(e.getState().getBlock().getHarvestTool(e.getState()) == ToolType.PICKAXE) {
			IHunterData data = HunterDataCapability.get(p);
			IAbilityData aData = AbilityDataCapability.get(p);
			
			if(p.getHeldItemMainhand().getItem() instanceof ShovelItem && data.hasQuest(ModQuests.SHUQUEST) && !data.getQuest(ModQuests.SHUQUEST).isFinished() && !data.getQuest(ModQuests.SHUQUEST).canFinish() && aData.getSlotAbility(ModAbilities.REN_ABILITY) != null && aData.getSlotAbility(ModAbilities.REN_ABILITY).isActive()) {
				data.getQuest(ModQuests.SHUQUEST).setProgress(data.getQuest(ModQuests.SHUQUEST).getProgress() + 1);
			}
		}
	}
	
	@SubscribeEvent
	public static void inQuest(LivingUpdateEvent e) {
		if(!(e.getEntityLiving() instanceof PlayerEntity))
			return;
		
		if(e.getEntityLiving().ticksExisted % 360 == 0) {
			PlayerEntity p = (PlayerEntity) e.getEntityLiving();
			IHunterData hData = HunterDataCapability.get(p);
			IAbilityData aData = AbilityDataCapability.get(p);
			if(hData.hasQuest(ModQuests.INQUEST) && !hData.getQuest(ModQuests.INQUEST).isFinished() && !hData.getQuest(ModQuests.INQUEST).canFinish() && aData.hasActiveAbility(ModAbilities.ZETSU_ABILITY)) {
				hData.getQuest(ModQuests.INQUEST).setProgress(hData.getQuest(ModQuests.INQUEST).getProgress() + 1);
			}
		}
	}
	
	@SubscribeEvent
	public static void tickKen(LivingUpdateEvent e) {
		if (e.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity p = (PlayerEntity) e.getEntityLiving();
			if (p.ticksExisted % 240 == 0) {
				IHunterData data = HunterDataCapability.get(p);
				IAbilityData ablData = AbilityDataCapability.get(p);
				if (data.hasQuest(ModQuests.KENQUEST) && !data.getQuest(ModQuests.KENQUEST).canFinish()
						&& !data.getQuest(ModQuests.KENQUEST).isFinished() && ablData.hasActiveAbility(ModAbilities.REN_ABILITY)) {
					data.getQuest(ModQuests.KENQUEST).setProgress(data.getQuest(ModQuests.KENQUEST).getProgress() + 1);
				}
			}
		}
	}
	@SubscribeEvent
	public static void initiateRyuQuest(AbilityActivateEvent e) {
		
		IHunterData data = HunterDataCapability.get(e.getEntity());
		if(!(e.getEntity() instanceof PlayerEntity))
			return;
		if(data.hasQuest(ModQuests.RYUQUEST) && !data.getQuest(ModQuests.RYUQUEST).isFinished() && !data.getQuest(ModQuests.RYUQUEST).canFinish()) {
			RyuQuest q = (RyuQuest) data.getQuest(ModQuests.RYUQUEST);
			if(q.count == -1 && e.getAbility().equals(ModAbilities.KO_ABILITY)) {
				if(e.getEntity().world.isRemote()) {
				e.getEntity().sendMessage(new StringTextComponent("Ryu Quest has started ticking!").applyTextStyle(TextFormatting.RED));
				}
				q.count = 0;
				q.iteration = 1;
				return;
			} else if(q.count < 1240 && q.count > 1160 && e.getAbility().equals(ModAbilities.REN_ABILITY)) {
				q.iteration = 2;
			}else if(q.count < 2440 && q.count > 2360 && e.getAbility().equals(ModAbilities.ZETSU_ABILITY)) {
				q.iteration = 3;
			}else if(q.count < 3640 && q.count > 3560 && e.getAbility().equals(ModAbilities.KO_ABILITY)) {
				q.iteration = 4;
			}else if(q.count < 4840 && q.count > 4760 && e.getAbility().equals(ModAbilities.REN_ABILITY)) {
				q.iteration = 5;
			}else if(q.count < 6040 && q.count > 5960 && e.getAbility().equals(ModAbilities.ZETSU_ABILITY)) {
				q.iteration = 6;
				data.getQuest(ModQuests.RYUQUEST).setProgress(100);
				if(e.getEntity().world.isRemote()) {
				e.getEntity().sendMessage(new StringTextComponent("Ryu Ticker: Congrats! You Did it!").applyTextStyle(TextFormatting.RED));
				}
			} else {
					if(e.getEntity().world.isRemote()) {
				e.getEntity().sendMessage(new StringTextComponent("Ryu Ticker: You have failed the quest, resetting progress.").applyTextStyle(TextFormatting.RED));
				e.getEntity().sendMessage(new StringTextComponent(String.valueOf(q.count)).applyTextStyle(TextFormatting.RED));
					}
				
				q.count = -1;
				q.iteration = 0;
			}
			
			
		}
	}
	
	@SubscribeEvent
	public static void tickRyuQuest(LivingUpdateEvent e) {
		IHunterData data = HunterDataCapability.get(e.getEntityLiving());
		
		if(data.hasQuest(ModQuests.RYUQUEST) && !data.getQuest(ModQuests.RYUQUEST).isFinished() && !data.getQuest(ModQuests.RYUQUEST).canFinish()) {
			RyuQuest q = (RyuQuest) data.getQuest(ModQuests.RYUQUEST);
			if(q.count > -1) {
			q.count++;
			}
			
			if(q.count % 20 == 0) {
				if(e.getEntity().world.isRemote()) {
				e.getEntityLiving().sendMessage(new StringTextComponent(String.valueOf(q.count / 20)));
				}
			}
		}
	}
	@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
	public static class BasicsQuestEventsClient{
		
		@SubscribeEvent
		public static void track(RenderLivingEvent.Post<LivingEntity, EntityModel<LivingEntity>> e) {
		
			
			
			if(!(e.getEntity().ticksExisted % 20 == 0))
				return;
			    PlayerEntity p = Minecraft.getInstance().player;
			
				IAbilityData pData = AbilityDataCapability.get(p);
				IAbilityData tData = AbilityDataCapability.get(e.getEntity());
			
				IHunterData hData = HunterDataCapability.get(p);
				if(!hData.hasQuest(ModQuests.GYOQUEST))
					return;
				if(!hData.getQuest(ModQuests.GYOQUEST).isFinished() && pData.getSlotAbility(ModAbilities.REN_ABILITY) != null && pData.getSlotAbility(ModAbilities.REN_ABILITY).isInPassive() && Helper.hasActiveAbility(tData)) {
					

					PacketHandler.INSTANCE.sendToServer(new QuestProgressPacket(ModQuests.GYOQUEST.getId(), 100));
			
				
			}
		}
		
		
		@SubscribeEvent
		public static void trackEn(RenderLivingEvent.Post<LivingEntity, EntityModel<LivingEntity>> e) {
		
			
			
			if(!(e.getEntity().ticksExisted % 20 == 0))
				return;
			    PlayerEntity p = Minecraft.getInstance().player;
			
				IAbilityData pData = AbilityDataCapability.get(p);
				IAbilityData tData = AbilityDataCapability.get(e.getEntity());
			
				IHunterData hData = HunterDataCapability.get(p);
				boolean case1 = !(e.getEntity() instanceof MonsterEntity);
				boolean case2 = !(e.getEntity() instanceof IMob);
				if(case1 && case2)
					return;
				if(!hData.hasQuest(ModQuests.ENQUEST))
					return;
				if(!hData.getQuest(ModQuests.ENQUEST).isFinished() && pData.hasActiveAbility(ModAbilities.GYO_ABILITY) ) {
					
					EnQuest quest = (EnQuest) hData.getQuest(ModQuests.ENQUEST);

					if(!quest.MONSTERS_DISCOVERED.contains(e.getEntity().getType().getRegistryName())) {
						quest.MONSTERS_DISCOVERED.add(e.getEntity().getType().getRegistryName());
						if((quest.MONSTERS_DISCOVERED.size() * 5) > 100) {
							quest.setProgress(100);
						} else {
						quest.setProgress(quest.MONSTERS_DISCOVERED.size() * 5);
						}
						
						PacketHandler.INSTANCE.sendToServer(new QuestUpdatePacket(quest));

					}
			
				
			}
		}


	}
}
