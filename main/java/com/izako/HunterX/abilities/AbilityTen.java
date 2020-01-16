package com.izako.HunterX.abilities;

import java.util.UUID;

import com.izako.HunterX.Main;
import com.izako.HunterX.entity.particles.EntityTenParticle;
import com.izako.HunterX.izapi.abilities.Ability;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.EntityModifierServerChange;
import com.izako.HunterX.network.packets.EntityStatsClientSync;
import com.izako.HunterX.network.packets.EntityStatsServerSync;
import com.izako.HunterX.network.packets.PacketParticles;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;
import com.izako.HunterX.util.Reference;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class AbilityTen extends Ability {
	
	private AttributeModifier defenseModifier;
	public static UUID defense_attributemodifier_uuid = UUID.fromString("806281d3-bb17-4b3f-8142-f03f077ba2e2");
	
	private AttributeModifier healthModifier;
	public static UUID health_attributemodifier_uuid = UUID.fromString("135d510d-26c6-403e-8615-899862332e86");
	public ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/abilities/abilityjajanken.png");

	public AbilityTen(String str) {
		super(str);
	}
	@Override
	public void startAbility(EntityPlayer player) {

		System.out.println(this.isPassiveActive(player));
	
		super.startAbility(player);
		
		
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		
		//defense increase
		IAttributeInstance attributedefense = ((EntityLivingBase) player)
				.getEntityAttribute(SharedMonsterAttributes.ARMOR);
		
		stats.setDefenseStat(stats.getDefenseStat()*2);
		double defenseStat = stats.getDefenseStat();
		
		if(player instanceof EntityPlayerMP) {
			ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getDefenseStat(), 2, false), (EntityPlayerMP) player);
		}
		defenseModifier = new AttributeModifier(defense_attributemodifier_uuid, "defenseStatIncrease", defenseStat, 0)
				.setSaved(true);
		
		attributedefense.removeModifier(defenseModifier);
		attributedefense.applyModifier(defenseModifier);
		
		
		//health increase
		IAttributeInstance attributehealth = ((EntityLivingBase) player)
				.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
		
		stats.setHealthStat(stats.getHealthStat()*2);
		Double healthStat = stats.getHealthStat();
		
		ModidPacketHandler.INSTANCE.sendToServer(new EntityStatsServerSync(stats.getHealthStat(), 1, false));
		ModidPacketHandler.INSTANCE.sendToServer(new EntityModifierServerChange(stats.getHealthStat(), 1) );
		
		healthModifier = new AttributeModifier(health_attributemodifier_uuid, "healthStatIncrease", stats.getHealthStat(), 0)
				.setSaved(true);
		
		attributehealth.removeModifier(healthModifier);
		attributehealth.applyModifier(healthModifier);
	}
	
	@Override
	public void endAbility(EntityPlayer player) {
		// TODO Auto-generated method stub
		super.endAbility(player);
		
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		
		//Defense decrease double than increase due to technical issues
		stats.setDefenseStat(stats.getDefenseStat()/4);
		stats.setHealthStat(stats.getHealthStat()/4);
		

	}
	
	
}
