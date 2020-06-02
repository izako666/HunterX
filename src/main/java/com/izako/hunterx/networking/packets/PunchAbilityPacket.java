package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.PunchAbility;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class PunchAbilityPacket {



	public PunchAbilityPacket() {
	}

String id;
int entityid;
	public PunchAbilityPacket(String id, Entity target) {

		this.id = id;
		this.entityid = target.getEntityId();
	}

	public void encode(PacketBuffer buf) {

		buf.writeString(id);
		buf.writeInt(entityid);
	}

	public static PunchAbilityPacket decode(PacketBuffer buf) {
		PunchAbilityPacket msg = new PunchAbilityPacket();
		msg.id = buf.readString();
		msg.entityid = buf.readInt();
		return msg;
	}

	public static void handle(PunchAbilityPacket msg, final Supplier<NetworkEvent.Context> ctx) {

		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
			ctx.get().enqueueWork(() -> {
				ClientHandler.handle(msg);
			});
		}
		 ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler{
		@OnlyIn(Dist.CLIENT)
		public static void handle(PunchAbilityPacket msg) {
			PlayerEntity p = Minecraft.getInstance().player;
            IAbilityData data = AbilityDataCapability.get(p);
           Ability abl = data.getSlotAbility(ModAbilities.getAbilityOfId(msg.id));
           LivingEntity e = (LivingEntity) p.world.getEntityByID(msg.entityid);
			((PunchAbility) abl).onPunch(p, e);
		}
	}

}
