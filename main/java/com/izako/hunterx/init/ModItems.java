package com.izako.hunterx.init;

import com.izako.hunterx.Main;
import com.izako.hunterx.ModEventSubscriber;
import com.izako.hunterx.items.KurapikaSword;
import com.izako.hunterx.items.tiers.HunterXItemTier;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)

public class ModItems {

	public static final HunterXItemTier KURAPIKAS_STICK_TIER = new HunterXItemTier(3, 2000, 12, 1, 15);
	public	static final KurapikaSword KURAPIKAS_STICK = new KurapikaSword(KURAPIKAS_STICK_TIER, 1, -2.2f, new Item.Properties().group(ItemGroup.TOOLS));
	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {

		event.getRegistry().registerAll(
				ModEventSubscriber.setup(KURAPIKAS_STICK, "kurapikas_stick")
				);
	}
}
