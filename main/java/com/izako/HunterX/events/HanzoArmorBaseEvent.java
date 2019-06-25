package com.izako.HunterX.events;

import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.HanzoArmorBasePacket;

import net.minecraft.advancements.critereon.BredAnimalsTrigger.Instance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HanzoArmorBaseEvent {

	public static int itemArea = 0;

	public static void removeItem(EntityPlayer ep, ItemStack removeitem) {
		IInventory inv = ep.inventory;
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i) != null) {
				ItemStack j = inv.getStackInSlot(i);
				if (j.getItem() != null && j.getItem() == removeitem.getItem()) {
					inv.removeStackFromSlot(i);
					itemArea = i;
				}
			}
		}
	}

	@SubscribeEvent
	public void hanzoArmorBaseEvent(PlayerInteractEvent event) {
		EntityPlayer playerIn = event.getEntityPlayer();
		IInventory inv = playerIn.inventory;
		World world = event.getWorld();
		ItemStack sword = new ItemStack(ModItems.HANZOS_SWORD);
		ItemStack chest = playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		  for (int i = 0; i < inv.getSizeInventory(); i++) {	
		if ((event instanceof PlayerInteractEvent.RightClickEmpty
				 || event instanceof PlayerInteractEvent.RightClickBlock
				 ) && chest.getItem().equals(ModItems.HANZOS_CHESTPLATE)
					&& playerIn.isSneaking()
					&& playerIn.inventory.getStackInSlot(i).getItem() != ModItems.HANZOS_SWORD) {

				ModidPacketHandler.INSTANCE.sendToServer(new HanzoArmorBasePacket(0));
			} else if ((event instanceof PlayerInteractEvent.RightClickEmpty
					|| event instanceof PlayerInteractEvent.RightClickBlock
					|| (event instanceof PlayerInteractEvent.RightClickItem
							&& playerIn.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() == ModItems.HANZOS_SWORD))
							&& chest.getItem().equals(ModItems.HANZOS_CHESTPLATE) && playerIn.isSneaking()
							 && playerIn.inventory.getStackInSlot(playerIn.inventory.getSlotFor(sword)) != null) {

				ModidPacketHandler.INSTANCE.sendToServer(new HanzoArmorBasePacket(1));
			}

		}
	}
}