package com.izako.hunterx.registerers;

import com.izako.hunterx.Main;
import com.izako.hunterx.entities.ThugEntity;
import com.izako.hunterx.items.entities.CardEntity;
import com.izako.hunterx.items.entities.NeedleEntity;
import com.izako.hunterx.items.entities.YoyoEntity;
import com.izako.hunterx.items.renderers.YoyoRenderer;
import com.izako.hunterx.renderers.ThugRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
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
			public YoyoRenderer<YoyoEntity> createRenderFor(EntityRendererManager manager) {

				return new YoyoRenderer<YoyoEntity>(manager);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(CardEntity.class, new IRenderFactory<CardEntity>() {

			@Override
			public SpriteRenderer<CardEntity> createRenderFor(EntityRendererManager manager) {
				// TODO Auto-generated method stub
				return new SpriteRenderer<CardEntity>(manager, Minecraft.getInstance().getItemRenderer());
			}});
		RenderingRegistry.registerEntityRenderingHandler(NeedleEntity.class, new IRenderFactory<NeedleEntity>() {

			@Override
			public SpriteRenderer<NeedleEntity> createRenderFor(EntityRendererManager manager) {
				// TODO Auto-generated method stub
				return new SpriteRenderer<NeedleEntity>(manager, Minecraft.getInstance().getItemRenderer());
			}});
		
		RenderingRegistry.registerEntityRenderingHandler(ThugEntity.class, new IRenderFactory<ThugEntity>() {

			@Override
			public ThugRenderer createRenderFor(EntityRendererManager manager) {
				// TODO Auto-generated method stub
				return new ThugRenderer(manager);
			}
			
		});
	}
	
}
