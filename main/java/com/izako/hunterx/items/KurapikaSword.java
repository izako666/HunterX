package com.izako.hunterx.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class KurapikaSword extends SwordItem {
	private double attackDamage = 0;

	public KurapikaSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
		this.addPropertyOverride(new ResourceLocation("sheath"), this.sheathProperty);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

		if (!worldIn.isRemote()) {

			IAttributeInstance attribute = ((LivingEntity) playerIn)
					.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);

			AttributeModifier attr = new AttributeModifier(ATTACK_DAMAGE_MODIFIER.toString(), 10,
					AttributeModifier.Operation.ADDITION);
			ItemStack itemstack = playerIn.getHeldItem(handIn);
			if (!itemstack.hasTag()) {
				itemstack.setTag(new CompoundNBT());
			}

			CompoundNBT nbt = itemstack.getTag();
			if (playerIn.isSneaking()) {
				if (nbt.getBoolean("sheathed")) {
					nbt.putBoolean("sheathed", false);
					attribute.removeAllModifiers();
					attribute.removeModifier(attr);
					attribute.applyModifier(attr);
					return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);

				} else if (!nbt.getBoolean("sheathed")) {
					nbt.putBoolean("sheathed", true);
					attr = new AttributeModifier(ATTACK_DAMAGE_MODIFIER.toString(), 6,
							AttributeModifier.Operation.ADDITION);

					attribute.removeAllModifiers();
					attribute.removeModifier(attr);
					attribute.applyModifier(attr);
					return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);

				} else {
				}
				return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);

			}
		}
		return new ActionResult<ItemStack>(ActionResultType.PASS, playerIn.getHeldItem(handIn));
	}

	private IItemPropertyGetter sheathProperty = (itemStack, world, livingEntity) -> {
		if (itemStack.hasTag()) {
			CompoundNBT nbt = itemStack.getTag();

			if (!nbt.getBoolean("sheathed")) {

				return 1.0f;
			}
		} else {
			return 0.0f;
		}
		return 0.0f;
	};
	/*
	 * @Override public Multimap<String, AttributeModifier>
	 * getAttributeModifiers(EquipmentSlotType equipmentSlot) { Multimap<String,
	 * AttributeModifier> multimap = HashMultimap.create();
	 * 
	 * AttributeModifier attr = new
	 * AttributeModifier(ATTACK_DAMAGE_MODIFIER.toString(), attackDamage,
	 * AttributeModifier.Operation.ADDITION);
	 * 
	 * 
	 * multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), attr);
	 * 
	 * return multimap;
	 * 
	 * }
	 */

}
