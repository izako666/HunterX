package com.izako.HunterX.commands;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.izako.HunterX.worlddata.StructureSpawning;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class CommandLocateHxH extends CommandBase {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "locatehxh";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "command.locatehxh.usage";
	}

	 public int getRequiredPermissionLevel()
	    {
	        return 2;
	    }
	  public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
	    {
	        return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[] {"Blimp"}) : Collections.emptyList();
	    }
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		// TODO Auto-generated method stub
		StructureSpawning data = StructureSpawning.get(server.getEntityWorld());
	 
		   sender.sendMessage(new TextComponentString(data.getPos().toString())); 

	   

	}

}
