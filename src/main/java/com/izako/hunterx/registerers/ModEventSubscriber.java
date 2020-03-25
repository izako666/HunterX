package com.izako.hunterx.registerers;

import com.izako.hunterx.Main;
import com.izako.hunterx.entities.ThugEntity;
import com.izako.hunterx.init.ModStructures;
import com.izako.hunterx.items.entities.CardEntity;
import com.izako.hunterx.items.entities.NeedleEntity;
import com.izako.hunterx.items.entities.YoyoEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ModEventSubscriber {


	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(Main.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
	  @SubscribeEvent
      public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event)
      {
		  event.getRegistry().registerAll(YoyoEntity.type, CardEntity.type, NeedleEntity.type, ThugEntity.type);
		  registerEntityWorldSpawn(ThugEntity.type, Biomes.PLAINS, Biomes.FOREST, Biomes.DESERT, Biomes.BEACH, Biomes.JUNGLE);
      }
	  
	  public static void registerEntityWorldSpawn(EntityType<?> type, Biome... biomes) {
		  for(Biome biome : biomes) {
			  if(biome != null) {
				  biome.getSpawns(type.getClassification()).add(new SpawnListEntry(type, 10, 1, 3));
			  }
		  }
	  }
	   @SubscribeEvent
	    public static void onFeatureRegistry(final RegistryEvent.Register<Feature<?>> event) {
	        IForgeRegistry<Feature<?>> registry = event.getRegistry();

	        ModStructures.registerStructure(registry);
	    }
}
