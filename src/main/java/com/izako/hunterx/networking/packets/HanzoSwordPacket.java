package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.init.ModItems;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class HanzoSwordPacket {

	public HanzoSwordPacket() {}
	public Boolean isSpawned;
	public HanzoSwordPacket(Boolean isSpawned) {
		this.isSpawned = isSpawned;
	}
	
	public void encode(PacketBuffer buf) {
		buf.writeBoolean(this.isSpawned);
	}
	
	public static HanzoSwordPacket decode(PacketBuffer buf) {
		HanzoSwordPacket msg = new HanzoSwordPacket();
		msg.isSpawned = buf.readBoolean();
		return msg;
	}
	
	public static void handle(HanzoSwordPacket msg,  final Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity player = ctx.get().getSender();
				if(msg.isSpawned == false) {

				player.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.HANZO_SWORD));
				System.out.println("false");
				} else {
					PlayerInventory inv = player.inventory;
					for (int i = 0; i < inv.getSizeInventory(); i++) {
						if (inv.getStackInSlot(i).getItem() == ModItems.HANZO_SWORD) {
							inv.getStackInSlot(i).shrink(1);
						}
					}
					System.out.println("true");
				}
			});
		}
	}
}
