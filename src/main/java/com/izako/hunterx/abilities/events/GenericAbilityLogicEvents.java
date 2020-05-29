package com.izako.hunterx.abilities.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.PunchAbility;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class GenericAbilityLogicEvents {

	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event)
	{
		if (event.getSource().getTrueSource() instanceof PlayerEntity)
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
					
					if(damage < 0)
						event.setCanceled(true);
					
					event.setAmount(damage);
				}
			}
		}
	}
}
