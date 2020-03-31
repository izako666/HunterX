package com.izako.hunterx.items;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.items.entities.YoyoEntity;
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

public class YoyoItem extends Item {

	public YoyoItem(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
		this.addPropertyOverride(new ResourceLocation("cast"), this.castProperty);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		Vec3d aim = playerIn.getLookVec();

		YoyoEntity yoyo_front = new YoyoEntity(YoyoEntity.type, playerIn, worldIn, item);
		if (!worldIn.isRemote) {

			playerIn.getCooldownTracker().setCooldown(this, 200);

			yoyo_front.setPosition(playerIn.posX + aim.x, playerIn.posY + playerIn.getEyeHeight(),
					playerIn.posZ + aim.z);
			yoyo_front.setMotion(aim.x * 2, aim.y * 2, aim.z * 2);
			playerIn.world.addEntity(yoyo_front);
			System.out.println("shouldspawn");

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
