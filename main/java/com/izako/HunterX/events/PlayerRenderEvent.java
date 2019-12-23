package com.izako.HunterX.events;


import org.lwjgl.opengl.GL11;

import com.izako.HunterX.entity.renderer.RenderAuraBlock;
import com.izako.HunterX.init.ListAbilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerRenderEvent {
	private RenderAuraBlock renderAuraBlock = new RenderAuraBlock(new ModelPlayer(1f, false));
	@SubscribeEvent
	public void onRenderEvent(RenderPlayerEvent.Pre e) {
		EntityPlayer p = e.getEntityPlayer();
		
		if(ListAbilities.ABILITYTEN.isPassiveActive(p)) {
		renderAuraBlock.doRender(p, p.posX, p.posY, p.posZ, 0, 0);
		}

	}
}
