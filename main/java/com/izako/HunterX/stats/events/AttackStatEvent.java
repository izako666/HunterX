package com.izako.HunterX.stats.events;

import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.EntityStatsClientSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AttackStatEvent {

	private AttributeModifier attackModifier;
	

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (event.getSource().getTrueSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
			IAttributeInstance attribute = ((EntityLivingBase) player)
					.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
			double attackStat = stats.getAttackStat();
			if (attackStat < 10) {
				stats.setAttackStat(attackStat + 0.02);
				attackStat = stats.getAttackStat();
				if(player instanceof EntityPlayerMP) {
				ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getAttackStat(), 4), (EntityPlayerMP) player);}
				attackModifier = new AttributeModifier(player.getUniqueID(), "attackStatIncrease", attackStat, 0)
						.setSaved(true);
				attribute.removeModifier(attackModifier);
				attribute.applyModifier(attackModifier);
			} else if (attackStat >= 10) {
				attackModifier = new AttributeModifier(player.getUniqueID(), "attackStatIncrease", 10, 0)
						.setSaved(true);
				attribute.removeModifier(attackModifier);
				attribute.applyModifier(attackModifier);

			}

		}
	}
}
