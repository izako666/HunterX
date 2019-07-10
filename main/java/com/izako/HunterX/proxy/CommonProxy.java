package com.izako.HunterX.proxy;

import com.izako.HunterX.init.EntityInit;
import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.items.entities.EntityCard;
import com.izako.HunterX.items.entities.YoyoProjectile;
import com.izako.HunterX.util.handlers.EventsHandler;
import com.izako.HunterX.util.handlers.RegistryHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	@EventHandler
	public void preInit (FMLPreInitializationEvent event) {
		
	}
	@EventHandler
	public  void init (FMLInitializationEvent event)
	{}
	public void RenderEntity(){
		EntityInit.registerEntities();
	}
	
	public void render(){
		
	}
 
	public static void preinitRegistries() {
		EventsHandler.registerEvents();
	}
	

}
