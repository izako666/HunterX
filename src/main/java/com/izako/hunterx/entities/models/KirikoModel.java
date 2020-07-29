package com.izako.hunterx.entities.models;
import com.izako.hunterx.entities.KirikoEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class KirikoModel<KirikoEntity> extends EntityModel<Entity> {
	private final ModelRenderer Rleg;
	private final ModelRenderer Lleg;
	private final ModelRenderer rarm;
	private final ModelRenderer larm;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public KirikoModel() {
		textureWidth = 128;
		textureHeight = 128;

		Rleg = new ModelRenderer(this);
		Rleg.setRotationPoint(-1.85F, 2.175F, 4.25F);
		setRotationAngle(Rleg, -0.7854F, 0.8727F, 0.0F);
		Rleg.setTextureOffset(0, 91).addBox(0.4033F, 13.8302F, 4.3613F, 0.0F, 0.0F, 4.0F, 0.0F, false);
		Rleg.setTextureOffset(23, 57).addBox(0.3685F, 13.7562F, 8.3749F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Rleg.setTextureOffset(0, 36).addBox(0.3685F, 14.039F, 8.3749F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Rleg.setTextureOffset(99, 99).addBox(0.5685F, 14.039F, 8.3749F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Rleg.setTextureOffset(93, 99).addBox(0.1685F, 14.039F, 8.3749F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Rleg.setTextureOffset(98, 57).addBox(0.1685F, 13.6147F, 8.3749F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Rleg.setTextureOffset(10, 95).addBox(0.5685F, 13.6147F, 8.3749F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Rleg.setTextureOffset(57, 89).addBox(-0.0557F, 13.2329F, -1.0516F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		Rleg.setTextureOffset(94, 18).addBox(0.1443F, 13.7986F, 1.6354F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Rleg.setTextureOffset(92, 41).addBox(0.1443F, 13.4451F, 1.7061F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Rleg.setTextureOffset(91, 81).addBox(0.5443F, 13.4451F, 1.7061F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Rleg.setTextureOffset(27, 46).addBox(0.1443F, 14.0815F, 1.6354F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Rleg.setTextureOffset(91, 86).addBox(0.1443F, 13.7279F, 1.7061F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Rleg.setTextureOffset(62, 94).addBox(0.5443F, 13.7986F, 1.6354F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Rleg.setTextureOffset(25, 93).addBox(0.5443F, 14.0815F, 1.6354F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Rleg.setTextureOffset(74, 92).addBox(0.5443F, 13.7279F, 1.7061F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Rleg.setTextureOffset(94, 32).addBox(-0.0557F, 13.5865F, -1.9708F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Rleg.setTextureOffset(73, 73).addBox(-0.5557F, 5.2723F, -2.1265F, 3.0F, 9.0F, 3.0F, 0.0F, false);
		Rleg.setTextureOffset(24, 67).addBox(-1.0861F, 3.4098F, -2.7389F, 4.0F, 7.0F, 4.0F, 0.0F, false);
		Rleg.setTextureOffset(53, 76).addBox(-0.5557F, 6.9794F, -2.4193F, 3.0F, 8.0F, 3.0F, 0.0F, false);
		Rleg.setTextureOffset(32, 89).addBox(-0.5785F, 12.7506F, 9.7053F, 3.0F, 6.0F, 1.0F, 0.0F, false);
		Rleg.setTextureOffset(50, 92).addBox(-0.1785F, 13.4577F, 9.8467F, 2.0F, 6.0F, 1.0F, 0.0F, false);
		Rleg.setTextureOffset(49, 59).addBox(-0.9827F, 15.1137F, 9.9684F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		Rleg.setTextureOffset(47, 17).addBox(-0.9827F, 19.1137F, 9.9684F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Rleg.setTextureOffset(40, 2).addBox(0.4315F, 19.1137F, 9.9684F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Rleg.setTextureOffset(14, 99).addBox(0.4315F, 15.1137F, 9.9684F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		Rleg.setTextureOffset(40, 0).addBox(1.9872F, 19.1137F, 9.9684F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Rleg.setTextureOffset(10, 99).addBox(1.9872F, 15.1137F, 9.9684F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		Rleg.setTextureOffset(91, 8).addBox(-1.0128F, 15.1137F, 9.9684F, 4.0F, 3.0F, 1.0F, 0.0F, false);

		Lleg = new ModelRenderer(this);
		Lleg.setRotationPoint(1.85F, 2.175F, 4.25F);
		setRotationAngle(Lleg, -0.7854F, -0.8727F, 0.0F);
		Lleg.setTextureOffset(0, 91).addBox(-1.2033F, 13.8302F, 4.3613F, 0.0F, 0.0F, 4.0F, 0.0F, true);
		Lleg.setTextureOffset(23, 57).addBox(-1.3685F, 13.7562F, 8.3749F, 1.0F, 1.0F, 2.0F, 0.0F, true);
		Lleg.setTextureOffset(0, 36).addBox(-1.3685F, 14.039F, 8.3749F, 1.0F, 1.0F, 2.0F, 0.0F, true);
		Lleg.setTextureOffset(99, 99).addBox(-1.5685F, 14.039F, 8.3749F, 1.0F, 1.0F, 2.0F, 0.0F, true);
		Lleg.setTextureOffset(93, 99).addBox(-1.1685F, 14.039F, 8.3749F, 1.0F, 1.0F, 2.0F, 0.0F, true);
		Lleg.setTextureOffset(98, 57).addBox(-1.1685F, 13.6147F, 8.3749F, 1.0F, 1.0F, 2.0F, 0.0F, true);
		Lleg.setTextureOffset(10, 95).addBox(-1.5685F, 13.6147F, 8.3749F, 1.0F, 1.0F, 2.0F, 0.0F, true);
		Lleg.setTextureOffset(57, 89).addBox(-1.9443F, 13.2329F, -1.0516F, 2.0F, 2.0F, 3.0F, 0.0F, true);
		Lleg.setTextureOffset(94, 18).addBox(-1.1443F, 13.7986F, 1.6354F, 1.0F, 1.0F, 3.0F, 0.0F, true);
		Lleg.setTextureOffset(92, 41).addBox(-1.1443F, 13.4451F, 1.7061F, 1.0F, 1.0F, 3.0F, 0.0F, true);
		Lleg.setTextureOffset(91, 81).addBox(-1.5443F, 13.4451F, 1.7061F, 1.0F, 1.0F, 3.0F, 0.0F, true);
		Lleg.setTextureOffset(27, 46).addBox(-1.1443F, 14.0815F, 1.6354F, 1.0F, 1.0F, 3.0F, 0.0F, true);
		Lleg.setTextureOffset(91, 86).addBox(-1.1443F, 13.7279F, 1.7061F, 1.0F, 1.0F, 3.0F, 0.0F, true);
		Lleg.setTextureOffset(62, 94).addBox(-1.5443F, 13.7986F, 1.6354F, 1.0F, 1.0F, 3.0F, 0.0F, true);
		Lleg.setTextureOffset(25, 93).addBox(-1.5443F, 14.0815F, 1.6354F, 1.0F, 1.0F, 3.0F, 0.0F, true);
		Lleg.setTextureOffset(74, 92).addBox(-1.5443F, 13.7279F, 1.7061F, 1.0F, 1.0F, 3.0F, 0.0F, true);
		Lleg.setTextureOffset(94, 32).addBox(-1.9443F, 13.5865F, -1.9708F, 2.0F, 2.0F, 2.0F, 0.0F, true);
		Lleg.setTextureOffset(73, 73).addBox(-2.4443F, 5.2723F, -2.1265F, 3.0F, 9.0F, 3.0F, 0.0F, true);
		Lleg.setTextureOffset(24, 67).addBox(-2.9139F, 3.4098F, -2.7389F, 4.0F, 7.0F, 4.0F, 0.0F, true);
		Lleg.setTextureOffset(53, 76).addBox(-2.4443F, 6.9794F, -2.4193F, 3.0F, 8.0F, 3.0F, 0.0F, true);
		Lleg.setTextureOffset(32, 89).addBox(-2.4215F, 12.7506F, 9.7053F, 3.0F, 6.0F, 1.0F, 0.0F, true);
		Lleg.setTextureOffset(50, 92).addBox(-1.8215F, 13.4577F, 9.8467F, 2.0F, 6.0F, 1.0F, 0.0F, true);
		Lleg.setTextureOffset(49, 59).addBox(-0.0173F, 15.1137F, 9.9684F, 1.0F, 4.0F, 1.0F, 0.0F, true);
		Lleg.setTextureOffset(47, 17).addBox(-0.0173F, 19.1137F, 9.9684F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Lleg.setTextureOffset(40, 2).addBox(-1.4315F, 19.1137F, 9.9684F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Lleg.setTextureOffset(14, 99).addBox(-1.4315F, 15.1137F, 9.9684F, 1.0F, 4.0F, 1.0F, 0.0F, true);
		Lleg.setTextureOffset(40, 0).addBox(-2.9872F, 19.1137F, 9.9684F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Lleg.setTextureOffset(10, 99).addBox(-2.9872F, 15.1137F, 9.9684F, 1.0F, 4.0F, 1.0F, 0.0F, true);
		Lleg.setTextureOffset(91, 8).addBox(-2.9872F, 15.1137F, 9.9684F, 4.0F, 3.0F, 1.0F, 0.0F, true);

		rarm = new ModelRenderer(this);
		rarm.setRotationPoint(-10.6462F, -4.1346F, -3.3F);
		setRotationAngle(rarm, -0.0873F, 0.1745F, 0.5236F);
		rarm.setTextureOffset(12, 75).addBox(-2.1538F, -6.3654F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		rarm.setTextureOffset(12, 4).addBox(-1.9038F, -6.6154F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		rarm.setTextureOffset(54, 55).addBox(-2.4038F, -5.8654F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		rarm.setTextureOffset(12, 83).addBox(-1.9038F, -6.1154F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		rarm.setTextureOffset(59, 80).addBox(-1.9038F, -2.1154F, -0.25F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(0, 46).addBox(-1.9038F, -2.1154F, -0.75F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(0, 87).addBox(-1.4038F, -2.1154F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
		rarm.setTextureOffset(56, 81).addBox(0.3462F, 5.6346F, -1.25F, 4.0F, 1.0F, 2.0F, 0.0F, false);
		rarm.setTextureOffset(56, 80).addBox(1.3462F, 5.6346F, -1.25F, 4.0F, 1.0F, 2.0F, 0.0F, false);
		rarm.setTextureOffset(90, 90).addBox(1.0962F, 5.1346F, -1.25F, 4.0F, 1.0F, 2.0F, 0.0F, false);
		rarm.setTextureOffset(76, 62).addBox(-0.4038F, 4.8846F, -1.0F, 6.0F, 2.0F, 2.0F, 0.0F, false);
		rarm.setTextureOffset(86, 48).addBox(4.5962F, 5.3846F, -0.25F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(50, 38).addBox(4.5962F, 5.6846F, -0.25F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(87, 0).addBox(4.5962F, 5.7346F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(86, 46).addBox(4.5962F, 5.1346F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(85, 79).addBox(4.5962F, 5.3846F, -0.75F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(64, 89).addBox(9.0962F, 5.3846F, -1.15F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(98, 78).addBox(9.0962F, 5.3846F, -0.35F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(99, 5).addBox(9.0962F, 5.3846F, 0.05F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(82, 32).addBox(4.5962F, 5.6846F, -0.75F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(85, 16).addBox(9.4962F, 5.6846F, -2.05F, 2.0F, 1.0F, 4.0F, 0.0F, false);
		rarm.setTextureOffset(84, 41).addBox(9.4962F, 5.7846F, -2.05F, 2.0F, 1.0F, 4.0F, 0.0F, false);
		rarm.setTextureOffset(98, 64).addBox(11.4962F, 5.6846F, -2.05F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(0, 13).addBox(13.4962F, 5.6846F, -2.05F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(0, 23).addBox(13.4962F, 5.6846F, -0.55F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(0, 34).addBox(13.4962F, 5.6846F, 0.95F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(25, 91).addBox(11.4962F, 5.6846F, 0.95F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(73, 11).addBox(11.4962F, 5.6846F, -0.55F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(95, 26).addBox(8.4962F, 5.6846F, -0.45F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		rarm.setTextureOffset(91, 56).addBox(8.4962F, 5.6846F, -1.55F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		rarm.setTextureOffset(56, 81).addBox(7.4962F, 5.6846F, -0.85F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		rarm.setTextureOffset(41, 17).addBox(7.4962F, 5.6846F, -1.15F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		rarm.setTextureOffset(89, 21).addBox(-1.1538F, 5.6346F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(97, 73).addBox(-1.6538F, -1.1154F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(60, 80).addBox(-1.6538F, 0.1346F, -0.75F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(59, 80).addBox(-1.6538F, -2.1154F, -0.85F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(60, 80).addBox(-1.6538F, -2.1154F, -0.1F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(58, 80).addBox(-1.6538F, 1.3846F, -0.25F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		rarm.setTextureOffset(10, 6).addBox(-1.9038F, -5.8654F, -2.25F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		rarm.setTextureOffset(25, 8).addBox(-1.9038F, -5.8654F, -0.75F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		larm = new ModelRenderer(this);
		larm.setRotationPoint(10.6462F, -4.1346F, -3.3F);
		setRotationAngle(larm, -0.0873F, -0.1745F, -0.5236F);
		larm.setTextureOffset(85, 16).addBox(-11.4962F, 5.6846F, -2.05F, 2.0F, 1.0F, 4.0F, 0.0F, true);
		larm.setTextureOffset(12, 75).addBox(-1.8462F, -6.3654F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);
		larm.setTextureOffset(54, 55).addBox(-0.5962F, -5.8654F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, true);
		larm.setTextureOffset(12, 83).addBox(-1.0962F, -6.1154F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, true);
		larm.setTextureOffset(59, 80).addBox(0.9038F, -2.1154F, -0.25F, 1.0F, 4.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(0, 46).addBox(0.9038F, -2.1154F, -0.75F, 1.0F, 4.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(0, 87).addBox(-0.5962F, -2.1154F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, true);
		larm.setTextureOffset(56, 81).addBox(-4.3462F, 5.6346F, -1.25F, 4.0F, 1.0F, 2.0F, 0.0F, true);
		larm.setTextureOffset(56, 80).addBox(-5.3462F, 5.6346F, -1.25F, 4.0F, 1.0F, 2.0F, 0.0F, true);
		larm.setTextureOffset(90, 90).addBox(-5.0962F, 5.1346F, -1.25F, 4.0F, 1.0F, 2.0F, 0.0F, true);
		larm.setTextureOffset(76, 62).addBox(-5.5962F, 4.8846F, -1.0F, 6.0F, 2.0F, 2.0F, 0.0F, true);
		larm.setTextureOffset(86, 48).addBox(-10.5962F, 5.3846F, -0.25F, 6.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(50, 38).addBox(-10.5962F, 5.6846F, -0.25F, 6.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(87, 0).addBox(-10.5962F, 5.7346F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(86, 46).addBox(-10.5962F, 5.1346F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(85, 79).addBox(-10.5962F, 5.3846F, -0.75F, 6.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(64, 89).addBox(-11.0962F, 5.3846F, -1.15F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(98, 78).addBox(-11.0962F, 5.3846F, -0.35F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(99, 5).addBox(-11.0962F, 5.3846F, 0.05F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(82, 32).addBox(-10.5962F, 5.6846F, -0.75F, 6.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(17, 8).addBox(-1.0962F, -6.6154F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, true);
		larm.setTextureOffset(84, 41).addBox(-11.4962F, 5.7846F, -2.05F, 2.0F, 1.0F, 4.0F, 0.0F, true);
		larm.setTextureOffset(98, 64).addBox(-13.4962F, 5.6846F, -2.05F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(0, 13).addBox(-14.4962F, 5.6846F, -2.05F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(0, 23).addBox(-14.4962F, 5.6846F, -0.55F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(0, 34).addBox(-14.4962F, 5.6846F, 0.95F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(25, 91).addBox(-13.4962F, 5.6846F, 0.95F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(73, 11).addBox(-13.4962F, 5.6846F, -0.55F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(95, 26).addBox(-10.4962F, 5.6846F, -0.45F, 2.0F, 1.0F, 2.0F, 0.0F, true);
		larm.setTextureOffset(91, 56).addBox(-10.4962F, 5.6846F, -1.55F, 2.0F, 1.0F, 2.0F, 0.0F, true);
		larm.setTextureOffset(56, 81).addBox(-9.4962F, 5.6846F, -0.85F, 2.0F, 1.0F, 2.0F, 0.0F, true);
		larm.setTextureOffset(41, 17).addBox(-9.4962F, 5.6846F, -1.15F, 2.0F, 1.0F, 2.0F, 0.0F, true);
		larm.setTextureOffset(89, 21).addBox(-0.8462F, 5.6346F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(97, 73).addBox(-0.3462F, -1.1154F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(60, 80).addBox(-0.3462F, 0.1346F, -0.75F, 2.0F, 4.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(59, 80).addBox(-0.3462F, -2.1154F, -0.85F, 2.0F, 4.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(60, 80).addBox(-0.3462F, -2.1154F, -0.1F, 2.0F, 4.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(58, 80).addBox(-0.3462F, 1.3846F, -0.25F, 2.0F, 4.0F, 1.0F, 0.0F, true);
		larm.setTextureOffset(18, 11).addBox(-1.0962F, -5.8654F, -2.25F, 3.0F, 3.0F, 3.0F, 0.0F, true);
		larm.setTextureOffset(16, 5).addBox(-1.0962F, -5.8654F, -0.75F, 3.0F, 3.0F, 3.0F, 0.0F, true);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 2.0F, 2.0F);
		setRotationAngle(body, 0.2618F, 0.0F, 0.0F);
		body.setTextureOffset(53, 53).addBox(-5.0F, -0.5F, -4.5F, 10.0F, 3.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(0, 65).addBox(-4.0F, -5.45F, -3.3F, 8.0F, 6.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(40, 30).addBox(-9.6F, -13.2962F, -4.1566F, 19.0F, 4.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-8.6F, -15.05F, -3.8F, 17.0F, 7.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(0, 34).addBox(-7.6F, -15.05F, -3.3F, 15.0F, 6.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(41, 7).addBox(-6.6F, -14.05F, -2.8F, 13.0F, 4.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(27, 50).addBox(-5.6F, -15.85F, -3.6F, 11.0F, 4.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(54, 62).addBox(-4.6F, -16.55F, -3.3F, 9.0F, 4.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(68, 38).addBox(-3.6F, -17.05F, -3.1F, 7.0F, 4.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(66, 0).addBox(-4.6F, -15.05F, -4.3F, 9.0F, 4.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(40, 0).addBox(-4.6F, -13.05F, -5.3F, 9.0F, 2.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(37, 23).addBox(-8.6F, -12.55F, -4.8F, 17.0F, 2.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(73, 7).addBox(-3.6F, -12.3F, -6.05F, 7.0F, 2.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(0, 13).addBox(-9.0F, -12.05F, -6.3F, 17.0F, 3.0F, 7.0F, 0.0F, false);
		body.setTextureOffset(0, 23).addBox(-7.6F, -11.45F, -5.15F, 15.0F, 4.0F, 7.0F, 0.0F, false);
		body.setTextureOffset(36, 40).addBox(-6.6F, -10.45F, -4.35F, 13.0F, 4.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(0, 46).addBox(-5.6F, -9.45F, -3.9F, 11.0F, 4.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(0, 55).addBox(-4.5F, -1.75F, -3.8F, 9.0F, 5.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(28, 59).addBox(-3.75F, 0.45F, -4.1F, 8.0F, 3.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(56, 70).addBox(-3.75F, 0.3F, -2.1F, 8.0F, 4.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(93, 12).addBox(-1.5F, 0.6F, -2.1F, 3.0F, 4.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(48, 17).addBox(-4.5F, 0.2F, -4.3F, 9.0F, 3.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(71, 47).addBox(-3.0F, 0.7F, -4.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(79, 11).addBox(-2.5F, 1.0F, -3.9F, 5.0F, 3.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(88, 51).addBox(-2.0F, 1.5F, -3.8F, 4.0F, 3.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(8, 90).addBox(-1.5F, 1.8F, -3.7F, 3.0F, 3.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(90, 93).addBox(-1.0F, 2.1F, -3.6F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-0.5F, 2.7F, -3.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.05F, -9.8667F, -6.3333F);
		setRotationAngle(head, 0.6109F, 0.0F, 0.0F);
		head.setTextureOffset(0, 15).addBox(-0.896F, -1.6791F, -1.3181F, 1.0F, 3.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(98, 48).addBox(-0.576F, -1.6791F, -0.9981F, 1.0F, 3.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(0, 25).addBox(-1.216F, -1.6791F, -0.9981F, 1.0F, 3.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(40, 91).addBox(-1.536F, -1.3591F, -1.3181F, 3.0F, 2.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-1.536F, 0.2409F, -1.7981F, 0.0F, 0.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(18, 6).addBox(-1.248F, -0.2391F, -1.7981F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(82, 73).addBox(-0.32F, -0.7831F, -2.1181F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(97, 22).addBox(-0.96F, -1.1031F, -1.6381F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(97, 22).addBox(-0.96F, -0.8123F, -1.6586F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(98, 54).addBox(-0.8F, -0.7831F, -2.1181F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(96, 85).addBox(-1.44F, -1.1031F, -1.6381F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(96, 85).addBox(-1.44F, -0.8123F, -1.6586F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(1.024F, 0.2409F, -1.7981F, 0.0F, 0.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(15, 0).addBox(-1.126F, 0.2409F, -3.0781F, 0.0F, 0.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(11, 12).addBox(-0.646F, 0.2409F, -4.1981F, 0.0F, 0.0F, 3.0F, 0.0F, false);
		head.setTextureOffset(12, 33).addBox(-0.506F, -0.1191F, -4.1981F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(10, 11).addBox(-0.576F, 0.2009F, -3.7181F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(10, 32).addBox(-0.896F, 0.4009F, -2.2781F, 1.0F, 0.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(64, 55).addBox(-0.576F, -0.4391F, -3.7181F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(14, 5).addBox(-0.45F, -0.7591F, -3.2381F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(0.254F, -0.1191F, -3.0781F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.25F, 31.5667F, 5.3333F);
		head.addChild(bone);
		bone.setTextureOffset(31, 12).addBox(-1.5824F, -34.3776F, -4.991F, 0.0F, 1.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(21, 12).addBox(-1.7802F, -35.3666F, -5.083F, 0.0F, 1.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(0, 0).addBox(-1.978F, -36.1578F, -5.083F, 0.0F, 1.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(19, 12).addBox(-2.3846F, -36.5534F, -5.175F, 1.0F, 1.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(21, 7).addBox(-3.7582F, -36.949F, -5.359F, 1.0F, 0.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(28, 12).addBox(-3.3626F, -36.7512F, -5.267F, 1.0F, 0.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(12, 9).addBox(-4.4703F, -37.1468F, -5.451F, 1.0F, 0.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(28, 11).addBox(-5.2615F, -37.3446F, -5.543F, 1.0F, 0.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(17, 6).addBox(-5.9736F, -37.5424F, -5.635F, 1.0F, 0.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(26, 21).addBox(-6.7648F, -37.6611F, -5.635F, 1.0F, 0.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(46, 42).addBox(-8.3076F, -37.7798F, -5.5163F, 2.0F, 0.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(46, 42).addBox(-8.3076F, -37.7798F, -5.7537F, 2.0F, 0.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(46, 42).addBox(-9.6922F, -37.9776F, -5.4768F, 2.0F, 0.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(46, 43).addBox(-9.6922F, -37.9776F, -5.7932F, 2.0F, 0.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(46, 43).addBox(-10.879F, -38.1754F, -5.4372F, 2.0F, 0.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(46, 42).addBox(-11.6702F, -38.3732F, -5.4372F, 2.0F, 0.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(46, 42).addBox(-10.879F, -38.1754F, -5.8328F, 2.0F, 0.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(46, 42).addBox(-11.6702F, -38.3732F, -5.8328F, 2.0F, 0.0F, 0.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-0.15F, 31.5667F, 5.3333F);
		head.addChild(bone2);
		bone2.setTextureOffset(20, 13).addBox(0.7912F, -34.3776F, -4.991F, 0.0F, 1.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(20, 10).addBox(0.989F, -35.3666F, -5.083F, 0.0F, 1.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(19, 11).addBox(1.1868F, -36.1578F, -5.083F, 0.0F, 1.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(14, 5).addBox(1.1758F, -36.5534F, -5.175F, 1.0F, 1.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(21, 11).addBox(2.1758F, -36.949F, -5.359F, 1.0F, 0.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(15, 6).addBox(1.7802F, -36.7512F, -5.267F, 1.0F, 0.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(20, 10).addBox(2.8879F, -37.1468F, -5.451F, 1.0F, 0.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(15, 8).addBox(3.6791F, -37.3446F, -5.543F, 1.0F, 0.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(23, 8).addBox(4.3912F, -37.5424F, -5.635F, 1.0F, 0.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(25, 14).addBox(5.1824F, -37.6611F, -5.635F, 1.0F, 0.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(46, 42).addBox(5.934F, -37.7798F, -5.5163F, 2.0F, 0.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(46, 43).addBox(5.934F, -37.7798F, -5.7537F, 2.0F, 0.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(46, 43).addBox(7.3186F, -37.9776F, -5.4768F, 2.0F, 0.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(46, 42).addBox(7.3186F, -37.9776F, -5.7932F, 2.0F, 0.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(46, 43).addBox(8.5054F, -38.1754F, -5.4372F, 2.0F, 0.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(46, 42).addBox(9.2966F, -38.3732F, -5.4372F, 2.0F, 0.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(46, 42).addBox(8.5054F, -38.1754F, -5.8328F, 2.0F, 0.0F, 0.0F, 0.0F, false);
		bone2.setTextureOffset(46, 43).addBox(9.2966F, -38.3732F, -5.8328F, 2.0F, 0.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		Lleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount + 5.4F;
		Rleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F  + (float) Math.PI) * 1.4F * limbSwingAmount + 5.4F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Rleg.render(matrixStack, buffer, packedLight, packedOverlay);
		Lleg.render(matrixStack, buffer, packedLight, packedOverlay);
		rarm.render(matrixStack, buffer, packedLight, packedOverlay);
		larm.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}