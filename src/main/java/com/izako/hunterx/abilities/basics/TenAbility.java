package com.izako.hunterx.abilities.basics;

import java.util.UUID;

import com.izako.hunterx.Main;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.PassiveAbility;
import com.izako.wypi.WyHelper;
import com.izako.wypi.particles.GenericParticleData;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;

public class TenAbility extends PassiveAbility{

	public TenAbility() {

		super();
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setConsumptionType(AuraConsumptionType.PERCENTAGE).setMaxCooldown(10).setMaxPassive(Integer.MAX_VALUE).setAuraConsumption(this::auraConsumptionEvent);
	}
	public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/items/card.png");
	public static final UUID modifierUUID = UUID.fromString("22560518-3370-4b84-a7a9-22a240cf3232");
	@Override
	public void onStartPassive(PlayerEntity p) {
		AttributeModifier mod = new AttributeModifier(modifierUUID, "tenmodifier", 8, Operation.ADDITION);
		if(p.getAttribute(SharedMonsterAttributes.ARMOR).getModifier(modifierUUID) == null) {
		p.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(mod);
		}
	}

	@Override
	public void duringPassive(PlayerEntity p) {
		if(p.getAttribute(SharedMonsterAttributes.ARMOR).getModifier(modifierUUID) == null) {
			AttributeModifier mod = new AttributeModifier(modifierUUID, "tenmodifier", 8, Operation.ADDITION);
			p.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(mod);

		}
	}

	@Override
	public void onEndPassive(PlayerEntity p) {
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
		 return 5;
		}
		return 0;
	}


}
