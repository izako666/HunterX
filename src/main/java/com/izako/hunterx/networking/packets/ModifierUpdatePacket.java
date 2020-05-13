package com.izako.hunterx.networking.packets;

import java.util.UUID;
import java.util.function.Supplier;

import com.izako.hunterx.events.StatUpdateEvent;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class ModifierUpdatePacket {

	
	public ModifierUpdatePacket() {}

	double amount;
	int statType;
	public ModifierUpdatePacket(double amount, int statType) {
		this.amount = amount;
		this.statType = statType;
	}
	
	public void encode(PacketBuffer buf) {

		buf.writeDouble(amount);
		buf.writeInt(statType);
	}
	
	public static ModifierUpdatePacket decode(PacketBuffer buf) {
		ModifierUpdatePacket msg = new ModifierUpdatePacket();
		msg.amount = buf.readDouble();
		msg.statType = buf.readInt();
		return msg;
	}
	public static void handle(ModifierUpdatePacket msg,  final Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity p = ctx.get().getSender();
		    	IAttributeInstance attributeH = ((LivingEntity) p)
						.getAttribute(SharedMonsterAttributes.MAX_HEALTH);
		    	IAttributeInstance attributeS = ((LivingEntity) p)
						.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);

		    	if(msg.statType == 1) {
		    		UUID hUUID = StatUpdateEvent.health_attributemodifier_uuid;
				     AttributeModifier hm = new AttributeModifier(hUUID, "healthStatIncrease", msg.amount, Operation.ADDITION );

				     attributeH.removeModifier(hUUID);
				     attributeH.applyModifier(hm);

		    	} else if(msg.statType == 2) {
		    		UUID sUUID = StatUpdateEvent.speed_attributemodifier_uuid;
				     AttributeModifier sm = new AttributeModifier(sUUID, "speedStatIncrease", msg.amount, Operation.ADDITION );

				     attributeS.removeModifier(sUUID);
				     attributeS.applyModifier(sm);

		    	}
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
