package com.izako.hunterx.abilities.basics;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IOnPlayerRender;
import com.izako.hunterx.izapi.ability.PassiveAbility;
import com.izako.hunterx.networking.ModidPacketHandler;
import com.izako.hunterx.networking.packets.SyncAbilityRenderingPacket;

import net.minecraft.entity.player.PlayerEntity;

public class ZetsuAbility extends PassiveAbility implements IOnPlayerRender{

	public ZetsuAbility() {

		super();
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxCooldown(10).setMaxPassive(Integer.MAX_VALUE).setMaxCooldown(0);
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
	public void onStartPassive(PlayerEntity p) {
		if(!p.world.isRemote()) {
		ModidPacketHandler.sendToTracking(p, new SyncAbilityRenderingPacket(this.getId(), p.getUniqueID(), true));
		}
	}

	@Override
	public void duringPassive(PlayerEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		data.setCurrentNen(data.getCurrentNen() + data.getNenCapacity() / 100);
	}
	
	@Override
	public void onEndPassive(PlayerEntity p) {
		if(!p.world.isRemote()) {
		ModidPacketHandler.sendToTracking(p, new SyncAbilityRenderingPacket(this.getId(), p.getUniqueID(), false));
	}
	}

}
