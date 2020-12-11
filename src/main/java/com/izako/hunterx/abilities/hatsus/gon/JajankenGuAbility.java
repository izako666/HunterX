package com.izako.hunterx.abilities.hatsus.gon;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.ChargeablePassiveAbility;
import com.izako.hunterx.izapi.ability.IHandOverlay;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.AbilityUsePacket;
import com.izako.wypi.WyHelper;
import com.izako.wypi.particles.GenericParticleData;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class JajankenGuAbility extends ChargeablePassiveAbility implements IHandOverlay,ITrainable{

	public JajankenGuAbility() {
		this.props = new Ability.Properties(this).setNenType(NenType.ENHANCER).setAbilityType(AbilityType.CHARGING_PASSIVE).setMaxCharging(20 * 20).setMaxPassive(40 * 20);
	}
	@Override
	public String getId() {
		return "jajankengu";
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
	@Override
	public void duringPassive(LivingEntity p) {
		p.addPotionEffect(new EffectInstance(Effects.SLOWNESS,20,2,false,false));
		super.duringPassive(p);
	}
	@Override
	public String getName() {
		return "Jajanken:Gu";
	}

	@Override
	public String getDesc() {
		return "Gon's hatsu, jajanken: Rock. Charge your aura and then attack with a punch.";
	}

	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event)
	{
		if (event.getSource().getTrueSource() instanceof PlayerEntity && event.getSource().getTrueSource() == event.getSource().getImmediateSource())
		{
			PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
			IAbilityData props = AbilityDataCapability.get(player);
			LivingEntity target = event.getEntityLiving();
			ItemStack heldItem = player.getHeldItemMainhand();

			for (Ability ability : props.getSlotAbilities())
			{
				if (ability == null)
					continue;

				if (ability instanceof JajankenGuAbility && ability.isInPassive() && heldItem.isEmpty())
				{
					float initialValue = (float) Helper.fromRangeToRange(0, ability.props.maxCharging, 1, 80, ability.getChargingTimer());
					float damage = Helper.getTrueValue(initialValue, ability, player);
					if(damage < 0)
						event.setCanceled(true);
					
					event.setAmount(event.getAmount() + damage);
					ability.endAbility(player);
					PacketHandler.INSTANCE.sendTo(new AbilityUsePacket(ability.getSlot()), ((ServerPlayerEntity)player).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
					if(ability instanceof ITrainable) {
						ITrainable trainable = (ITrainable) ability;
						ability.setXp(ability.getXp() + (trainable.getXPOnUsage() + (ability.rand.nextDouble() - 0.5))/ 6, player);
					}
				}
			}
		}
	}
	@Override
	public boolean isFullArm() {
		return false;
	}

}
