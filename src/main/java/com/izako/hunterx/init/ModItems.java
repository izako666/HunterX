package com.izako.hunterx.init;

import java.util.Arrays;

import com.izako.hunterx.Main;
import com.izako.hunterx.entities.KirikoEntity;
import com.izako.hunterx.entities.ThugEntity;
import com.izako.hunterx.entities.WingEntity;
import com.izako.hunterx.items.BadgeItem;
import com.izako.hunterx.items.CardItem;
import com.izako.hunterx.items.GonsFishingRodItem;
import com.izako.hunterx.items.HunterLicenseItem;
import com.izako.hunterx.items.HunterXArmorMaterial;
import com.izako.hunterx.items.KurapikaSwordItem;
import com.izako.hunterx.items.NeedleItem;
import com.izako.hunterx.items.PistolItem;
import com.izako.hunterx.items.ShurikenItem;
import com.izako.hunterx.items.YoyoItem;
import com.izako.hunterx.items.armor.HunterXArmor;
import com.izako.hunterx.items.nen.ConjurerTool;
import com.izako.hunterx.items.tiers.HunterXItemTier;
import com.izako.hunterx.registerers.ModEventSubscriber;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)

public class ModItems {

	public static final ItemGroup HUNTERX_ITEM_GROUP = new ItemGroup("hunterxitemgroup") {
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon() {
			return new ItemStack(ModItems.YOYO);
		}
	};

	public static final HunterXItemTier KURAPIKAS_STICK_TIER = new HunterXItemTier(3, 2000, 12, 1, 15);
	public static final HunterXItemTier BASIC_CONJURER_TIER = new HunterXItemTier(3,10000, 8, 1, 15);
	public static final HunterXItemTier JAJANKEN_SCISSORS_TIER = new HunterXItemTier(3, 10000,1,0,15);
	public static final HunterXItemTier HANZO_SWORD_TIER = new HunterXItemTier(3, 2000, 12, 3, 15);
	public static final KurapikaSwordItem KURAPIKAS_STICK = new KurapikaSwordItem(KURAPIKAS_STICK_TIER, 1, -2.2f,
			new Item.Properties().group(HUNTERX_ITEM_GROUP));
	public static final ConjurerTool BASIC_CONJURER_TOOL = new ConjurerTool(BASIC_CONJURER_TIER, 2, -2f, new Item.Properties().maxStackSize(1), Arrays.asList(ToolType.PICKAXE,ToolType.AXE,ToolType.SHOVEL));
	public static final ConjurerTool JAJANKEN_SCISSORS = new ConjurerTool(JAJANKEN_SCISSORS_TIER, 5, 1f, new Item.Properties().maxStackSize(1), Arrays.asList());

	public static final FishingRodItem GONS_FISHING_ROD = new GonsFishingRodItem(new Item.Properties().group(HUNTERX_ITEM_GROUP));
	public static final YoyoItem YOYO = new YoyoItem(
			new Item.Properties().group(HUNTERX_ITEM_GROUP).maxStackSize(1).maxDamage(200));
	public static final CardItem CARD = new CardItem(new Item.Properties().group(HUNTERX_ITEM_GROUP).maxStackSize(16));
	public static final NeedleItem NEEDLE = new NeedleItem(
			new Item.Properties().group(HUNTERX_ITEM_GROUP).maxStackSize(16));
	public static final ShurikenItem SHURIKEN = new ShurikenItem(
			new Item.Properties().maxStackSize(16));

	public static final PistolItem PISTOL = new PistolItem(new Item.Properties().maxStackSize(1));
	public static final BadgeItem BADGE = new BadgeItem(new Item.Properties().maxStackSize(1));

	public static final HunterLicenseItem HUNTER_LICENSE = new HunterLicenseItem(new Item.Properties().maxStackSize(1));
	public static final SwordItem HANZO_SWORD = new SwordItem(HANZO_SWORD_TIER, 3, -2f, new Item.Properties());

	public static final SpawnEggItem THUG_EGG = new SpawnEggItem(ThugEntity.TYPE, 4996656, 9794134, new Item.Properties().group(ItemGroup.MISC));
	public static final SpawnEggItem KIRIKO_EGG = new SpawnEggItem(KirikoEntity.TYPE, 13552826, 16711680, new Item.Properties().group(ItemGroup.MISC));
	public static final SpawnEggItem WING_EGG = new SpawnEggItem(WingEntity.TYPE, 16711680, 13552826, new Item.Properties().group(ItemGroup.MISC));

	// Armor Material
	public static final HunterXArmorMaterial HUNTERX_ARMOR_MATERIAL = new HunterXArmorMaterial(
			Main.MODID + ":hunterx_armor_material", 40, new int[] { 6, 10, 8, 0 }, 30);

	// Armor
	public static final HunterXArmor GON_CHESTPLATE = new HunterXArmor("gon_chestplate", "gon", EquipmentSlotType.CHEST,
			new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor GON_LEGGINGS = new HunterXArmor("gon_leggings", "gon", EquipmentSlotType.LEGS,
			new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor GON_BOOTS = new HunterXArmor("gon_boots", "gon", EquipmentSlotType.FEET,
			new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));

	public static final HunterXArmor HANZO_CHESTPLATE = new HunterXArmor("hanzo_chestplate", "hanzo",
			EquipmentSlotType.CHEST, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor HANZO_LEGGINGS = new HunterXArmor("hanzo_leggings", "hanzo",
			EquipmentSlotType.LEGS, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor HANZO_BOOTS = new HunterXArmor("hanzo_boots", "hanzo", EquipmentSlotType.FEET,
			new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));

	public static final HunterXArmor KILLUA_CHESTPLATE = new HunterXArmor("killua_chestplate", "killua",
			EquipmentSlotType.CHEST, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor KILLUA_LEGGINGS = new HunterXArmor("killua_leggings", "killua",
			EquipmentSlotType.LEGS, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor KILLUA_BOOTS = new HunterXArmor("killua_boots", "killua", EquipmentSlotType.FEET,
			new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));

	public static final HunterXArmor KURAPIKA_CHESTPLATE = new HunterXArmor("kurapika_chestplate", "kurapika",
			EquipmentSlotType.CHEST, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor KURAPIKA_LEGGINGS = new HunterXArmor("kurapika_leggings", "kurapika",
			EquipmentSlotType.LEGS, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor KURAPIKA_BOOTS = new HunterXArmor("kurapika_boots", "kurapika",
			EquipmentSlotType.FEET, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));

	public static final HunterXArmor LEORIO_CHESTPLATE = new HunterXArmor("leorio_chestplate", "leorio",
			EquipmentSlotType.CHEST, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor LEORIO_LEGGINGS = new HunterXArmor("leorio_leggings", "leorio",
			EquipmentSlotType.LEGS, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor LEORIO_BOOTS = new HunterXArmor("leorio_boots", "leorio", EquipmentSlotType.FEET,
			new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));

	public static final HunterXArmor ILLUMI_CHESTPLATE = new HunterXArmor("illumi_chestplate", "illumi",
			EquipmentSlotType.CHEST, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor ILLUMI_LEGGINGS = new HunterXArmor("illumi_leggings", "illumi",
			EquipmentSlotType.LEGS, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor ILLUMI_BOOTS = new HunterXArmor("illumi_boots", "illumi", EquipmentSlotType.FEET,
			new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));

	public static final HunterXArmor HISOKA_CHESTPLATE = new HunterXArmor("hisoka_chestplate", "hisoka",
			EquipmentSlotType.CHEST, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor HISOKA_LEGGINGS = new HunterXArmor("hisoka_leggings", "hisoka",
			EquipmentSlotType.LEGS, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor HISOKA_BOOTS = new HunterXArmor("hisoka_boots", "hisoka", EquipmentSlotType.FEET,
			new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));

	public static final HunterXArmor KITE_CHESTPLATE = new HunterXArmor("kite_chestplate", "kite",
			EquipmentSlotType.CHEST, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor KITE_LEGGINGS = new HunterXArmor("kite_leggings", "kite", EquipmentSlotType.LEGS,
			new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor KITE_BOOTS = new HunterXArmor("kite_boots", "kite", EquipmentSlotType.FEET,
			new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));

	public static final HunterXArmor NETERO_CHESTPLATE = new HunterXArmor("netero_chestplate", "netero",
			EquipmentSlotType.CHEST, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor NETERO_LEGGINGS = new HunterXArmor("netero_leggings", "netero",
			EquipmentSlotType.LEGS, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor NETERO_BOOTS = new HunterXArmor("netero_boots", "netero", EquipmentSlotType.FEET,
			new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));

	public static final HunterXArmor GING_CHESTPLATE = new HunterXArmor("ging_chestplate", "ging",
			EquipmentSlotType.CHEST, new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor GING_LEGGINGS = new HunterXArmor("ging_leggings", "ging", EquipmentSlotType.LEGS,
			new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));
	public static final HunterXArmor GING_BOOTS = new HunterXArmor("ging_boots", "ging", EquipmentSlotType.FEET,
			new Item.Properties().setNoRepair().group(HUNTERX_ITEM_GROUP));

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {

		event.getRegistry().registerAll(
				ModEventSubscriber.setup(KURAPIKAS_STICK, "kurapikas_stick"),
				ModEventSubscriber.setup(YOYO, "yoyo"),
				ModEventSubscriber.setup(CARD, "card"),
				ModEventSubscriber.setup(PISTOL, "pistol"),
				ModEventSubscriber.setup(BADGE, "badge"),
				ModEventSubscriber.setup(HUNTER_LICENSE, "hunter_license"),
				ModEventSubscriber.setup(NEEDLE, "needle"),
				ModEventSubscriber.setup(SHURIKEN, "shuriken"),
				ModEventSubscriber.setup(HANZO_SWORD, "hanzo_sword"),
				ModEventSubscriber.setup(GONS_FISHING_ROD, "gons_fishing_rod"),
				ModEventSubscriber.setup(THUG_EGG, "thug_egg"),
				ModEventSubscriber.setup(KIRIKO_EGG, "kiriko_egg"),
				ModEventSubscriber.setup(WING_EGG, "wing_egg"),
				ModEventSubscriber.setup(GON_CHESTPLATE, "gon_chestplate"),
				ModEventSubscriber.setup(GON_LEGGINGS, "gon_leggings"),
				ModEventSubscriber.setup(GON_BOOTS, "gon_boots"),
				ModEventSubscriber.setup(HANZO_CHESTPLATE, "hanzo_chestplate"),
				ModEventSubscriber.setup(HANZO_LEGGINGS, "hanzo_leggings"),
				ModEventSubscriber.setup(HANZO_BOOTS, "hanzo_boots"),
				ModEventSubscriber.setup(KILLUA_CHESTPLATE, "killua_chestplate"),
				ModEventSubscriber.setup(KILLUA_LEGGINGS, "killua_leggings"),
				ModEventSubscriber.setup(KILLUA_BOOTS, "killua_boots"),
				ModEventSubscriber.setup(KURAPIKA_CHESTPLATE, "kurapika_chestplate"),
				ModEventSubscriber.setup(KURAPIKA_LEGGINGS, "kurapika_leggings"),
				ModEventSubscriber.setup(KURAPIKA_BOOTS, "kurapika_boots"),
				ModEventSubscriber.setup(LEORIO_CHESTPLATE, "leorio_chestplate"),
				ModEventSubscriber.setup(LEORIO_LEGGINGS, "leorio_leggings"),
				ModEventSubscriber.setup(LEORIO_BOOTS, "leorio_boots"),
				ModEventSubscriber.setup(ILLUMI_CHESTPLATE, "illumi_chestplate"),
				ModEventSubscriber.setup(ILLUMI_LEGGINGS, "illumi_leggings"),
				ModEventSubscriber.setup(ILLUMI_BOOTS, "illumi_boots"),
				ModEventSubscriber.setup(HISOKA_CHESTPLATE, "hisoka_chestplate"),
				ModEventSubscriber.setup(HISOKA_LEGGINGS, "hisoka_leggings"),
				ModEventSubscriber.setup(HISOKA_BOOTS, "hisoka_boots"),
				ModEventSubscriber.setup(KITE_CHESTPLATE, "kite_chestplate"),
				ModEventSubscriber.setup(KITE_LEGGINGS, "kite_leggings"),
				ModEventSubscriber.setup(KITE_BOOTS, "kite_boots"),
				ModEventSubscriber.setup(NETERO_CHESTPLATE, "netero_chestplate"),
				ModEventSubscriber.setup(NETERO_LEGGINGS, "netero_leggings"),
				ModEventSubscriber.setup(NETERO_BOOTS, "netero_boots"),
				ModEventSubscriber.setup(GING_CHESTPLATE, "ging_chestplate"),
				ModEventSubscriber.setup(GING_LEGGINGS, "ging_leggings"),
				ModEventSubscriber.setup(GING_BOOTS, "ging_boots"),
				ModEventSubscriber.setup(BASIC_CONJURER_TOOL, "paxel"),
				ModEventSubscriber.setup(JAJANKEN_SCISSORS, "jajanken_scissors")

		);
	}
}
