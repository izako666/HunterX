package com.izako.hunterx.networking.packets;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.data.worlddata.ModWorldData;
import com.izako.hunterx.gui.ComputerScreen;
import com.izako.hunterx.gui.ComputerScreen.PCEntry;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class ActivateComputerPacket {



	List<PCEntry> hStock;
	List<PCEntry> nStock;
	
	//to serverside variables
	
	List<PCEntry> itemsBought;
	boolean isHunter;
	
	public ActivateComputerPacket(List<PCEntry> normalStock, List<PCEntry> hunterStock) {
		this.hStock = hunterStock;
		this.nStock = normalStock;
	}
	
	public ActivateComputerPacket(List<PCEntry> itemsBought, boolean isHunter) {

		this.itemsBought = itemsBought;
		this.isHunter = isHunter;
		
	}


	public ActivateComputerPacket() {
	}




	public void encode(PacketBuffer buf) {

		boolean toServerSide = this.itemsBought == null ? false : true;
		
		buf.writeBoolean(toServerSide);
		if(toServerSide) {
			buf.writeInt(this.itemsBought.size());
			
			for(int i = 0; i < this.itemsBought.size(); i++) {
				buf.writeCompoundTag(this.itemsBought.get(i).write());
			}
			buf.writeBoolean(isHunter);
		} else {
		buf.writeInt(nStock.size());
		buf.writeInt(hStock.size());
		for(int i = 0; i < nStock.size(); i++) {
			buf.writeCompoundTag(nStock.get(i).write());
		}
		

		for(int i = 0; i < hStock.size(); i++) {
			buf.writeCompoundTag(hStock.get(i).write());
		}

		}
	}

	public static ActivateComputerPacket decode(PacketBuffer buf) {
		ActivateComputerPacket msg = new ActivateComputerPacket();
		
		boolean toServerSide = buf.readBoolean();
		if(toServerSide) {
			List<PCEntry> itemsBought = new ArrayList<>();
			int size = buf.readInt();
			
			for(int i = 0; i < size; i++) {
				itemsBought.add(PCEntry.read(buf.readCompoundTag()));
			}
			msg.itemsBought = itemsBought;
			msg.isHunter = buf.readBoolean();
		} else {
		int nSize = buf.readInt();
		int hSize = buf.readInt();
		List<PCEntry> nStock = new ArrayList<>();
		List<PCEntry> hStock = new ArrayList<>();
		for(int i = 0; i < nSize; i++) {
			nStock.add(PCEntry.read(buf.readCompoundTag()));
		}
		
		for(int i = 0; i < hSize; i++) {
			hStock.add(PCEntry.read(buf.readCompoundTag()));
		}
		msg.nStock = nStock;
		msg.hStock = hStock;
		}
		return msg;
	}

	public static void handle(ActivateComputerPacket msg, final Supplier<NetworkEvent.Context> ctx) {

		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
			ctx.get().enqueueWork(() -> {
				ClientHandler.handle(msg);
			});
		}
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(()-> {
				if(msg.isHunter) {
					ModWorldData data = ModWorldData.get(ctx.get().getSender().getServerWorld());
					
					List<PCEntry> hStock = data.getHunterStock();
					
					for(PCEntry entry : msg.itemsBought) {

						
						for(PCEntry test : hStock) {
							boolean equals = test.getItem() == entry.getItem();
							if(equals) {
									test.setCount(test.getCount() - entry.getCount());
							}
						}
					}
					boolean failedMissing= false;
					for(PCEntry entry : hStock) {
						if(entry.getCount() == 0) {
							hStock.remove(entry);
						} else if (entry.getCount() < 0) {
							failedMissing = true;
						}
					}
					if(failedMissing) {
						ctx.get().getSender().sendMessage(new StringTextComponent("your purchase could not be finalized, certain items are out of stock...").applyTextStyle(TextFormatting.RED));
					} else {
						float finalPrice = 0;
						for(PCEntry entry : msg.itemsBought) {
							finalPrice += (entry.getPrice() * entry.getCount());
						}
						IHunterData hData = HunterDataCapability.get(ctx.get().getSender());
						if(hData.getJenny() >= finalPrice) {
							hData.setJenny((int) (hData.getJenny() - finalPrice));
							//remember to add the edgecase of information items
							for(PCEntry entry : msg.itemsBought) {
								ItemStack stack = entry.getItem().copy();
								stack.setCount(entry.getCount());
								boolean done = ctx.get().getSender().addItemStackToInventory(stack);
								if(!done)
									ctx.get().getSender().dropItem(stack, true, true);
								
							}
							data.setStock(hStock, true);

						} else {
							ctx.get().getSender().sendMessage(new StringTextComponent("your purchase could not be finalized, you don't have the required amount of jenny...").applyTextStyle(TextFormatting.RED));

						}
					}
				}
				
				
				if(!msg.isHunter) {
					ModWorldData data = ModWorldData.get(ctx.get().getSender().getServerWorld());
					
					List<PCEntry> nStock = data.getNormalStock();
					
					for(PCEntry entry : msg.itemsBought) {

						
						for(PCEntry test : nStock) {
							boolean equals = test.getItem() == entry.getItem();
							if(equals) {
									test.setCount(test.getCount() - entry.getCount());
							}
						}
					}
					boolean failedMissing= false;
					for(PCEntry entry : nStock) {
						if(entry.getCount() == 0) {
							nStock.remove(entry);
						} else if (entry.getCount() < 0) {
							failedMissing = true;
						}
					}
					if(failedMissing) {
						ctx.get().getSender().sendMessage(new StringTextComponent("your purchase could not be finalized, certain items are out of stock...").applyTextStyle(TextFormatting.RED));
					} else {
						float finalPrice = 0;
						for(PCEntry entry : msg.itemsBought) {
							finalPrice += (entry.getPrice() * entry.getCount());
						}
						IHunterData hData = HunterDataCapability.get(ctx.get().getSender());
						if(hData.getJenny() >= finalPrice) {
							hData.setJenny((int) (hData.getJenny() - finalPrice));
							//remember to add the edgecase of information items
							for(PCEntry entry : msg.itemsBought) {
								ItemStack stack = entry.getItem().copy();
								stack.setCount(entry.getCount());
								boolean done = ctx.get().getSender().addItemStackToInventory(stack);
								if(!done)
									ctx.get().getSender().dropItem(stack, true, true);
								
							}
							data.setStock(nStock, false);

						} else {
							ctx.get().getSender().sendMessage(new StringTextComponent("your purchase could not be finalized, you don't have the required amount of jenny...").applyTextStyle(TextFormatting.RED));

						}
					}
				}
				
			});
		}
		 ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler{
		@OnlyIn(Dist.CLIENT)
		public static void handle(ActivateComputerPacket msg) {
			Minecraft.getInstance().displayGuiScreen(new ComputerScreen());
			
		}
	}
}
