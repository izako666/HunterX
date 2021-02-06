package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.networking.PacketHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class ActivateAbilityPacket {

	public ActivateAbilityPacket() {
	}

boolean start;
String ablId;
int entityId;
int aura;
	public ActivateAbilityPacket(Ability abl, boolean start, LivingEntity entity,int aura) {

		this.ablId = abl.getId();
		this.start = start;
		this.entityId = entity.getEntityId();
		this.aura = aura;
	}

	public void encode(PacketBuffer buf) {

		buf.writeBoolean(start);
		
		buf.writeInt(this.ablId.length());
		buf.writeString(this.ablId, this.ablId.length());
		buf.writeInt(this.entityId);
		buf.writeInt(aura);
	}

	public static ActivateAbilityPacket decode(PacketBuffer buf) {
		ActivateAbilityPacket msg = new ActivateAbilityPacket();
		msg.start = buf.readBoolean();
		int length = buf.readInt();
		msg.ablId = buf.readString(length);
		msg.entityId = buf.readInt();
		msg.aura = buf.readInt();
		return msg;
	}

	public static void handle(ActivateAbilityPacket msg, final Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				if(msg.start) {
					PlayerEntity p = ctx.get().getSender();
					LivingEntity user = (LivingEntity) p.world.getEntityByID(msg.entityId);
					IAbilityData data = AbilityDataCapability.get(user);
					 Ability abl = data.getAbility(ModAbilities.getAbilityOfId(msg.ablId));
					 
					 if(user instanceof PlayerEntity) {
						 abl = data.getSlotAbility(ModAbilities.getAbilityOfId(msg.ablId));
					 }
					 
					 if(!abl.isActive()) {
					abl.use(user);
					 }
				} else {
					PlayerEntity p = ctx.get().getSender();
					LivingEntity user = (LivingEntity) p.world.getEntityByID(msg.entityId);
					IAbilityData data = AbilityDataCapability.get(user);
					 Ability abl = data.getAbility(ModAbilities.getAbilityOfId(msg.ablId));
					 
					 if(user instanceof PlayerEntity) {
						 abl = data.getSlotAbility(ModAbilities.getAbilityOfId(msg.ablId));
					 }
					 
					 abl.endAbility(user);		
					 }
			});
		} 
		else if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
			ctx.get().enqueueWork(() -> {
				ClientHandler.handle(msg);
			});
			ctx.get().setPacketHandled(true);
		}
	}

	public static class ClientHandler{
		@OnlyIn(Dist.CLIENT)
		public static void handle(ActivateAbilityPacket msg) {
			if(msg.start) {

				ClientPlayerEntity p = Minecraft.getInstance().player;
				LivingEntity user = (LivingEntity) p.world.getEntityByID(msg.entityId);
				IAbilityData data = AbilityDataCapability.get(user);
				 Ability abl = data.getAbility(ModAbilities.getAbilityOfId(msg.ablId));
				 
				 if(user instanceof PlayerEntity) {
					 abl = data.getSlotAbility(ModAbilities.getAbilityOfId(msg.ablId));
				 }
				 
				 if(!abl.isActive()) {
				abl.use(user);
				 }
				 data.setCurrentNen(msg.aura);
			} else {
				ClientPlayerEntity p = Minecraft.getInstance().player;
				LivingEntity user = (LivingEntity) p.world.getEntityByID(msg.entityId);
				IAbilityData data = AbilityDataCapability.get(user);
				 Ability abl = data.getAbility(ModAbilities.getAbilityOfId(msg.ablId));
				 
				 if(user instanceof PlayerEntity) {
					 abl = data.getSlotAbility(ModAbilities.getAbilityOfId(msg.ablId));
				 }
				 
				 abl.endAbility(user);	
				 
				 data.setCurrentNen(msg.aura);

				 }
			
			
		}
	}
}
