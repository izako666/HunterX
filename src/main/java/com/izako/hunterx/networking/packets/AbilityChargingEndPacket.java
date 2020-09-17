package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.Ability.AbilityType;
import com.izako.hunterx.izapi.ability.ChargeableAbility;
import com.izako.hunterx.izapi.ability.ChargeablePassiveAbility;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class AbilityChargingEndPacket {

	int slot;
	public AbilityChargingEndPacket() {}
	public AbilityChargingEndPacket(int slot) {
		this.slot = slot;
	}


	public void encode(PacketBuffer buf) {

		buf.writeInt(slot);
	}
	
	public static AbilityChargingEndPacket decode(PacketBuffer buf) {
		AbilityChargingEndPacket msg = new AbilityChargingEndPacket();
		msg.slot = buf.readInt();
		return msg;
	}
	public static void handle(AbilityChargingEndPacket msg,  final Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity p = ctx.get().getSender();
				IAbilityData data = AbilityDataCapability.get(p);
				if(data.getAbilityInSlot(msg.slot) != null) {
					Ability a = data.getAbilityInSlot(msg.slot);
					if(a.props.type == AbilityType.CHARGING) {
					a.setCharging(false);
					((ChargeableAbility) a).onEndCharging(p);
					a.setCooldown(a.props.maxCooldown);
					a.setChargingTimer(0);
					} else {
						a.setCharging(false);
						((ChargeablePassiveAbility) a).onStartPassive(p);
						a.setPassiveTimer(a.props.maxPassive);
						a.setInPassive(true);

					}
				}
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
