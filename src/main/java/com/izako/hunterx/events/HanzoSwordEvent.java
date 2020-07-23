package com.izako.hunterx.events;

import com.izako.hunterx.init.ModItems;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.HanzoSwordPacket;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HanzoSwordEvent {

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.LOW)
	public void onInteract(PlayerInteractEvent event) {
		PlayerEntity p = event.getPlayer();
		ItemStack chest = p.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack sword = new ItemStack(ModItems.HANZO_SWORD);

		if (p.isSneaking() && chest.getItem() == ModItems.HANZO_CHESTPLATE && p.getEntityWorld().isRemote) {

			if (event instanceof PlayerInteractEvent.RightClickEmpty
					|| event instanceof PlayerInteractEvent.RightClickItem
					|| event instanceof PlayerInteractEvent.RightClickBlock) {
				if (!event.getHand().equals(Hand.OFF_HAND)) {

					if (!p.inventory.hasItemStack(sword) && p.getHeldItem(Hand.MAIN_HAND).isEmpty()) {

						PacketHandler.INSTANCE.sendToServer(new HanzoSwordPacket(false));

					} else if (p.inventory.hasItemStack(sword)) {

						PacketHandler.INSTANCE.sendToServer(new HanzoSwordPacket(true));

					} else {
						event.setResult(Result.DEFAULT);
					}

				}
			}

		}

	}
}
