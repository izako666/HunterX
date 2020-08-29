package com.izako.hunterx.abilities.events;

import java.util.Set;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

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
			Set<Block> blocks = ObfuscationReflectionHelper.getPrivateValue(ToolItem.class,(ToolItem) p.getHeldItem(hand).getItem().getItem(), "effectiveBlocks");

			if(blocks.contains(e.getState().getBlock().getBlock())) {
				if(data.getSlotAbility(ModAbilities.SHU_ABILITY) != null && data.getSlotAbility(ModAbilities.SHU_ABILITY).isInPassive()) {
					e.setNewSpeed((float) ((e.getOriginalSpeed()  * data.getSlotAbility(ModAbilities.SHU_ABILITY).getCurrentPowerScale()) + 0.5));
				}
			}
		}
	}
}
