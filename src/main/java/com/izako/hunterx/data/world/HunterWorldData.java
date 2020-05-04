package com.izako.hunterx.data.world;

import com.izako.hunterx.Main;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
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
	public BlockPos pos;
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
		this.pos = new BlockPos(nbt.getInt("posx"),nbt.getInt("posy"),nbt.getInt("posz"));
	
		System.out.println("debug");
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		// TODO Auto-generated method stub
		compound.putBoolean("spawned", this.spawned);
		compound.putInt("posx", this.pos.getX());
		compound.putInt("posy", this.pos.getY());
		compound.putInt("posz", this.pos.getZ());

		return compound;
	}

	public boolean getSpawned() {
		return this.spawned;
	}
	
	public void setSpawned(boolean val) {
		this.spawned = val;
		this.markDirty();
	}
	public void setPos(BlockPos pos) {
		this.pos = pos;
		this.markDirty();
	}
	public BlockPos getPos() {
		return this.pos;
	}
}