package com.izako.hunterx.events;

import java.util.UUID;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.networking.ModidPacketHandler;
import com.izako.hunterx.networking.packets.ModifierUpdatePacket;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class StatUpdateEvent {

	public static UUID attack_attributemodifier_uuid = UUID.fromString("aa18be1b-b1d0-4917-a168-42dd240d000d");
	public static UUID defense_attributemodifier_uuid = UUID.fromString("806281d3-bb17-4b3f-8142-f03f077ba2e2");
	public static UUID health_attributemodifier_uuid = UUID.fromString("135d510d-26c6-403e-8615-899862332e86");
	public static UUID speed_attributemodifier_uuid = UUID.fromString("69082b90-7357-407c-9e82-7852b6925932");

	@SubscribeEvent
	public static void onAttack(LivingHurtEvent e) {
		AttributeModifier attackModifier;
		if (e.getSource().getTrueSource() instanceof PlayerEntity) {
			PlayerEntity p = (PlayerEntity) e.getSource().getTrueSource();
			IAttributeInstance attribute = ((LivingEntity) p).getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			IHunterData data = HunterDataCapability.get(p);
			double attackStat = data.getAttackStat();

			data.setAttackStat(attackStat + 0.05);
			attackModifier = new AttributeModifier(attack_attributemodifier_uuid, "attackStatIncrease", attackStat,
					Operation.ADDITION).setSaved(true);

			attribute.removeModifier(attack_attributemodifier_uuid);
			attribute.applyModifier(attackModifier);

		}
	}

	@SubscribeEvent
	public static void onHurt(LivingHurtEvent e) {
		AttributeModifier defenseModifier;
		if (e.getEntity() instanceof PlayerEntity) {
			PlayerEntity playerIn = (PlayerEntity) e.getEntity();

			IAttributeInstance attribute = ((LivingEntity) playerIn).getAttribute(SharedMonsterAttributes.ARMOR);
			IHunterData stats = HunterDataCapability.get(playerIn);
			double defenseStatCap = stats.getDefenseStat();
			stats.setDefenseStat(defenseStatCap + 0.025);
			defenseStatCap = stats.getDefenseStat();
			defenseModifier = new AttributeModifier(defense_attributemodifier_uuid, "defenseStatIncrease",
					defenseStatCap * 2, Operation.ADDITION).setSaved(true);
			attribute.removeModifier(defenseModifier);
			attribute.applyModifier(defenseModifier);
		}
	}

	@SubscribeEvent
	public static void onEat(LivingEntityUseItemEvent.Finish e) {
		if (e.getItem().isFood() && e.getEntityLiving() instanceof PlayerEntity) {

			PlayerEntity p = (PlayerEntity) e.getEntityLiving();

			IHunterData data = HunterDataCapability.get(p);
			data.setHealthStat(data.getHealthStat() + 0.1);
			ModidPacketHandler.INSTANCE.sendToServer(new StatsUpdatePacket(data));
			ModidPacketHandler.INSTANCE.sendToServer(new ModifierUpdatePacket(data.getHealthStat(), 1));
			e.setResult(Result.DEFAULT);
		}
	}

	@SubscribeEvent
	public static void onRun(LivingUpdateEvent e) {
		AttributeModifier speedModifier;
		if (e.getEntityLiving() instanceof PlayerEntity) {
			if (!e.getEntityLiving().world.isRemote) {
				PlayerEntity p = (PlayerEntity) e.getEntityLiving();
				if (p.isSprinting()) {
					IHunterData data = HunterDataCapability.get(p);
					IAttributeInstance attribute = ((LivingEntity) p)
							.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
					data.setSpeedStat(data.getSpeedStat() + 0.00026);
					speedModifier = new AttributeModifier(StatUpdateEvent.speed_attributemodifier_uuid,
							"speedStatIncrease", data.getSpeedStat() / 67, Operation.ADDITION);

					attribute.removeModifier(StatUpdateEvent.speed_attributemodifier_uuid);
					attribute.applyModifier(speedModifier);
				}
			}
		}
	}
}
