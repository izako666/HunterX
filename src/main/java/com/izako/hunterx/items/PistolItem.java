package com.izako.hunterx.items;

import com.izako.hunterx.items.entities.BulletEntity;
import com.izako.hunterx.items.entities.YoyoEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PistolItem  extends Item{

	public PistolItem(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	@Override
public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	  	
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        Vec3d aim = playerIn.getLookVec();
        
        
       


        if(!worldIn.isRemote) {
        	BulletEntity bullet = new BulletEntity(BulletEntity.type, playerIn, worldIn);
        	playerIn.getCooldownTracker().setCooldown(this, 20);
        
            bullet.setPosition(playerIn.posX + aim.x, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ + aim.z);
            bullet.setMotion(aim.x * 4, aim.y * 4, aim.z * 4);
	       

            worldIn.addEntity(bullet);
        }
            
        

		playerIn.addStat(Stats.ITEM_USED.get(this));
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
	}
}
