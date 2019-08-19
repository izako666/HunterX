package com.izako.HunterX.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.izako.HunterX.world.gen.generators.WorldGenStructure;
import com.izako.HunterX.worlddata.StructureSpawning;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomStructures implements IWorldGenerator {
	
	NBTTagCompound nbt = new NBTTagCompound();
	

	public static final WorldGenStructure BLIMP = new WorldGenStructure("blimp");
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,IChunkProvider chunkProvider) {
		StructureSpawning data = StructureSpawning.get(world);
		switch(world.provider.getDimension()) {
		case 1:
			
		break;
		
		case 0:
		if(data.getBlimpCount() < 1) {
			generateStructure(BLIMP, world, random, chunkX, chunkZ, 1000, Blocks.GRASS , BiomePlains.class);
			   System.out.println(Integer.toString(data.getBlimpCount()));

		} 
		break;
		
		case -1:
			
		}
		
		
	}
	private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock,  Class<?>... classes) {
		
		
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		
		if(!world.isRemote) {
			
		StructureSpawning data = StructureSpawning.get(world);

			
			
		
		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGenerationHeight(world, x, z, topBlock);
		BlockPos pos = new BlockPos(x,y,z);
		
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
		
		if(world.getWorldType() != WorldType.FLAT) {
			
			if(classesList.contains(biome)) {
				if (random.nextInt(chance) == 0) {
					generator.generate(world, random, pos);
                     data.setPos(pos.getX(), pos.getY(), pos.getZ());					
         			data.setBlimpCount(data.getBlimpCount() + 1);

					
					
					
				}
					
			
				}
			}
			
		}
		
	}
	
	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock) {
		int y = world.getHeight();
		boolean foundground = false;
		
		while(!foundground && y-- >= 0) {
			Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundground = block == topBlock;
		}
		
	return y;
	}

}
