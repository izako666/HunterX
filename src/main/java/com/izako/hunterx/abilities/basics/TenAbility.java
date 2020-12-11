package com.izako.hunterx.abilities.basics;

import java.util.UUID;

import com.izako.hunterx.Main;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IOnPlayerRender;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PassiveAbility;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.SyncAbilityRenderingPacket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class TenAbility extends PassiveAbility implements IOnPlayerRender , ITrainable{

	public TenAbility() {

		super();
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setConsumptionType(AuraConsumptionType.NONE).setMaxCooldown(10).setMaxPassive(Integer.MAX_VALUE).setAuraConsumption(this::auraConsumptionEvent).setMaxCooldown(20 * 4).setNenType(NenType.UNKNOWN);
	}
	

	public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/items/card.png");
	public static final UUID modifierUUID = UUID.fromString("22560518-3370-4b84-a7a9-22a240cf3232");
	@Override
	public void onStartPassive(LivingEntity p) {
		AttributeModifier mod = new AttributeModifier(modifierUUID, "tenmodifier", Helper.getTrueValue(30, this, p), Operation.ADDITION);
		if(p.getAttribute(SharedMonsterAttributes.ARMOR).getModifier(modifierUUID) == null) {
		p.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(mod);
		}
		}

	@Override
	public void duringPassive(LivingEntity p) {
		if(p.getAttribute(SharedMonsterAttributes.ARMOR).getModifier(modifierUUID) == null) {
			AttributeModifier mod = new AttributeModifier(modifierUUID, "tenmodifier", Helper.getTrueValue(30, this, p), Operation.ADDITION);
			p.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(mod);

		}
	}

	@Override
	public void onEndPassive(LivingEntity p) {
		p.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier(modifierUUID);
	}

	@Override
	public String getId() {
		return "ten";
	}

	@Override
	public String getName() {
		return "Ten";
	}
	private int auraConsumptionEvent() {
		if(this.getPassiveTimer() % 20 == 0) {
		int val =  (int) (5 * (this.getCurrentAuraConScale()));
		 return val;
		}
		return 0;
	}

	@Override
	public String getDesc() {
		return "Ten controls your aura and keeps it from leaking, which makes you passively stronger.";
	}

}
