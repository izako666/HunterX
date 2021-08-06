package com.izako.hunterx.gui;

import com.izako.wypi.WyHelper;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class GenericTexturedButton extends Button{


	ResourceLocation texture;
	public GenericTexturedButton(ResourceLocation loc, int x, int y, int width, int height, String text,
			IPressable onPress) {
		super(x, y, width, height, text, onPress);
		this.texture = loc;
	}

	
	@Override
	public void render(int x, int y, float partialTicks) {
	
		RenderSystem.pushMatrix();
		RenderSystem.enableBlend();
		WyHelper.drawIcon(texture, this.x, this.y, this.width, this.height,0);
		RenderSystem.disableBlend();
		RenderSystem.popMatrix();
	}
}
