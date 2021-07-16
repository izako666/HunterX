package com.izako.hunterx.data.json;

import java.util.Set;
import java.util.function.UnaryOperator;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.izako.hunterx.Main;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.LootParameter;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class SetInfoFunction extends LootFunction {

	   private static final Logger LOGGER = LogManager.getLogger();
	   private final ITextComponent info;
	   @Nullable
	   private final LootContext.EntityTarget field_215940_d;

	   private SetInfoFunction(ILootCondition[] p_i51218_1_, @Nullable ITextComponent p_i51218_2_, @Nullable LootContext.EntityTarget p_i51218_3_) {
	      super(p_i51218_1_);
	      this.info = p_i51218_2_;
	      this.field_215940_d = p_i51218_3_;
	   }

	   public Set<LootParameter<?>> getRequiredParameters() {
	      return this.field_215940_d != null ? ImmutableSet.of(this.field_215940_d.getParameter()) : ImmutableSet.of();
	   }

	   public static UnaryOperator<ITextComponent> func_215936_a(LootContext p_215936_0_, @Nullable LootContext.EntityTarget p_215936_1_) {
	      if (p_215936_1_ != null) {
	         Entity entity = p_215936_0_.get(p_215936_1_.getParameter());
	         if (entity != null) {
	            CommandSource commandsource = entity.getCommandSource().withPermissionLevel(2);
	            return (p_215937_2_) -> {
	               try {
	                  return TextComponentUtils.updateForEntity(commandsource, p_215937_2_, entity, 0);
	               } catch (CommandSyntaxException commandsyntaxexception) {
	                  LOGGER.warn("Failed to resolve text component", (Throwable)commandsyntaxexception);
	                  return p_215937_2_;
	               }
	            };
	         }
	      }

	      return (p_215938_0_) -> {
	         return p_215938_0_;
	      };
	   }

	   public ItemStack doApply(ItemStack stack, LootContext context) {
	      if (this.info != null) {
	    	  stack.getOrCreateTag().putString("info", this.info.getString());
	      }

	      return stack;
	   }

	   public static class Serializer extends LootFunction.Serializer<SetInfoFunction> {
	      public Serializer() {
	         super(new ResourceLocation(Main.MODID, "set_info"), SetInfoFunction.class);
	      }

	      public void serialize(JsonObject object, SetInfoFunction functionClazz, JsonSerializationContext serializationContext) {
	         super.serialize(object, functionClazz, serializationContext);
	         if (functionClazz.info!= null) {
	            object.add("name", ITextComponent.Serializer.toJsonTree(functionClazz.info));
	         }

	         if (functionClazz.field_215940_d != null) {
	            object.add("entity", serializationContext.serialize(functionClazz.field_215940_d));
	         }

	      }

	      public SetInfoFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
	         ITextComponent itextcomponent = ITextComponent.Serializer.fromJson(object.get("name"));
	         LootContext.EntityTarget lootcontext$entitytarget = JSONUtils.deserializeClass(object, "entity", (LootContext.EntityTarget)null, deserializationContext, LootContext.EntityTarget.class);
	         return new SetInfoFunction(conditionsIn, itextcomponent, lootcontext$entitytarget);
	      }
	   }

}
