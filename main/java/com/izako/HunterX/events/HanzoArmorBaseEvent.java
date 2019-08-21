package com.izako.HunterX.events;

import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.HanzoArmorBasePacket;

import net.minecraft.advancements.critereon.BredAnimalsTrigger.Instance;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HanzoArmorBaseEvent {

	@SubscribeEvent
	public void hanzoArmorBaseEvent(PlayerInteractEvent event) {
		EntityPlayer p = event.getEntityPlayer();
		ItemStack chest = p.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		ItemStack sword = new ItemStack(ModItems.HANZOS_SWORD);
		if (p.isSneaking() && chest.getItem() == ModItems.HANZOS_CHESTPLATE) {
			if (event instanceof PlayerInteractEvent.RightClickEmpty
					|| event instanceof PlayerInteractEvent.RightClickBlock
					|| event instanceof PlayerInteractEvent.RightClickItem) {
				
				if (!p.inventory.hasItemStack(sword) && p.getHeldItem(EnumHand.MAIN_HAND).isEmpty()) {
					ModidPacketHandler.INSTANCE.sendToServer(new HanzoArmorBasePacket(0));

				} else if (p.inventory.hasItemStack(sword)) {
					ModidPacketHandler.INSTANCE.sendToServer(new HanzoArmorBasePacket(1));

				}

			}

		}
	}
}