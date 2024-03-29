package com.izako.hunterx.izapi;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.gui.SequencedString;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.ActivateAbilityPacket;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;

public class Helper {


	public static final List<NenType> ENHANCER = new ArrayList<>(Arrays.asList(NenType.EMITTER, NenType.TRANSMUTER,
			NenType.MANIPULATOR, NenType.CONJURER, NenType.SPECIALIST));
	public static final List<NenType> EMITTER = new ArrayList<>(Arrays.asList(NenType.ENHANCER, NenType.MANIPULATOR,
			NenType.SPECIALIST, NenType.TRANSMUTER, NenType.CONJURER));
	public static final List<NenType> TRANSMUTER = new ArrayList<>(Arrays.asList(NenType.ENHANCER, NenType.CONJURER,
			NenType.EMITTER, NenType.SPECIALIST, NenType.MANIPULATOR));
	public static final List<NenType> MANIPULATOR = new ArrayList<>(
			Arrays.asList(NenType.EMITTER, NenType.SPECIALIST, NenType.ENHANCER, NenType.CONJURER, NenType.TRANSMUTER));
	public static final List<NenType> CONJURER = new ArrayList<>(Arrays.asList(NenType.SPECIALIST, NenType.TRANSMUTER,
			NenType.MANIPULATOR, NenType.ENHANCER, NenType.EMITTER));

	public static int getCurrentQuest(Quest[] q, PlayerEntity p) {

		IHunterData data = HunterDataCapability.get(p);
		for (int i = 0; i < q.length; i++) {
			if (!data.hasQuest(q[i]) || !data.getQuest(q[i]).isFinished()) {
				return i;
			}
		}
		return -1;
	}

	public static Ability addSlotAbility(Ability abl, LivingEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		Ability ability = null;
		if (p instanceof PlayerEntity) {
			for (int i = 0; i < data.getSlotAbilities().length; i++) {
				if (data.getSlotAbilities()[i] == null) {
					data.putAbilityInSlot(abl, i);
					ability = abl;
					break;
				}
			}
			if (ability == null) {
				data.putAbilityInSlot(abl, 0);
				ability = abl;
			}
		} else {
			data.giveAbility(abl);
			ability = abl;
		}
		return ability;
	}

	public static SequencedString getNewSqStringInstance(SequencedString str) {
		SequencedString newstr = new SequencedString(str.string, str.maxLength, str.maxTicks);
		newstr.color = str.color;
		newstr.delayTicks = str.delayTicks;

		return newstr;
	}

	public static SequencedString[] getNewSqStringInstance(SequencedString[] sqstr) {
		SequencedString[] newstr = new SequencedString[sqstr.length];
		for (int i = 0; i < sqstr.length; i++) {
			SequencedString tempsq = new SequencedString(sqstr[i].string, sqstr[i].maxLength, sqstr[i].maxTicks);
			tempsq.color = sqstr[i].color;
			tempsq.delayTicks = sqstr[i].delayTicks;
			newstr[i] = tempsq;
		}
		return newstr;
	}

	@OnlyIn(Dist.CLIENT)
	public static void renderItem(ItemStack itemStackIn, ItemCameraTransforms.TransformType transformTypeIn,
			boolean leftHand, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn,
			int combinedOverlayIn, IBakedModel modelIn, RenderType renderType, Color color) {
		if (!itemStackIn.isEmpty()) {
			matrixStackIn.push();
			boolean flag = transformTypeIn == ItemCameraTransforms.TransformType.GUI;
			boolean flag1 = flag || transformTypeIn == ItemCameraTransforms.TransformType.GROUND
					|| transformTypeIn == ItemCameraTransforms.TransformType.FIXED;
			if (itemStackIn.getItem() == Items.TRIDENT && flag1) {
				modelIn = Minecraft.getInstance().getItemRenderer().getItemModelMesher().getModelManager()
						.getModel(new ModelResourceLocation("minecraft:trident#inventory"));
			}

			modelIn = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(matrixStackIn, modelIn,
					transformTypeIn, leftHand);
			matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
			if (!modelIn.isBuiltInRenderer() && (itemStackIn.getItem() != Items.TRIDENT || flag1)) {
				RenderType rendertype = renderType;
				RenderType rendertype1;
				if (flag && Objects.equals(rendertype, Atlases.getTranslucentBlockType())) {
					rendertype1 = Atlases.getTranslucentCullBlockType();
				} else {
					rendertype1 = rendertype;
				}

				IVertexBuilder ivertexbuilder = ItemRenderer.getBuffer(bufferIn, rendertype1, true,
						itemStackIn.hasEffect());
				Helper.renderItemModel(modelIn, itemStackIn, combinedLightIn, combinedOverlayIn, matrixStackIn,
						ivertexbuilder, color);
			} else {
				itemStackIn.getItem().getItemStackTileEntityRenderer().render(itemStackIn, matrixStackIn, bufferIn,
						combinedLightIn, combinedOverlayIn);
			}

			matrixStackIn.pop();
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static void renderItemOnPlayer(LayerRenderer render, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn,
			int packedLightIn, PlayerEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, RenderType type, Color color) {
		boolean flag = entitylivingbaseIn.getPrimaryHand() == HandSide.RIGHT;
		ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
		ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand()
				: entitylivingbaseIn.getHeldItemOffhand();
		if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
			matrixStackIn.push();
			if (render.getEntityModel().isChild) {
				float f = 0.5F;
				matrixStackIn.translate(0.0D, 0.75D, 0.0D);
				matrixStackIn.scale(0.5F, 0.5F, 0.5F);
			}

			Helper.renderItem3d(render, entitylivingbaseIn, itemstack1,
					ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT, matrixStackIn, bufferIn,
					packedLightIn, type, color);
			Helper.renderItem3d(render, entitylivingbaseIn, itemstack,
					ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT, matrixStackIn, bufferIn,
					packedLightIn, type, color);
			matrixStackIn.pop();
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static void renderItem3d(LayerRenderer render, LivingEntity p_229135_1_, ItemStack itemStack,
			ItemCameraTransforms.TransformType p_229135_3_, HandSide p_229135_4_, MatrixStack p_229135_5_,
			IRenderTypeBuffer p_229135_6_, int p_229135_7_, RenderType type, Color color) {
		if (!itemStack.isEmpty()) {
			p_229135_5_.push();
			((IHasArm) render.getEntityModel()).translateHand(p_229135_4_, p_229135_5_);
			p_229135_5_.rotate(Vector3f.XP.rotationDegrees(-90.0F));
			p_229135_5_.rotate(Vector3f.YP.rotationDegrees(180.0F));
			boolean flag = p_229135_4_ == HandSide.LEFT;
			p_229135_5_.translate((double) ((float) (flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);
			IBakedModel ibakedmodel = Minecraft.getInstance().getItemRenderer().getItemModelWithOverrides(itemStack,
					p_229135_1_.world, p_229135_1_);
			Helper.renderItem(itemStack, p_229135_3_, p_229135_4_ == HandSide.LEFT, p_229135_5_, p_229135_6_,
					p_229135_7_, OverlayTexture.NO_OVERLAY, ibakedmodel, type, color);

			p_229135_5_.pop();
		}
	}

	private static void renderItemModel(IBakedModel modelIn, ItemStack stack, int combinedLightIn,
			int combinedOverlayIn, MatrixStack matrixStackIn, IVertexBuilder bufferIn, Color color) {
		Random random = new Random();
		long i = 42L;

		for (Direction direction : Direction.values()) {
			random.setSeed(42L);
			Helper.renderQuads(matrixStackIn, bufferIn, modelIn.getQuads((BlockState) null, direction, random), stack,
					combinedLightIn, combinedOverlayIn, color);
		}

		random.setSeed(42L);
		Helper.renderQuads(matrixStackIn, bufferIn, modelIn.getQuads((BlockState) null, (Direction) null, random),
				stack, combinedLightIn, combinedOverlayIn, color);
	}

	public static void renderQuads(MatrixStack matrixStackIn, IVertexBuilder bufferIn, List<BakedQuad> quadsIn,
			ItemStack itemStackIn, int combinedLightIn, int combinedOverlayIn, Color color) {
		MatrixStack.Entry matrixstack$entry = matrixStackIn.getLast();

		for (BakedQuad bakedquad : quadsIn) {

			float f = (float) (color.getRed()) / 255;
			float f1 = (float) (color.getGreen()) / 255;
			float f2 = (float) (color.getBlue()) / 255;
			float f3 = (float) (color.getAlpha()) / 255;
			bufferIn.addVertexData(matrixstack$entry, bakedquad, f, f1, f2, f3, combinedLightIn, combinedOverlayIn);
		}
	}

	public static boolean hasActiveAbility(IAbilityData data) {

		for (int i = 0; i < data.getAbilities().size(); i++) {
			Ability a = data.getAbilities().get(i);
			if (a.isActive()) {
				return true;
			}
		}
		for (int i = 0; i < data.getSlotAbilities().length; i++) {
			Ability a = data.getSlotAbilities()[i];
			if (a != null && a.isActive()) {
				return true;
			}
		}

		return false;
	}

	/*
	 * specialist is impossible 0 and 1 is enhancer 2 and 3 is emitter 4 and 5 is
	 * transmuter 6 and 7 is manipulator 8 and 9 is conjurer
	 * 
	 */
	public static NenType getAffinity(UUID uuid) {
		String[] bits = ("" + uuid.getMostSignificantBits()).split("");
		int sum = 0;
		for (String bit : bits) {
			if (bit.equalsIgnoreCase("-"))
				continue;
			sum += Integer.parseInt(bit);
		}
		sum = MathHelper.clamp(sum % 9, 0, 9);

		switch (sum) {

		case 0:
			return NenType.ENHANCER;
		case 1:
			return NenType.ENHANCER;
		case 2:
			return NenType.EMITTER;
		case 3:
			return NenType.EMITTER;
		case 4:
			return NenType.TRANSMUTER;
		case 5:
			return NenType.TRANSMUTER;
		case 6:
			return NenType.MANIPULATOR;
		case 7:
			return NenType.MANIPULATOR;
		case 8:
			return NenType.CONJURER;
		case 9:
			return NenType.CONJURER;
		default:
			return NenType.UNKNOWN;
		}
	}

	public static void drawIMG(ResourceLocation rs, int x, int y, int u, int v, int width, int height, float zLevel,
			float uWidth, float uHeight) {
		final float uScale = 1f / 0x100;
		final float vScale = 1f / 0x100;
		Minecraft.getInstance().getTextureManager().bindTexture(rs);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder wr = tessellator.getBuffer();
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		wr.pos(x, y + height, zLevel).tex(u * uScale, ((v + uHeight) * vScale)).endVertex();
		wr.pos(x + width, y + height, zLevel).tex((u + uWidth) * uScale, ((v + uHeight) * vScale)).endVertex();
		wr.pos(x + width, y, zLevel).tex((u + uWidth) * uScale, (v * vScale)).endVertex();
		wr.pos(x, y, zLevel).tex(u * uScale, (v * vScale)).endVertex();
		tessellator.draw();

	}

	public static void drawAbilityIMG(ResourceLocation rs, int x, int y, int width, int height, float uMin, float vMin,
			float uMax, float vMax) {
		Minecraft.getInstance().getTextureManager().bindTexture(rs);
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x, y + height, 1).tex(uMin, vMax).endVertex();
		bufferbuilder.pos(x + width, y + height, 1).tex(uMax, vMax).endVertex();
		bufferbuilder.pos(x + width, y, 1).tex(uMax, vMin).endVertex();
		bufferbuilder.pos(x, y, 1).tex(uMin, vMin).endVertex();
		Tessellator.getInstance().draw();
	}

	public static double fromRangeToRange(double oldMin, double oldMax, double min, double max, double oldValue) {

		double newValue = (((oldValue - oldMin) * (max - min)) / (oldMax - oldMin)) + min;
		return newValue;
	}

	public static float getTrueValue(float value, Ability abl, LivingEntity p) {
		double trainablePowerScale = 1f;
		if (abl instanceof ITrainable) {
			trainablePowerScale = abl.getCurrentPowerScale();
		}
		IAbilityData data = AbilityDataCapability.get(p);
		double percentage = Helper.getPercentageBetweenTypes(data.getNenType(), abl.props.nenType);

		float trueValue = (float) (value * abl.getStrength() * trainablePowerScale * percentage);
		return trueValue;
	}

	public static double getPercentageBetweenTypes(NenType type1, NenType type2) {
		List<NenType> reference = new ArrayList<>();
		if (type1 == NenType.ENHANCER) {
			reference = ENHANCER;
		} else if (type1 == NenType.EMITTER) {
			reference = EMITTER;
		} else if (type1 == NenType.TRANSMUTER) {
			reference = TRANSMUTER;
		} else if (type1 == NenType.CONJURER) {
			reference = CONJURER;
		} else if (type1 == NenType.MANIPULATOR) {
			reference = MANIPULATOR;
		} else {
			return 100;
		}

		int index = reference.indexOf(type2);

		if (type2 == NenType.UNKNOWN) {
			return 1;
		}
		if (type1 == type2) {
			return 1;
		}
		if (index < 2) {
			return 0.8;
		} else if (index < 4) {
			return 0.6;
		} else {
			return 0.4;
		}

	}

	public static float getRotatedX(float radius, float yaw) {
		return (float) (radius * (Math.cos(Math.toRadians(yaw))));
	}

	public static float getRotatedZ(float radius, float yaw) {
		return (float) (radius * (Math.sin(Math.toRadians(yaw))));
	}

	public static float toCtrClockwise(float val) {
		float trueVal = val;
		if (val < 0) {
			trueVal = 360 - val;
		}
		return trueVal + 180;
	}

	public static boolean consumeAura(float aura, LivingEntity p, Ability abl) {

		IAbilityData data = AbilityDataCapability.get(p);
		if (data.getCurrentNen() >= aura) {
			data.setCurrentNen((int) (data.getCurrentNen() - aura));
			return true;
		} else {
			Helper.endAbilitySafe(p, abl);
			return false;
		}
	}
	
	public static void endAbilitySafe(LivingEntity user, Ability abl) {
		
		if(!user.world.isRemote()) {
			IAbilityData data = AbilityDataCapability.get(user);
			abl.endAbility(user);
			if(user instanceof ServerPlayerEntity) {
				ServerPlayerEntity p = (ServerPlayerEntity) user;
			PacketHandler.INSTANCE.sendTo(new ActivateAbilityPacket(abl,false,user,data.getCurrentNen()), p.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
			}
		} else {
			abl.endAbility(user);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	  public static EntityRayTraceResult getMouseOver(float partialTicks, float distance) {
		  Minecraft mc = Minecraft.getInstance();
	      Entity entity = mc.getRenderViewEntity();
	      if (entity != null) {
	         if (mc.world != null) {
	            mc.getProfiler().startSection("pick");
	            mc.pointedEntity = null;
	            double d0 = (double)mc.playerController.getBlockReachDistance();
	            mc.objectMouseOver = entity.pick(d0, partialTicks, false);
	            Vec3d vec3d = entity.getEyePosition(partialTicks);
	            boolean flag = false;
	            int i = 3;
	            double d1 = d0;
	          
	            flag = true;
	            d0 = distance;
	            d1 = distance;
	            d1 = d1 * d1;
	            if (mc.objectMouseOver != null) {
	               d1 = mc.objectMouseOver.getHitVec().squareDistanceTo(vec3d);
	            }

	            Vec3d vec3d1 = entity.getLook(1.0F);
	            Vec3d vec3d2 = vec3d.add(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
	            float f = 1.0F;
	            AxisAlignedBB axisalignedbb = entity.getBoundingBox().expand(vec3d1.scale(d0)).grow(1.0D, 1.0D, 1.0D);
	            EntityRayTraceResult entityraytraceresult = ProjectileHelper.rayTraceEntities(entity, vec3d, vec3d2, axisalignedbb, (p_215312_0_) -> {
	               return !p_215312_0_.isSpectator() && p_215312_0_.canBeCollidedWith();
	            }, d1);
	            if (entityraytraceresult != null) {
	               Entity entity1 = entityraytraceresult.getEntity();
	               Vec3d vec3d3 = entityraytraceresult.getHitVec();
	               double d2 = vec3d.squareDistanceTo(vec3d3);
	               if (flag && d2 > 9.0D) {
	                  mc.objectMouseOver = BlockRayTraceResult.createMiss(vec3d3, Direction.getFacingFromVector(vec3d1.x, vec3d1.y, vec3d1.z), new BlockPos(vec3d3));
	               } else if (d2 < d1 || mc.objectMouseOver == null) {
	                  mc.objectMouseOver = entityraytraceresult;
	                  if (entity1 instanceof LivingEntity || entity1 instanceof ItemFrameEntity) {
	                     mc.pointedEntity = entity1;
	                  }
	               }
	               return entityraytraceresult;
	            }

	            mc.getProfiler().endSection();
	         }
	      }
	      return new EntityRayTraceResult(null, mc.player.getPositionVec());

	   }
	
	   @Nullable
	   public static EntityRayTraceResult rayTraceEntities(Entity shooter, Vec3d startVec, Vec3d endVec, AxisAlignedBB boundingBox, Predicate<Entity> filter, double distance) {
	      World world = shooter.world;
	      double d0 = distance;
	      Entity entity = null;
	      Vec3d vec3d = null;

	      for(Entity entity1 : world.getEntitiesInAABBexcluding(shooter, boundingBox, filter)) {
	         AxisAlignedBB axisalignedbb = entity1.getBoundingBox().grow((double)entity1.getCollisionBorderSize());
	         Optional<Vec3d> optional = axisalignedbb.rayTrace(startVec, endVec);
	         if (axisalignedbb.contains(startVec)) {
	            if (d0 >= 0.0D) {
	               entity = entity1;
	               vec3d = optional.orElse(startVec);
	               d0 = 0.0D;
	            }
	         } else if (optional.isPresent()) {
	            Vec3d vec3d1 = optional.get();
	            double d1 = startVec.squareDistanceTo(vec3d1);
	            if (d1 < d0 || d0 == 0.0D) {
	               if (entity1.getLowestRidingEntity() == shooter.getLowestRidingEntity() && !entity1.canRiderInteract()) {
	                  if (d0 == 0.0D) {
	                     entity = entity1;
	                     vec3d = vec3d1;
	                  }
	               } else {
	                  entity = entity1;
	                  vec3d = vec3d1;
	                  d0 = d1;
	               }
	            }
	         }
	      }

	      return entity == null ? null : new EntityRayTraceResult(entity, vec3d);
	   }

	   public static Optional<Entity> getTargetEntity(@Nullable Entity entityIn, int distance) {
		      if (entityIn == null) {
		         return Optional.empty();
		      } else {
		         Vec3d vec3d = entityIn.getEyePosition(1.0F);
		         Vec3d vec3d1 = entityIn.getLook(1.0F).scale((double)distance);
		         Vec3d vec3d2 = vec3d.add(vec3d1);
		         AxisAlignedBB axisalignedbb = entityIn.getBoundingBox().expand(vec3d1).grow(1.0D);
		         int i = distance * distance;
		         Predicate<Entity> predicate = (p_217727_0_) -> {
		            return !p_217727_0_.isSpectator() && p_217727_0_.canBeCollidedWith();
		         };
		         EntityRayTraceResult entityraytraceresult = ProjectileHelper.rayTraceEntities(entityIn, vec3d, vec3d2, axisalignedbb, predicate, (double)i);
		         if (entityraytraceresult == null) {
		            return Optional.empty();
		         } else {
		            return vec3d.squareDistanceTo(entityraytraceresult.getHitVec()) > (double)i ? Optional.empty() : Optional.of(entityraytraceresult.getEntity());
		         }
		      }
		   }

}
