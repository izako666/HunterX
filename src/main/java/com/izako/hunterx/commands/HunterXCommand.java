package com.izako.hunterx.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class HunterXCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
        dispatcher.register(
                LiteralArgumentBuilder.<CommandSource>literal("hntrx")
                .then(AbilityCommand.register())

            );
	}
	
}
