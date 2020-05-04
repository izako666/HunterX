package com.izako.hunterx.gui;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.config.GuiUtils;

@OnlyIn(Dist.CLIENT)
public class AbilitySlotsEvent {


	public static final ResourceLocation SLOTS = new ResourceLocation(Main.MODID, "textures/gui/sloticons.png");
	public static void drawIcon(ResourceLocation loc, int x, int y, int u, int v, int zLevel) {
		Minecraft.getInstance().getTextureManager().bindTexture(loc);
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x, y + v, zLevel).tex(0.0, 1.0).endVertex();
		bufferbuilder.pos(x + u, y + v, zLevel).tex(1.0, 1.0).endVertex();
		bufferbuilder.pos(x + u, y, zLevel).tex(1.0, 0.0).endVertex();
		bufferbuilder.pos(x, y, zLevel).tex(0.0, 0.0).endVertex();
		Tessellator.getInstance().draw();
	}

	@SubscribeEvent
	public void renderScreen(RenderGameOverlayEvent.Post e) {

		if(e.getType().equals(ElementType.HOTBAR)) {
		AbilitySlotsEvent.renderSlots();
		}
	}

	public static void renderSlots() {
		int zlevel = 0;
		if(Minecraft.getInstance().currentScreen != null) {
			zlevel = -1;
		} else {
			zlevel = 0;
		}
		double height = Minecraft.getInstance().mainWindow.getScaledHeight();
		double width = Minecraft.getInstance().mainWindow.getScaledWidth();
		int initialHeight;
		if(Minecraft.getInstance().mainWindow.getGuiScaleFactor() == 4) {
		 initialHeight = 0;
		} else if(Minecraft.getInstance().mainWindow.getGuiScaleFactor() == 3) {
			initialHeight = (int) (height /8);
		} else {
			initialHeight = (int) (height /4);
		}
		PlayerEntity p = Minecraft.getInstance().player;
		IAbilityData abldata = AbilityDataCapability.get(p);
		GlStateManager.pushMatrix();
	         GlStateManager.enableBlend();
			
			for (int i = 0; i < 8; i++) {
				Minecraft.getInstance().getTextureManager().bindTexture(SLOTS);
				GuiUtils.drawTexturedModalRect(0, (int) (initialHeight + (32 * i )), 0, 0, 32, 32, zlevel);
			}
			for (int i = 0; i < 8; i++) {
				if (abldata.getAbilityInSlot(i) != null) {
					AbilitySlotsEvent.drawIcon(abldata.getAbilityInSlot(i).getTexture(), 4, (int) (initialHeight + (32 * (i)) + 4), 24,
							24, zlevel);
					Minecraft.getInstance().getTextureManager().bindTexture(SLOTS);
					if (abldata.getAbilityInSlot(i).isCharging()) {
						GuiUtils.drawTexturedModalRect(0, (int) (initialHeight + (32 * (i))), 0, 192, 32, 32, zlevel);
						int oldValue = abldata.getAbilityInSlot(i).getChargingTimer();
						int oldMax = abldata.getAbilityInSlot(i).getMaxCharging();
						GuiUtils.drawTexturedModalRect(0, (int) (initialHeight + (32 * (i))), 0, 224, 4 + (int) (((oldValue * 100) / oldMax) * 0.24), 32, zlevel);
					}
					if (abldata.getAbilityInSlot(i).isInPassive()) {
						GuiUtils.drawTexturedModalRect(0, (int) (initialHeight + (32 * (i))), 0, 32, 32, 32, zlevel);
					}
					if (abldata.getAbilityInSlot(i).getCooldown() > 0) {
						int oldValue = abldata.getAbilityInSlot(i).getCooldown();
						int oldMax = abldata.getAbilityInSlot(i).getMaxCooldown();
						GuiUtils.drawTexturedModalRect(0, (int) (initialHeight + (32 * (i))), 0, AbilitySlotsEvent.getCooldownTexture((int) (((oldValue * 100) / oldMax))), 32, 32, zlevel);

					}
				}
			}
	         GlStateManager.disableBlend();
	         GlStateManager.popMatrix();

	}
	private static int getCooldownTexture(int i) {
		// TODO Auto-generated method stub

		if(i > 75) {
			return 64;
		} else if(i > 50) {
			return 96;
		} else if(i > 25) {
			return 128;
		} else if(i > 0) {
			return 160;
		}
		return 0;
	}

	public static void renderSlotsWithoutEffects() {
		double height = Minecraft.getInstance().mainWindow.getScaledHeight();
		double width = Minecraft.getInstance().mainWindow.getScaledWidth();
		int initialHeight;
		if(Minecraft.getInstance().mainWindow.getGuiScaleFactor() == 4) {
		 initialHeight = 0;
		} else if(Minecraft.getInstance().mainWindow.getGuiScaleFactor() == 3) {
			initialHeight = (int) (height /8);
		} else {
			initialHeight = (int) (height /4);
		}
		PlayerEntity p = Minecraft.getInstance().player;
		IAbilityData abldata = AbilityDataCapability.get(p);
			GlStateManager.pushMatrix();
			Minecraft.getInstance().getTextureManager().bindTexture(SLOTS);
			GlStateManager.enableBlend();
			
			for (int i = 0; i < 8; i++) {
				GuiUtils.drawTexturedModalRect(0, (int) (initialHeight + (32 * i )), 0, 0, 32, 32, 0);
			}
			for (int i = 0; i < 8; i++) {
				if (abldata.getAbilityInSlot(i) != null) {
					AbilitySlotsEvent.drawIcon(abldata.getAbilityInSlot(i).getTexture(), 4, (int) (initialHeight + (32 * (i)) + 4), 24,
							24, 0);
					Minecraft.getInstance().getTextureManager().bindTexture(SLOTS);
				}
			}
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		
	}

}
