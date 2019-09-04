package com.izako.HunterX.proxy;

import com.izako.HunterX.Main;
import com.izako.HunterX.gui.GuiHandler;
import com.izako.HunterX.init.ListKeybinds;
import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.items.entities.EntityBullet;
import com.izako.HunterX.items.entities.EntityCard;
import com.izako.HunterX.items.entities.EntityNeedle;
import com.izako.HunterX.items.entities.YoyoProjectile;
import com.izako.HunterX.stats.capabilities.EntityStatsBase;
import com.izako.HunterX.stats.capabilities.EntityStatsCapability;
import com.izako.HunterX.stats.capabilities.IEntityStats;
import com.izako.HunterX.util.handlers.RegistryHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy{
	

	@Override
	public  void preInit(FMLPreInitializationEvent event)
	{
	    ListKeybinds.register();
	}
	
    @Override
	public  void init (FMLInitializationEvent event)
	{
    	NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
    	super.init(event);
    	
	}
	@Override
	public void RenderEntity() {
		RegistryHandler.preInitRegistries();
	}
	
	@Override
	public void render(){
		
		
		
		
	}
	public static void keyBinds() {
		ListKeybinds.register();
	}
	@Override
	public void spawnCustomParticles(Particle particle) {
		Minecraft.getMinecraft().effectRenderer.addEffect(particle);
		System.out.println("spawn please uwu owo");
	}
	
	

}
