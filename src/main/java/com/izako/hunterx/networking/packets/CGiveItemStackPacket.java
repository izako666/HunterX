package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class CGiveItemStackPacket {


	private ItemStack stack;
	public CGiveItemStackPacket() {}
	
	public CGiveItemStackPacket(ItemStack stack)
	{
		this.stack = stack.copy();
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeItemStack(stack, false);
	}
	
	public static CGiveItemStackPacket decode(PacketBuffer buffer)
	{
		CGiveItemStackPacket msg = new CGiveItemStackPacket();
		msg.stack = buffer.readItemStack();
		return msg;
	}

	public static void handle(CGiveItemStackPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				
				int empty =  player.inventory.mainInventory.lastIndexOf(ItemStack.EMPTY);
				if(!player.inventory.addItemStackToInventory(message.stack) || empty == -1) {

					player.dropItem(message.stack, true, true);
					System.out.println("dropped item on server " + message.stack.toString());
				}
				
			});	
		}
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
			ctx.get().enqueueWork(()-> {ClientHandler.handle(message);});
		}
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler{
		@OnlyIn(Dist.CLIENT)
		public static void handle(CGiveItemStackPacket msg) {
			ClientPlayerEntity p = Minecraft.getInstance().player;

			int empty =  p.inventory.mainInventory.lastIndexOf(ItemStack.EMPTY);

			p.inventory.addItemStackToInventory(msg.stack);
			

		}
	}
}