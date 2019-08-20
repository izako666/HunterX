package com.izako.HunterX.items.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGonHook extends EntityFishHook{
	EntityPlayer thrower;
	 @SideOnly(Side.CLIENT)
	    public EntityGonHook(World worldIn, EntityPlayer p_i47290_2_, double x, double y, double z)
	    {
	        super(worldIn, p_i47290_2_, x, y, z);
			 this.thrower = p_i47290_2_;
	    }

	    public EntityGonHook(World worldIn, EntityPlayer fishingPlayer)
	    {
	        super(worldIn, fishingPlayer);
	        this.thrower = fishingPlayer;
	    }
	    
	    @Override
	    public void onUpdate() {
	    	if(this.caughtEntity instanceof EntityLiving) {
	    		this.caughtEntity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getAngler()), (float) 3);
	    	}
	    	super.onUpdate();
	    }
}
