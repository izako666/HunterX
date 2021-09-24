package com.izako.hunterx.init;

import com.izako.hunterx.Main;
import com.izako.hunterx.blocks.ComputerBlock;
import com.izako.hunterx.blocks.DivinationCupBlock;
import com.izako.hunterx.registerers.ModEventSubscriber;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModBlocks {

	public static final Block CUP = new DivinationCupBlock(Block.Properties.create(Material.GLASS));
	public static final Block COMPUTER = new ComputerBlock(Block.Properties.create(Material.IRON));

	
	public static final Item CUP_ITEM = new BlockItem(CUP, new Item.Properties().group(ModItems.HUNTERX_ITEM_GROUP).maxStackSize(1));
	public static final Item COMPUTER_ITEM = new BlockItem(COMPUTER, new Item.Properties().group(ModItems.HUNTERX_ITEM_GROUP).maxStackSize(1));

	@SubscribeEvent
	public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ModEventSubscriber.setup(CUP, "divination_cup_block"), ModEventSubscriber.setup(COMPUTER, "computer"));

	}
	
	@SubscribeEvent
	public static void onRegisterBlockItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModEventSubscriber.setup(CUP_ITEM, "divination_cup_block"),ModEventSubscriber.setup(COMPUTER_ITEM, "computer"));
	}

}
