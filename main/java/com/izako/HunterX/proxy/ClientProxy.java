package com.izako.HunterX.proxy;

import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.items.entities.EntityBullet;
import com.izako.HunterX.items.entities.EntityCard;
import com.izako.HunterX.items.entities.EntityNeedle;
import com.izako.HunterX.stats.capabilities.EntityStatsBase;
import com.izako.HunterX.stats.capabilities.EntityStatsCapability;
import com.izako.HunterX.stats.capabilities.IEntityStats;
import com.izako.HunterX.util.handlers.RegistryHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
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
		
<<<<<<< HEAD
		RenderingRegistry.registerEntityRenderingHandler(EntityCard.class, new IRenderFactory() {

			@Override
			public Render<EntityCard> createRenderFor(RenderManager manager) {
				// TODO Auto-generated method stub
				return new RenderSnowball<EntityCard>(manager, ModItems.HISOKAS_CARD, Minecraft.getMinecraft().getRenderItem());
			}
			
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityNeedle.class, new IRenderFactory() {

			@Override
			public Render<EntityNeedle> createRenderFor(RenderManager manager) {
				// TODO Auto-generated method stub
				return new RenderSnowball<EntityNeedle>(manager, ModItems.ILLUMIS_NEEDLE, Minecraft.getMinecraft().getRenderItem());
			}
			
		});
		/*RenderingRegistry.registerEntityRenderingHandler(YoyoProjectile.class, new IRenderFactory() {

			@Override
			public Render<YoyoProjectile> createRenderFor(RenderManager manager) {
				// TODO Auto-generated method stub
				return new RenderSnowball<YoyoProjectile>(manager, ModItems.YOYO, Minecraft.getMinecraft().getRenderItem());
			}
			
		})*/;
=======
		
		
		
>>>>>>> 920ffd5a73cb330de45adf43f12ce21c0fa47722
	}

}
