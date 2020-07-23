package com.izako.hunterx.data.hunterdata;

import com.izako.hunterx.Main;
import com.izako.hunterx.events.StatUpdateEvent;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class HunterDataResetEvent {

	@SubscribeEvent
	public static void onDeath(PlayerEvent.Clone e) {
		
			PlayerEntity oldP = e.getOriginal();
			PlayerEntity p = e.getPlayer();
			IHunterData oldData = HunterDataCapability.get(oldP);
			IHunterData data = HunterDataCapability.get(p);
			
			data.setHealthStat(oldData.getHealthStat());
			data.setSpeedStat(oldData.getSpeedStat());
			data.setAttackStat(oldData.getAttackStat());
			data.setDefenseStat(oldData.getDefenseStat());
			data.setIsHunter(oldData.isHunter());
			data.setIsCharacterMade(oldData.isCharacterMade());
			IAttributeInstance attributeH = p.getAttribute(SharedMonsterAttributes.MAX_HEALTH);
			IAttributeInstance attributeS = p.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
			IAttributeInstance attributeA = p.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			IAttributeInstance attributeD = p.getAttribute(SharedMonsterAttributes.ARMOR);
			
			AttributeModifier modifierH = oldP.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(StatUpdateEvent.health_attributemodifier_uuid);
			AttributeModifier modifierS = oldP.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(StatUpdateEvent.speed_attributemodifier_uuid);
			AttributeModifier modifierA = oldP.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(StatUpdateEvent.attack_attributemodifier_uuid);
			AttributeModifier modifierD = oldP.getAttribute(SharedMonsterAttributes.ARMOR).getModifier(StatUpdateEvent.defense_attributemodifier_uuid);

			if(modifierH != null)
				attributeH.applyModifier(modifierH);
			if(modifierS != null)
				attributeS.applyModifier(modifierS);
			if(modifierA != null)
				attributeA.applyModifier(modifierA);
			if(modifierD != null)
				attributeD.applyModifier(modifierD);

			oldData.getQuests().forEach((k, v) -> {
				data.giveQuest(k, v);
			});
			oldData.getExtraQuestData().forEach((k, v) -> {
				data.getExtraQuestData().put(k, v);
			});
		
	}

	@SubscribeEvent
	public static void onJoinWorld(PlayerEvent.PlayerLoggedInEvent e) {
		PlayerEntity p = e.getPlayer();
		if(!p.world.isRemote) {
			IHunterData data = HunterDataCapability.get(p);
		PacketHandler.INSTANCE.sendTo(new StatsUpdatePacket(data, false), ((ServerPlayerEntity) p).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
		}
	}
	
	@SubscribeEvent
	public static void changeDimensionEvent(PlayerEvent.PlayerChangedDimensionEvent evt) {
		PlayerEntity p = evt.getPlayer();
		if(!p.world.isRemote) {
			IHunterData data = HunterDataCapability.get(p);
		PacketHandler.INSTANCE.sendTo(new StatsUpdatePacket(data, false), ((ServerPlayerEntity) p).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
