package com.izako.hunterx.abilities.events;

public class GenericAbilityRenderEvents {
/*
	@SubscribeEvent
	public static void renderFirstPersonHand(RenderSpecificHandEvent evt) {
		//Yeah maybe fucking later idfk kill myself please

		Minecraft mc = Minecraft.getInstance();
		AbstractClientPlayerEntity clientPlayer = mc.player;
		IAbilityData data = AbilityDataCapability.get(clientPlayer);

		for(int i = 0; i < data.getSlotAbilities().length; i++) {
			if(data.getAbilityInSlot(i) != null) {
				if(data.getAbilityInSlot(i) instanceof IHandOverlay && data.getAbilityInSlot(i).isActive()) {
				   //GenericAbilityRenderEvents.renderOverlay(evt, data, clientPlayer);
				}
			}
		}
		
	}

	private static void renderOverlay(RenderSpecificHandEvent evt, IAbilityData data, AbstractClientPlayerEntity clientPlayer) {
		Minecraft mc = Minecraft.getInstance();
		EntityRendererManager renderManager = mc.getRenderManager();
		float swingProgress = evt.getSwingProgress();
		float equippedProgress = evt.getEquipProgress();
		if(evt.getItemStack().isEmpty())
		{
		
	      boolean flag = true;
	      float f = flag ? 1.0F : -1.0F;
	      float f1 = MathHelper.sqrt(swingProgress);
	      float f2 = -0.3F * MathHelper.sin(f1 * (float)Math.PI);
	      float f3 = 0.4F * MathHelper.sin(f1 * ((float)Math.PI * 2F));
	      float f4 = -0.4F * MathHelper.sin(swingProgress * (float)Math.PI);
	      float f5 = MathHelper.sin(swingProgress * swingProgress * (float)Math.PI);
	      float f6 = MathHelper.sin(f1 * (float)Math.PI);
	      PlayerRenderer playerrenderer = mc.getRenderManager().getRenderer(clientPlayer);
	    
	      GlStateManager.enableCull();
	      
		      GlStateManager.translatef(f * (f2 + 0.64000005F), f3 + -0.6F + equippedProgress * -0.6F, f4 + -0.71999997F);
		      GlStateManager.rotatef(f * 45.0F, 0.0F, 1.0F, 0.0F);
		      GlStateManager.rotatef(f * f6 * 70.0F, 0.0F, 1.0F, 0.0F);
		      GlStateManager.rotatef(f * f5 * -20.0F, 0.0F, 0.0F, 1.0F);
		     GlStateManager.disableTexture();
		      GlStateManager.translatef(f * -1.0F, 3.6F, 3.5F);
		      GlStateManager.rotatef(f * 120.0F, 0.0F, 0.0F, 1.0F);
		      GlStateManager.rotatef(200.0F, 1.0F, 0.0F, 0.0F);
		      GlStateManager.rotatef(f * -135.0F, 0.0F, 1.0F, 0.0F);
		      GlStateManager.translatef(f * 5.6F, 0.0F, 0.0F);
		      GlStateManager.enableBlend();
		      GlStateManager.color4f(data.getAuraColor().getRed() / 255.0f, data.getAuraColor().getGreen() / 255.0f, data.getAuraColor().getBlue() / 255.0f, 0.3f);
		    	  GenericAbilityRenderEvents.renderRightArm(clientPlayer, playerrenderer);
		      

		      GlStateManager.color4f(1.0f,1.0f,1.0f,1.0f);
		      GlStateManager.enableTexture();
		      GlStateManager.disableBlend();

		}
	}
	
	
	   public static void renderRightArm(AbstractClientPlayerEntity clientPlayer, PlayerRenderer renderer) {
		      float f = 1.0F;
		      float f1 = 0.0625F;
		      PlayerModel<AbstractClientPlayerEntity> playermodel = renderer.getEntityModel();
			try {
				Method method = renderer.getClass().getDeclaredMethod("setModelVisibilities", AbstractClientPlayerEntity.class);
			      method.setAccessible(true);
			      method.invoke(renderer, clientPlayer);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		      playermodel.swingProgress = 0.0F;
		      playermodel.isSneak = false;
		      playermodel.swimAnimation = 0.0F;
		      playermodel.setRotationAngles(clientPlayer, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		      playermodel.bipedRightArm.rotateAngleX = 0.0F;
		      playermodel.bipedRightArm.render(0.0625F);
		      playermodel.bipedRightArmwear.rotateAngleX = 0.0F;
		      playermodel.bipedRightArmwear.render(0.0625F);
		   }*/

}
