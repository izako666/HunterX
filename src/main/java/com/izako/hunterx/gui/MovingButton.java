package com.izako.hunterx.gui;

import com.izako.hunterx.izapi.Helper;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class MovingButton extends Button {

	public float trueY;
	public ResourceLocation loc;
	public int u;
	public int v;
	public MovingButton(int widthIn, int heightIn, int width, int height, int u, int v, ResourceLocation loc, IPressable onPress) {
		super(widthIn, heightIn, width, height, "", onPress);
		this.loc = loc;
		this.trueY = heightIn;
		this.u=u;
		this.v=v;
	}

	@Override
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
		if(this.visible) {
		if(this.isMouseOver(p_render_1_, p_render_2_)) {
			this.y = (int) (this.trueY -5);
		} else {
			this.y = (int) this.trueY;
		}
		Helper.drawIMG(loc, this.x, this.y, this.u, this.v, this.width, this.height, 0, this.width, this.height);
	
		}
	}


	
}
