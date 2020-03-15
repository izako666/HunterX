package com.izako.hunterx.registerers;

import com.izako.hunterx.Main;
import com.izako.hunterx.items.entities.YoyoEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.IFactory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
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
		  EntityType<?> type = EntityType.Builder.create(YoyoEntity::new ,EntityClassification.MISC).build("yoyo").setRegistryName(Main.MODID, "yoyo");
		  event.getRegistry().registerAll(type);
      }
}