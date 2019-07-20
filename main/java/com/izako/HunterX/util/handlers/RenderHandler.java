package com.izako.HunterX.util.handlers;

import com.izako.HunterX.entity.BossExam;
import com.izako.HunterX.entity.EntityStats;
import com.izako.HunterX.entity.Examiner;
import com.izako.HunterX.entity.Thug;
import com.izako.HunterX.entity.renderer.RenderBossExam;
import com.izako.HunterX.entity.renderer.RenderEntityStats;
import com.izako.HunterX.entity.renderer.RenderExaminer;
import com.izako.HunterX.entity.renderer.RenderThug;
import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.items.entities.YoyoProjectile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
	
	public static void registerEntityRenders() {
		
		RenderingRegistry.registerEntityRenderingHandler(Thug.class, new IRenderFactory<Thug>() {
			@Override
			public Render<? super Thug> createRenderFor(RenderManager manager) {
				
				return new RenderThug(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(Examiner.class, new IRenderFactory<Examiner>() {
			@Override
			public Render<? super Examiner> createRenderFor(RenderManager manager) {
				
				return new RenderExaminer(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(BossExam.class, new IRenderFactory<BossExam>() {
			@Override
			public Render<? super BossExam> createRenderFor(RenderManager manager) {
				
				return new RenderBossExam(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityStats.class, new IRenderFactory<EntityStats>() {
			@Override
			public Render<? super EntityStats> createRenderFor(RenderManager manager) {
				
				return new RenderEntityStats(manager);
			}
		});
		
		
		
		
	}

}
