package com.izako.hunterx.data.hunterdata;

import com.izako.hunterx.Main;
import com.izako.hunterx.events.StatUpdateEvent;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class HunterDataResetEvent {

	@SubscribeEvent
	public static void onDeath(PlayerEvent.Clone e) {
		
		if(e.isWasDeath()) {
			PlayerEntity oldP = e.getOriginal();
			PlayerEntity p = e.getPlayer();
			IHunterData oldData = HunterDataCapability.get(oldP);
			IHunterData data = HunterDataCapability.get(p);
			
			data.setHealthStat(oldData.getHealthStat());
			data.setSpeedStat(oldData.getSpeedStat());
			data.setAttackStat(oldData.getAttackStat());
			data.setDefenseStat(oldData.getDefenseStat());
			data.setIsHunter(oldData.isHunter());
			
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
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
