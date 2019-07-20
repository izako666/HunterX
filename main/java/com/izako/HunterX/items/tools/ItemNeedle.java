package com.izako.HunterX.items.tools;

import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.items.ItemBase;
import com.izako.HunterX.items.entities.EntityCard;
import com.izako.HunterX.items.entities.EntityNeedle;

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

public class ItemNeedle extends ItemBase{



	  public ItemNeedle(String name)
	    {
		    super(name);
	        this.maxStackSize = 16;
	        this.setCreativeTab(ModItems.HunterX);
	    }

	    /**
	     * Called when the equipped item is right clicked.
	     */
	    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	    {
	    	
	        ItemStack itemstack = playerIn.getHeldItem(handIn);
	        Vec3d aim = playerIn.getLookVec();
	        
	        
	        if (!playerIn.capabilities.isCreativeMode)
	        {
	            itemstack.shrink(1);
	        }

	        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

	        if(!worldIn.isRemote) {
	        	EntityNeedle entityneedle = new EntityNeedle(worldIn,playerIn, 1, 1, 1);
	           
	            entityneedle.setPosition(playerIn.posX + aim.x, playerIn.posY + playerIn.eyeHeight, playerIn.posZ + aim.z);
	    		entityneedle.motionX = aim.x * 4;
	    		entityneedle.motionY = aim.y * 4;
	    		entityneedle.motionZ = aim.z * 4;
	    		
	            worldIn.spawnEntity(entityneedle);
	        }
	        

	        playerIn.addStat(StatList.getObjectUseStats(this));
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	    }

}
