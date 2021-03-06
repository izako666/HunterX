package com.izako.hunterx.items;

import com.izako.hunterx.items.entities.YoyoEntity;
import com.izako.hunterx.izapi.IThugDrop;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class YoyoItem extends Item implements IThugDrop {

	public YoyoItem(Properties properties) {
		super(properties);
		this.addPropertyOverride(new ResourceLocation("cast"), this.castProperty);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		Vec3d aim = playerIn.getLookVec();

		YoyoEntity yoyo_front = new YoyoEntity(YoyoEntity.TYPE, playerIn, worldIn, item);
		if (!worldIn.isRemote) {
			playerIn.getCooldownTracker().setCooldown(this, 200);

			yoyo_front.setPosition(playerIn.getPosX() + aim.x, playerIn.getPosY() + playerIn.getEyeHeight(),
					playerIn.getPosZ() + aim.z);
			yoyo_front.setMotion(aim.x * 2, aim.y * 2, aim.z * 2);
			playerIn.world.addEntity(yoyo_front);

		}

		item.damageItem(1, playerIn, (p_220045_0_) -> {
			p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		});
		playerIn.addStat(Stats.ITEM_USED.get(this));
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, item);

	}

	private IItemPropertyGetter castProperty = (itemStack, world, livingEntity) -> {
		if (itemStack.hasTag()) {
			CompoundNBT nbt = itemStack.getTag();

			if (nbt.getBoolean("cast")) {

				return 1.0f;
			}
		} else {
			return 0.0f;
		}
		return 0.0f;
	};

}
