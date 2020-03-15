package com.izako.hunterx.registerers;

import com.izako.hunterx.Main;
import com.izako.hunterx.items.entities.YoyoEntity;
import com.izako.hunterx.items.renderers.YoyoRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT ,modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ClientSideRegistry {

	
	public static void RegisterEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(YoyoEntity.class, new IRenderFactory<YoyoEntity>() {
			@Override
			public SpriteRenderer<? super YoyoEntity> createRenderFor(EntityRendererManager manager) {

				return new YoyoRenderer(manager, Minecraft.getInstance().getItemRenderer());
			}
		});
		System.out.println("rendererRegistered");
	}
	
}
