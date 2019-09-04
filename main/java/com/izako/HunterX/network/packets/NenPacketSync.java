package com.izako.HunterX.network.packets;

import com.izako.HunterX.init.ListAbilities;
import com.izako.HunterX.izapi.abilities.Ability;
import com.izako.HunterX.stats.capabilities.EntityStatsCapability;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class NenPacketSync implements IMessage{

	  // A default constructor is always required
	  public NenPacketSync(){}

	  
	//type 1 for nen capacity, type 2 for nentype, if nen type 1 for conjuration, 2 for enhancement, 3 for emmision, 
	  //4 for manipulation, 5 for specializiation, 6 for transmutation
	  public int index;
	  public int type;
	  public NenPacketSync(Integer index, Integer type) {
	   this.index = index;
	   this.type = type;
	  }

	  @Override public void toBytes(ByteBuf buf) {
	    // Writes the int into the buf
		  buf.writeInt(type);
		  buf.writeInt(index);
	    
	  }

	  @Override public void fromBytes(ByteBuf buf) {
	    // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
	      type = buf.readInt();
		  index = buf.readInt();
	  }

}