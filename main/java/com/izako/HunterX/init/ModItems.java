package com.izako.HunterX.init;

import java.util.ArrayList;
import java.util.List;

import com.izako.HunterX.items.ItemBase;
import com.izako.HunterX.items.armor.ArmorBase;
import com.izako.HunterX.items.armor.ArmorBaseSkin;
import com.izako.HunterX.items.armor.HanzoArmorBase;
import com.izako.HunterX.items.tools.GonsFishingRod;
import com.izako.HunterX.items.tools.ItemCard;
import com.izako.HunterX.items.tools.ItemNeedle;
import com.izako.HunterX.items.tools.KurapikasSword;
import com.izako.HunterX.items.tools.Pistol;
import com.izako.HunterX.items.tools.ToolSword;
import com.izako.HunterX.items.tools.Yoyo;
import com.izako.HunterX.util.Reference;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {
	public static final List<Item> ITEMS = new ArrayList<Item>();

//tabs
	public static final CreativeTabs HunterX = new CreativeTabs("HunterX") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.LOGO);
		}

		@Override
		public boolean hasSearchBar() {
			return true;
		}

	}.setBackgroundImageName("item_search.png");

//Armor Materials
	public static final ArmorMaterial ARMOR_MATERIAL_GON = EnumHelper.addArmorMaterial("armor_material_gon",
			Reference.MOD_ID + ":gon", 40, new int[] { 6, 10, 8, 0 }, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0);
	public static final ArmorMaterial ARMOR_MATERIAL_KILLUA = EnumHelper.addArmorMaterial("armor_material_killua",
			Reference.MOD_ID + ":killua", 40, new int[] { 6, 10, 8, 0 }, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0);
	public static final ArmorMaterial ARMOR_MATERIAL_LEORIO = EnumHelper.addArmorMaterial("armor_material_leorio",
			Reference.MOD_ID + ":leorio", 40, new int[] { 6, 10, 8, 0 }, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0);
	public static final ArmorMaterial ARMOR_MATERIAL_KURAPIKA = EnumHelper.addArmorMaterial("armor_material_kurapika",
			Reference.MOD_ID + ":kurapika", 40, new int[] { 6, 10, 8, 0 }, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0);
	public static final ArmorMaterial ARMOR_MATERIAL_ILLUMI = EnumHelper.addArmorMaterial("armor_material_illumi",
			Reference.MOD_ID + ":illumi", 40, new int[] { 6, 10, 8, 0 }, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0);
	public static final ArmorMaterial ARMOR_MATERIAL_HISOKA = EnumHelper.addArmorMaterial("armor_material_hisoka",
			Reference.MOD_ID + ":hisoka", 40, new int[] { 6, 10, 8, 0 }, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0);
	public static final ArmorMaterial ARMOR_MATERIAL_NETERO = EnumHelper.addArmorMaterial("armor_material_netero",
			Reference.MOD_ID + ":netero", 40, new int[] { 6, 10, 8, 0 }, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0);
	public static final ArmorMaterial ARMOR_MATERIAL_GING = EnumHelper.addArmorMaterial("armor_material_ging",
			Reference.MOD_ID + ":ging", 40, new int[] { 6, 10, 8, 0 }, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0);
	public static final ArmorMaterial ARMOR_MATERIAL_KITE = EnumHelper.addArmorMaterial("armor_material_kite",
			Reference.MOD_ID + ":kite", 40, new int[] { 6, 10, 8, 0 }, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0);

//Tool Materials
	public static final ToolMaterial KURAPIKAS_STICKS = EnumHelper.addToolMaterial("kurapikas_sticks", 3, 2000, 12, 5,
			15);
	public static final ToolMaterial KURAPIKAS_BLADE = EnumHelper.addToolMaterial("kurapikas_blade", 3, 2000, 12, 9,
			15);
	public static final ToolMaterial HANZOS_BLADE = EnumHelper.addToolMaterial("hanzos_blade", 3, 1500, 12, 4, 7);

//Items
	public static final Item LOGO = new ItemBase("logo").setUnlocalizedName("logo");
	public static final Item HUNTER_CARD = new ItemBase("hunter_card").setUnlocalizedName("hunter_card")
			.setCreativeTab(HunterX);
	public static final Item BULLET = new ItemBase("bullet");

//Rightclick Items
	public static final Item YOYO = new Yoyo("yoyo").setUnlocalizedName("yoyo").setCreativeTab(HunterX);
	public static final ItemCard HISOKAS_CARD = new ItemCard("hisokas_card");
	public static final ItemNeedle ILLUMIS_NEEDLE = new ItemNeedle("illumis_needle");
	public static final GonsFishingRod GONS_FISHING_ROD = new GonsFishingRod("gons_fishing_rod");
	public static final Pistol PISTOL = new Pistol ("pistol");

//Armor

//Gon
	public static final Item GON_CHESTPLATE = new ArmorBaseSkin("gon_chestplate", "gon_", ARMOR_MATERIAL_GON, 1,
			EntityEquipmentSlot.CHEST);
	public static final Item GON_LEGGINGS = new ArmorBaseSkin("gon_leggings", "gon_", ARMOR_MATERIAL_GON, 2,
			EntityEquipmentSlot.LEGS);
	public static final Item GON_BOOTS = new ArmorBaseSkin("gon_boots", "gon_", ARMOR_MATERIAL_GON, 1,
			EntityEquipmentSlot.FEET);

//Killua
	public static final Item KILLUA_CHESTPLATE = new ArmorBaseSkin("killua_chestplate", "killua_", ARMOR_MATERIAL_GON,
			1, EntityEquipmentSlot.CHEST);
	public static final Item KILLUA_LEGGINGS = new ArmorBaseSkin("killua_leggings", "killua_", ARMOR_MATERIAL_GON, 2,
			EntityEquipmentSlot.LEGS);
	public static final Item KILLUA_BOOTS = new ArmorBaseSkin("killua_boots", "killua_", ARMOR_MATERIAL_GON, 1,
			EntityEquipmentSlot.FEET);

//Leorio
	public static final Item LEORIO_CHESTPLATE = new ArmorBaseSkin("leorio_chestplate", "leorio_", ARMOR_MATERIAL_GON,
			1, EntityEquipmentSlot.CHEST);
	public static final Item LEORIO_LEGGINGS = new ArmorBaseSkin("leorio_leggings", "leorio_", ARMOR_MATERIAL_GON, 2,
			EntityEquipmentSlot.LEGS);
	public static final Item LEORIO_BOOTS = new ArmorBaseSkin("leorio_boots", "leorio_", ARMOR_MATERIAL_GON, 1,
			EntityEquipmentSlot.FEET);

//Kurapika
	public static final Item KURAPIKA_CHESTPLATE = new ArmorBaseSkin("kurapika_chestplate", "kurapika_",
			ARMOR_MATERIAL_GON, 1, EntityEquipmentSlot.CHEST);
	public static final Item KURAPIKA_LEGGINGS = new ArmorBaseSkin("kurapika_leggings", "kurapika_", ARMOR_MATERIAL_GON,
			2, EntityEquipmentSlot.LEGS);
	public static final Item KURAPIKA_BOOTS = new ArmorBaseSkin("kurapika_boots", "kurapika_", ARMOR_MATERIAL_GON, 1,
			EntityEquipmentSlot.FEET);

//Illumi
	public static final Item ILLUMI_CHESTPLATE = new ArmorBaseSkin("illumi_chestplate", "illumi_", ARMOR_MATERIAL_GON,
			1, EntityEquipmentSlot.CHEST);
	public static final Item ILLUMI_LEGGINGS = new ArmorBaseSkin("illumi_leggings", "illumi_", ARMOR_MATERIAL_GON, 2,
			EntityEquipmentSlot.LEGS);
	public static final Item ILLUMI_BOOTS = new ArmorBaseSkin("illumi_boots", "illumi_", ARMOR_MATERIAL_GON, 1,
			EntityEquipmentSlot.FEET);

//Hisoka
	public static final Item HISOKA_CHESTPLATE = new ArmorBaseSkin("hisoka_chestplate", "hisoka_", ARMOR_MATERIAL_GON,
			1, EntityEquipmentSlot.CHEST);
	public static final Item HISOKA_LEGGINGS = new ArmorBaseSkin("hisoka_leggings", "hisoka_", ARMOR_MATERIAL_GON, 2,
			EntityEquipmentSlot.LEGS);
	public static final Item HISOKA_BOOTS = new ArmorBaseSkin("hisoka_boots", "hisoka_", ARMOR_MATERIAL_GON, 1,
			EntityEquipmentSlot.FEET);

//Netero
	public static final Item NETERO_CHESTPLATE = new ArmorBaseSkin("netero_chestplate", "netero_", ARMOR_MATERIAL_GON,
			1, EntityEquipmentSlot.CHEST);
	public static final Item NETERO_LEGGINGS = new ArmorBaseSkin("netero_leggings", "netero_", ARMOR_MATERIAL_GON, 2,
			EntityEquipmentSlot.LEGS);
	public static final Item NETERO_BOOTS = new ArmorBaseSkin("netero_boots", "netero_", ARMOR_MATERIAL_GON, 1,
			EntityEquipmentSlot.FEET);

//Ging
	public static final Item GING_CHESTPLATE = new ArmorBaseSkin("ging_chestplate", "ging_", ARMOR_MATERIAL_GON, 1,
			EntityEquipmentSlot.CHEST);
	public static final Item GING_LEGGINGS = new ArmorBaseSkin("ging_leggings", "ging_", ARMOR_MATERIAL_GON, 2,
			EntityEquipmentSlot.LEGS);
	public static final Item GING_BOOTS = new ArmorBaseSkin("ging_boots", "ging_", ARMOR_MATERIAL_GON, 1,
			EntityEquipmentSlot.FEET);

//Wing
	public static final Item KITE_CHESTPLATE = new ArmorBaseSkin("kite_chestplate", "kite_", ARMOR_MATERIAL_GON, 1,
			EntityEquipmentSlot.CHEST);
	public static final Item KITE_LEGGINGS = new ArmorBaseSkin("kite_leggings", "kite_", ARMOR_MATERIAL_GON, 2,
			EntityEquipmentSlot.LEGS);
	public static final Item KITE_BOOTS = new ArmorBaseSkin("kite_boots", "kite_", ARMOR_MATERIAL_GON, 1,
			EntityEquipmentSlot.FEET);

//Hanzo
	public static final Item HANZOS_CHESTPLATE = new HanzoArmorBase("hanzo_chestplate", "hanzo_", ARMOR_MATERIAL_GON, 1,
			EntityEquipmentSlot.CHEST);
	public static final Item HANZOS_LEGGINGS = new ArmorBaseSkin("hanzo_leggings", "hanzo_", ARMOR_MATERIAL_GON, 2,
			EntityEquipmentSlot.LEGS);
	public static final Item HANZOS_BOOTS = new ArmorBaseSkin("hanzo_boots", "hanzo_", ARMOR_MATERIAL_GON, 1,
			EntityEquipmentSlot.FEET);

//Tools

//Weapons
	public static final ItemSword KURAPIKAS_SWORD = new KurapikasSword("kurapikas_sword", KURAPIKAS_STICKS, 9);
	public static final ItemSword KURAPIKAS_SWORD_UNSHEATHED = new KurapikasSword("kurapikas_sword_unsheathed",
			KURAPIKAS_BLADE, 13);
	public static final ItemSword HANZOS_SWORD = new ToolSword("hanzos_sword", HANZOS_BLADE);

//Food

}
