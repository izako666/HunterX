package com.izako.hunterx.registerers;

import com.izako.hunterx.Main;
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
import com.izako.hunterx.renderers.ExaminerRenderer;
import com.izako.hunterx.renderers.HanzoRenderer;
import com.izako.hunterx.renderers.KirikoRenderer;
import com.izako.hunterx.renderers.ThugRenderer;
import com.izako.hunterx.renderers.WingRenderer;

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
		RenderingRegistry.registerEntityRenderingHandler(ExaminerEntity.class, new IRenderFactory<ExaminerEntity>() {

			@Override
			public ExaminerRenderer createRenderFor(EntityRendererManager manager) {
				// TODO Auto-generated method stub
				return new ExaminerRenderer(manager);
			}
			
		});

		RenderingRegistry.registerEntityRenderingHandler(KirikoEntity.class, new IRenderFactory<KirikoEntity>() {

			@Override
			public KirikoRenderer<KirikoEntity, KirikoModel<KirikoEntity>> createRenderFor(EntityRendererManager manager) {
				// TODO Auto-generated method stub
				return new KirikoRenderer<KirikoEntity, KirikoModel<KirikoEntity>>(manager, new KirikoModel<KirikoEntity>());
			}
			
		});

		RenderingRegistry.registerEntityRenderingHandler(BulletEntity.class, new IRenderFactory<BulletEntity>() {

			@Override
			public BulletRenderer<BulletEntity> createRenderFor(EntityRendererManager manager) {
				// TODO Auto-generated method stub
				return new BulletRenderer<BulletEntity>(manager, new BulletModel());
			}});
			
		RenderingRegistry.registerEntityRenderingHandler(WingEntity.class, new IRenderFactory<WingEntity>() {

			@Override
			public WingRenderer createRenderFor(EntityRendererManager manager) {
				// TODO Auto-generated method stub
				return new WingRenderer(manager);
			}
			
		});
		RenderingRegistry.registerEntityRenderingHandler(HanzoEntity.class, new IRenderFactory<HanzoEntity>() {

			@Override
			public HanzoRenderer createRenderFor(EntityRendererManager manager) {
				// TODO Auto-generated method stub
				return new HanzoRenderer(manager);
			}
			
		});


	}
	
}
