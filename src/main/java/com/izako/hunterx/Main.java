package com.izako.hunterx;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.events.EventsHandler;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.init.ModStructures;
import com.izako.hunterx.networking.ModidPacketHandler;
import com.izako.hunterx.registerers.ClientSideRegistry;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(Main.MODID)
public final class Main {

	public static final String MODID = "hntrx";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public Main() {
		LOGGER.debug("laaaaasaaaaaggggnaaaa");
		EventsHandler.registerEvents();
		ModidPacketHandler.registerPackets();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
	}
	
	private void clientSetup(FMLClientSetupEvent event) {
		ClientSideRegistry.RegisterEntityRenderers();
	}

	private  void commonSetup(FMLCommonSetupEvent event) {
		EventsHandler.registerEvents();
		HunterDataCapability.register();

		ModQuests.questRegister();
		
        ForgeRegistries.BIOMES.getValues().stream().forEach((biome -> {
            if (biome.getCategory() == Biome.Category.FOREST || biome.getCategory() == Biome.Category.PLAINS) {
                biome.addStructure(ModStructures.BLIMP, IFeatureConfig.NO_FEATURE_CONFIG);
                biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModStructures.BLIMP, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));

            }
        }));

	}


}
