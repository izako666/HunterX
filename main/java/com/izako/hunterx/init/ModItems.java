package com.izako.hunterx.init;

import com.izako.hunterx.Main;
import com.izako.hunterx.items.HunterXArmorMaterial;
import com.izako.hunterx.items.KurapikaSwordItem;
import com.izako.hunterx.items.YoyoItem;
import com.izako.hunterx.items.armor.HunterXArmor;
import com.izako.hunterx.items.tiers.HunterXItemTier;
import com.izako.hunterx.registerers.ModEventSubscriber;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)

public class ModItems {

	public static final HunterXItemTier KURAPIKAS_STICK_TIER = new HunterXItemTier(3, 2000, 12, 1, 15);
	public	static final KurapikaSwordItem KURAPIKAS_STICK = new KurapikaSwordItem(KURAPIKAS_STICK_TIER, 1, -2.2f, new Item.Properties().group(ItemGroup.TOOLS));
	public static final YoyoItem YOYO = new YoyoItem(new Item.Properties().group(ItemGroup.TOOLS));
	//Armor Material
	public static final HunterXArmorMaterial HUNTERX_ARMOR_MATERIAL = new HunterXArmorMaterial(Main.MODID + ":hunterx_armor_material", 40, new int[] { 6, 10, 8, 0}, 30);
	
	//Armor
	public static final HunterXArmor GON_CHESTPLATE = new HunterXArmor("gon_chestplate", "gon", EquipmentSlotType.CHEST, new Item.Properties().setNoRepair().group(ItemGroup.TOOLS));
	public static final HunterXArmor GON_LEGGINGS = new HunterXArmor("gon_leggings", "gon", EquipmentSlotType.LEGS, new Item.Properties().setNoRepair().group(ItemGroup.TOOLS));
	public static final HunterXArmor GON_BOOTS = new HunterXArmor("gon_boots","gon", EquipmentSlotType.FEET, new Item.Properties().setNoRepair().group(ItemGroup.TOOLS));

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {

		event.getRegistry().registerAll(
				ModEventSubscriber.setup(KURAPIKAS_STICK, "kurapikas_stick"),
				ModEventSubscriber.setup(YOYO, "yoyo"),
				ModEventSubscriber.setup(GON_CHESTPLATE, "gon_chestplate"),
				ModEventSubscriber.setup(GON_LEGGINGS, "gon_leggings"),
				ModEventSubscriber.setup(GON_BOOTS, "gon_boots")

				);
	}
}
