package com.izako.hunterx.gui;

import com.izako.hunterx.Main;
import com.izako.hunterx.izapi.IZAHelper;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class AnimatedButton extends Button {


	SpriteTemplate defaultIMG;
	SpriteTemplate onClickIMG;

	private int tick = 0;
	public AnimatedButton(int x, int y, int width, int height, String text, IPressable onPress, SpriteTemplate defaultIMG, SpriteTemplate onClickIMG) {
		super(x, y, width, height, text, onPress);

		this.defaultIMG = new SpriteTemplate(defaultIMG.rs, x,y,defaultIMG.u,defaultIMG.v,width,height,defaultIMG.zLevel,width,height);
		this.onClickIMG = new SpriteTemplate(onClickIMG.rs, x,y,onClickIMG.u,onClickIMG.v,width,height,onClickIMG.zLevel,width,height);
	}
	


	@Override
	public void onClick(double p_onClick_1_, double p_onClick_3_) {

		
		this.tick = 5;
		
	}

	
	@Override
	public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
		
		RenderSystem.pushMatrix();
		RenderSystem.enableBlend();
		if(this.tick > 0) {
			this.onClickIMG.draw();
			this.tick--;
			if(this.tick == 0) {
				this.onPress();
			}
		} else {
			this.defaultIMG.draw();
		}
		RenderSystem.disableBlend();
		RenderSystem.popMatrix();
	}


	static class SpriteTemplate {
		ResourceLocation rs;
		int x,y,u,v,width,height;
		float zLevel,uWidth,uHeight;
		public SpriteTemplate(ResourceLocation rs, int x,int y, int u, int v, int width, int height, float zLevel, float uWidth,float uHeight) {
			this.x = x;
			this.y = y;
			this.u = u;
			this.v = v;
			this.width = width;
			this.height = height;
			this.zLevel = zLevel;
			this.uWidth = uWidth;
			this.uHeight = uHeight;
			this.rs = rs;
		}
		
		public SpriteTemplate(ResourceLocation rs,int u,int v,float zLevel,float uWidth,float uHeight) {
			this.rs = rs;
			this.u = u;
			this.v = v;
			this.zLevel = zLevel;
			this.uWidth = uWidth;
			this.uHeight = uHeight;
		}
		
		public SpriteTemplate(ResourceLocation rs,int u,int v,float zLevel) {
			this.rs = rs;
			this.u = u;
			this.v = v;
			this.zLevel = zLevel;
			
		}

		void draw() {
			IZAHelper.drawIMG(rs, x, y, u, v, width, height, zLevel, uWidth, uHeight);
		}
	}
}
