package com.izako.HunterX.commands;

import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandStats extends CommandBase{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "stats";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "Command.stats.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		// TODO Auto-generated method stub
				IEntityStats stats =  sender.getCommandSenderEntity().getCapability(EntityStatsProvider.ENTITY_STATS, null);
	   sender.sendMessage(new TextComponentString("Health is:" + Double.toString(Math.round(stats.getHealthStat()))));
	   sender.sendMessage(new TextComponentString("Defense is:" + Double.toString(Math.round(stats.getDefenseStat() / 2))));
	   sender.sendMessage(new TextComponentString("Attack is:" + Double.toString(Math.round(stats.getAttackStat()))));
	   sender.sendMessage(new TextComponentString("Speed is:" + Double.toString(Math.round(stats.getSpeedStat() * 66))));

	
	}

}
