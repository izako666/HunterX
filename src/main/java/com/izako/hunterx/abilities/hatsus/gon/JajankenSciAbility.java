package com.izako.hunterx.abilities.hatsus.gon;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModEffects;
import com.izako.hunterx.init.ModItems;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.ChargeableEquipAbility;
import com.izako.hunterx.izapi.ability.IHandOverlay;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.wypi.WyHelper;
import com.izako.wypi.particles.GenericParticleData;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class JajankenSciAbility extends ChargeableEquipAbility implements ITrainable,IHandOverlay {
	public JajankenSciAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.CHARGING_PASSIVE).setMaxPassive(Integer.MAX_VALUE).setNenType(NenType.TRANSMUTER);
	}

	@Override
	public ItemStack createItem(LivingEntity p) {
		ItemStack stack = new ItemStack(ModItems.JAJANKEN_SCISSORS);

		double val = Helper.fromRangeToRange(0, this.props.maxCharging, 11, 20, this.getChargingTimer());
		AttributeModifier mod = new AttributeModifier("attackmod", Helper.getTrueValue((float)val, this, p), Operation.ADDITION);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), mod, null);

		return stack;
	}

	@Override
	public String getId() {
		return "jajankensci";
	}

	@Override
	public String getName() {
		return "Jajanken:Sci";
	}

	@Override
	public String getDesc() {
		return "Transmute the aura in your hand into a sharp blade.";
	}

	@Override
	public boolean isFullArm() {
		return false;
	}

	@SubscribeEvent
	public static void attack(LivingDamageEvent evt) {
		if(evt.getSource().getImmediateSource() == evt.getSource().getTrueSource()) {
			if(evt.getSource().getTrueSource() instanceof LivingEntity) {
				LivingEntity entity = (LivingEntity) evt.getSource().getTrueSource();
			
				if(entity.getHeldItemMainhand().getItem() == ModItems.JAJANKEN_SCISSORS) {
					evt.getEntityLiving().addPotionEffect(new EffectInstance(ModEffects.BLEEDING_EFFECT, 100, 2, false, false));
				}
			}
		}
	}
	
	@Override
	public void duringCharging(LivingEntity p) {
		super.duringCharging(p);
		p.addPotionEffect(new EffectInstance(Effects.SLOWNESS,20,2,false,false));
	
		IAbilityData abilityData = AbilityDataCapability.get(p);
		if(!p.world.isRemote() && p.ticksExisted % 8 == 0) {
			double xRange = (p.world.getRandom().nextDouble()  -0.5) / 2;
			double yRange = (p.world.getRandom().nextDouble())/ 2;
			double zRange = (p.world.getRandom().nextDouble() -0.5) / 2;

			GenericParticleData data = new GenericParticleData();
			data.setTexture(new ResourceLocation(Main.MODID, "textures/particles/genericaura2.png"));
			data.setMotion(-xRange / 20, -yRange/8, -zRange/20);
			data.setLife(20);
			data.setSize(0.15f);
			data.setColor(abilityData.getAuraColor().getRed() / 255.0f, abilityData.getAuraColor().getGreen() / 255.0f, abilityData.getAuraColor().getBlue() / 255.0f, 0.7f);
			 WyHelper.spawnParticles(data, (ServerWorld) p.world, p.getPosX()+ Helper.getRotatedX(0.5f, p.rotationYaw + 180), p.getPosY()+ 1.1 + yRange, p.getPosZ()+ Helper.getRotatedZ(0.5f, p.rotationYaw + 180));

		}
	}

}
