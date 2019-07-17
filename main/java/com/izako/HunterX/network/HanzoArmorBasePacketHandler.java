package com.izako.HunterX.network;

import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.network.packets.HanzoArmorBasePacket;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class HanzoArmorBasePacketHandler implements IMessageHandler<HanzoArmorBasePacket, IMessage> {
		  // Do note that the default constructor is required, but implicitly defined in this case

		  @Override
		  public IMessage onMessage(HanzoArmorBasePacket message, MessageContext ctx) {
		    // This is the player the packet was sent to the server from
		    EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
		    // The value that was sent
		    int isSpawned = message.isSpawned;
		    IInventory inv = serverPlayer.inventory;
		    ItemStack sword = new ItemStack(ModItems.HANZOS_SWORD);
		    // Execute the action on the main server thread by adding it as a scheduled task
		    if(isSpawned == 0) {
		    serverPlayer.getServerWorld().addScheduledTask(() -> {
		      serverPlayer.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, sword);
		    }); 
		    	
		 
		    
		    
		  } else if(isSpawned == 1) {
			  serverPlayer.getServerWorld().addScheduledTask(() -> { 
				  for (int i = 0; i < inv.getSizeInventory(); i++) {
					if(inv.getStackInSlot(i).getItem() == ModItems.HANZOS_SWORD) {
					  inv.getStackInSlot(i).shrink(1);
					}
				  }
		  });
}
		    return null;
}
}