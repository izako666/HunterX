package com.izako.hunterx.commands;

import com.izako.hunterx.data.worlddata.ModWorldData;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class LocateBlimpCommand {
	static ArgumentBuilder<CommandSource, ?> register() {

		return Commands.literal("locate_blimp").executes(
						context -> LocateBlimpCommand.debug(context));

	}

	private static int debug(CommandContext<CommandSource> context) {
		   ServerPlayerEntity player = null;
		try {
			player = context.getSource().asPlayer();
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}

		ModWorldData data = ModWorldData.get(player.getServerWorld());
		
		if(data.getBlimpPos() == null) {
			player.sendMessage(new StringTextComponent("Blimp not found yet, keep searching."));
		} else {
			player.sendMessage(new StringTextComponent("Blimp is located at" + data.getBlimpPos().toString()));
		}
		return 1;

	}
}


