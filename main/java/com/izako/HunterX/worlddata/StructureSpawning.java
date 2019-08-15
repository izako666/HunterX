package com.izako.HunterX.worlddata;

import org.apache.logging.log4j.LogManager;

import com.izako.HunterX.util.Reference;
import com.sun.jna.platform.win32.Winioctl.STORAGE_DEVICE_NUMBER;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StructureSpawning extends WorldSavedData {

	private static final String COUNT = "STRUCTUREDATA";
	private NBTTagCompound data = new NBTTagCompound();
	private int blimpCount = 0;

	public StructureSpawning(String s) {
		super(s);
	}

	public StructureSpawning() {
		super(COUNT);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		nbt.getInteger(COUNT);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger(COUNT, this.blimpCount);
		return nbt;
	}

	public static StructureSpawning get(World world) {
		StructureSpawning save = (StructureSpawning) world.getMapStorage().getOrLoadData(StructureSpawning.class,
				COUNT);
		world.getMapStorage().getOrLoadData(StructureSpawning.class, COUNT);

		if (save == null) {

			save = new StructureSpawning();
			world.getMapStorage().setData(COUNT, save);

		} else {
			LogManager.getLogger().debug("getting");
		}
		return save;
	}

	public void setBlimpCount(int value) {
		this.blimpCount = value;
	}
	public int getBlimpCount() {return this.blimpCount;}}
