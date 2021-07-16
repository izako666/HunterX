package com.izako.hunterx.networking.packets;

import java.util.List;
import java.util.function.Supplier;

import com.izako.hunterx.data.worlddata.ModWorldData;
import com.izako.hunterx.gui.ComputerScreen;
import com.izako.hunterx.gui.ComputerScreen.PCEntry;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class UpdatePCStockPacket {



	List<ComputerScreen.PCEntry> stock;
	boolean isHunter;
	public UpdatePCStockPacket() {}
	public UpdatePCStockPacket(List<ComputerScreen.PCEntry> stock, boolean isHunter) {
		
	}
	
	public void encode(PacketBuffer buf) {
		buf.writeInt(stock.size());
		for(int i = 0; i < stock.size(); i++) {
			buf.writeCompoundTag(stock.get(i).write());
		}

	}
	
	public static UpdatePCStockPacket decode(PacketBuffer buf) {
		UpdatePCStockPacket msg = new UpdatePCStockPacket();
		int size = buf.readInt();
		
		for(int i = 0; i < size; i++) {
			msg.stock.add(PCEntry.read(buf.readCompoundTag()));
		}

		return msg;
	}
	
	public static void handle(UpdatePCStockPacket msg,  final Supplier<NetworkEvent.Context> ctx) {
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				ServerPlayerEntity p = ctx.get().getSender();
				
				ModWorldData data = ModWorldData.get(p.getServerWorld());
				data.setStock(msg.stock, msg.isHunter);
			});
		}
		ctx.get().setPacketHandled(true);
	}

}
