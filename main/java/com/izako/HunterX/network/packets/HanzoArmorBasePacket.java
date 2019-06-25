package com.izako.HunterX.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class HanzoArmorBasePacket implements IMessage {
	  // A default constructor is always required
	  public HanzoArmorBasePacket(){}

	  public int isSpawned;
	  public HanzoArmorBasePacket(int isSpawned) {
	    this.isSpawned = isSpawned;
	  }

	  @Override public void toBytes(ByteBuf buf) {
	    // Writes the int into the buf
	    buf.writeInt(isSpawned);
	  }

	  @Override public void fromBytes(ByteBuf buf) {
	    // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
	    isSpawned = buf.readInt();
	  }
	}
