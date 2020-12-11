package com.izako.hunterx.commands;

import java.util.Collection;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.quest.Quest;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

public class QuestCommand {


	static ArgumentBuilder<CommandSource, ?> register() {

		
		return Commands.literal("quest")
				.requires(s -> s.hasPermissionLevel(3))
				.then(Commands.literal("give").then(Commands.argument("targets", EntityArgument.players()).then(Commands.argument("quest", new QuestArgument()).executes(context -> addQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayers(context, "targets"))))))
				.then(Commands.literal("remove").then(Commands.argument("targets", EntityArgument.players()).then(Commands.argument("quest", new QuestArgument()).executes(context -> removeQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayers(context, "targets"))))))
				.then(Commands.literal("finish").then(Commands.argument("targets", EntityArgument.players()).then(Commands.argument("quest", new QuestArgument()).executes(context -> finishQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayers(context, "targets"))))));
	}

	private static int addQuest(CommandContext<CommandSource> context, Quest q,
			Collection<ServerPlayerEntity> targets) {
		for (ServerPlayerEntity player : targets) {
			Quest quest = ModQuests.newInstance(q.getId());
			
			quest.giveQuest(player);
		}

		return 1;
	}

	private static int removeQuest(CommandContext<CommandSource> context, Quest q,
			Collection<ServerPlayerEntity> targets) {
		for (ServerPlayerEntity player : targets) {
			IHunterData data = HunterDataCapability.get(player);
			data.removeQuest(q);
			System.out.println("");
		}

		return 1;
	}

	private static int finishQuest(CommandContext<CommandSource> context, Quest q,
			Collection<ServerPlayerEntity> targets) {
		for (ServerPlayerEntity player : targets) {
			
			IHunterData data = HunterDataCapability.get(player);
			data.getQuest(q).finishQuest(player);
		}

		return 1;
	}


}
