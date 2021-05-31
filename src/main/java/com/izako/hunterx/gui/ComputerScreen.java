package com.izako.hunterx.gui;

import com.izako.hunterx.Main;
import com.izako.hunterx.izapi.Helper;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class ComputerScreen extends Screen {

	public float originx = this.width / 2 - (330 - 2);
	public float originy = this.height / 2 - (240 / 2);
	public static final ResourceLocation BACKGROUND = new ResourceLocation(Main.MODID, "textures/gui/computer_background.png");
	public static final ResourceLocation FOREGROUND = new ResourceLocation(Main.MODID, "textures/gui/computer_foreground.png");

	public ComputerScreen() {
		super(new StringTextComponent(""));

	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {

		this.renderBackground();
		RenderSystem.pushMatrix();

		//this.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
		//GuiUtils.drawTexturedModalRect((int)originx, (int)originy, 0, 0, 360, 240, 0);
		Helper.drawIMG(BACKGROUND, (int)originx, (int)originy, 0, 0, 360, 240, 1, 360 / 2, 240 / 2);


		RenderSystem.popMatrix();
		RenderSystem.pushMatrix();
		this.buttons.forEach(b -> {b.render(mouseX, mouseY, partialTicks);});

		RenderSystem.popMatrix();
	}

	@Override
	public void init() {
		 originx = this.width / 2 - (330 / 2);
		 originy = this.height / 2 - (240 / 2);

		MovingButton bartender = new MovingButton((int)(originx + 125),(int)(originy + 120), 70, 118,0,123, FOREGROUND, new Button.IPressable() {
			
			@Override
			public void onPress(Button p_onPress_1_) {
				System.out.println("ass");
			}
		});
		
	 this.addButton(bartender);
	}
}
