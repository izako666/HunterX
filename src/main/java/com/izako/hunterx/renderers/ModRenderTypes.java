package com.izako.hunterx.renderers;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;

public abstract class ModRenderTypes extends RenderType{

	   public ModRenderTypes(String nameIn, VertexFormat formatIn, int drawModeIn, int bufferSizeIn, boolean useDelegateIn,
			boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
		super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
	}

		    private static final  RenderType.State rendertype$state = RenderType.State.getBuilder().texture(NO_TEXTURE).transparency(RenderType.TRANSLUCENT_TRANSPARENCY).diffuseLighting(DIFFUSE_LIGHTING_ENABLED).alpha(DEFAULT_ALPHA).cull(CULL_DISABLED).lightmap(LIGHTMAP_ENABLED).overlay(OVERLAY_ENABLED).build(true);
		    private static final   RenderType TRANSLUCENT_ENTITY_NOTEX =  RenderType.makeType("entity_translucent_notexture", DefaultVertexFormats.ENTITY, 7, 256, true, true, rendertype$state);
		   
		    public static RenderType getTranslucentEntity() {
		    	return TRANSLUCENT_ENTITY_NOTEX;
		    }

}
