package com.izako.hunterx.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.ability.Ability;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;

public class AbilityArgument implements ArgumentType<Ability>{


	public static final List<ResourceLocation> ABILITY_KEYS = new ArrayList<ResourceLocation>();
	public static void register(){
		ModAbilities.ABILITY_INSTANCES.forEach((a) -> {
			ABILITY_KEYS.add(new ResourceLocation(Main.MODID, a.getId()));
		});
		

	}
	@Override
	public Ability parse(StringReader reader) throws CommandSyntaxException
	{
		ResourceLocation loc = ResourceLocation.read(reader);
		
		Ability abl = ModAbilities.getNewInstanceFromId(loc.getPath());
		if(abl != null) {
			return abl;
		}
		return null;
	}

	public static <S> Ability getAbility(CommandContext<S> context, String name)
	{
		return context.getArgument(name, Ability.class);
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder)
	{
		StringReader stringreader = new StringReader(builder.getInput());
		stringreader.setCursor(builder.getStart());

		return this.suggestAbility(builder);
	}


	private CompletableFuture<Suggestions> suggestAbility(SuggestionsBuilder builder)
	{
		return ISuggestionProvider.suggestIterable(AbilityArgument.ABILITY_KEYS, builder);
	}
}
