package com.izako.HunterX.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;


public class HotBarGui extends Gui {
	
	

	Minecraft mc;
	public HotBarGui(Minecraft mc) {
		this.mc =  mc;
	}
}
