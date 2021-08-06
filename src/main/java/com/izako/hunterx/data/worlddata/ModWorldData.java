package com.izako.hunterx.data.worlddata;

import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.Main;
import com.izako.hunterx.gui.ComputerScreen;
import com.izako.hunterx.gui.ComputerScreen.PCEntry;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

public class ModWorldData extends WorldSavedData {

	public static final ResourceLocation normalStockLoc = new ResourceLocation(Main.MODID, "pc/normal");
	public static final ResourceLocation hunterStockLoc = new ResourceLocation(Main.MODID, "pc/hunter");

	public List<ComputerScreen.PCEntry> getNormalStock() {
		return NormalStock;
	}

	public List<ComputerScreen.PCEntry> getHunterStock() {
		return HunterStock;
	}

	private static final String DATA_NAME = Main.MODID + "hunterdata";
    private BlockPos structurePos = null;
    private List<ComputerScreen.PCEntry> NormalStock = new ArrayList<>();
    private List<ComputerScreen.PCEntry> HunterStock = new ArrayList<>();

    
	public ModWorldData() {
		super(DATA_NAME);
	}

	@Override
	public void read(CompoundNBT nbt) {

		if(nbt.contains("structurex")) {
			this.structurePos = new BlockPos(nbt.getInt("structurex"),nbt.getInt("structurey"),nbt.getInt("structurez"));
		}
		int normalSize = nbt.getInt("normalstocksize");
		int hunterSize = nbt.getInt("hunterstocksize");

		
		for(int i = 0; i < normalSize; i++) {
			NormalStock.add(PCEntry.read(nbt.getCompound("normalstock:" + String.valueOf(i))));
		}
		
		
		for(int i = 0; i < hunterSize; i++) {
			HunterStock.add(PCEntry.read(nbt.getCompound("hunterstock:" + String.valueOf(i))));
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		if(structurePos != null) {
			compound.putInt("structurex", structurePos.getX());
			compound.putInt("structurey", structurePos.getY());
			compound.putInt("structurez", structurePos.getZ());

		}
		compound.putInt("normalstocksize", NormalStock.size());
		for(int i = 0; i < NormalStock.size(); i++) {
			compound.put("normalstock:" + String.valueOf(i), NormalStock.get(i).write());
		}
		
		compound.putInt("hunterstocksize", HunterStock.size());
		for(int i = 0; i < HunterStock.size(); i++) {
			compound.put("hunterstock:" + String.valueOf(i), HunterStock.get(i).write());
		}

		
		return compound;
	}

	public static ModWorldData get(ServerWorld world) {
		return world.getSavedData().getOrCreate(ModWorldData::new, DATA_NAME);

	}
	
	public void setBlimpPos(int x, int y, int z) {
		this.structurePos = new BlockPos(x,y,z);
		this.markDirty();
	}
	public BlockPos getBlimpPos() {
		return this.structurePos;
	}
	
	public boolean isBlimpSpawned() {
		return this.structurePos != null;
	}
	
	public void setStock(List<PCEntry> stock, boolean isHunter) {
		if(isHunter) {
			this.HunterStock = stock;
		} else {
			this.NormalStock = stock;
		}
		this.markDirty();
	}
}
