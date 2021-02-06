package com.izako.hunterx.commands;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;

public class DebugCommand {
	static ArgumentBuilder<CommandSource, ?> register() {

		
		return Commands.literal("debug")
				.requires(s -> s.hasPermissionLevel(2))
				  .then(Commands.argument("target", EntityArgument.player())
				    		.executes(context -> 
				    			 getMaxNen(context, 
				    					EntityArgument.getPlayer(context, "target"))
				    		));
	}

	private static int getMaxNen(CommandContext<CommandSource> context,
			ServerPlayerEntity target) {
		IAbilityData data = AbilityDataCapability.get(target);
		try {
			ServerPlayerEntity commandUser = context.getSource().asPlayer();
			commandUser.sendMessage(new StringTextComponent(target.getName().getString() + "'s nen capacity is " + String.valueOf(data.getNenCapacity())), ChatType.GAME_INFO);
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}

		return 1;
	}
}
