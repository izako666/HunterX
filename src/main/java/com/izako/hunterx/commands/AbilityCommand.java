package com.izako.hunterx.commands;

import java.util.Collection;

import com.izako.hunterx.izapi.ability.Ability;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

public class AbilityCommand {

	static ArgumentBuilder<CommandSource, ?> register() {

		
		return Commands.literal("ability")
				.requires(s -> s.hasPermissionLevel(3))
				.then(Commands.literal("give")
				  .then(Commands.argument("targets", EntityArgument.players())
				    .then(Commands.argument("ability", new AbilityArgument())
				    		.executes(context -> {
				    			return addAbility(context, AbilityArgument.getAbility(context, "ability"), 
				    					EntityArgument.getPlayers(context, "targets"));
				    		}))));
	}

	private static int addAbility(CommandContext<CommandSource> context, Ability ability,
			Collection<ServerPlayerEntity> targets) {
		for (ServerPlayerEntity player : targets) {
			ability.give(player);
		}

		return 1;
	}
}
