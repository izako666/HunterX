package com.izako.wypi.abilities;

import com.izako.wypi.APIConfig.AbilityCategory;
import com.izako.wypi.APIDefaults;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;

public abstract class ItemAbility extends ContinuousAbility implements IParallelContinuousAbility
{	
	public ItemAbility(String name, AbilityCategory category)
	{
		super(name, category);
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
	}

	/*
	 *  Event Consumers
	 */
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if (player.getHeldItemMainhand().isEmpty() && !this.getItemStack().isEmpty())
		{
			player.inventory.setInventorySlotContents(player.inventory.currentItem, this.getItemStack());
			return true;
		}
		else
		{
			if(this.getItemStack().isEmpty())
				player.sendMessage(new TranslationTextComponent(APIDefaults.ABILITY_MESSAGE_EMPTY_STACK));
			else
				player.sendMessage(new TranslationTextComponent(APIDefaults.ABILITY_MESSAGE_ANOTHER_ITEM_IN_HAND));
			return false;
		}
	}
	
	/*
	 * 	Methods
	 */
	public abstract ItemStack getItemStack();
	public abstract boolean canBeActive(PlayerEntity player);
}
