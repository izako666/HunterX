package com.izako.HunterX.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityFoxbear extends EntityPolarBear {
	boolean parent = true;

	public EntityFoxbear(World worldIn, boolean parent) {
		super(worldIn);
		this.parent = parent;
		
		
		
	}
	
	public EntityFoxbear(World worldIn) {
		super(worldIn);
		
		
	}
	
	public EntityAgeable createChild(EntityAgeable ageable)
    {
        return new EntityFoxbear(this.world, false);
    }
	
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		
		if(this.parent == true && !this.world.isRemote) {
			
			EntityLivingBase child = new EntityFoxbear(this.world, false);
			
			child.setLocationAndAngles(this.posX + 1, this.posY, this.posZ + 1, this.rotationYaw, 0.0F);
			this.world.spawnEntity(child);
		}
		
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	
	@Override
	public boolean getCanSpawnHere() {

		return this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
	}

}
