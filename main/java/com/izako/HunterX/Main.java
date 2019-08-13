package com.izako.HunterX;



import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.init.ModRecipes;
import com.izako.HunterX.items.entities.EntityCard;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.EntityModifierServerChangeHandler;
import com.izako.HunterX.network.EntityStatsClientSyncHandler;
import com.izako.HunterX.network.EntityStatsServerSyncHandler;
import com.izako.HunterX.network.HanzoArmorBasePacketHandler;
import com.izako.HunterX.network.packets.EntityModifierServerChange;
import com.izako.HunterX.network.packets.EntityStatsClientSync;
import com.izako.HunterX.network.packets.EntityStatsServerSync;
import com.izako.HunterX.network.packets.HanzoArmorBasePacket;
import com.izako.HunterX.proxy.CommonProxy;
import com.izako.HunterX.util.Reference;
import com.izako.HunterX.util.handlers.RegistryHandler;
import com.izako.HunterX.world.ModWorldGen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit (FMLPreInitializationEvent event){
		Main.proxy.RenderEntity();
		Main.proxy.preinitRegistries();
		Main.proxy.render();
	GameRegistry.registerWorldGenerator(new ModWorldGen(), 3 );
		ModidPacketHandler.INSTANCE.registerMessage(HanzoArmorBasePacketHandler.class, HanzoArmorBasePacket.class, 0, Side.SERVER);
		ModidPacketHandler.INSTANCE.registerMessage(EntityStatsServerSyncHandler.class, EntityStatsServerSync.class, 1, Side.SERVER);
		ModidPacketHandler.INSTANCE.registerMessage(EntityStatsClientSyncHandler.class, EntityStatsClientSync.class, 2, Side.CLIENT);
		ModidPacketHandler.INSTANCE.registerMessage(EntityModifierServerChangeHandler.class, EntityModifierServerChange.class, 3, Side.SERVER );
	}
	
	@EventHandler
	public static void init (FMLInitializationEvent event)
	{
		proxy.init(event);
	ModRecipes.init();
	
	}
	
	@EventHandler
	public static void Postinit (FMLPostInitializationEvent event)
	{
	
	}
}
