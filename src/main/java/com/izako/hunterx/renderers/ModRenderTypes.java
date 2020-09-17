package com.izako.hunterx.renderers;

import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;

public abstract class ModRenderTypes extends RenderType {

	public ModRenderTypes(String nameIn, VertexFormat formatIn, int drawModeIn, int bufferSizeIn, boolean useDelegateIn,
			boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
		super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
	}

	// private static final RenderType.State rendertype$state =
	// RenderType.State.getBuilder().texture(NO_TEXTURE).cull(CULL_DISABLED).lightmap(LIGHTMAP_ENABLED).build(true);
	// private static final RenderType TRANSLUCENT_ENTITY_NOTEX =
	// RenderType.makeType("entity_translucent_notexture",
	// DefaultVertexFormats.ENTITY, 7, 256, true, true, rendertype$state);
	private static final RenderType.State TRANSLUCENT_ENTITY_STATE = RenderType.State.getBuilder()
			.transparency(TRANSLUCENT_TRANSPARENCY).texture(NO_TEXTURE).cull(CULL_ENABLED).lightmap(LIGHTMAP_DISABLED)
			.build(true);
	private static final RenderType TRANSLUCENT_ENTITY_NOTEX = RenderType.makeType("entity_translucent_notexture",
			DefaultVertexFormats.ENTITY, 7, 256, true, true, TRANSLUCENT_ENTITY_STATE);

	public static RenderType getTranslucentEntity() {
		return TRANSLUCENT_ENTITY_NOTEX;

	}

	public static RenderType getTranslucentItem(ResourceLocation loc) {
		final RenderType.State TRANSLUCENT_ITEM_STATE = RenderType.State.getBuilder()
				.transparency(TransparencyState.TRANSLUCENT_TRANSPARENCY)
				.texture(new RenderState.TextureState(loc, false, false)).texturing(RenderState.OUTLINE_TEXTURING)
				.cull(CULL_DISABLED).alpha(AlphaState.DEFAULT_ALPHA).lightmap(LIGHTMAP_ENABLED).writeMask(COLOR_WRITE)
				.build(true);
		final RenderType TRANSLUCENT_ITEM_NOTEX = RenderType.makeType("entity_translucent_notexture",
				DefaultVertexFormats.ENTITY, 7, 256, true, true, TRANSLUCENT_ENTITY_STATE);
		return TRANSLUCENT_ITEM_NOTEX;
	}

	   public static RenderType getCustomEyes(ResourceLocation locationIn) {
		      RenderState.TextureState renderstate$texturestate = new RenderState.TextureState(locationIn, false, false);
		      return makeType("hntrxeyes", DefaultVertexFormats.ENTITY, 7, 256, false, true, RenderType.State.getBuilder().texture(renderstate$texturestate).transparency(TRANSLUCENT_TRANSPARENCY).writeMask(COLOR_WRITE).fog(BLACK_FOG).build(false));
		   }

}
