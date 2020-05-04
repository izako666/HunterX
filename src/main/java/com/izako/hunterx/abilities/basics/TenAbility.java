package com.izako.hunterx.abilities.basics;

import java.util.UUID;

import com.izako.hunterx.Main;
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

	private static final ResourceLocation loc = new ResourceLocation(Main.MODID, "textures/particles/genericaura.png");
	public TenAbility() {
		this.setType(AbilityType.PASSIVE);
		this.setPassiveTimer(100);
        this.setMaxPassive(Integer.MAX_VALUE);
        this.setMaxCooldown(10);
		
	}
	public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/items/card.png");
	public static final UUID modifierUUID = UUID.fromString("22560518-3370-4b84-a7a9-22a240cf3232");
	@Override
	public void onStartPassive(PlayerEntity p) {
		AttributeModifier mod = new AttributeModifier(modifierUUID, "tenmodifier", 8, Operation.ADDITION);
		if(p.getAttribute(SharedMonsterAttributes.ARMOR).getModifier(modifierUUID) == null) {
		p.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(mod);
		}
		if(!p.world.isRemote()) {
			for(double i = 0; i <= Math.PI * 2; i += Math.PI / 32 ) {
				double offsetX = Math.cos(i);
				double offsetZ = Math.sin(i);
				GenericParticleData data = new GenericParticleData();
				data.setLife(20);
				data.setColor(0.96f, 0.4f,0.1f, 0.5f);
				data.setTexture(loc);
				data.setMotion(0, 0.1, 0);
				WyHelper.spawnParticles(data, (ServerWorld) p.world, p.posX + offsetX, p.posY + 0.5, p.posZ + offsetZ);

			}
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

	@Override
	public void renderDesc(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void use(PlayerEntity p) {
		
	}


	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

}
