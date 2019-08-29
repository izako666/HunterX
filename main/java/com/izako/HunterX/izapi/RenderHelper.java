package com.izako.HunterX.izapi;

import com.izako.HunterX.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class RenderHelper {

	public static void drawAbilityIcon(String iconName, int x, int y, int u, int v)
	{
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/abilities/" + iconName + ".png"));        
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x			, y + v			, 1).tex(0.0, 1.0).endVertex();
		bufferbuilder.pos(x + u		, y + v			, 1).tex(1.0, 1.0).endVertex();
		bufferbuilder.pos(x + u		, y        		, 1).tex(1.0, 0.0).endVertex();
		bufferbuilder.pos(x			, y				, 1).tex(0.0, 0.0).endVertex();
		Tessellator.getInstance().draw();   
	}
}
