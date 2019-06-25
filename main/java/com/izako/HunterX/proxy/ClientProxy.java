package com.izako.HunterX.proxy;

import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.items.entities.EntityCard;
import com.izako.HunterX.items.entities.EntityNeedle;
import com.izako.HunterX.items.entities.YoyoProjectile;
import com.izako.HunterX.util.handlers.RegistryHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
	

	@Override
	public  void preInit(FMLPreInitializationEvent event)
	{
	}
	
    @Override
	public  void init (FMLInitializationEvent event)
	{
    	super.init(event);
    	
	}
	@Override
	public void RenderEntity() {
		RegistryHandler.preInitRegistries();
	}
	
	@Override
	public void render(){
		
		RenderingRegistry.registerEntityRenderingHandler(EntityCard.class, new RenderSnowball<EntityCard>(Minecraft.getMinecraft().getRenderManager(),ModItems.HISOKAS_CARD, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(YoyoProjectile.class, new RenderSnowball<YoyoProjectile>(Minecraft.getMinecraft().getRenderManager(),ModItems.YOYO, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityNeedle.class, new RenderSnowball<EntityNeedle>(Minecraft.getMinecraft().getRenderManager(),ModItems.ILLUMIS_NEEDLE, Minecraft.getMinecraft().getRenderItem()));

	}

}
