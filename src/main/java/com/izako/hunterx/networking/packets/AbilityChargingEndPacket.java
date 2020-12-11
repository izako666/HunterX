package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.Ability.AbilityType;
import com.izako.hunterx.izapi.ability.ChargeableAbility;
import com.izako.hunterx.izapi.ability.ChargeablePassiveAbility;
import com.izako.hunterx.izapi.ability.ITrainable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class AbilityChargingEndPacket {

	int slot;
	int chargeTimer;
	public AbilityChargingEndPacket() {}
	public AbilityChargingEndPacket(int slot, int chargeTimer) {
		this.slot = slot;
		this.chargeTimer = chargeTimer;
	}


	public void encode(PacketBuffer buf) {

		buf.writeInt(slot);
		buf.writeInt(chargeTimer);
	}
	
	public static AbilityChargingEndPacket decode(PacketBuffer buf) {
		AbilityChargingEndPacket msg = new AbilityChargingEndPacket();
		msg.slot = buf.readInt();
		msg.chargeTimer = buf.readInt();
		return msg;
	}
	public static void handle(AbilityChargingEndPacket msg,  final Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity p = ctx.get().getSender();
				IAbilityData data = AbilityDataCapability.get(p);
				if(data.getAbilityInSlot(msg.slot) != null) {
					Ability a = data.getAbilityInSlot(msg.slot);
					a.setChargingTimer(msg.chargeTimer);
					if(a.props.type == AbilityType.CHARGING) {
					a.setCharging(false);
					((ChargeableAbility) a).onEndCharging(p);
					a.setCooldown(a.props.maxCooldown);
					a.setChargingTimer(0);
					if (a instanceof ITrainable) {
						ITrainable trainable = (ITrainable) a;
						double scale = (a.getChargingTimer() / a.props.maxCharging) + 0.5;
						a.setXp(a.getXp() + ((trainable.getXPOnUsage() + (a.rand.nextDouble() - 0.5)) * scale), p);
					}

					} else {
						a.setCharging(false);
						((ChargeablePassiveAbility) a).onStartPassive(p);
						a.setPassiveTimer(a.props.maxPassive);
						a.setInPassive(true);
						if (a instanceof ITrainable) {
							ITrainable trainable = (ITrainable) a;
							double scale = (a.getChargingTimer() / a.props.maxCharging) + 0.5;
							a.setXp(a.getXp() + ((trainable.getXPOnUsage() + (a.rand.nextDouble() - 0.5)) * scale), p);
						}

					}
				}
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
