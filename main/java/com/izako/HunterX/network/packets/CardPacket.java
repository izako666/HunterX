package com.izako.HunterX.network.packets;

import javax.xml.ws.handler.MessageContext;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public class CardPacket implements IMessage {

	
		  // A default constructor is always required
		  public CardPacket(){}

		  public int isThrown;
		  public CardPacket(int isThrown) {
		    this.isThrown = isThrown;
		  }

		  @Override public void toBytes(ByteBuf buf) {
		    // Writes the int into the buf
		    buf.writeInt(isThrown);
		  }

		  @Override public void fromBytes(ByteBuf buf) {
		    // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
		    isThrown = buf.readInt();
		  }

		}

