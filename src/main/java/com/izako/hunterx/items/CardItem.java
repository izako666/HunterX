package com.izako.hunterx.items;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.items.entities.CardEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CardItem extends Item{

	public CardItem(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	 @Override
	 public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	 
		    ItemStack itemstack = playerIn.getHeldItem(handIn);
	        Vec3d aim = playerIn.getLookVec();
	        
	        
	        if (!playerIn.isCreative())
	        {
	            itemstack.shrink(1);
	        }


	        if(!worldIn.isRemote) {
	        	CardEntity entitycard = new CardEntity(CardEntity.type, playerIn, worldIn);
	        
	            entitycard.setPosition(playerIn.getPosX() + aim.x, playerIn.getPosY() + playerIn.getEyeHeight(), playerIn.getPosZ() + aim.z);
	    		entitycard.setMotion(aim.x *2, aim.y *2, aim.z *2);
		       

	            worldIn.addEntity(entitycard);
	        }
	            
	        

		      playerIn.addStat(Stats.ITEM_USED.get(this));
		      return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
	    }

	 
}
