package com.izako.hunterx.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModItems;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.networking.ModidPacketHandler;
import com.izako.hunterx.networking.packets.AbilityUpdatePacket;

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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;

@OnlyIn(Dist.CLIENT)
public class HunterScreen extends Screen {

	/*
	 * gui state 0 for base gui 1 for stats gui 2 for quests gui 3 for hunter card
	 * gui state 4 for abilities gui
	 */
	public int guiState = 0;
	public int questPage = 0;
	public int abilityPage = 0;
	public List<String> quests;
	public Ability currentAbility;
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
		AbilitySlotsEvent.renderSlotsWithoutEffects();
		int scaledHeight = this.getMinecraft().mainWindow.getScaledHeight();
		int scaledWidth = this.getMinecraft().mainWindow.getScaledWidth();
		PlayerEntity p = this.getMinecraft().player;

		if (guiState == 3 && isLicenseBG) {
			this.getMinecraft().getTextureManager().bindTexture(LICENSE_GUI);
			GuiUtils.drawTexturedModalRect((int) ((scaledWidth / 2) - 128), 0, 0, 0, 256, 256, 0);

		} else {
			this.getMinecraft().getTextureManager()
					.bindTexture(new ResourceLocation(Main.MODID, "textures/gui/slots.png"));
			GuiUtils.drawTexturedModalRect((int) ((scaledWidth / 2) - 128), 0, 0, 0, 256, 256, 0);

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
		case (4):
			this.renderAbilitiesGui(scaledWidth, scaledHeight);
		}
	}

	public void renderAbilitiesGui(int scaledWidth, int scaledHeight) {
		int initialHeight;
		if (Minecraft.getInstance().mainWindow.getGuiScaleFactor() == 4) {
			initialHeight = 0;
		} else if (Minecraft.getInstance().mainWindow.getGuiScaleFactor() == 3) {
			initialHeight = (int) (height / 8);
		} else {
			initialHeight = (int) (height / 4);
		}
		this.buttons.forEach((b) -> {
			if (b instanceof HunterButton) {
				HunterButton hb = (HunterButton) b;
				if (hb.name.contains("forward")) {
					this.drawIcon(QUEST_FORWARD, hb.x, hb.y, 16, 16, 0);
				} else if (hb.name.contains("backward")) {
					this.drawIcon(QUEST_BACKWARD, hb.x, hb.y, 16, 16, 0);

				}
			}
			if (b instanceof AbilityButton) {
				AbilityButton ab = (AbilityButton) b;
				this.drawIcon(ab.ablTexture, ab.x, ab.y, 32, 32, 0);
			}
		});
		if (this.currentAbility != null) {
			for (int i = 0; i < 8; i++) {
				this.drawString(this.font, String.valueOf(i + 1), 20, (int) (initialHeight + 6 + (32 * i + 1)),
						16777215);
			}
		}
	}

	public void renderStatsGui() {

		int width = this.getMinecraft().mainWindow.getScaledWidth();
		int height = this.getMinecraft().mainWindow.getScaledHeight();
		PlayerEntity player = this.getMinecraft().player;
		IHunterData data = HunterDataCapability.get(player);

		this.drawString(font, "Health:    " + Math.round(data.getHealthStat()), (int) ((width * 0.5) - 70), (int) (20),
				16777215);
		this.drawString(font, "Speed:    " + Math.round(data.getSpeedStat()), (int) ((width * 0.5) - 70), (int) (60),
				16777215);
		this.drawString(font, "Attack:    " + Math.round(data.getAttackStat()), (int) ((width * 0.5) - 70), (int) (100),
				16777215);
		this.drawString(font, "Defense:    " + Math.round(data.getDefenseStat()), (int) ((width * 0.5) - 70),
				(int) (140), 16777215);

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
			this.currentQuest.renderDesc((int) ((scaledWidth * 0.5) - 10), (int) (70));

			PlayerEntity p = this.getMinecraft().player;
			IHunterData data = HunterDataCapability.get(p);
			if (data.getProgress(this.currentQuest.getId()) > 100) {
				this.drawString(font, "progress:", (int) ((scaledWidth * 0.5) - 15), (int) (200), 16777215);
				this.drawString(font, "FINISHED", (int) ((scaledWidth * 0.5) + 45), (int) (200), Color.red.getRGB());

			} else {
				this.drawString(font, "progress:", (int) ((scaledWidth * 0.5) - 15), (int) (200), 16777215);
				this.drawString(font, data.getProgress(this.currentQuest.getId()).toString(),
						(int) ((scaledWidth * 0.5) + 45), (int) (200), 16777215);
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
			font.drawString(p.getName().getString(), (int) ((scaledWidth * 0.5) - 93), (int) (160),
					Color.black.getRGB());
			font.drawString("Standard Hunter", (int) ((scaledWidth * 0.5) - 93), (int) (180), Color.black.getRGB());
			font.drawString("0 star ", (int) ((scaledWidth * 0.5) - 93), (int) (200), Color.black.getRGB());
			font.drawString(p.getUUID(p.getGameProfile()).toString(), (int) ((scaledWidth * 0.5) - 98), (int) (220),
					Color.black.getRGB());

		}
	}

	@Override
	public void init() {
		int width = this.getMinecraft().mainWindow.getScaledWidth();
		int height = this.getMinecraft().mainWindow.getScaledHeight();

		this.buttons.clear();
		this.children.clear();
		if (this.guiState == 0) {
			this.addButton(new HunterButton((int) ((width * 0.50) - 64), (int) (20), 128, 32, "stats", false,
					new Button.IPressable() {

						@Override
						public void onPress(Button p_onPress_1_) {
							// TODO Auto-generated method stub

							if (Minecraft.getInstance().currentScreen instanceof HunterScreen) {
								((HunterScreen) Minecraft.getInstance().currentScreen).guiState = 1;
								((HunterScreen) Minecraft.getInstance().currentScreen).init();
							}
						}
					}));
			this.addButton(new HunterButton((int) ((width * 0.50) - 64), (int) (60), 128, 32, "quests", false,
					new Button.IPressable() {

						@Override
						public void onPress(Button p_onPress_1_) {
							// TODO Auto-generated method stub

							if (Minecraft.getInstance().currentScreen instanceof HunterScreen) {
								((HunterScreen) Minecraft.getInstance().currentScreen).guiState = 2;
								((HunterScreen) Minecraft.getInstance().currentScreen).init();
							}
						}
					}));

			this.addButton(new HunterButton((int) ((width * 0.50) - 64), (int) (100), 128, 32, "card", false,
					new Button.IPressable() {

						@Override
						public void onPress(Button p_onPress_1_) {
							// TODO Auto-generated method stub

							if (Minecraft.getInstance().currentScreen instanceof HunterScreen) {
								((HunterScreen) Minecraft.getInstance().currentScreen).guiState = 3;
								((HunterScreen) Minecraft.getInstance().currentScreen).init();
							}
						}
					}));
			this.addButton(new HunterButton((int) ((width * 0.50) - 64), (int) (140), 128, 32, "abilities", false,
					new Button.IPressable() {

						@Override
						public void onPress(Button p_onPress_1_) {
							// TODO Auto-generated method stub
							if (Minecraft.getInstance().currentScreen instanceof HunterScreen) {
								((HunterScreen) Minecraft.getInstance().currentScreen).guiState = 4;
								((HunterScreen) Minecraft.getInstance().currentScreen).init();
							}
						}
					}));

		} else if (guiState == 2) {

			this.addButton(new HunterButton((int) ((width * 0.5) - 70), (int) (210), 16, 16, "pagebuttonforward", false,
					new Button.IPressable() {

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
			this.addButton(new HunterButton((int) ((width * 0.5) - 90), (int) (210), 16, 16, "pagebuttonbackward",
					false, new Button.IPressable() {

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
					this.addButton(new HunterButton((int) ((width * 0.5) - 125), (int) (30 + 50 * i), 96, 32,
							this.quests.get(i), true, new Button.IPressable() {

								@Override
								public void onPress(Button but) {
									// TODO Auto-generated method stub
									screen.currentQuest = ModQuests.getInstance(((HunterButton) but).questID);

								}
							}));
				}
			}
		}else if(guiState==3&&!this.isLicenseBG)

	{
		this.addButton(new HunterButton((int) (width * 0.5 - 48), (int) (100), 96, 32, "bgchanger", false,
				new Button.IPressable() {

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
	}else if(guiState==4)
	{
		this.addButton(new HunterButton((int) ((width * 0.5) - 70), (int) (210), 16, 16, "abilitybuttonforward", false,
				new Button.IPressable() {

					@Override
					public void onPress(Button p_onPress_1_) {
						// TODO Auto-generated method stub
						if (Minecraft.getInstance().currentScreen instanceof HunterScreen) {
							((HunterScreen) Minecraft.getInstance().currentScreen).abilityPage++;
							((HunterScreen) Minecraft.getInstance().currentScreen).currentAbility = null;
							((HunterScreen) Minecraft.getInstance().currentScreen).init();

						}
					}
				}));
		this.addButton(new HunterButton((int) ((width * 0.5) - 90), (int) (210), 16, 16, "abilitybuttonbackward", false,
				new Button.IPressable() {

					@Override
					public void onPress(Button p_onPress_1_) {
						// TODO Auto-generated method stub
						if (Minecraft.getInstance().currentScreen instanceof HunterScreen) {
							int page = ((HunterScreen) Minecraft.getInstance().currentScreen).abilityPage;
							if (!(page <= 0)) {
								((HunterScreen) Minecraft.getInstance().currentScreen).abilityPage--;
								((HunterScreen) Minecraft.getInstance().currentScreen).currentAbility = null;
								((HunterScreen) Minecraft.getInstance().currentScreen).init();

							}
						}
					}
				}));

		PlayerEntity p = this.getMinecraft().player;
		IAbilityData abldata = AbilityDataCapability.get(p);
		List<Ability> list = abldata.getAbilities();
		for (int i = 4 * abilityPage; i <= 4 * (abilityPage + 1); i++) {
			if ((list.size() - 1) >= i) {
				HunterScreen screen = ((HunterScreen) Minecraft.getInstance().currentScreen);
				this.addButton(new AbilityButton((int) ((width * 0.5) - 125), (int) (30 + 50 * i), 32, 32,
						list.get(i).getId(), new Button.IPressable() {

							@Override
							public void onPress(Button but) {
								// TODO Auto-generated method stub

								screen.currentAbility = ((AbilityButton) but).ability;

							}
						}, list.get(i)));
			}
		}
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

	@Override
	   public boolean keyPressed(int kcode, int p_keyPressed_2_, int p_keyPressed_3_) {
	    if(this.guiState == 4) {
	     IAbilityData data = AbilityDataCapability.get(this.getMinecraft().player);
		 for(int i = 1; i < 9; i++) {
			 if(kcode == 48 + i) {
				  if(this.currentAbility != null) {
				 if(data.getAbilityInSlot(i - 1) == null) {
					 boolean flag = true;
					 for(Ability ab : data.getSlotAbilities()) {
						 if(ab != null) {
						 if(ab.getId() == this.currentAbility.getId()) {
							 flag = false;
						 }
						 }
					 }
					 if(flag == true) {
				 data.putAbilityInSlot(this.currentAbility, i - 1);
				 ModidPacketHandler.INSTANCE.sendToServer(new AbilityUpdatePacket(data, false));
					 }
				 this.currentAbility = null;
				 } else {
					 this.currentAbility =null;
				 }
			 } else {
				 if(data.getAbilityInSlot(i - 1) != null) {
					 data.putAbilityInSlot(null, i - 1);
					 ModidPacketHandler.INSTANCE.sendToServer(new AbilityUpdatePacket(data, false));
				 }
			 }
			 
		 }
	     }
	    }return super.keyPressed(kcode,p_keyPressed_2_,p_keyPressed_3_);
}

}
