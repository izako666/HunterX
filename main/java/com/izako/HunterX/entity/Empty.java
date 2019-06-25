package com.izako.HunterX.entity;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTable;

public class Empty extends EntityZombie {

	public Empty(World worldIn) {
		super(worldIn);
		
	}
	
	@Override
	protected void applyEntityAI() {
		
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	@Override 
    protected boolean isValidLightLevel() {
		
		 
	            return true;
	        
    }
	
	@Override
	protected ResourceLocation getLootTable() {
		
		return null;
	}
	
	@Override
	public boolean getCanSpawnHere() {
		
		return this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
	}
	
	@Override
	protected boolean shouldBurnInDay() {
		
		return false;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		
		return false;
	}
	
	@Override
	public void onEntityUpdate() {
		if(this.ticksExisted >= 24000) {
			setDead();
		}
	}
	
	

}
