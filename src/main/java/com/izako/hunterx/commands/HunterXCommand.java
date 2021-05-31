package com.izako.hunterx.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.command.CommandSource;

public class HunterXCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(LiteralArgumentBuilder.<CommandSource>literal("hntrx")
				.then(DebugCommand.register())
				.then(AbilityCommand.register())
				.then(QuestCommand.register())
				.then(LocateBlimpCommand.register())
				.then(JennyCommand.register())

		);
	}

}
