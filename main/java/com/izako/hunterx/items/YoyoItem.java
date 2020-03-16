package com.izako.hunterx.items;

import com.izako.hunterx.items.entities.YoyoEntity;
import com.izako.hunterx.registerers.ModEventSubscriber;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class YoyoItem extends Item {

	public YoyoItem(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

    

	 @Override
	 public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		 ItemStack item = playerIn.getHeldItem(handIn);
			Vec3d aim = playerIn.getLookVec();
			
			if(!worldIn.isRemote) {
				
				YoyoEntity yoyo_front = new YoyoEntity(ModEventSubscriber.type, playerIn, worldIn);
				playerIn.getCooldownTracker().setCooldown(this, 60);
				
							
				yoyo_front.setPosition(playerIn.posX + aim.x, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ + aim.z);
				yoyo_front.setMotion(aim.x * 2, aim.y * 2, aim.z * 2);
				worldIn.addEntity(yoyo_front);
				System.out.println("shouldspawn");
				
	   }
	
		      playerIn.addStat(Stats.ITEM_USED.get(this));
		      return new ActionResult<ItemStack>(ActionResultType.SUCCESS, item);

	 }
}
