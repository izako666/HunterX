package com.izako.hunterx.gui;

import org.lwjgl.opengl.GL11;

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
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.config.GuiUtils;

@OnlyIn(Dist.CLIENT)
public class AbilitySlotsEvent {

	public static final ResourceLocation BASESLOT = new ResourceLocation(Main.MODID, "textures/gui/baseslot.png");
	public static final ResourceLocation CHARGINGFRAME = new ResourceLocation(Main.MODID,
			"textures/gui/chargingslotframe.png");
	public static final ResourceLocation CHARGINGBAR = new ResourceLocation(Main.MODID,
			"textures/gui/chargingslotbar.png");
	public static final ResourceLocation PASSIVESLOT = new ResourceLocation(Main.MODID, "textures/gui/passiveslot.png");

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
	public void renderScreen(RenderGameOverlayEvent e) {

		AbilitySlotsEvent.renderSlots();
	}

	public static void renderSlots() {
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
			GlStateManager.color4f(1, 1, 1, 1);
			for (int i = 0; i < 8; i++) {
				AbilitySlotsEvent.drawIcon(BASESLOT, 0, (int) (initialHeight + (32 * i )), 32, 32, 0);
			}
			for (int i = 0; i < 8; i++) {
				if (abldata.getAbilityInSlot(i) != null) {
					AbilitySlotsEvent.drawIcon(abldata.getAbilityInSlot(i).getTexture(), 4, (int) (initialHeight + (32 * (i)) + 4), 24,
							24, 0);
					if (abldata.getAbilityInSlot(i).isCharging()) {
						AbilitySlotsEvent.drawIcon(CHARGINGFRAME, 0, (int) (initialHeight + (32 * (i))), 32, 32, 0);
						int oldValue = abldata.getAbilityInSlot(i).getChargingTimer();
						int oldMax = abldata.getAbilityInSlot(i).getMaxCharging();
						Minecraft.getInstance().getTextureManager().bindTexture(CHARGINGBAR);
						GuiUtils.drawTexturedModalRect(0, (int) (initialHeight + (32 * (i))), 0, 0,
								(int) (((oldValue * 100) / oldMax) * 0.24), 32, 0);
					}
					if (abldata.getAbilityInSlot(i).isInPassive()) {
						AbilitySlotsEvent.drawIcon(PASSIVESLOT, 0, (int) (initialHeight + (32 * (i))), 32, 32, 0);
					}
					if (abldata.getAbilityInSlot(i).getCooldown() > 0) {
						int oldValue = abldata.getAbilityInSlot(i).getCooldown();
						int oldMax = abldata.getAbilityInSlot(i).getMaxCooldown();
						AbilitySlotsEvent.drawIcon(AbilitySlotsEvent.getCooldownTexture((int) (((oldValue * 100) / oldMax) * 0.04)), 0,
								(int) (initialHeight + (32 * (i))), 32, 32, 0);

					}
				}
			}
			GlStateManager.color4f(1, 1, 1, 1);
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		
	}
	private static ResourceLocation getCooldownTexture(int i) {
		// TODO Auto-generated method stub
		ResourceLocation cooldown1 = new ResourceLocation(Main.MODID, "textures/gui/cooldownslot1.png");
		ResourceLocation cooldown2 = new ResourceLocation(Main.MODID, "textures/gui/cooldownslot2.png");
		ResourceLocation cooldown3 = new ResourceLocation(Main.MODID, "textures/gui/cooldownslot3.png");
		ResourceLocation cooldown4 = new ResourceLocation(Main.MODID, "textures/gui/cooldownslot4.png");

		switch (i) {
		case (1):
			return cooldown1;
		case (2):
			return cooldown2;
		case (3):
			return cooldown3;
		case (4):
			return cooldown4;
		}
		return cooldown1;
	}

}
