package com.izako.hunterx.structures;

import java.util.Random;
import java.util.function.Function;

import com.izako.hunterx.data.worlddata.ModWorldData;
import com.mojang.datafixers.Dynamic;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class BlimpStructure extends Structure<NoFeatureConfig> {

	public BlimpStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51470_1_) {
		super(p_i51470_1_);
	}

	public String getStructureName() {
		return "Blimp";

	}

	public int getSize() {
		return 10;
	}


	
	public Structure.IStartFactory getStartFactory() {
		return BlimpStructure.Start::new;
	}

	protected int getSeedModifier() {
		return 165745296;
	}

	public static class Start extends StructureStart {
		public Start(Structure<?> p_i50497_1_, int p_i50497_2_, int p_i50497_3_,
				MutableBoundingBox p_i50497_5_, int p_i50497_6_, long p_i50497_7_) {
			super(p_i50497_1_, p_i50497_2_, p_i50497_3_, p_i50497_5_, p_i50497_6_, p_i50497_7_);
		}

		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ,
				Biome biomeIn) {
			BlockPos blockpos = new BlockPos(chunkX * 16, 90, chunkZ * 16);
			CompoundNBT nbt = new CompoundNBT();
			nbt.putInt("TPX", blockpos.getX());
			nbt.putInt("TPY", blockpos.getY());
			nbt.putInt("TPZ", blockpos.getZ());

			IWorld world = ObfuscationReflectionHelper.getPrivateValue(ChunkGenerator.class, generator, "world");
			if(world instanceof ServerWorld) {
				ModWorldData data = ModWorldData.get((ServerWorld) world);
			   if(!data.isBlimpSpawned()) {
				data.setBlimpPos(blockpos.getX(), blockpos.getY(), blockpos.getZ());
			
			BlimpPiece.addPieces(templateManagerIn, this.components, nbt, this.rand);
			this.recalculateStructureSize();
			   }
			}
		}
	}

	@Override
	public boolean canBeGenerated(net.minecraft.world.biome.BiomeManager biomeManagerIn, ChunkGenerator<?> generatorIn,
			Random randIn, int chunkX, int chunkZ, Biome biomeIn) {
		
		boolean flag1 = randIn.nextInt(500) > 498;
		boolean flag2 = biomeIn == Biomes.PLAINS || biomeIn == Biomes.DESERT || biomeIn == Biomes.TAIGA_HILLS || biomeIn == Biomes.MOUNTAINS;
		 if(flag1 && flag2) {
		   return true;
	   }
		return false; 
	}
}
