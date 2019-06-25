package com.izako.HunterX.network;

import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.items.entities.EntityCard;
import com.izako.HunterX.network.packets.CardPacket;
import com.izako.HunterX.network.packets.HanzoArmorBasePacket;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CardPacketHandler implements IMessageHandler<CardPacket, IMessage>{
	
	  // Do note that the default constructor is required, but implicitly defined in this case

	  @Override
	  public IMessage onMessage(CardPacket message, MessageContext ctx) {
	    // This is the player the packet was sent to the server from
	    EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
	    World worldIn = serverPlayer.getServerWorld();
	    // The value that was sent
	    int isThrown = message.isThrown;
	    ItemStack itemstack = serverPlayer.getHeldItem(EnumHand.MAIN_HAND);
        Vec3d aim = serverPlayer.getLookVec();
	
	  if(isThrown == 0) {
		  serverPlayer.getServerWorld().addScheduledTask(() -> { 
		  EntityCard entitycard = new EntityCard(worldIn,serverPlayer, 1, 1, 1);
          
          entitycard.setPosition(serverPlayer.posX + aim.x, serverPlayer.posY + serverPlayer.eyeHeight, serverPlayer.posZ + aim.z);
  		entitycard.motionX = aim.x * 4;
  		entitycard.motionY = aim.y * 4;
  		entitycard.motionZ = aim.z * 4;
  		
          worldIn.spawnEntity(entitycard);
		  });
	  }

	    return null;
}
}

