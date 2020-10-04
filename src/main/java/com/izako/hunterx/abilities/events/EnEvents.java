package com.izako.hunterx.abilities.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.abilities.basics.EnAbility;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.entities.EnEntity;
import com.izako.hunterx.init.ModAbilities;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class EnEvents {

	@SubscribeEvent
	public static void render(RenderGameOverlayEvent evt) {
		if (evt.getType() == ElementType.HOTBAR) {
			PlayerEntity p = Minecraft.getInstance().player;

			IAbilityData data = AbilityDataCapability.get(p);

			if (data.hasActiveAbility(ModAbilities.EN_ABILITY)) {
				EnAbility abl = (EnAbility) data.getActiveAbility(ModAbilities.EN_ABILITY, p);

				if (abl.getChargingTimer() >= abl.props.maxCharging) {
					int centerY = evt.getWindow().getScaledHeight() / 2;
					int centerX = evt.getWindow().getScaledWidth() / 2;
					RenderSystem.pushMatrix();
					RenderSystem.enableBlend();
					Minecraft.getInstance().getTextureManager()
							.bindTexture(new ResourceLocation(Main.MODID, "textures/gui/en_gui.png"));
					int boardX = centerX + 50;
					int boardY = centerY - 40;
					GuiUtils.drawTexturedModalRect(boardX, boardY, 0, 0, 94, 93, 0);

					EnEntity enEntity = abl.entity;
					if (enEntity != null) {

						for (int i = 0; i < abl.clientEntities.size(); i++) {
							LivingEntity entity = abl.clientEntities.get(i);
							int u = 0;
							int v = 104;
							if (entity != null) {
							double yDistance = Math.abs(entity.getPosY() - p.getPosY());
							double yDistanceRange = yDistance / 40.0;
							if (!(entity instanceof PlayerEntity)) {
								v = 111;
								RenderSystem.color4f(1f, 1f, 1f, (float) (1 - yDistanceRange));
							}
							int enOriginX = (int) (enEntity.getPosX() - (enEntity.renderScale / 2));
							int enOriginZ = (int) (enEntity.getPosZ() - (enEntity.renderScale / 2));
								int posX = (int) (boardX + (entity.getPosX() - enOriginX) * 0.8);
								int posZ = (int) (boardY + (entity.getPosZ() - enOriginZ) * 0.8);

								GuiUtils.drawTexturedModalRect(posX, posZ, u, v, 6, 6, 1);
							}
							RenderSystem.color4f(1f, 1f, 1f, 1f);

					}
					int u = 0;
					int v = 118;
					int enOriginX = (int) (enEntity.getPosX() - (enEntity.renderScale / 2));
					int enOriginZ = (int) (enEntity.getPosZ() - (enEntity.renderScale / 2));
					int posX = (int) (boardX + (p.getPosX() - enOriginX) * 0.8);
					int posZ = (int) (boardY + (p.getPosZ() - enOriginZ) * 0.8);
					GuiUtils.drawTexturedModalRect(posX, posZ, u, v, 6, 6, 1);

					}
					RenderSystem.disableBlend();
					RenderSystem.popMatrix();
				}
			}
		}
	}
}
