package com.izako.hunterx.abilities.basics;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IHandOverlay;
import com.izako.hunterx.izapi.ability.PunchAbility;
import com.izako.hunterx.networking.ModidPacketHandler;
import com.izako.hunterx.networking.packets.SyncAbilityRenderingPacket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;

public class KoAbility extends PunchAbility implements IHandOverlay {

	private boolean queuedAuraConsumption = false;
	private boolean isInitialAuraConsumption = false;

	public KoAbility() {
		this.props = new Ability.Properties(this).setConsumptionType(AuraConsumptionType.PERCENTAGE)
				.setAuraConsumption(this::consumeAura).setAbilityType(AbilityType.PASSIVE)
				.setMaxPassive(Integer.MAX_VALUE).setMaxCooldown(20 * 8);
	}

	@Override
	public String getId() {
		return "ko";
	}

	@Override
	public String getName() {
		return "Ko";
	}

	@Override
	public void onStartPassive(PlayerEntity p) {
		this.queuedAuraConsumption = true;
		this.isInitialAuraConsumption = true;
		if(!p.world.isRemote()) {
		ModidPacketHandler.sendToTracking(p, new SyncAbilityRenderingPacket(this.getId(), p.getUniqueID(), true));
		}
		}

	@Override
	public float onPunch(PlayerEntity p, LivingEntity target) {
		IAbilityData data = AbilityDataCapability.get(p);
		this.queuedAuraConsumption = true;
		return 30;
	}

	@Override
	public void onEndPassive(PlayerEntity p) {
		if(!p.world.isRemote()) {

		ModidPacketHandler.sendToTracking(p, new SyncAbilityRenderingPacket(this.getId(), p.getUniqueID(), false));
		}
		}
	private int consumeAura() {
		if (queuedAuraConsumption) {
			queuedAuraConsumption = false;
			if (isInitialAuraConsumption) {
				isInitialAuraConsumption = false;
				return 10;
			} else {
			return 5;
			}

		}
		return 0;
	}
}
