package com.izako.HunterX.gui;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.util.List;
import java.util.Set;

import javax.swing.event.HyperlinkEvent.EventType;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.izako.HunterX.izapi.RenderHelper;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;
import com.izako.HunterX.util.Reference;
import com.sun.jna.platform.unix.X11.Screen;

import akka.actor.FSM.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiActive {

	@SubscribeEvent
	public void renderEvent(RenderGameOverlayEvent e) {
		
		
		
		GlStateManager.pushMatrix();
		
	HotBarGui gui =	new HotBarGui(Minecraft.getMinecraft());
	IEntityStats stats = gui.mc.player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
	int posX = e.getResolution().getScaledWidth();
	int posY = e.getResolution().getScaledHeight();
	
	
	
	
	if(e.getType() == net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.HOTBAR) {
		gui.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/abilities/hotbar.png"));
	 
	for(int i = 0; i < 9; i++ ) {
		
		
	    	  
	    	  if(stats.getAbilityNonNull(i) != null) {
	    		  gui.drawTexturedModalRect(posX - posX + 2, i * 22, 256, 256, 23, 23);
	    		  RenderHelper.drawAbilityIcon(stats.getAbilityNonNull(i).getID(),  posX - posX + 6,(i * 22) + 4, 16, 16);

	    	  }  else {
	    			gui.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/abilities/hotbar.png"));
	    		  gui.drawTexturedModalRect(posX - posX + 2, i * 22, 256, 256, 23, 23);

	    	  }
	    	  
	    	  
	      }
	int barLength = (int) (stats.getAura() * (125 / stats.getAuraCapacity()));
	gui.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/abilities/nenbar.png"));
	gui.drawTexturedModalRect(posX - posX + 60, 0, 100, 100, 125, 15);
	gui.drawTexturedModalRect(posX - posX + 60, 0, 256, 256, barLength, 15);
Minecraft.getMinecraft().fontRenderer.drawString(stats.getAura()+" / "+stats.getAuraCapacity(), posX - posX + 60, posY - posY + 17, 125);
	System.out.println("aura" + stats.getAura() + ",auracap" + stats.getAuraCapacity() + ",barLength" + barLength );

	
	}
	
	GlStateManager.popMatrix();
	

	}
}
