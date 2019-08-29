package com.izako.HunterX.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.izako.HunterX.init.ListKeybinds;
import com.izako.HunterX.izapi.RenderHelper;
import com.izako.HunterX.izapi.abilities.Ability;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.AbilityPacketSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;
import com.izako.HunterX.util.Reference;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.boss.dragon.phase.PhaseChargingPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GeneralGui extends GuiScreen {

	EntityPlayer p;
	IEntityStats stats;
	public boolean canDrawString = false;
	public Ability currentAbility = null;

	public GeneralGui(EntityPlayer player) {
		this.p = player;
		stats = p.getCapability(EntityStatsProvider.ENTITY_STATS, null);
	}

	public void drawScreen(int x, int y, float f) {
		drawDefaultBackground();
		ScaledResolution sr = new ScaledResolution(mc);

		int posX = sr.getScaledWidth();
		int posY = sr.getScaledHeight();
		drawTexturedModalRect((posX - 250) / 2, (posY - 230) / 2, 0, 0, 256, 256);
		drawTexturedModalRect((posX - 250) / 2, posY - 60, 0, 0, 256, 256);

		for (int i = 0; i < 9; i++) {
			if (stats.getAbilities().size() > i) {
				drawTexturedModalRect(200, (i + 5) * 15, 16, 16, 16, 16);
				RenderHelper.drawAbilityIcon(stats.getAbilities().get(i).getID(), 200, (i + 5) * 15, 16, 16);
			}
		}

		this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/abilities/slots.png"));

		for (int i = 0; i < 9; i++) {
			if (stats.getSlotsList().size() > i) {
				if (stats.getAbilityNonNull(i) != null) {
					this.drawTexturedModalRect(posX - posX + 2, i * 24, 256, 256, 23, 23);
					RenderHelper.drawAbilityIcon(stats.getAbilityNonNull(i).getID(), posX - posX + 6, i * 2 + 5, 16,
							16);

				}
			} else {
				this.mc.getTextureManager()
						.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/abilities/slots.png"));
				this.drawTexturedModalRect(posX - posX + 2, i * 22, 256, 256, 23, 23);

			}
		}

		for (int i = 0; i < 9; i++) {
			if (canDrawString == true) {
				this.drawCenteredString(mc.fontRenderer, Integer.toString(i + 1), posX - posX + 6, i * 22,
						TextFormatting.BLACK.getColorIndex());
			}
		}
	}

	public void initGui() {
		ScaledResolution sr = new ScaledResolution(mc);

		int posX = sr.getScaledWidth();
		int posY = sr.getScaledHeight();
		for (int i = 0; i < 9; i++) {
			if (stats.getAbilities().size() > i) {

				this.buttonList.add(new GuiButtonTextured(i, 200, (i + 5) * 15, 16, 16, Integer.toString(i),
						stats.getAbilities().get(i).getAbilityTexture()));
				System.out.println(i);
			}
		}
	}

	@Override
	protected void actionPerformed(GuiButton guiButton) throws IOException {
		ScaledResolution sr = new ScaledResolution(mc);

		int posX = sr.getScaledWidth();
		int posY = sr.getScaledHeight();

		if (guiButton instanceof GuiButtonTextured) {
			canDrawString = true;
			if(stats.getAbilities().size() > guiButton.id) {
			currentAbility = stats.getAbilities().get(guiButton.id);
			System.out.println(currentAbility.getID());
			}
		}

	}

	@Override
	protected void keyTyped(char c, int a) throws IOException {

		
		if (canDrawString == true) {

			if (currentAbility != null) {
				
				if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
					stats.setAbilityToSlot(0, currentAbility);
					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(currentAbility, 0, 1));
				} else if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
					stats.setAbilityToSlot(1, currentAbility);
					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(currentAbility, 1, 1));
				} else if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
					stats.setAbilityToSlot(2, currentAbility);
					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(currentAbility, 2, 1));
				} else if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
					stats.setAbilityToSlot(3, currentAbility);
					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(currentAbility, 3, 1));
				} else if (Keyboard.isKeyDown(Keyboard.KEY_5)) {
					stats.setAbilityToSlot(4, currentAbility);
					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(currentAbility, 4, 1));
				} else if (Keyboard.isKeyDown(Keyboard.KEY_6)) {
					stats.setAbilityToSlot(5, currentAbility);
					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(currentAbility, 5, 1));
				} else if (Keyboard.isKeyDown(Keyboard.KEY_7)) {
					stats.setAbilityToSlot(6, currentAbility);
					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(currentAbility, 6, 1));
				} else if (Keyboard.isKeyDown(Keyboard.KEY_8)) {
					stats.setAbilityToSlot(7, currentAbility);
					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(currentAbility, 7, 1));
				} else if (Keyboard.isKeyDown(Keyboard.KEY_9)) {
					stats.setAbilityToSlot(8, currentAbility);
					ModidPacketHandler.INSTANCE.sendToServer(new AbilityPacketSync(currentAbility, 8, 1));
				}
			}
		}
		super.keyTyped(c, a);
	}

}
