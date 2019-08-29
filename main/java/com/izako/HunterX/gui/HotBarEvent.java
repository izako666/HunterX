package com.izako.HunterX.gui;

import com.izako.HunterX.izapi.RenderHelper;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;
import com.izako.HunterX.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HotBarEvent {

	@SubscribeEvent
	public void renderEvent(RenderGameOverlayEvent e) {
		
	HotBarGui gui =	new HotBarGui(Minecraft.getMinecraft());
	IEntityStats stats = gui.mc.player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
	int posX = e.getResolution().getScaledWidth();
	int posY = e.getResolution().getScaledHeight();

	
	gui.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/abilities/slots.png"));
	 
	for(int i = 0; i < 9; i++ ) {
	    	  
	    	  if(stats.getAbilityNonNull(i) != null) {
	    		  gui.drawTexturedModalRect(posX - posX + 2, i * 22, 256, 256, 23, 23);
	    		  RenderHelper.drawAbilityIcon(stats.getAbilityNonNull(i).getID(),  posX - posX + 6,(i * 22) + 4, 16, 16);

	    	  }  else {
	    			gui.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/abilities/slots.png"));
	    		  gui.drawTexturedModalRect(posX - posX + 2, i * 22, 256, 256, 23, 23);

	    	  }
	      }
	}
}
