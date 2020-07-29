package com.izako.hunterx.data.worlddata;

import com.izako.hunterx.Main;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

public class ModWorldData extends WorldSavedData {

	private static final String DATA_NAME = Main.MODID + "hunterdata";
    private BlockPos structurePos = null;
	public ModWorldData() {
		super(DATA_NAME);
	}

	@Override
	public void read(CompoundNBT nbt) {

		if(nbt.contains("structurex")) {
			this.structurePos = new BlockPos(nbt.getInt("structurex"),nbt.getInt("structurey"),nbt.getInt("structurez"));
		}
		
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		if(structurePos != null) {
			compound.putInt("structurex", structurePos.getX());
			compound.putInt("structurey", structurePos.getY());
			compound.putInt("structurez", structurePos.getZ());

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
}
