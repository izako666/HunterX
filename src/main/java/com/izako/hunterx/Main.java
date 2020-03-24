package com.izako.hunterx;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.izako.hunterx.events.EventsHandler;
import com.izako.hunterx.networking.ModidPacketHandler;
import com.izako.hunterx.registerers.ClientSideRegistry;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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

	}

}
