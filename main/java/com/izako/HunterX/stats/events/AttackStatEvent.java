package com.izako.HunterX.stats.events;

import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AttackStatEvent {

	private AttributeModifier attackModifier;
	private double attackStat = 0;

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (event.getSource().getTrueSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
			IAttributeInstance attribute = ((EntityLivingBase) player)
					.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
			if (attackStat < 10) {
				stats.setAttackStat(attackStat + 1);
				attackStat = stats.getAttackStat();
				attackModifier = new AttributeModifier(player.getUniqueID(), "attackStatIncrease", attackStat, 0)
						.setSaved(true);
				attribute.removeModifier(attackModifier);
				attribute.applyModifier(attackModifier);
				player.sendMessage(new TextComponentString(Double.toString(attackStat)));
				player.sendMessage(new TextComponentString(Double.toString(event.getAmount())));
			} else if (attackStat >= 10) {
				AttributeModifier attackModifierMax = new AttributeModifier(player.getUniqueID(), "attackStatIncreaseMax", 10, 0)
						.setSaved(true);
				attribute.removeModifier(attackModifier);
				attribute.removeModifier(attackModifierMax);
				attribute.applyModifier(attackModifierMax);

			}

		}
	}
}
