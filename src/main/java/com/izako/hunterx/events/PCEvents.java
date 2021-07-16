package com.izako.hunterx.events;

import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.worlddata.ModWorldData;
import com.izako.hunterx.gui.ComputerScreen.PCEntry;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class PCEvents {

	
	@SubscribeEvent
	public static void tick(WorldTickEvent evt) {
		if(!evt.world.isRemote()) {
			ModWorldData data = ModWorldData.get((ServerWorld) evt.world);

			if(data.getHunterStock().isEmpty()) {
				data.setStock(PCEvents.generateStocks(((ServerWorld)evt.world), ModWorldData.hunterStockLoc),true);
			}
			
			if(data.getNormalStock().isEmpty()) {
				data.setStock(PCEvents.generateStocks(((ServerWorld)evt.world), ModWorldData.normalStockLoc),false);
			}
			
		if(evt.world.getGameTime() % 24000 == 0) {
			
			data.setStock(PCEvents.generateStocks(((ServerWorld)evt.world), ModWorldData.normalStockLoc),false);
			data.setStock(PCEvents.generateStocks(((ServerWorld)evt.world), ModWorldData.hunterStockLoc),true);

				
			}
		}
	}
	
	
	public static List<PCEntry> generateStocks(ServerWorld world, ResourceLocation loc) {
		
		LootTable lootTable = world.getServer().getLootTableManager().getLootTableFromLocation(loc);
		LootContext.Builder builder = (new LootContext.Builder(world));

		LootContext context = builder.build(LootParameterSets.EMPTY);
		List<ItemStack> stacks = lootTable.generate(context);
		List<PCEntry> stock = new ArrayList<>();
		
		for(ItemStack stack: stacks) {
			if(stack.getOrCreateTag().getString("info") != "") {
				String[] string = stack.getOrCreateTag().getString("info").split(":");
				List <String> info = new ArrayList<>();
				for(int i = 1; i < string.length; i++) {
					info.add(string[i]);
				}
				PCEntry entry = new PCEntry(stack,stack.getOrCreateTag().getFloat("price"),info,string[0],stack.getOrCreateTag().getInt("count"));
			} else {
			PCEntry entry = new PCEntry(stack, stack.getOrCreateTag().getFloat("price"), null, stack.getDisplayName().getFormattedText(), stack.getOrCreateTag().getInt("count"));
			
			stock.add(entry);
			}}
		return stock;
	}
}
