package com.izako.HunterX.items.tools;



import com.izako.HunterX.items.ItemBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/*public class Yoyo extends ItemBase{
	public static EntityPlayer owner;
	public Yoyo(String name) {
		super(name);
		this.setMaxStackSize(1);
	}
	
	private int coolDown = 0;
	
	
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean B) {
	
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
		
		ItemStack item = playerIn.getHeldItem(handIn);
		Vec3d aim = playerIn.getLookVec();
		
		if(!worldIn.isRemote) {
			
			YoyoProjectile yoyo_front = new  YoyoProjectile(worldIn,playerIn,1,1,1);
			playerIn.getCooldownTracker().setCooldown(this, 60);
			
			
			worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			
			yoyo_front.setPosition(playerIn.posX + aim.x, playerIn.posY + playerIn.eyeHeight, playerIn.posZ + aim.z);
			yoyo_front.motionX = aim.x * 2;
			yoyo_front.motionY = aim.y * 2;
			yoyo_front.motionZ = aim.z * 2;
			worldIn.spawnEntity(yoyo_front);
			
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
		
		
		
	}

}*/
