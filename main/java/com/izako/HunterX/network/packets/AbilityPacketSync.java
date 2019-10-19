package com.izako.HunterX.network.packets;

import com.izako.HunterX.init.ListAbilities;
import com.izako.HunterX.izapi.abilities.Ability;
import com.izako.HunterX.stats.capabilities.EntityStatsCapability;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class AbilityPacketSync implements IMessage{

	  // A default constructor is always required
	  public AbilityPacketSync(){}

	  
	
	  public int index;
	  public Ability a;
	  public int type;
	  public boolean valuebool;
	  public AbilityPacketSync(Ability a, Integer index, Integer type, boolean valuebool) {
	   this.index = index;
	   this.a = a;
	   this.type = type;
	   this.valuebool = valuebool;
	  }

	  @Override public void toBytes(ByteBuf buf) {
	    // Writes the int into the buf
		  PacketBuffer pbuf = new PacketBuffer(buf);
		  buf.writeInt(type);
		  buf.writeInt(index);
		  pbuf.writeInt(a.getID().length());
		  pbuf.writeString(a.getID());
		  buf.writeBoolean(valuebool);
	    
	  }

	  @Override public void fromBytes(ByteBuf buf) {
	    // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
	  PacketBuffer pbuf = new PacketBuffer(buf);
	      type = buf.readInt();
		  index = buf.readInt();
		  int length = pbuf.readInt();
	   a = ListAbilities.getAbilityFromID(pbuf.readString(length));
	   valuebool = buf.readBoolean();
	  }

}