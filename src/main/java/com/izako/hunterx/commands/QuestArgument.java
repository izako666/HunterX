package com.izako.hunterx.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.quest.Quest;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;

public class QuestArgument implements ArgumentType<Quest> {

	public static final List<ResourceLocation> QUEST_KEYS = new ArrayList<ResourceLocation>();

	public static void register() {
		ModQuests.QUESTS.forEach((a) -> {
			QUEST_KEYS.add(new ResourceLocation(Main.MODID, a.getId()));
		});

	}

	@Override
	public Quest parse(StringReader reader) throws CommandSyntaxException {
		ResourceLocation loc = ResourceLocation.read(reader);

		Quest q = ModQuests.newInstance(loc.getPath());
		if (q != null) {
			return q;
		}
		return null;
	}

	public static QuestArgument quest() {
		return new QuestArgument();
	}

	public static <S> Quest getQuest(CommandContext<S> context, String name) {
		return context.getArgument(name, Quest.class);
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		StringReader stringreader = new StringReader(builder.getInput());
		stringreader.setCursor(builder.getStart());

		return this.suggestQuest(builder);
	}

	private CompletableFuture<Suggestions> suggestQuest(SuggestionsBuilder builder) {
		return ISuggestionProvider.suggestIterable(QuestArgument.QUEST_KEYS, builder);
	}
}
