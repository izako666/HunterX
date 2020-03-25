package com.izako.hunterx.structures;

import java.util.Random;

import com.izako.hunterx.init.ModStructures;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class BlimpPiece extends TemplateStructurePiece {

	public BlimpPiece(IStructurePieceType p_i51339_1_, CompoundNBT p_i51339_2_) {
		super(p_i51339_1_, p_i51339_2_);
		// TODO Auto-generated constructor stub
	}
	
	public BlimpPiece(TemplateManager temp, CompoundNBT nbt) {
		super(ModStructures.BLIMP_PIECE_TYPE, nbt);
		// TODO Auto-generated constructor stub
	}
	public BlimpPiece(IStructurePieceType type, int value) {
		super(type, value);
		// TODO Auto-generated constructor stub
	}



	@Override
	protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand,
			MutableBoundingBox sbb) {
		// TODO Auto-generated method stub
		
	}

}
