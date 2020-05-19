package com.izako.hunterx.gui;

import com.izako.wypi.WyHelper;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class GenericTexturedButton extends Button{


	ResourceLocation texture;
	public GenericTexturedButton(ResourceLocation loc, int x, int y, int width, int height, String text,
			IPressable onPress) {
		super(x, y, width, height, text, onPress);
		this.texture = loc;
	}

	
	@Override
	public void render(int x, int y, float partialTicks) {
		/*GlStateManager.enableBlend();
		GlStateManager.enableAlphaTest();
		WyHelper.drawIcon(texture, this.x, this.y, this.width, this.height);
		GlStateManager.disableAlphaTest();
		GlStateManager.disableBlend();
		GlStateManager.translatef(0, 0, 1); */
		super.render(x, y, partialTicks);
		//GlStateManager.translatef(0, 0, 0);
	}
}
