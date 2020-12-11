package com.izako.hunterx.abilities.basics;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IOnPlayerRender;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PassiveAbility;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.SyncAbilityRenderingPacket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class ZetsuAbility extends PassiveAbility implements IOnPlayerRender{

	public ZetsuAbility() {

		super();
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxCooldown(10).setMaxPassive(Integer.MAX_VALUE).setMaxCooldown(0).setNenType(NenType.UNKNOWN);
	}

	@Override
	public String getId() {
		return "zetsu";
	}

	@Override
	public String getName() {
		return "Zetsu";
	}


	@Override
	public void duringPassive(LivingEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		data.setCurrentNen((int) (data.getCurrentNen() + data.getNenCapacity() / (400 / this.getStrength())));
	}
	

	@Override
	public String getDesc() {
		return "Zetsu blocks of your aura nodes leaving you vulnerable but undetected.";
	}
}
