package com.izako.HunterX.network;

import com.izako.HunterX.network.packets.EntityModifierServerChange;
import com.izako.HunterX.network.packets.EntityStatsServerSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;
import com.izako.HunterX.stats.events.HealthStatEvent;
import com.izako.HunterX.stats.events.SpeedStatEvent;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class EntityModifierServerChangeHandler implements IMessageHandler<EntityModifierServerChange, IMessage>{
	@Override
	public IMessage onMessage(EntityModifierServerChange message, MessageContext ctx) {
		// TODO Auto-generated method stub
	    EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
	    Double amount = message.amount;
	    int statType = message.statType;

	    serverPlayer.getServerWorld().addScheduledTask(() -> {
	    	IAttributeInstance attributeH = ((EntityLivingBase) serverPlayer)
					.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
	    	IAttributeInstance attributeS = ((EntityLivingBase) serverPlayer)
					.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		      if(statType == 1) {
				     AttributeModifier hm = new AttributeModifier(HealthStatEvent.health_attributemodifier_uuid, "healthStatIncrease", amount, 0 );

				     attributeH.removeModifier(hm);
				     attributeH.applyModifier(hm);
		      } else if(statType == 2) {
				     AttributeModifier sm = new AttributeModifier(SpeedStatEvent.speed_attribute_modifier, "speedStatIncrease", amount, 0 );

				     attributeS.removeModifier(sm);
				     attributeS.applyModifier(sm);

		      } 
		    }); 
		return null;
	}
}
