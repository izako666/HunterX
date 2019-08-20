package com.izako.HunterX.entity.model;

//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class  ModelKiriko extends ModelBase {
	private final ModelRenderer Rleg;
	private final ModelRenderer Rleg2;
	private final ModelRenderer Lleg;
	private final ModelRenderer lleg2;
	private final ModelRenderer rarm;
	private final ModelRenderer larm;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public ModelKiriko() {
		textureWidth = 128;
		textureHeight = 128;

		Rleg = new ModelRenderer(this);
		Rleg.setRotationPoint(-3.85F, 4.175F, 2.25F);
		setRotationAngle(Rleg, -0.7854F, 0.8727F, 0.0F);
		Rleg.cubeList.add(new ModelBox(Rleg, 73, 73, -0.8022F, 1.8657F, -1.5483F, 3, 9, 3, 0.0F, false));
		Rleg.cubeList.add(new ModelBox(Rleg, 24, 67, -1.3326F, 0.0032F, -2.1607F, 4, 7, 4, 0.0F, false));
		Rleg.cubeList.add(new ModelBox(Rleg, 53, 76, -0.8022F, 3.5728F, -1.8412F, 3, 8, 3, 0.0F, false));

		Rleg2 = new ModelRenderer(this);
		Rleg2.setRotationPoint(0.6481F, 11.065F, -0.4454F);
		Rleg.addChild(Rleg2);
		Rleg2.cubeList.add(new ModelBox(Rleg2, 94, 32, -0.9503F, -0.8852F, -0.9472F, 2, 2, 2, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 0, 91, -0.6913F, -0.8415F, 5.3849F, 1, 1, 4, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 23, 57, -0.5261F, -0.7155F, 9.3986F, 1, 1, 2, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 0, 36, -0.5261F, -0.4326F, 9.3986F, 1, 1, 2, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 99, 99, -0.3261F, -0.4326F, 9.3986F, 1, 1, 2, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 93, 99, -0.7261F, -0.4326F, 9.3986F, 1, 1, 2, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 98, 57, -0.7261F, -0.8569F, 9.3986F, 1, 1, 2, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 10, 95, -0.3261F, -0.8569F, 9.3986F, 1, 1, 2, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 57, 89, -0.9503F, -1.2387F, -0.028F, 2, 2, 3, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 94, 18, -0.7503F, -0.673F, 2.659F, 1, 1, 3, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 92, 41, -0.7503F, -1.0266F, 2.7298F, 1, 1, 3, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 91, 81, -0.3503F, -1.0266F, 2.7298F, 1, 1, 3, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 27, 46, -0.7503F, -0.3902F, 2.659F, 1, 1, 3, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 91, 86, -0.7503F, -0.7437F, 2.7298F, 1, 1, 3, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 62, 94, -0.3503F, -0.673F, 2.659F, 1, 1, 3, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 25, 93, -0.3503F, -0.3902F, 2.659F, 1, 1, 3, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 74, 92, -0.3503F, -0.7437F, 2.7298F, 1, 1, 3, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 32, 89, -1.4731F, -1.7211F, 10.7289F, 3, 6, 1, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 50, 92, -1.0731F, -1.014F, 10.8703F, 2, 6, 1, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 49, 59, -1.8773F, 0.6421F, 10.9921F, 1, 4, 1, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 47, 17, -1.8773F, 4.6421F, 10.9921F, 1, 1, 1, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 40, 2, -0.4631F, 4.6421F, 10.9921F, 1, 1, 1, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 14, 99, -0.4631F, 0.6421F, 10.9921F, 1, 4, 1, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 40, 0, 1.0926F, 4.6421F, 10.9921F, 1, 1, 1, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 10, 99, 1.0926F, 0.6421F, 10.9921F, 1, 4, 1, 0.0F, false));
		Rleg2.cubeList.add(new ModelBox(Rleg2, 91, 8, -1.9074F, 0.6421F, 10.9921F, 4, 3, 1, 0.0F, false));

		Lleg = new ModelRenderer(this);
		Lleg.setRotationPoint(4.85F, 4.175F, 1.25F);
		setRotationAngle(Lleg, -0.7854F, -0.9599F, 0.0F);
		Lleg.cubeList.add(new ModelBox(Lleg, 73, 73, -2.0745F, 0.8695F, -0.5521F, 3, 9, 3, 0.0F, true));
		Lleg.cubeList.add(new ModelBox(Lleg, 24, 67, -2.5442F, -0.993F, -1.1645F, 4, 7, 4, 0.0F, true));
		Lleg.cubeList.add(new ModelBox(Lleg, 53, 76, -2.0745F, 2.5766F, -0.845F, 3, 8, 3, 0.0F, true));

		lleg2 = new ModelRenderer(this);
		lleg2.setRotationPoint(0.0F, 10.0F, 0.0F);
		Lleg.addChild(lleg2);
		lleg2.cubeList.add(new ModelBox(lleg2, 0, 91, -1.0336F, -0.7726F, 5.9356F, 1, 1, 4, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 23, 57, -0.9988F, -0.6466F, 9.9493F, 1, 1, 2, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 0, 36, -0.9988F, -0.3638F, 9.9493F, 1, 1, 2, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 99, 99, -1.1988F, -0.3638F, 9.9493F, 1, 1, 2, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 93, 99, -0.7988F, -0.3638F, 9.9493F, 1, 1, 2, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 98, 57, -0.7988F, -0.7881F, 9.9493F, 1, 1, 2, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 10, 95, -1.1988F, -0.7881F, 9.9493F, 1, 1, 2, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 57, 89, -1.5745F, -1.1699F, 0.5228F, 2, 2, 3, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 94, 18, -0.7745F, -0.6042F, 3.2098F, 1, 1, 3, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 92, 41, -0.7745F, -0.9577F, 3.2805F, 1, 1, 3, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 91, 81, -1.1745F, -0.9577F, 3.2805F, 1, 1, 3, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 27, 46, -0.7745F, -0.3213F, 3.2098F, 1, 1, 3, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 91, 86, -0.7745F, -0.6749F, 3.2805F, 1, 1, 3, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 62, 94, -1.1745F, -0.6042F, 3.2098F, 1, 1, 3, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 25, 93, -1.1745F, -0.3213F, 3.2098F, 1, 1, 3, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 74, 92, -1.1745F, -0.6749F, 3.2805F, 1, 1, 3, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 94, 32, -1.5745F, -0.8163F, -0.3964F, 2, 2, 2, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 32, 89, -2.0517F, -1.6522F, 11.2796F, 3, 6, 1, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 50, 92, -1.4517F, -0.9451F, 11.4211F, 2, 6, 1, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 49, 59, 0.3525F, 0.7109F, 11.5428F, 1, 4, 1, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 47, 17, 0.3525F, 4.7109F, 11.5428F, 1, 1, 1, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 40, 2, -1.0618F, 4.7109F, 11.5428F, 1, 1, 1, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 14, 99, -1.0618F, 0.7109F, 11.5428F, 1, 4, 1, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 40, 0, -2.6174F, 4.7109F, 11.5428F, 1, 1, 1, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 10, 99, -2.6174F, 0.7109F, 11.5428F, 1, 4, 1, 0.0F, true));
		lleg2.cubeList.add(new ModelBox(lleg2, 91, 8, -2.6174F, 0.7109F, 11.5428F, 4, 3, 1, 0.0F, true));

		rarm = new ModelRenderer(this);
		rarm.setRotationPoint(-10.6462F, -4.1346F, -3.3F);
		setRotationAngle(rarm, -0.0873F, 0.1745F, 0.5236F);
		rarm.cubeList.add(new ModelBox(rarm, 12, 75, -2.1538F, -6.3654F, -2.0F, 4, 4, 4, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 12, 4, -1.9038F, -6.6154F, -1.5F, 3, 4, 3, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 54, 55, -2.4038F, -5.8654F, -1.5F, 3, 3, 3, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 12, 83, -1.9038F, -6.1154F, -1.5F, 3, 4, 3, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 59, 80, -1.9038F, -2.1154F, -0.25F, 1, 4, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 0, 46, -1.9038F, -2.1154F, -0.75F, 1, 4, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 0, 87, -1.4038F, -2.1154F, -1.0F, 2, 8, 2, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 56, 81, 0.3462F, 5.6346F, -1.25F, 4, 1, 2, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 56, 80, 1.3462F, 5.6346F, -1.25F, 4, 1, 2, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 90, 90, 1.0962F, 5.1346F, -1.25F, 4, 1, 2, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 76, 62, -0.4038F, 4.8846F, -1.0F, 6, 2, 2, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 86, 48, 4.5962F, 5.3846F, -0.25F, 6, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 50, 38, 4.5962F, 5.6846F, -0.25F, 6, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 87, 0, 4.5962F, 5.7346F, -0.5F, 6, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 86, 46, 4.5962F, 5.1346F, -0.5F, 6, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 85, 79, 4.5962F, 5.3846F, -0.75F, 6, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 64, 89, 9.0962F, 5.3846F, -1.15F, 2, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 98, 78, 9.0962F, 5.3846F, -0.35F, 2, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 99, 5, 9.0962F, 5.3846F, 0.05F, 2, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 82, 32, 4.5962F, 5.6846F, -0.75F, 6, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 85, 16, 9.4962F, 5.6846F, -2.05F, 2, 1, 4, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 84, 41, 9.4962F, 5.7846F, -2.05F, 2, 1, 4, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 98, 64, 11.4962F, 5.6846F, -2.05F, 2, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 0, 13, 13.4962F, 5.6846F, -2.05F, 1, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 0, 23, 13.4962F, 5.6846F, -0.55F, 1, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 0, 34, 13.4962F, 5.6846F, 0.95F, 1, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 25, 91, 11.4962F, 5.6846F, 0.95F, 2, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 73, 11, 11.4962F, 5.6846F, -0.55F, 2, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 95, 26, 8.4962F, 5.6846F, -0.45F, 2, 1, 2, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 91, 56, 8.4962F, 5.6846F, -1.55F, 2, 1, 2, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 56, 81, 7.4962F, 5.6846F, -0.85F, 2, 1, 2, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 41, 17, 7.4962F, 5.6846F, -1.15F, 2, 1, 2, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 89, 21, -1.1538F, 5.6346F, -0.5F, 2, 1, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 97, 73, -1.6538F, -1.1154F, -0.5F, 2, 4, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 60, 80, -1.6538F, 0.1346F, -0.75F, 2, 4, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 59, 80, -1.6538F, -2.1154F, -0.85F, 2, 4, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 60, 80, -1.6538F, -2.1154F, -0.1F, 2, 4, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 58, 80, -1.6538F, 1.3846F, -0.25F, 2, 4, 1, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 10, 6, -1.9038F, -5.8654F, -2.25F, 3, 3, 3, 0.0F, false));
		rarm.cubeList.add(new ModelBox(rarm, 25, 8, -1.9038F, -5.8654F, -0.75F, 3, 3, 3, 0.0F, false));

		larm = new ModelRenderer(this);
		larm.setRotationPoint(10.6462F, -4.1346F, -3.3F);
		setRotationAngle(larm, -0.0873F, -0.1745F, -0.5236F);
		larm.cubeList.add(new ModelBox(larm, 85, 16, -11.4962F, 5.6846F, -2.05F, 2, 1, 4, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 12, 75, -1.8462F, -6.3654F, -2.0F, 4, 4, 4, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 54, 55, -0.5962F, -5.8654F, -1.5F, 3, 3, 3, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 12, 83, -1.0962F, -6.1154F, -1.5F, 3, 4, 3, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 59, 80, 0.9038F, -2.1154F, -0.25F, 1, 4, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 0, 46, 0.9038F, -2.1154F, -0.75F, 1, 4, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 0, 87, -0.5962F, -2.1154F, -1.0F, 2, 8, 2, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 56, 81, -4.3462F, 5.6346F, -1.25F, 4, 1, 2, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 56, 80, -5.3462F, 5.6346F, -1.25F, 4, 1, 2, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 90, 90, -5.0962F, 5.1346F, -1.25F, 4, 1, 2, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 76, 62, -5.5962F, 4.8846F, -1.0F, 6, 2, 2, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 86, 48, -10.5962F, 5.3846F, -0.25F, 6, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 50, 38, -10.5962F, 5.6846F, -0.25F, 6, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 87, 0, -10.5962F, 5.7346F, -0.5F, 6, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 86, 46, -10.5962F, 5.1346F, -0.5F, 6, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 85, 79, -10.5962F, 5.3846F, -0.75F, 6, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 64, 89, -11.0962F, 5.3846F, -1.15F, 2, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 98, 78, -11.0962F, 5.3846F, -0.35F, 2, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 99, 5, -11.0962F, 5.3846F, 0.05F, 2, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 82, 32, -10.5962F, 5.6846F, -0.75F, 6, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 17, 8, -1.0962F, -6.6154F, -1.5F, 3, 4, 3, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 84, 41, -11.4962F, 5.7846F, -2.05F, 2, 1, 4, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 98, 64, -13.4962F, 5.6846F, -2.05F, 2, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 0, 13, -14.4962F, 5.6846F, -2.05F, 1, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 0, 23, -14.4962F, 5.6846F, -0.55F, 1, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 0, 34, -14.4962F, 5.6846F, 0.95F, 1, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 25, 91, -13.4962F, 5.6846F, 0.95F, 2, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 73, 11, -13.4962F, 5.6846F, -0.55F, 2, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 95, 26, -10.4962F, 5.6846F, -0.45F, 2, 1, 2, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 91, 56, -10.4962F, 5.6846F, -1.55F, 2, 1, 2, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 56, 81, -9.4962F, 5.6846F, -0.85F, 2, 1, 2, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 41, 17, -9.4962F, 5.6846F, -1.15F, 2, 1, 2, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 89, 21, -0.8462F, 5.6346F, -0.5F, 2, 1, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 97, 73, -0.3462F, -1.1154F, -0.5F, 2, 4, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 60, 80, -0.3462F, 0.1346F, -0.75F, 2, 4, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 59, 80, -0.3462F, -2.1154F, -0.85F, 2, 4, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 60, 80, -0.3462F, -2.1154F, -0.1F, 2, 4, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 58, 80, -0.3462F, 1.3846F, -0.25F, 2, 4, 1, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 18, 11, -1.0962F, -5.8654F, -2.25F, 3, 3, 3, 0.0F, true));
		larm.cubeList.add(new ModelBox(larm, 16, 5, -1.0962F, -5.8654F, -0.75F, 3, 3, 3, 0.0F, true));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 2.0F, 2.0F);
		setRotationAngle(body, 0.2618F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 53, 53, -5.0F, -0.5F, -4.5F, 10, 3, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 65, -4.0F, -5.45F, -3.3F, 8, 6, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 40, 30, -9.6F, -13.2962F, -4.1566F, 19, 4, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -8.6F, -15.05F, -3.8F, 17, 7, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 34, -7.6F, -15.05F, -3.3F, 15, 6, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 7, -6.6F, -14.05F, -2.8F, 13, 4, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 27, 50, -5.6F, -15.85F, -3.6F, 11, 4, 5, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 54, 62, -4.6F, -16.55F, -3.3F, 9, 4, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 68, 38, -3.6F, -17.05F, -3.1F, 7, 4, 3, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 66, 0, -4.6F, -15.05F, -4.3F, 9, 4, 3, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 40, 0, -4.6F, -13.05F, -5.3F, 9, 2, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 37, 23, -8.6F, -12.55F, -4.8F, 17, 2, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 73, 7, -3.6F, -12.3F, -6.05F, 7, 2, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 13, -9.0F, -12.05F, -6.3F, 17, 3, 7, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 23, -7.6F, -11.45F, -5.15F, 15, 4, 7, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 36, 40, -6.6F, -10.45F, -4.35F, 13, 4, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 46, -5.6F, -9.45F, -3.9F, 11, 4, 5, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 55, -4.5F, -1.75F, -3.8F, 9, 5, 5, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 28, 59, -3.75F, 0.45F, -4.1F, 8, 3, 5, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 56, 70, -3.75F, 0.3F, -2.1F, 8, 4, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 93, 12, -1.5F, 0.6F, -2.1F, 3, 4, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 48, 17, -4.5F, 0.2F, -4.3F, 9, 3, 3, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 71, 47, -3.0F, 0.7F, -4.0F, 6, 3, 3, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 79, 11, -2.5F, 1.0F, -3.9F, 5, 3, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 88, 51, -2.0F, 1.5F, -3.8F, 4, 3, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 8, 90, -1.5F, 1.8F, -3.7F, 3, 3, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 90, 93, -1.0F, 2.1F, -3.6F, 2, 3, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -0.5F, 2.7F, -3.5F, 1, 3, 1, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.05F, -9.8667F, -6.3333F);
		setRotationAngle(head, 0.6109F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 15, -0.896F, -1.4791F, -1.3181F, 1, 3, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 98, 48, -0.576F, -1.4791F, -0.9981F, 1, 3, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 25, -1.216F, -1.4791F, -0.9981F, 1, 3, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 40, 91, -1.536F, -0.7991F, -1.3181F, 3, 2, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 0, -1.536F, 0.8809F, -1.7981F, 0, 0, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 18, 6, -1.248F, 0.4009F, -1.7981F, 2, 0, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 82, 73, -0.32F, -0.5031F, -2.1181F, 1, 1, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 97, 22, -0.96F, -0.8231F, -1.6381F, 2, 1, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 97, 22, -0.96F, -0.5323F, -1.6586F, 2, 1, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 98, 54, -0.8F, -0.5031F, -2.1181F, 1, 1, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 96, 85, -1.44F, -0.8231F, -1.6381F, 2, 1, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 96, 85, -1.44F, -0.5323F, -1.6586F, 2, 1, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 0, 1.024F, 0.8809F, -1.7981F, 0, 0, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 12, 33, -0.506F, -0.1191F, -4.1981F, 1, 1, 4, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 10, 11, -0.576F, 1.2009F, -3.7181F, 1, 0, 4, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 10, 32, -0.896F, 1.0409F, -2.2781F, 1, 0, 4, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 64, 55, -0.576F, -0.4391F, -3.7181F, 1, 1, 4, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 14, 5, -0.256F, 0.2409F, -3.2381F, 0, 0, 4, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.25F, 31.5667F, 5.3333F);
		head.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 31, 12, -1.5824F, -33.7952F, -4.991F, 0, 1, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 21, 12, -1.7802F, -34.7842F, -5.083F, 0, 1, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.978F, -35.5754F, -5.083F, 0, 1, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 19, 12, -2.1758F, -35.971F, -5.175F, 0, 1, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 21, 7, -3.7582F, -36.1578F, -5.359F, 1, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 28, 12, -3.3626F, -35.96F, -5.267F, 1, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 12, 9, -4.4703F, -36.3556F, -5.451F, 1, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 28, 11, -5.2615F, -36.5534F, -5.543F, 1, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 17, 6, -5.9736F, -36.7512F, -5.635F, 1, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 26, 21, -6.7648F, -36.8699F, -5.635F, 1, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 46, 42, -8.3076F, -36.9886F, -5.5163F, 2, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 46, 42, -8.3076F, -36.9886F, -5.7537F, 2, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 46, 42, -9.6922F, -37.1864F, -5.4768F, 2, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 46, 43, -9.6922F, -37.1864F, -5.7932F, 2, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 46, 43, -10.879F, -37.3842F, -5.4372F, 2, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 46, 42, -11.6702F, -37.582F, -5.4372F, 2, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 46, 42, -10.879F, -37.3842F, -5.8328F, 2, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 46, 42, -11.6702F, -37.582F, -5.8328F, 2, 0, 0, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-0.15F, 31.5667F, 5.3333F);
		head.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 20, 13, 0.7912F, -33.7952F, -4.991F, 0, 1, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 20, 10, 0.989F, -34.7842F, -5.083F, 0, 1, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 19, 11, 1.1868F, -35.5754F, -5.083F, 0, 1, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 14, 5, 1.3846F, -35.971F, -5.175F, 0, 1, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 21, 11, 2.1758F, -36.1578F, -5.359F, 1, 0, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 15, 6, 1.7802F, -35.96F, -5.267F, 1, 0, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 20, 10, 2.8879F, -36.3556F, -5.451F, 1, 0, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 15, 8, 3.6791F, -36.5534F, -5.543F, 1, 0, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 23, 8, 4.3912F, -36.7512F, -5.635F, 1, 0, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 25, 14, 5.1824F, -36.8699F, -5.635F, 1, 0, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 46, 42, 5.934F, -36.9886F, -5.5163F, 2, 0, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 46, 43, 5.934F, -36.9886F, -5.7537F, 2, 0, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 46, 43, 7.3186F, -37.1864F, -5.4768F, 2, 0, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 46, 42, 7.3186F, -37.1864F, -5.7932F, 2, 0, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 46, 43, 8.5054F, -37.3842F, -5.4372F, 2, 0, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 46, 42, 9.2966F, -37.582F, -5.4372F, 2, 0, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 46, 42, 8.5054F, -37.3842F, -5.8328F, 2, 0, 0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 46, 43, 9.2966F, -37.582F, -5.8328F, 2, 0, 0, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Rleg.render(f5);
		Lleg.render(f5);
		rarm.render(f5);
		larm.render(f5);
		body.render(f5);
		head.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) { 
		Rleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount + 5.4F;
		Lleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount + 5.4F;
		

	}
}
