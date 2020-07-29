package com.izako.hunterx.gui;

import com.izako.wypi.WyHelper;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlickeringString {

	private String message;
	private int flicker;
	private boolean isVisible = true;
	private Minecraft mc;
	private float scale = 1f;
	public int zLevel = 1;

	public FlickeringString(String str, int flicker)
	{
		this.mc = Minecraft.getInstance();
		this.message = str;
		this.flicker = flicker;
	}
	
	public FlickeringString setScale(float value) {
		this.scale = value;
		return this;
	}
	public void render(int posX, int posY)
	{
		if(this.mc.getFrameTimer().getIndex() % this.flicker == 0)
			this.isVisible = !this.isVisible;
		else
		{
			String msg = this.isVisible ? this.message : "";

			GlStateManager.pushMatrix();
			GlStateManager.translatef(posX, posY, zLevel);
			GlStateManager.scalef(scale, scale, scale);
			WyHelper.drawStringWithBorder(this.mc.fontRenderer, msg, 0, 0, WyHelper.hexToRGB("#FFFFFF").getRGB());
		    GlStateManager.scalef(0, 0, 0);
		    GlStateManager.popMatrix();
		}
	}
}
