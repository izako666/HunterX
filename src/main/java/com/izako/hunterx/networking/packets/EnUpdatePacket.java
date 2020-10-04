package com.izako.hunterx.networking.packets;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.izako.hunterx.abilities.basics.EnAbility;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.entities.EnEntity;
import com.izako.hunterx.init.ModAbilities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class EnUpdatePacket {

	public EnUpdatePacket() {
	}

	List<LivingEntity> list= new ArrayList<>();

	List<Integer> temp = new ArrayList<>();
	int owner;
	int en;
	public EnUpdatePacket(List<LivingEntity> entities, LivingEntity owner, EnEntity en) {

		this.list = entities;
		this.owner = owner.getEntityId();
		this.en = en.getEntityId();
	}

	public void encode(PacketBuffer buf) {
		buf.writeInt(list.size());
		this.list.forEach(entity -> {
			buf.writeInt(entity.getEntityId());
		});
		buf.writeInt(owner);
		buf.writeInt(en);
	}

	public static EnUpdatePacket decode(PacketBuffer buf) {
		EnUpdatePacket msg = new EnUpdatePacket();
		int size  = buf.readInt();
		for(int i = 0; i < size; i++) {
			msg.temp.add(buf.readInt());
		}
		msg.owner = buf.readInt();
		msg.en = buf.readInt();
		return msg;
	}

	public static void handle(EnUpdatePacket msg, final Supplier<NetworkEvent.Context> ctx) {

		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
			ctx.get().enqueueWork(() -> {
				ClientHandler.handle(msg);
			});
		}
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler {
		@OnlyIn(Dist.CLIENT)
		public static void handle(EnUpdatePacket msg) {
			PlayerEntity p = Minecraft.getInstance().player;
			World world = Minecraft.getInstance().world;
			LivingEntity owner = (LivingEntity) world.getEntityByID(msg.owner);
			IAbilityData data = AbilityDataCapability.get(owner);
			EnEntity enEntity = (EnEntity) world.getEntityByID(msg.en);
			EnAbility en = (EnAbility) data.getActiveAbility(ModAbilities.EN_ABILITY, owner);

			en.entity = enEntity;
			msg.temp.forEach(in -> {
				msg.list.add((LivingEntity) world.getEntityByID(in));
			});
			if(en != null) {
				en.clientEntities = msg.list;
			}

		}
	}

}
