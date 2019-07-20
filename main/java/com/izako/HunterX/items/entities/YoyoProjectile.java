package com.izako.HunterX.items.entities;

import java.util.UUID;
import java.util.concurrent.Delayed;

import javax.annotation.Nullable;

import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.items.tools.Yoyo;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderLeashKnot;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class YoyoProjectile extends EntityThrowable{
	int cooldowncount = 0;
	static int maxticks = 80;
	public static int count = maxticks;
	private boolean comeBack = false;
	 private EntityPlayer owner;
	 
	 
	 
	
	 public YoyoProjectile(World worldIn) {
			super(worldIn);
		}

		public YoyoProjectile(World worldIn, EntityLivingBase throwerIn) {
			super(worldIn, throwerIn);
		}

	 
	
	 public YoyoProjectile(World worldIn,EntityPlayer playerIn, double x, double y, double z) {
			super(worldIn, x, y, z);
			this.owner=playerIn;
		} 
	 
	
	 
	
	 
	 
	
	 
	//Sets the entity NBT
	    public void writeEntityToNBT(NBTTagCompound compound)
	    {
	        compound.setBoolean("comeBack", this.comeBack);
	    }

	    //Reads the Entity NBT
	    public void readEntityFromNBT(NBTTagCompound compound)
	    {
	        this.comeBack = compound.getBoolean("comeBack");
	    }
    
    

	public boolean isComingBack()
    {
        return this.comeBack;
    }

	
	//Manages when the yoyo should come back and when it despawns
	@Override
    public void onEntityUpdate()
    {
		
		
        if( this.ticksExisted > maxticks)
        {
                this.setDead();
                count = maxticks;
        }
        
        if (this.ticksExisted >= count*2) {
			 this.setDead();
			 count = maxticks;
			 
		 }
        
        if(this.ticksExisted == maxticks/2-2 && count == maxticks) {
        	this.motionX = (this.owner.posX - this.posX)*0.05;
			  this.motionY = (this.owner.posY + this.owner.eyeHeight - this.posY)*0.1;
			  this.motionZ = (this.owner.posZ - this.posZ)*0.05;
			 count = this.ticksExisted;
			 cooldowncount ++;
        }
        super.onEntityUpdate();
    }
	
    //called when the yoyo hits something
	@Override
	protected void onImpact(RayTraceResult result) 
	{
		
		
		
		 if(!this.world.isRemote && ticksExisted > 1)
	        {
	                
	        
		 if (!this.world.isRemote)
	        {
	            this.world.setEntityState(this, (byte)3);
	            if(result.entityHit instanceof EntityPlayer)
	            {
	                if(((EntityPlayer) result.entityHit) == this.owner)
	                {
	                    
	                }
	            }
	        }
		
		if (result.entityHit != null && result.entityHit != owner)
        {
            int i = 20;
            this.comeBack = true;
            
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
            

            

            
        }
		
		 if(ticksExisted > 1) {
			  this.motionX = (this.owner.posX - this.posX)*0.05;
			  this.motionY = (this.owner.posY + this.owner.eyeHeight - this.posY)*0.1;
			  this.motionZ = (this.owner.posZ - this.posZ)*0.05;
			 
			 if(cooldowncount ==0) {
			 count = this.ticksExisted;
			 owner.getCooldownTracker().setCooldown(ModItems.YOYO, count);
			 }
			 cooldowncount ++;
			  
		 }
		
	        }
		 
		
		 

		 
		 
		 
		 
		 
		
		
	}

	
	
}
