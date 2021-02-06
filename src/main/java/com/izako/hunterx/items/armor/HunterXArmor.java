package com.izako.hunterx.items.armor;

import javax.annotation.Nullable;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModItems;
import com.izako.hunterx.izapi.IThugDrop;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HunterXArmor extends ArmorItem implements IThugDrop {

	


	

	private String skinType = "slim";
	private String pathName;


	

	public HunterXArmor(String name, String pathName,  EquipmentSlotType equipmentSlot, Item.Properties builder)

    {

        super( ModItems.HUNTERX_ARMOR_MATERIAL, equipmentSlot, builder);


        this.pathName = pathName;

    }

	@Override

	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		if(slot == EquipmentSlotType.LEGS) {

 		return String.format("%s:textures/models/armor/%s_%d.png", Main.MODID, this.pathName, 2);

		} else {
			return String.format("%s:textures/models/armor/%s_%d.png", Main.MODID, this.pathName, 1);
		}

	}

	

	@OnlyIn(Dist.CLIENT)

	@Override
	
	@Nullable

	public BipedModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, BipedModel _default) {

		if(itemStack != ItemStack.EMPTY && itemStack.getItem() instanceof HunterXArmor) {

			this.skinType = "sli";
			if(entityLiving instanceof AbstractClientPlayerEntity) {
			this.skinType = ((AbstractClientPlayerEntity) entityLiving).getSkinType();		
			}

			PlayerModel model = new PlayerModel(0.3f, this.skinType == "slim");			

			

			model.bipedHead.showModel = armorSlot == EquipmentSlotType.HEAD;

			model.bipedBody.showModel = armorSlot == EquipmentSlotType.CHEST;

			model.bipedLeftArm.showModel = armorSlot == EquipmentSlotType.CHEST;

			model.bipedRightArm.showModel = armorSlot == EquipmentSlotType.CHEST;

			model.bipedLeftLeg.showModel = armorSlot == EquipmentSlotType.LEGS;

			model.bipedRightLeg.showModel = armorSlot == EquipmentSlotType.LEGS;

			

			model.isChild = _default.isChild;

			model.isSitting = _default.isSitting;

			model.isSneak = _default.isSneak;

			model.rightArmPose = _default.rightArmPose;

			model.leftArmPose = _default.leftArmPose;	 

		 	return model;

		}

		return null;

	}	

	

}
