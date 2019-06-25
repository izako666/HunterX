package com.izako.HunterX.items.tools;

import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.items.ItemBase;
import com.izako.HunterX.items.entities.EntityCard;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemCard extends ItemBase {

	  public ItemCard(String name)
	    {
		    super(name);
	        this.maxStackSize = 54;
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

	        
	        	EntityCard entitycard = new EntityCard(worldIn,playerIn, 1, 1, 1);
	           
	            entitycard.setPosition(playerIn.posX + aim.x, playerIn.posY + playerIn.eyeHeight, playerIn.posZ + aim.z);
	    		entitycard.motionX = aim.x * 4;
	    		entitycard.motionY = aim.y * 4;
	    		entitycard.motionZ = aim.z * 4;
	    		
	            worldIn.spawnEntity(entitycard);
	            
	        

	        playerIn.addStat(StatList.getObjectUseStats(this));
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	    }

}
