package com.izako.HunterX.items.tools;

import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.items.ItemBase;
import com.izako.HunterX.items.entities.EntityBullet;
import com.izako.HunterX.items.entities.EntityCard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Pistol extends ItemBase {

	public Pistol(String name) {
		super(name);
		 this.maxStackSize = 1;
	     this.setCreativeTab(ModItems.HunterX);
	}
	
	 /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        Vec3d aim = playerIn.getLookVec();
        
        
       

        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if(!worldIn.isRemote) {
        	EntityBullet bullet = new EntityBullet(worldIn, playerIn, 1, 1, 1);
        
            bullet.setPosition(playerIn.posX + aim.x, playerIn.posY + playerIn.eyeHeight, playerIn.posZ + aim.z);
            bullet.motionX = aim.x * 4;
            bullet.motionY = aim.y * 4;
            bullet.motionZ = aim.z * 4;
	       

            worldIn.spawnEntity(bullet);
        }
            
        

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

}
