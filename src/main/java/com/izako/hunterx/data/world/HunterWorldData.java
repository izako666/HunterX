package com.izako.hunterx.data.world;

import com.izako.hunterx.Main;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

public class HunterWorldData extends WorldSavedData{

	private static final String IDENTIFIER = Main.MODID + "_worlddata";
	public HunterWorldData() {
		super(IDENTIFIER);
	}
	public boolean spawned = false;
	public HunterWorldData(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static HunterWorldData get(ServerWorld world) {
		  // The IS_GLOBAL constant is there for clarity, and should be simplified into the right branch.
		  DimensionSavedDataManager storage = world.getSavedData();
		  HunterWorldData instance = (HunterWorldData) storage.getOrCreate(HunterWorldData::new, IDENTIFIER);

		  if (instance == null) {
		    instance = new HunterWorldData();
		    storage.set(instance);
		  }
		  return instance;
		}

	@Override
	public void read(CompoundNBT nbt) {
		// TODO Auto-generated method stub
		this.spawned = nbt.getBoolean("spawned");
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		// TODO Auto-generated method stub
		compound.putBoolean("spawned", this.spawned);
		return compound;
	}

	public boolean getSpawned() {
		return this.spawned;
	}
	
	public void setSpawned(boolean val) {
		this.spawned = val;
		this.markDirty();
	}
}
