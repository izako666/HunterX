package com.izako.hunterx.commands;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.AbilityUpdatePacket;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkDirection;

public class DebugCommand {
	static ArgumentBuilder<CommandSource, ?> register() {

		return Commands.literal("debug").executes(
						context -> DebugCommand.debug(context));

	}

	private static int debug(CommandContext<CommandSource> context) {
		   ServerPlayerEntity player = null;
		try {
			player = context.getSource().asPlayer();
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
			IAbilityData aData = AbilityDataCapability.get(player);
			IHunterData hData = HunterDataCapability.get(player);
			aData.setIsNenUser(true);
			hData.setIsHunter(true);
			PacketHandler.INSTANCE.sendTo(new AbilityUpdatePacket(aData, false), player.connection.getNetworkManager(),
					NetworkDirection.PLAY_TO_CLIENT);
			PacketHandler.INSTANCE.sendTo(new StatsUpdatePacket(hData, false), player.connection.getNetworkManager(),
					NetworkDirection.PLAY_TO_CLIENT);

		
		return 1;

	}
}
