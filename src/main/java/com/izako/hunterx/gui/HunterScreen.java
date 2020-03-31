package com.izako.hunterx.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModItems;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.config.GuiUtils;

public class HunterScreen extends Screen {

	/*
	 * gui state 0 for base gui 1 for stats gui 2 for quests gui 3 for hunter card
	 * gui
	 */
	public int guiState = 0;
	public int questPage = 0;
	public List<String> quests;
	public boolean isLicenseBG = false;
	public Quest currentQuest = null;
	public String cardString = null;

	public static final ResourceLocation LICENSE_GUI = new ResourceLocation(Main.MODID, "textures/gui/licensegui.png");
	public static final ResourceLocation GENERAL_BUTTON = new ResourceLocation(Main.MODID, "textures/gui/button1.png");
	public static final ResourceLocation QUEST_FORWARD = new ResourceLocation(Main.MODID,
			"textures/gui/forwardbutton.png");
	public static final ResourceLocation QUEST_BACKWARD = new ResourceLocation(Main.MODID,
			"textures/gui/backwardbutton.png");

	public HunterScreen() {
		super(new StringTextComponent(""));
		// TODO Auto-generated constructor stub
	}

	public void drawIcon(ResourceLocation loc, int x, int y, int u, int v, int zLevel) {
		Minecraft.getInstance().getTextureManager().bindTexture(loc);
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x, y + v, zLevel).tex(0.0, 1.0).endVertex();
		bufferbuilder.pos(x + u, y + v, zLevel).tex(1.0, 1.0).endVertex();
		bufferbuilder.pos(x + u, y, zLevel).tex(1.0, 0.0).endVertex();
		bufferbuilder.pos(x, y, zLevel).tex(0.0, 0.0).endVertex();
		Tessellator.getInstance().draw();
	}

	@Override
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {

		this.renderBackground();
		int scaledHeight = this.getMinecraft().mainWindow.getScaledHeight();
		int scaledWidth = this.getMinecraft().mainWindow.getScaledWidth();
		PlayerEntity p = this.getMinecraft().player;

		if (guiState == 3 && isLicenseBG) {
			this.getMinecraft().getTextureManager().bindTexture(LICENSE_GUI);
			GuiUtils.drawTexturedModalRect((int) (scaledWidth - (scaledWidth * 0.74)), 0, 0, 0, 256, 256, 0);

		} else {
			this.getMinecraft().getTextureManager()
					.bindTexture(new ResourceLocation(Main.MODID, "textures/gui/slots.png"));
			GuiUtils.drawTexturedModalRect((int) (scaledWidth - (scaledWidth * 0.74)), 0, 0, 0, 256, 256, 0);

		}
		switch (guiState) {

		case (0):
			this.renderGeneralGui();
			break;
		case (1):
			this.renderStatsGui();
			break;
		case (2):
			this.renderQuestsGui(scaledWidth, scaledHeight);
			break;
		case (3):
			this.renderCardGui(p, scaledWidth, scaledHeight);
			break;
		}
	}

	public void renderStatsGui() {

		int width = this.width;
		int height = this.height;
		PlayerEntity player = this.getMinecraft().player;
		IHunterData data = HunterDataCapability.get(player);

		this.drawString(font, "Health:    " + Math.round(data.getHealthStat()), (int) (width - width * 0.60),
				(int) (height - (height * 0.85)), 16777215);
		this.drawString(font, "Speed:    " + Math.round(data.getSpeedStat()), (int) (width - width * 0.60),
				(int) (height - (height * 0.70)), 16777215);
		this.drawString(font, "Attack:    " + Math.round(data.getAttackStat()), (int) (width - width * 0.60),
				(int) (height - (height * 0.55)), 16777215);
		this.drawString(font, "Defense:    " + Math.round(data.getDefenseStat()), (int) (width - width * 0.60),
				(int) (height - (height * 0.40)), 16777215);

	}

	public void renderGeneralGui() {
		this.children.forEach((w) -> {
			if (w instanceof HunterButton) {
				HunterButton b = (HunterButton) w;

				this.drawIcon(GENERAL_BUTTON, b.x, b.y, 128, 32, 0);
				this.drawCenteredString(this.font, b.name, b.x + 50, b.y + 5, 16777215);
			}
		});
	}

	public void renderQuestsGui(int scaledWidth, int scaledHeight) {

		this.buttons.forEach((b) -> {
			if (b instanceof HunterButton) {
				HunterButton hb = (HunterButton) b;
				if (hb.name.contains("forward")) {
					this.drawIcon(QUEST_FORWARD, hb.x, hb.y, 16, 16, 0);
				} else if (hb.name.contains("backward")) {
					this.drawIcon(QUEST_BACKWARD, hb.x, hb.y, 16, 16, 0);

				} else {
					this.drawIcon(GENERAL_BUTTON, hb.x, hb.y, 96, 32, 0);
					this.drawString(font, hb.name, hb.x + 5, hb.y + 5, 16777215);
				}

			}
		});

		if (this.currentQuest != null) {
			this.currentQuest.renderDesc((int) (scaledWidth - (scaledWidth * 0.48)),
					(int) (scaledHeight - (scaledHeight * 0.8)));

			PlayerEntity p = this.getMinecraft().player;
			IHunterData data = HunterDataCapability.get(p);
			if (data.getProgress(this.currentQuest.getId()) > 100) {
				this.drawString(font, "progress:", (int) (scaledWidth - (scaledWidth * 0.50)),
						(int) (scaledHeight - (scaledHeight / 3)), 16777215);
				this.drawString(font, "FINISHED", (int) (scaledWidth - (scaledWidth * 0.37)),
						(int) (scaledHeight - (scaledHeight / 3)), Color.red.getRGB());

			} else {
				this.drawString(font, "progress:", (int) (scaledWidth - (scaledWidth * 0.50)),
						(int) (scaledHeight - (scaledHeight / 3)), 16777215);
				this.drawString(font, data.getProgress(this.currentQuest.getId()).toString(),
						(int) (scaledWidth - (scaledWidth * 0.37)), (int) (scaledHeight - (scaledHeight / 3)),
						16777215);
			}

		}
	}

	public void renderCardGui(PlayerEntity p, int scaledWidth, int scaledHeight) {

		this.buttons.forEach((b) -> {
			this.drawIcon(GENERAL_BUTTON, b.x, b.y, b.getWidth(), b.getHeight(), 0);
			if (this.cardString != null) {
				this.drawString(font, this.cardString, b.x, b.y - 10, Color.red.getRGB());
			}
		});
		if (isLicenseBG) {
			font.drawString(p.getName().getString(), (int)(scaledWidth / 2.9), (int) (160), Color.black.getRGB());
			font.drawString( "Standard Hunter", (int)(scaledWidth / 2.9), (int) (180), Color.black.getRGB());
			font.drawString( "0 star ", (int)(scaledWidth / 2.9), (int) (200), Color.black.getRGB());
			font.drawString( p.getUUID(p.getGameProfile()).toString(), (int) (scaledWidth / 3.2), (int) (220), Color.black.getRGB());

		}
	}

	@Override
	public void init() {
		int width = this.width;
		int height = this.height;

		this.buttons.clear();
		this.children.clear();
		if (this.guiState == 0) {
			this.addButton(new HunterButton((int) (width - width * 0.63), (int) (height - (height * 0.85)), 128, 32,
					"stats", false, new Button.IPressable() {

						@Override
						public void onPress(Button p_onPress_1_) {
							// TODO Auto-generated method stub

							if (Minecraft.getInstance().currentScreen instanceof HunterScreen) {
								((HunterScreen) Minecraft.getInstance().currentScreen).guiState = 1;
								((HunterScreen) Minecraft.getInstance().currentScreen).init();
							}
						}
					}));
			this.addButton(new HunterButton((int) (width - width * 0.63), (int) (height - (height * 0.70)), 128, 32,
					"quests", false, new Button.IPressable() {

						@Override
						public void onPress(Button p_onPress_1_) {
							// TODO Auto-generated method stub

							if (Minecraft.getInstance().currentScreen instanceof HunterScreen) {
								((HunterScreen) Minecraft.getInstance().currentScreen).guiState = 2;
								((HunterScreen) Minecraft.getInstance().currentScreen).init();
							}
						}
					}));

			this.addButton(new HunterButton((int) (width - width * 0.63), (int) (height - (height * 0.55)), 128, 32,
					"card", false, new Button.IPressable() {

						@Override
						public void onPress(Button p_onPress_1_) {
							// TODO Auto-generated method stub

							if (Minecraft.getInstance().currentScreen instanceof HunterScreen) {
								((HunterScreen) Minecraft.getInstance().currentScreen).guiState = 3;
								((HunterScreen) Minecraft.getInstance().currentScreen).init();
							}
						}
					}));

		} else if (guiState == 2) {

			this.addButton(new HunterButton((int) (width - width * 0.55), (int) (height - (height * 0.20)), 16, 16,
					"pagebuttonforward", false, new Button.IPressable() {

						@Override
						public void onPress(Button p_onPress_1_) {
							// TODO Auto-generated method stub
							if (Minecraft.getInstance().currentScreen instanceof HunterScreen) {
								((HunterScreen) Minecraft.getInstance().currentScreen).questPage++;
								((HunterScreen) Minecraft.getInstance().currentScreen).currentQuest = null;
								((HunterScreen) Minecraft.getInstance().currentScreen).init();

							}
						}
					}));
			this.addButton(new HunterButton((int) (width - width * 0.63), (int) (height - (height * 0.20)), 16, 16,
					"pagebuttonbackward", false, new Button.IPressable() {

						@Override
						public void onPress(Button p_onPress_1_) {
							// TODO Auto-generated method stub
							if (Minecraft.getInstance().currentScreen instanceof HunterScreen) {
								int page = ((HunterScreen) Minecraft.getInstance().currentScreen).questPage;
								if (!(page <= 0)) {
									((HunterScreen) Minecraft.getInstance().currentScreen).questPage--;
									((HunterScreen) Minecraft.getInstance().currentScreen).currentQuest = null;
									((HunterScreen) Minecraft.getInstance().currentScreen).init();

								}
							}
						}
					}));

			PlayerEntity p = this.getMinecraft().player;
			IHunterData data = HunterDataCapability.get(p);
			this.quests = new ArrayList<String>(data.getQuests().keySet());
			for (int i = 4 * questPage; i <= 4 * (questPage + 1); i++) {
				if ((this.quests.size() - 1) >= i) {
					HunterScreen screen = ((HunterScreen) Minecraft.getInstance().currentScreen);
					this.addButton(new HunterButton((int) (width - width * 0.73),
							(int) (height - ((height * 0.95) - (50 * i))), 96, 32, this.quests.get(i), true,
							new Button.IPressable() {

								@Override
								public void onPress(Button but) {
									// TODO Auto-generated method stub
									screen.currentQuest = ModQuests.getInstance(((HunterButton) but).questID);

								}
							}));
				}
			}
		} else if (guiState == 3 && !this.isLicenseBG) {
			this.addButton(new HunterButton((int) (width - width * 0.73), (int) (height - ((height * 0.95) - (50 * 2))),
					96, 32, "bgchanger", false, new Button.IPressable() {

						@Override
						public void onPress(Button p_onPress_1_) {
							// TODO Auto-generated method stub
							PlayerEntity p = Minecraft.getInstance().player;

							if (p.inventory.hasItemStack(new ItemStack(ModItems.HUNTER_LICENSE))) {
								((HunterScreen) Minecraft.getInstance().currentScreen).isLicenseBG = true;
								((HunterScreen) Minecraft.getInstance().currentScreen).init();

							} else {
								((HunterScreen) Minecraft
										.getInstance().currentScreen).cardString = "you dont have a license.";

							}
						}
					}));
		}

	}

	public int getSlotForStack(ItemStack stack, PlayerEntity p) {
		for (int i = 0; i <= p.inventory.getSizeInventory(); i++) {
			if (p.inventory.getStackInSlot(i).getItem() == stack.getItem()) {
				return i;
			}
		}
		return -1;
	}

}
