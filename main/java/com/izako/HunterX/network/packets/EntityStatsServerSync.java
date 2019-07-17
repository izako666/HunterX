package com.izako.HunterX.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class EntityStatsServerSync implements IMessage {

	  // A default constructor is always required
	  public EntityStatsServerSync(){}

	  
	 //the amount for the current 4 stats is, 1 for health, 2 for defense, 3 for speed, 4 for attack
	public Double amount;
	public int statType;
	  public EntityStatsServerSync(Double amount, int statType) {
	    this.amount = amount;
	    this.statType = statType;
	  }

	  @Override public void toBytes(ByteBuf buf) {
	    // Writes the int into the buf
	    buf.writeDouble(amount);
	    buf.writeInt(statType);
	  }

	  @Override public void fromBytes(ByteBuf buf) {
	    // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
	    amount = buf.readDouble();
	    statType = buf.readInt();
	  }
}
