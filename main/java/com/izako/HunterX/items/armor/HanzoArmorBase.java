package com.izako.HunterX.items.armor;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.util.Reference;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HanzoArmorBase extends ItemArmor {

	// Credit to SunFlow

	private String armorTexturePath;

	private String skinType;

	public HanzoArmorBase(String name, String pathName, ArmorMaterial material, int renderIndex,
			EntityEquipmentSlot equipmentSlot)

	{
		super(material, renderIndex, equipmentSlot);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModItems.HunterX);

		this.armorTexturePath = Reference.MOD_ID + ":textures/models/armor/" + pathName;

		this.skinType = "default";

		ModItems.ITEMS.add(this);
	}

	
	private void setPaths(final String input)

	{

		String[] str = input.split(":", 2);

		this.armorTexturePath = str[0] + ":" + "textures/models/armor/";

		str = str[1].split("");

		for (int i = 0; i < str.length; i++)

			this.armorTexturePath += str[i] + "_";

		this.skinType = "default";

	}

	@Override

	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {

		return this.armorTexturePath + this.skinType + "_layer_" + this.renderIndex + ".png";

	}

	@SideOnly(Side.CLIENT)

	@Override

	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,
			ModelBiped _default) {

		if (itemStack != ItemStack.EMPTY && itemStack.getItem() instanceof HanzoArmorBase) {

			this.skinType = ((AbstractClientPlayer) entityLiving).getSkinType();

			ModelPlayer model = new ModelPlayer(0.3f, this.skinType == "slim");

			model.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;

			model.bipedBody.showModel = armorSlot == EntityEquipmentSlot.CHEST;

			model.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;

			model.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;

			model.bipedLeftLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS;

			model.bipedRightLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS;

			model.isChild = _default.isChild;

			model.isRiding = _default.isRiding;

			model.isSneak = _default.isSneak;

			model.rightArmPose = _default.rightArmPose;

			model.leftArmPose = _default.leftArmPose;

			return model;

		}

		return null;

	}
}
