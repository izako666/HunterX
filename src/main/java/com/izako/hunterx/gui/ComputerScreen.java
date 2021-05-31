package com.izako.hunterx.gui;

import com.izako.hunterx.Main;
import com.izako.hunterx.izapi.Helper;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class ComputerScreen extends Screen {

	public float originx = this.width / 2 - (330 - 2);
	public float originy = this.height / 2 - (240 / 2);
	public static final ResourceLocation BACKGROUND = new ResourceLocation(Main.MODID, "textures/gui/computer_background.png");
	public static final ResourceLocation FOREGROUND = new ResourceLocation(Main.MODID, "textures/gui/computer_foreground.png");

	public SequencedString openingStatement = new SequencedString("You lookin ta know somethin'?.... I've also got some peculiar goods if it fancies ya...", 100, 100);
	public boolean isTalking = false;
	public boolean isShopping = false;
	public ComputerScreen() {
		super(new StringTextComponent(""));

	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {

		this.renderBackground();
		RenderSystem.pushMatrix();

		//this.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
		//GuiUtils.drawTexturedModalRect((int)originx, (int)originy, 0, 0, 360, 240, 0);
		Helper.drawIMG(BACKGROUND, (int)originx, (int)originy, 0, 0, 360, 240, 0, 360 / 2, 240 / 2);


		this.buttons.forEach(b -> {b.render(mouseX, mouseY, partialTicks);});

		RenderSystem.popMatrix();
		if(isTalking) {
			RenderSystem.pushMatrix();
			RenderSystem.translated(originx + 160, originy+60, 0);
			RenderSystem.scaled(1.5, 1.5, 1);
			Helper.drawIMG(FOREGROUND, 0, 0, 162, 0, 75, 55, 0, 75, 55);
			RenderSystem.scaled(1, 1, 1);
			RenderSystem.popMatrix();
			openingStatement.render((int)(originx+165),(int)(originy+65));

		}

	}

	@Override
	public void init() {
		 originx = this.width / 2 - (330 / 2);
		 originy = this.height / 2 - (240 / 2);

		MovingButton bartender = new MovingButton((int)(originx + 125),(int)(originy + 120), 70, 118,0,123, FOREGROUND, new Button.IPressable() {
			
			@Override	
			public void onPress(Button p_onPress_1_) {
				((ComputerScreen)Minecraft.getInstance().currentScreen).isTalking = true;
			}
		});
		
	 this.addButton(bartender);
	 
	 openingStatement.event = new SequencedString.IRenderEndEvent() {
		
		@Override
		public void onEnd() {

			((ComputerScreen)Minecraft.getInstance().currentScreen).isShopping = true;
			((ComputerScreen)Minecraft.getInstance().currentScreen).isTalking = false;
		}
	};
	}
}
