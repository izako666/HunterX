package com.izako.HunterX;



import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.init.ModRecipes;
import com.izako.HunterX.items.entities.EntityCard;
import com.izako.HunterX.items.entities.YoyoProjectile;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.CardPacketHandler;
import com.izako.HunterX.network.HanzoArmorBasePacketHandler;
import com.izako.HunterX.network.packets.CardPacket;
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
	GameRegistry.registerWorldGenerator(new ModWorldGen(), 3 );
		ModidPacketHandler.INSTANCE.registerMessage(HanzoArmorBasePacketHandler.class, HanzoArmorBasePacket.class, 0, Side.SERVER);
	}
	
	@EventHandler
	public static void init (FMLInitializationEvent event)
	{
		Main.proxy.render();
	ModRecipes.init();
	
	}
	
	@EventHandler
	public static void Postinit (FMLPostInitializationEvent event)
	{
	
	}
}
