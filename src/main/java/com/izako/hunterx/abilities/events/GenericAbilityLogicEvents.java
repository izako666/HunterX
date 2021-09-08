package com.izako.hunterx.abilities.events;

import java.util.List;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.PunchAbility;
import com.izako.hunterx.izapi.ability.TruePassiveAbility;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.PunchAbilityPacket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class GenericAbilityLogicEvents {

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

				if (ability instanceof PunchAbility && ability.isInPassive() && heldItem.isEmpty())
				{
					float damage = ((PunchAbility) ability).onPunch(player, target);
					PacketHandler.INSTANCE.sendTo(new PunchAbilityPacket(ability.getId(),target), ((ServerPlayerEntity)player).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
					if(damage < 0)
						event.setCanceled(true);
					
					event.setAmount(event.getAmount() + damage);
					if(ability instanceof ITrainable) {
						ITrainable trainable = (ITrainable) ability;
						ability.setXp(ability.getXp() + (trainable.getXPOnUsage() + (ability.rand.nextDouble() - 0.5))/ 6, player);
					}
				}
			}
		}
	}
	
	@SubscribeEvent 
	public static void tick(LivingUpdateEvent evt) {
		
		IAbilityData data = AbilityDataCapability.get(evt.getEntityLiving());
		
	
		List<Ability> abilities = data.getAbilities();
		
		for(Ability abl : abilities) {
			if(abl instanceof TruePassiveAbility) {
				((TruePassiveAbility)abl).tick(evt.getEntityLiving());
			}
		}
	}
}
