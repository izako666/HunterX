package com.izako.hunterx.gui;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class AbilitySlotsEvent {


	public static final ResourceLocation NENBAR = new ResourceLocation(Main.MODID, "textures/gui/nenbar.png");
	public static final ResourceLocation SLOTS = new ResourceLocation(Main.MODID, "textures/gui/sloticons.png");
	@OnlyIn(Dist.CLIENT)
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
	public static void renderScreen(RenderGameOverlayEvent.Post e) {

		if(e.getType().equals(ElementType.HOTBAR)) {
		IAbilityData data = AbilityDataCapability.get(Minecraft.getInstance().player);
		if(data.isNenUser()) {
		AbilitySlotsEvent.renderSlots();
		}
		}
	}

	@OnlyIn(Dist.CLIENT)
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
			
	         AbilitySlotsEvent.renderNenBar(width, height, Minecraft.getInstance(), zlevel, abldata);

	         for (int i = 0; i < 8; i++) {
				Minecraft.getInstance().getTextureManager().bindTexture(SLOTS);
				GuiUtils.drawTexturedModalRect(0, (int) (initialHeight + (32 * i )), 0, 0, 32, 32, zlevel);
			}
			for (int i = 0; i < 8; i++) {
				if (abldata.getAbilityInSlot(i) != null) {
					AbilitySlotsEvent.drawIcon(abldata.getAbilityInSlot(i).props.tex, 4, (int) (initialHeight + (32 * (i)) + 4), 24,
							24, zlevel);
					Minecraft.getInstance().getTextureManager().bindTexture(SLOTS);
					if (abldata.getAbilityInSlot(i).isCharging()) {
						GuiUtils.drawTexturedModalRect(0, (int) (initialHeight + (32 * (i))), 0, 192, 32, 32, zlevel);
						int oldValue = abldata.getAbilityInSlot(i).getChargingTimer();
						int oldMax = abldata.getAbilityInSlot(i).props.maxCharging;
						GuiUtils.drawTexturedModalRect(0, (int) (initialHeight + (32 * (i))), 0, 224, 4 + (int) (((oldValue * 100) / oldMax) * 0.24), 32, zlevel);
					}
					if (abldata.getAbilityInSlot(i).isInPassive()) {
						GuiUtils.drawTexturedModalRect(0, (int) (initialHeight + (32 * (i))), 0, 32, 32, 32, zlevel);
					}
					if (abldata.getAbilityInSlot(i).getCooldown() > 0) {
						int oldValue = abldata.getAbilityInSlot(i).getCooldown();
						int oldMax = abldata.getAbilityInSlot(i).props.maxCooldown;
						GuiUtils.drawTexturedModalRect(0, (int) (initialHeight + (32 * (i))), 0, AbilitySlotsEvent.getCooldownTexture((int) (((oldValue * 100) / oldMax))), 32, 32, zlevel);

					}
				}
			}
			GlStateManager.scaled(1d, 1d, 1d);
	         GlStateManager.disableBlend();
	         GlStateManager.popMatrix();

	}
	@OnlyIn(Dist.CLIENT)
	private static int getCooldownTexture(int i) {

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
	@OnlyIn(Dist.CLIENT)
	public static void renderNenBar(double width, double height, Minecraft mc, int zlevel, IAbilityData data) {
		
		mc.getTextureManager().bindTexture(NENBAR);
		GlStateManager.pushMatrix();
		GlStateManager.translatef(32, (float) (height - 42), 0);
		GlStateManager.scalef(0.9f, 0.9f, 1);
		GuiUtils.drawTexturedModalRect(0, 0, 10, 44, 115, 46, 0);
		GlStateManager.scaled(1d, 1d, 1d);
		if(data.getAuraColor() != null) {
		GlStateManager.color3f(data.getAuraColor().getRed() / 255.0f, data.getAuraColor().getGreen() / 255.0f, data.getAuraColor().getBlue() / 255.0f);
		}
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.translatef(51.8f, (float) (height + 5 - 38.9f), 0);
		GlStateManager.scalef(0.9f, 0.9f, 1); 
		GuiUtils.drawTexturedModalRect(0, 0, 32, 6, (data.getCurrentNen() * 92) / data.getNenCapacity(), 38, 0);
	    GlStateManager.color3f(1f, 1f, 1f);
	    GlStateManager.scaled(1d, 1d, 1d);
	    GlStateManager.popMatrix();
	    
	}

	@OnlyIn(Dist.CLIENT)
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
					AbilitySlotsEvent.drawIcon(abldata.getAbilityInSlot(i).props.tex, 4, (int) (initialHeight + (32 * (i)) + 4), 24,
							24, 0);
					Minecraft.getInstance().getTextureManager().bindTexture(SLOTS);
				}
			}
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		
	}

}
