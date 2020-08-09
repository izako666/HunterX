package com.izako.hunterx.renderers;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;

public abstract class ModRenderTypes extends RenderType{

	   public ModRenderTypes(String nameIn, VertexFormat formatIn, int drawModeIn, int bufferSizeIn, boolean useDelegateIn,
			boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
		super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
	}

		 //   private static final  RenderType.State rendertype$state = RenderType.State.getBuilder().texture(NO_TEXTURE).cull(CULL_DISABLED).lightmap(LIGHTMAP_ENABLED).build(true);
		   // private static final   RenderType TRANSLUCENT_ENTITY_NOTEX =  RenderType.makeType("entity_translucent_notexture", DefaultVertexFormats.ENTITY, 7, 256, true, true, rendertype$state);
   private static final	RenderType.State TRANSLUCENT_ENTITY_STATE = RenderType.State.getBuilder().transparency(TRANSLUCENT_TRANSPARENCY).texture(NO_TEXTURE).cull(CULL_ENABLED).lightmap(LIGHTMAP_DISABLED).build(true);
	private static final    RenderType TRANSLUCENT_ENTITY_NOTEX =  RenderType.makeType("entity_translucent_notexture", DefaultVertexFormats.ENTITY, 7, 256, true, true, TRANSLUCENT_ENTITY_STATE);

		    public static RenderType getTranslucentEntity() {
			   return TRANSLUCENT_ENTITY_NOTEX;

		    }

}
