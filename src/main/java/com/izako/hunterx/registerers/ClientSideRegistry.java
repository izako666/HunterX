package com.izako.hunterx.registerers;

import java.lang.reflect.InvocationTargetException;

import com.izako.hunterx.Main;
import com.izako.hunterx.entities.EnEntity;
import com.izako.hunterx.entities.ExaminerEntity;
import com.izako.hunterx.entities.HanzoEntity;
import com.izako.hunterx.entities.KirikoEntity;
import com.izako.hunterx.entities.ThugEntity;
import com.izako.hunterx.entities.WingEntity;
import com.izako.hunterx.entities.models.KirikoModel;
import com.izako.hunterx.items.entities.BulletEntity;
import com.izako.hunterx.items.entities.CardEntity;
import com.izako.hunterx.items.entities.NeedleEntity;
import com.izako.hunterx.items.entities.YoyoEntity;
import com.izako.hunterx.items.entities.models.BulletModel;
import com.izako.hunterx.items.renderers.BulletRenderer;
import com.izako.hunterx.items.renderers.YoyoRenderer;
import com.izako.hunterx.renderers.EnRenderer;
import com.izako.hunterx.renderers.ExaminerRenderer;
import com.izako.hunterx.renderers.HanzoRenderer;
import com.izako.hunterx.renderers.KirikoRenderer;
import com.izako.hunterx.renderers.ThugRenderer;
import com.izako.hunterx.renderers.WingRenderer;
import com.izako.hunterx.renderers.layers.GenericOverlayLayer;
import com.izako.hunterx.renderers.layers.GyoEyesLayer;
import com.izako.hunterx.renderers.layers.HandOverlayLayer;
import com.izako.hunterx.renderers.layers.RyuOverlayLayer;
import com.izako.hunterx.renderers.layers.ShuOverlayLayer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT ,modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ClientSideRegistry {

	
	public static void RegisterEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(YoyoEntity.type, new IRenderFactory<YoyoEntity>() {
			@Override
			public YoyoRenderer<YoyoEntity> createRenderFor(EntityRendererManager manager) {

				return new YoyoRenderer<YoyoEntity>(manager);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(CardEntity.type, new IRenderFactory<CardEntity>() {

			@Override
			public SpriteRenderer<CardEntity> createRenderFor(EntityRendererManager manager) {
				return new SpriteRenderer<CardEntity>(manager, Minecraft.getInstance().getItemRenderer());
			}});
		RenderingRegistry.registerEntityRenderingHandler(NeedleEntity.type, new IRenderFactory<NeedleEntity>() {

			@Override
			public SpriteRenderer<NeedleEntity> createRenderFor(EntityRendererManager manager) {
				return new SpriteRenderer<NeedleEntity>(manager, Minecraft.getInstance().getItemRenderer());
			}});
		
		RenderingRegistry.registerEntityRenderingHandler(ThugEntity.type, new IRenderFactory<ThugEntity>() {

			@Override
			public ThugRenderer createRenderFor(EntityRendererManager manager) {
				return new ThugRenderer(manager);
			}
			
		});
		RenderingRegistry.registerEntityRenderingHandler(ExaminerEntity.type, new IRenderFactory<ExaminerEntity>() {

			@Override
			public ExaminerRenderer createRenderFor(EntityRendererManager manager) {
				return new ExaminerRenderer(manager);
			}
			
		});

		RenderingRegistry.registerEntityRenderingHandler(KirikoEntity.type, new IRenderFactory<KirikoEntity>() {

			@Override
			public KirikoRenderer createRenderFor(EntityRendererManager manager) {
				return new KirikoRenderer(manager, new KirikoModel<KirikoEntity>());
			}
			
		});

		RenderingRegistry.registerEntityRenderingHandler(BulletEntity.type, new IRenderFactory<BulletEntity>() {

			@Override
			public BulletRenderer<BulletEntity> createRenderFor(EntityRendererManager manager) {
				return new BulletRenderer<BulletEntity>(manager, new BulletModel());
			}});
			
		RenderingRegistry.registerEntityRenderingHandler(WingEntity.type, new IRenderFactory<WingEntity>() {

			@Override
			public WingRenderer createRenderFor(EntityRendererManager manager) {
				return new WingRenderer(manager);
			}
			
		});
		RenderingRegistry.registerEntityRenderingHandler(HanzoEntity.type, new IRenderFactory<HanzoEntity>() {

			@Override
			public HanzoRenderer createRenderFor(EntityRendererManager manager) {
				return new HanzoRenderer(manager);
			}
			
		});

		RenderingRegistry.registerEntityRenderingHandler(EnEntity.TYPE, ClientSideRegistry.factory(EnRenderer.class));
		for(PlayerRenderer render : Minecraft.getInstance().getRenderManager().getSkinMap().values()) {
			render.addLayer(new GenericOverlayLayer(render));
			render.addLayer(new HandOverlayLayer(render));
			render.addLayer(new ShuOverlayLayer(render));
			render.addLayer(new GyoEyesLayer(render));
			render.addLayer(new RyuOverlayLayer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>>(render));
		}
		for(EntityRenderer render : Minecraft.getInstance().getRenderManager().renderers.values()) {
			if(render instanceof LivingRenderer) {
				((LivingRenderer) render).addLayer(new GenericOverlayLayer((IEntityRenderer) render));
				if(((LivingRenderer) render).getEntityModel() instanceof BipedModel) {
				((LivingRenderer) render).addLayer(new RyuOverlayLayer((IEntityRenderer<LivingEntity, BipedModel<LivingEntity>>) render));
				}

			}
		}

	}
	
	
	public static  <T extends Entity, R extends EntityRenderer<T>> IRenderFactory<T> factory(Class<R> render) {
		return new IRenderFactory<T>() {

			@Override
			public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
				try {
					return render.getConstructor(manager.getClass()).newInstance(manager);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
				return null;
			}};
	}
}
