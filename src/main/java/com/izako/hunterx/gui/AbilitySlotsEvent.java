package com.izako.hunterx.gui;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.mojang.blaze3d.systems.RenderSystem;

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
import net.minecraftforge.fml.client.gui.GuiUtils;
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
		bufferbuilder.pos(x, y + v, zLevel).tex(0.0f, 1.0f).endVertex();
		bufferbuilder.pos(x + u, y + v, zLevel).tex(1.0f, 1.0f).endVertex();
		bufferbuilder.pos(x + u, y, zLevel).tex(1.0f, 0.0f).endVertex();
		bufferbuilder.pos(x, y, zLevel).tex(0.0f, 0.0f).endVertex();
		Tessellator.getInstance().draw();
	}

	@SubscribeEvent
	public static void renderScreen(RenderGameOverlayEvent.Post e) {

		if (e.getType().equals(ElementType.HOTBAR)) {
			IAbilityData data = AbilityDataCapability.get(Minecraft.getInstance().player);
			if (data.isNenUser()) {
				AbilitySlotsEvent.renderSlots();
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static void renderSlots() {
		int zlevel = 0;
		if (Minecraft.getInstance().currentScreen != null) {
			zlevel = -1;
		} else {
			zlevel = 0;
		}
		double height = Minecraft.getInstance().getMainWindow().getScaledHeight();
		double width = Minecraft.getInstance().getMainWindow().getScaledWidth();
		int initialHeight;
		if (Minecraft.getInstance().getMainWindow().getGuiScaleFactor() == 4) {
			initialHeight = 0;
		} else if (Minecraft.getInstance().getMainWindow().getGuiScaleFactor() == 3) {
			initialHeight = (int) (height / 8);
		} else {
			initialHeight = (int) (height / 4);
		}
		PlayerEntity p = Minecraft.getInstance().player;
		IAbilityData abldata = AbilityDataCapability.get(p);
		IHunterData data = HunterDataCapability.get(p);
		RenderSystem.pushMatrix();
		RenderSystem.enableBlend();

		AbilitySlotsEvent.renderNenBar(width, height, Minecraft.getInstance(), zlevel, abldata);

		if (!data.isSelectingAbility()) {
			Minecraft.getInstance().getTextureManager().bindTexture(SLOTS);
			int slotsPosX = 52;
			int slotsPosY = (int) (height - 82);
			GuiUtils.drawTexturedModalRect(slotsPosX, slotsPosY, 0, 0, 32, 32, zlevel);

			if (abldata.getSlotAbilities()[abldata.getActiveAbility()] != null) {
				int i = abldata.getActiveAbility();
				AbilitySlotsEvent.drawIcon(abldata.getAbilityInSlot(i).props.tex, slotsPosX + 4, slotsPosY + 4, 24, 24,
						zlevel);
				Minecraft.getInstance().getTextureManager().bindTexture(SLOTS);
				if (abldata.getAbilityInSlot(i).isCharging()) {
					GuiUtils.drawTexturedModalRect(slotsPosX, slotsPosY, 0, 192, 32, 32, zlevel);
					int oldValue = abldata.getAbilityInSlot(i).getChargingTimer();
					int oldMax = abldata.getAbilityInSlot(i).props.maxCharging;
					GuiUtils.drawTexturedModalRect(slotsPosX, slotsPosY, 0, 224,
							4 + (int) (((oldValue * 100) / oldMax) * 0.24), 32, zlevel);
				} else if (abldata.getAbilityInSlot(i).isInPassive()) {
					GuiUtils.drawTexturedModalRect(slotsPosX, slotsPosY, 0, 32, 32, 32, zlevel);
				} else if (abldata.getAbilityInSlot(i).getCooldown() > 0) {
					int oldValue = abldata.getAbilityInSlot(i).getCooldown();
					if(oldValue < 1)
						oldValue = 1;
					int oldMax = abldata.getAbilityInSlot(i).props.maxCooldown;
					GuiUtils.drawTexturedModalRect(slotsPosX, slotsPosY, 0,
							AbilitySlotsEvent.getCooldownTexture((int) (((oldValue * 100) / oldMax))), 32, 32, zlevel);

				}

			}

		} else {
			for(int i = 0; i < abldata.getSlotAbilities().length; i++) {
				Minecraft.getInstance().getTextureManager().bindTexture(SLOTS);
				int slotsPosX = 52;
				int slotsPosY = (int) (height - 82);
				GuiUtils.drawTexturedModalRect(slotsPosX + (32 * i), slotsPosY, 0, 0, 32, 32, zlevel);

				if (abldata.getSlotAbilities()[i] != null) {
					AbilitySlotsEvent.drawIcon(abldata.getAbilityInSlot(i).props.tex, slotsPosX + 4 + (32 * i), slotsPosY + 4, 24, 24,
							zlevel);
					Minecraft.getInstance().getTextureManager().bindTexture(SLOTS);
					if (abldata.getAbilityInSlot(i).isCharging()) {
						GuiUtils.drawTexturedModalRect(slotsPosX+ (32 * i), slotsPosY, 0, 192, 32, 32, zlevel);
						int oldValue = abldata.getAbilityInSlot(i).getChargingTimer();
						int oldMax = abldata.getAbilityInSlot(i).props.maxCharging;
						GuiUtils.drawTexturedModalRect(slotsPosX+ (32 * i), slotsPosY, 0, 224,
								4 + (int) (((oldValue * 100) / oldMax) * 0.24), 32, zlevel);
					} else if (abldata.getAbilityInSlot(i).isInPassive()) {
						GuiUtils.drawTexturedModalRect(slotsPosX+ (32 * i), slotsPosY, 0, 32, 32, 32, zlevel);
					} else if (abldata.getAbilityInSlot(i).getCooldown() > 0) {
						int oldValue = abldata.getAbilityInSlot(i).getCooldown();
						int oldMax = abldata.getAbilityInSlot(i).props.maxCooldown;
						GuiUtils.drawTexturedModalRect(slotsPosX+ (32 * i), slotsPosY, 0,
								AbilitySlotsEvent.getCooldownTexture((int) (((oldValue * 100) / oldMax))), 32, 32, zlevel);

					}

				}
				if(abldata.getActiveAbility() == i) {
					GuiUtils.drawTexturedModalRect(slotsPosX+ (32 * i), slotsPosY, 39, 0,
							32, 32, zlevel);
				}

			}
		}

		RenderSystem.scaled(1d, 1d, 1d);
		RenderSystem.disableBlend();
		RenderSystem.popMatrix();

	}

	@OnlyIn(Dist.CLIENT)
	private static int getCooldownTexture(int i) {

		if (i > 75) {
			return 64;
		} else if (i > 50) {
			return 96;
		} else if (i > 25) {
			return 128;
		} else if (i > 0) {
			return 160;
		}
		return 0;
	}

	@OnlyIn(Dist.CLIENT)
	public static void renderNenBar(double width, double height, Minecraft mc, int zlevel, IAbilityData data) {

		mc.getTextureManager().bindTexture(NENBAR);
		RenderSystem.pushMatrix();
		RenderSystem.translatef(32, (float) (height - 42), 0);
		RenderSystem.scalef(0.9f, 0.9f, 1);
		GuiUtils.drawTexturedModalRect(0, 0, 10, 44, 115, 46, 0);
		RenderSystem.scaled(1d, 1d, 1d);
		if (data.getAuraColor() != null) {
			RenderSystem.color3f(data.getAuraColor().getRed() / 255.0f, data.getAuraColor().getGreen() / 255.0f,
					data.getAuraColor().getBlue() / 255.0f);
		}
		RenderSystem.popMatrix();
		RenderSystem.pushMatrix();
		RenderSystem.translatef(51.8f, (float) (height + 5 - 38.9f), 0);
		RenderSystem.scalef(0.9f, 0.9f, 1);
		GuiUtils.drawTexturedModalRect(0, 0, 32, 6, (data.getCurrentNen() * 92) / data.getNenCapacity(), 38, 0);
		RenderSystem.color3f(1f, 1f, 1f);
		RenderSystem.scaled(1d, 1d, 1d);
		RenderSystem.popMatrix();

	}

	@OnlyIn(Dist.CLIENT)
	public static void renderSlotsWithoutEffects() {
		int zlevel = 0;
		if (Minecraft.getInstance().currentScreen != null) {
			zlevel = -1;
		} else {
			zlevel = 0;
		}
		double height = Minecraft.getInstance().getMainWindow().getScaledHeight();
		double width = Minecraft.getInstance().getMainWindow().getScaledWidth();
		int initialHeight;
		if (Minecraft.getInstance().getMainWindow().getGuiScaleFactor() == 4) {
			initialHeight = 0;
		} else if (Minecraft.getInstance().getMainWindow().getGuiScaleFactor() == 3) {
			initialHeight = (int) (height / 8);
		} else {
			initialHeight = (int) (height / 4);
		}
		PlayerEntity p = Minecraft.getInstance().player;
		IAbilityData abldata = AbilityDataCapability.get(p);
		IHunterData data = HunterDataCapability.get(p);
		RenderSystem.pushMatrix();
		RenderSystem.enableBlend();

		AbilitySlotsEvent.renderNenBar(width, height, Minecraft.getInstance(), zlevel, abldata);

		if (!data.isSelectingAbility()) {
			Minecraft.getInstance().getTextureManager().bindTexture(SLOTS);
			int slotsPosX = 52;
			int slotsPosY = (int) (height - 82);
			GuiUtils.drawTexturedModalRect(slotsPosX, slotsPosY, 0, 0, 32, 32, zlevel);

			if (abldata.getSlotAbilities()[abldata.getActiveAbility()] != null) {
				int i = abldata.getActiveAbility();
				AbilitySlotsEvent.drawIcon(abldata.getAbilityInSlot(i).props.tex, slotsPosX + 4, slotsPosY + 4, 24, 24,
						zlevel);
				Minecraft.getInstance().getTextureManager().bindTexture(SLOTS);

			}

		}

		RenderSystem.scaled(1d, 1d, 1d);
		RenderSystem.disableBlend();
		RenderSystem.popMatrix();
	}

}
