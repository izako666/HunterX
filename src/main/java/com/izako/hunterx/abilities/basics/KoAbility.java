package com.izako.hunterx.abilities.basics;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IHandOverlay;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PunchAbility;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.SyncAbilityRenderingPacket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class KoAbility extends PunchAbility implements IHandOverlay {

	private boolean queuedAuraConsumption = false;
	private boolean isInitialAuraConsumption = false;

	public KoAbility() {
		this.props = new Ability.Properties(this).setConsumptionType(AuraConsumptionType.PERCENTAGE)
				.setAuraConsumption(this::consumeAura).setAbilityType(AbilityType.PASSIVE)
				.setMaxPassive(Integer.MAX_VALUE).setMaxCooldown(20 * 8).setNenType(NenType.UNKNOWN);
		this.onPunch = this::onPunch;
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
	public String getDesc() {
		return "Ko puts all your aura in your first for maximum damage";
	}
	@Override
	public void onStartPassive(LivingEntity p) {
		this.queuedAuraConsumption = true;
		this.isInitialAuraConsumption = true;
	}





	private int consumeAura() {
		if (queuedAuraConsumption) {
			queuedAuraConsumption = false;
			if (isInitialAuraConsumption) {
				isInitialAuraConsumption = false;
				return 10;
			} else {
				return 10;
			}

		}
		return 0;
	}

	@Override
	public boolean isFullArm() {
		return true;
	}

	@Override
	public float onPunch(PlayerEntity p, LivingEntity target) {
		IAbilityData data = AbilityDataCapability.get(p);

		this.queuedAuraConsumption = true;
		return Helper.getTrueValue(30, this, p);
	}
}
