package com.izako.hunterx;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.izako.hunterx.registerers.ClientSideRegistry;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MODID)
public final class Main {

	public static final String MODID = "hntrx";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public Main() {
		LOGGER.debug("laaaaasaaaaaggggnaaaa");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
	}
	
	private void clientRegistries(FMLClientSetupEvent event) {
		ClientSideRegistry.RegisterEntityRenderers();
	}

}
