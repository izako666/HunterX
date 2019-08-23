package com.izako.HunterX.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class QuestPacketSync implements IMessage{

	  // A default constructor is always required
	  public QuestPacketSync(){}

	  
	 //the amount for the current 2 stats is, 1 for health, 2 for speed
	public int amount;
	public int statType;
	public String str;
	public int length;
	  public QuestPacketSync(String str, int statType, int amount) {
	    this.statType = statType;
	    this.amount = amount;
	    this.str = str;
	  }

	  @Override public void toBytes(ByteBuf buf) {
	    // Writes the int into the buf
		  PacketBuffer pbuf = new PacketBuffer(buf);
		  buf.writeInt(statType);
		  buf.writeInt(amount);
		  buf.writeInt(this.str.length());
		 pbuf.writeString(str);
		  
	  }

	  @Override public void fromBytes(ByteBuf buf) {
	    // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
	     PacketBuffer pbuf = new PacketBuffer(buf);
		  statType = buf.readInt();
		  amount = buf.readInt();
		  length = buf.readInt();
		  str = pbuf.readString(length);

		  
	  }

}