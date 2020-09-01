package com.izako.hunterx.abilities.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.abilities.basics.ShuAbility;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ShuEvents {

	@SubscribeEvent
	public static void digSpeedEvent(PlayerEvent.BreakSpeed e) {
		PlayerEntity p = e.getPlayer();
		IAbilityData data = AbilityDataCapability.get(e.getPlayer());
		Hand hand = p.getActiveHand();
		if(hand == null)
			hand = Hand.MAIN_HAND;
		if(e.getPlayer().getHeldItem(hand).getItem().getItem() instanceof ToolItem) {

			
				if(ShuAbility.canHarvestBlock(e.getState(), p.getHeldItem(hand))) {
					if(data.getSlotAbility(ModAbilities.SHU_ABILITY) != null && data.getSlotAbility(ModAbilities.SHU_ABILITY).isInPassive()) {
						e.setNewSpeed((float) ((e.getOriginalSpeed()  * data.getSlotAbility(ModAbilities.SHU_ABILITY).getCurrentPowerScale()) + 0.5));
					}

				
			}
		}
	}
	@SubscribeEvent
	public static void update(LivingUpdateEvent e) {
		
		if(e.getEntityLiving().ticksExisted % (20 * 20) == 0 && e.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity p = (PlayerEntity) e.getEntityLiving();
			
			for(int i = 0; i < p.inventory.getSizeInventory(); i++) {
				ItemStack stack = p.inventory.getStackInSlot(i);
				if(stack.hasTag()) {
					if(stack.getTag().getBoolean("activeshu")) {
						IAbilityData data = AbilityDataCapability.get(p);
						if(data.getSlotAbility(ModAbilities.SHU_ABILITY) == null) {
							ShuAbility.removeEnchantment(Enchantments.UNBREAKING, stack);
							ShuAbility.removeEnchantment(Enchantments.SHARPNESS, stack);
							stack.getTag().remove("activeshu");
						} else if(!data.getSlotAbility(ModAbilities.SHU_ABILITY).isInPassive()) {
							ShuAbility.removeEnchantment(Enchantments.UNBREAKING, stack);
							ShuAbility.removeEnchantment(Enchantments.SHARPNESS, stack);
							stack.getTag().remove("activeshu");
						}
					}
				}
			}
		}
	}
}
