package com.izako.hunterx.commands;

import java.util.Collection;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;
import com.izako.wypi.network.WyNetwork;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkDirection;

public class JennyCommand {


	static ArgumentBuilder<CommandSource, ?> register()  {
	LiteralArgumentBuilder<CommandSource> builder = Commands.literal("jenny").requires(source -> source.hasPermissionLevel(3));

	builder.then(Commands.literal("+")
			.then(Commands.argument("value", IntegerArgumentType.integer(1, Integer.MAX_VALUE))
				.then(Commands.argument("targets", EntityArgument.players())
					.executes(context -> 
						{ 
							return addValue(context, IntegerArgumentType.getInteger(context, "value"), EntityArgument.getPlayers(context, "targets")); 
						}
					)
				)	
			)
		);
	
	builder
		.then(Commands.literal("=")
			.then(Commands.argument("value", IntegerArgumentType.integer(0, Integer.MAX_VALUE))
				.then(Commands.argument("targets", EntityArgument.players())
					.executes(context -> 
						{ 
							return setValue(context, IntegerArgumentType.getInteger(context, "value"), EntityArgument.getPlayers(context, "targets")); 
						}
					)
				)	
			)	
		);
	
	builder
		.then(Commands.literal("-")
			.then(Commands.argument("value", IntegerArgumentType.integer(1, Integer.MAX_VALUE))
				.then(Commands.argument("targets", EntityArgument.players())
					.executes(context -> 
						{ 
							return subtractValue(context, IntegerArgumentType.getInteger(context, "value"), EntityArgument.getPlayers(context, "targets")); 
						}
					)
				)	
			)
		);
	
	return builder;
}

private static int subtractValue(CommandContext<CommandSource> context, int value, Collection<ServerPlayerEntity> targets)
{		
	for (ServerPlayerEntity player : targets)
	{
		IHunterData data = HunterDataCapability.get(player);
		
		data.setJenny(data.getJenny() - value);
		

		PacketHandler.INSTANCE.sendTo(new StatsUpdatePacket(data, false), player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
	}
	
	return 1;
}

private static int setValue(CommandContext<CommandSource> context, int value, Collection<ServerPlayerEntity> targets)
{		
	for (ServerPlayerEntity player : targets)
	{
		IHunterData data = HunterDataCapability.get(player);
		
		data.setJenny(value);
		

		PacketHandler.INSTANCE.sendTo(new StatsUpdatePacket(data, false), player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
	}
	
	return 1;
}

private static int addValue(CommandContext<CommandSource> context, int value, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException 
{
	for (ServerPlayerEntity player : targets)
	{
		IHunterData data = HunterDataCapability.get(player);
		
		data.setJenny(data.getJenny() + value);
		

		PacketHandler.INSTANCE.sendTo(new StatsUpdatePacket(data, false), player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
	}
	
	return 1;
}
}

