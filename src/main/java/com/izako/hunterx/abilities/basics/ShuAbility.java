package com.izako.hunterx.abilities.basics;

import java.util.UUID;

import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IOnPlayerRender;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.PassiveAbility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.ToolType;

public class ShuAbility extends PassiveAbility implements IOnPlayerRender, ITrainable {

	public static final UUID SHU_UUID = UUID.fromString("b2bb389e-a2a9-4c42-85d2-0010c09901fc");

	public ShuAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxPassive(Integer.MAX_VALUE);
	}
	@Override
	public String getId() {
		return "shu";
	}

	@Override
	public String getName() {
		return "Shu";
	}

	public double getEfficiencyModifier() {
		return this.getCurrentPowerScale();
	}

	@Override
	public double getPowerScale() {
		return 3;
	}
	@Override
	public void onStartPassive(PlayerEntity p) {
		p.getHeldItemMainhand().addEnchantment(Enchantments.UNBREAKING, 4);
		p.getHeldItemMainhand().addEnchantment(Enchantments.SHARPNESS, 3);
		p.getHeldItemMainhand().getOrCreateTag().putBoolean("activeshu", true);
		super.onStartPassive(p);
	}
	@Override
	public void onEndPassive(PlayerEntity p) {
		if(p.getHeldItemMainhand().getOrCreateTag().getBoolean("activeshu")) {
		ShuAbility.removeEnchantment(Enchantments.UNBREAKING, p.getHeldItemMainhand());
		ShuAbility.removeEnchantment(Enchantments.SHARPNESS, p.getHeldItemMainhand());
		p.getHeldItemMainhand().getOrCreateTag().putBoolean("activeshu", false);
		}
		super.onEndPassive(p);
	}
	
	   public static void removeEnchantment(Enchantment ench, ItemStack stack) {
		      stack.getOrCreateTag();
		      if (!stack.getTag().contains("Enchantments", 9)) {
		         stack.getTag().put("Enchantments", new ListNBT());
		      }

		      ListNBT listnbt = stack.getTag().getList("Enchantments", 10);
		     for(int i = 0; i < listnbt.size(); i++) {
		    	if( (listnbt.getCompound(i).getString("id").equals(ench.getRegistryName().toString()))) {
		    		listnbt.remove(i);
		    	}
		     }
	   }
	   
	   public static boolean canHarvestBlock(BlockState blockIn, ItemStack stack) {
		      Item item = stack.getItem().getItem();
		      if(item instanceof ToolItem) {
		    	  ToolItem tool = (ToolItem) item;
			      int i = tool.getTier().getHarvestLevel();
			      ToolType type = ToolType.PICKAXE;
			      if(tool instanceof ShovelItem) {
			    	  type = ToolType.SHOVEL;
			      } else {
			    	  type = ToolType.AXE;
			      }
			      if (blockIn.getHarvestTool() == type) {
			         return i >= (blockIn.getHarvestLevel() - 1);
			      }
			      Material material = blockIn.getMaterial();
			      return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL;

		      }
			return false;
		   }


}
