package com.izako.hunterx.gui;

import java.awt.Color;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.CharacterInitPacket;
import com.izako.wypi.WyHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiSlider;
import net.minecraftforge.fml.client.config.GuiUtils;

@OnlyIn(Dist.CLIENT)
public class CharacterCreatorScreen extends Screen {

	public static final ResourceLocation BASE = new ResourceLocation(Main.MODID, "textures/gui/slots.png");
	public static final ResourceLocation GENERIC_BUTTON = new ResourceLocation(Main.MODID, "textures/gui/button1.png");
	int guiState = 0;
	int currentRed = 1;
	int currentGreen = 1;
	int currentBlue = 1;
	int baseX;
	int baseY;
	
	
	public CharacterCreatorScreen() {
		super(new StringTextComponent(""));
	}


	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.getMinecraft().getTextureManager().bindTexture(BASE);
		GuiUtils.drawTexturedModalRect(baseX, baseY, 0, 0, 256, 256, 0);
		switch(this.guiState) {
		case 0:
			Color c = new Color(currentRed, currentGreen, currentBlue);
			WyHelper.drawColourOnScreen(c.getRGB(),  255, baseX + 90, baseY + 20, 16, 16, 1);
			break;
		case 1:
			WyHelper.drawColourOnScreen(currentRed, currentGreen, currentBlue, 255, baseX + 90, baseY + 40, 64, 64, 1);
		   this.drawString(this.font, "Red", baseX + 150, baseY + 145, Color.WHITE.getRGB());
		   this.drawString(this.font, "Green", baseX + 150, baseY + 175, Color.WHITE.getRGB());
		   this.drawString(this.font, "Blue", baseX + 150, baseY + 205, Color.WHITE.getRGB());

		   break;
		}
		this.buttons.forEach((b) -> {b.render(mouseX, mouseY, partialTicks);});
		super.render(mouseX, mouseY, partialTicks);
	}


	@Override
	public void init(Minecraft mc, int mouseX, int mouseY) {

		super.init(mc, mouseX, mouseY);
		this.minecraft = Minecraft.getInstance();
		this.font = mc.fontRenderer;
		baseX = mc.mainWindow.getScaledWidth() / 2 - 128;
		baseY = mc.mainWindow.getScaledHeight() / 2 - 128;
        Button auraColorButton = new Button(baseX + 20, baseY + 20, 64, 20, "auracolor", this::auraButtonAction);
        Button create = new Button(baseX + 256 - 64, baseY + 236, 64, 20, "Create", this::createCharacter);
        this.addButton(auraColorButton);
        this.addButton(create);

	}


	private void reInit() {
		this.buttons.clear();
		this.children.clear();
		switch(this.guiState) {
		
		case 0:
	        Button auraColorButton = new Button(baseX + 20, baseY + 20, 64, 20, "Aura Color", this::auraButtonAction);
	        Button create = new Button(baseX + 256 - 64, baseY + 236, 64, 20, "Create", this::createCharacter);

	        this.addButton(auraColorButton);
	        this.addButton(create);
           break;
		case 1:
			GuiSlider redSlider = new GuiSlider(baseX + 20, baseY + 140, 128, 20, "red", "slider", 1, 255, 1, false, false, b -> {}, s -> this.changeColor(s, "red"));
			GuiSlider greenSlider = new GuiSlider(baseX + 20, baseY + 170, 128, 20, "green", "slider", 1, 255, 1, false, false, b -> {}, s -> this.changeColor(s, "green"));
			GuiSlider blueSlider = new GuiSlider(baseX + 20, baseY + 200, 128, 20, "blue", "slider", 1, 255, 1, false, false, b -> {}, s -> this.changeColor(s, "blue"));
            Button returnButton = new Button(baseX, baseY, 64, 20, "Return", this::returnButton);
			redSlider.setValue(this.currentRed);
			greenSlider.setValue(this.currentGreen);
			blueSlider.setValue(this.currentBlue);
            this.addButton(redSlider);
            this.addButton(greenSlider);
            this.addButton(blueSlider);
            this.addButton(returnButton);
            break;
		}
	}
	@Override
	public void tick() {
		super.tick();
	}


	private void changeColor(GuiSlider slider, String type) {				
	
		int color = slider.getValueInt();
		if(type == "red") {
			this.currentRed = color;
		} else if(type == "green") {
			this.currentGreen = color;
		} else if(type == "blue") {
			this.currentBlue = color;
		}
	
	}
	
	private void auraButtonAction(Button but) {
		this.guiState = 1;
		this.reInit();
	}
	
	private void returnButton(Button but) {
		this.guiState = 0;
		this.reInit();
	}
	
	private void createCharacter(Button but) {
		IHunterData data = HunterDataCapability.get(this.getMinecraft().player);
		IAbilityData ablData = AbilityDataCapability.get(this.getMinecraft().player);
	    data.setIsCharacterMade(true);
	    ablData.setAuraColor(currentRed,currentGreen,currentBlue);
	    PacketHandler.INSTANCE.sendToServer(new CharacterInitPacket(data, ablData));
	    this.onClose();
	}
}
