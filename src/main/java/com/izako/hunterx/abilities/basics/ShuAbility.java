package com.izako.hunterx.abilities.basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.IBlacklistPassive;
import com.izako.hunterx.izapi.ability.IOnPlayerRender;
import com.izako.hunterx.izapi.ability.ITrainable;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PassiveAbility;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.ToolType;

public class ShuAbility extends PassiveAbility implements IOnPlayerRender, ITrainable, IBlacklistPassive {

	public static final UUID SHU_UUID = UUID.fromString("b2bb389e-a2a9-4c42-85d2-0010c09901fc");

	public ShuAbility() {
		this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxPassive(Integer.MAX_VALUE).setMaxCooldown(20 * 20).setNenType(NenType.UNKNOWN);
	}
	@Override
	public void duringPassive(LivingEntity p) {
		
		if(this.getPassiveTimer() % 20 == 0) {
			Helper.consumeAura(2, p, this);
		}
		super.duringPassive(p);
	}
	@Override
	public String getId() {
		return "shu";
	}

	@Override
	public String getName() {
		return "Shu";
	} 
	
	@Override
	public String getDesc() {
		return "Shu clads the item you're using for increased efficiency.";
	}

	public double getEfficiencyModifier() {
		return this.getCurrentPowerScale();
	}

	@Override
	public double getPowerScale() {
		return 3;
	}
	@Override
	public void onStartPassive(LivingEntity p) {
		int shuSharpLvl = (int) (Helper.getTrueValue(3, this, p));
		int shuUnbreakLvl =  (int) (Helper.getTrueValue(4, this, p));
		ShuAbility.addEnchantment(Enchantments.UNBREAKING, shuUnbreakLvl,p.getHeldItemMainhand());
		ShuAbility.addEnchantment(Enchantments.SHARPNESS, shuSharpLvl, p.getHeldItemMainhand());
		p.getHeldItemMainhand().getOrCreateTag().putBoolean("activeshu", true);
		p.getHeldItemMainhand().getOrCreateTag().putInt("shuSharpLvl", shuSharpLvl);
		p.getHeldItemMainhand().getOrCreateTag().putInt("shuUnbreakLvl", shuUnbreakLvl);

		super.onStartPassive(p);
		
		Helper.consumeAura(20, p, this);
	}
	@Override
	public void onEndPassive(LivingEntity p) {
		if(!p.getHeldItemMainhand().hasTag())
			return;
		if(p.getHeldItemMainhand().getTag().getBoolean("activeshu")) {
			int shuSharpLvl = p.getHeldItemMainhand().getTag().getInt("shuSharpLvl");
			int shuUnbreakLvl =  p.getHeldItemMainhand().getTag().getInt("shuUnbreakLvl");
		ShuAbility.removeEnchantment(Enchantments.UNBREAKING,shuUnbreakLvl, p.getHeldItemMainhand());
		ShuAbility.removeEnchantment(Enchantments.SHARPNESS,shuSharpLvl,p.getHeldItemMainhand());
		}
		p.getHeldItemMainhand().getTag().remove("activeshu");
		p.getHeldItemMainhand().getTag().remove("shuSharpLvl");
		p.getHeldItemMainhand().getTag().remove("shuUnbreakLvl");

		
		if(p.getHeldItemMainhand().getTag().contains("Enchantments", 9) &&  p.getHeldItemMainhand().getTag().getList("Enchantments", 10).isEmpty()) {
			p.getHeldItemMainhand().getTag().remove("Enchantments");
		}
		if(p.getHeldItemMainhand().getTag().isEmpty()) {
			p.getHeldItemMainhand().setTag(null);
		}
		super.onEndPassive(p);
	}
	
	public static void addEnchantment(Enchantment ench, int lvl, ItemStack stack) {

		ListNBT listnbt = stack.getEnchantmentTagList();
		int finalLevel = 0;
		int index = -1;
		boolean addedEnch = false;
		for(int i = 0; i < listnbt.size(); i++) {
			if(listnbt.getCompound(i).getString("id").equalsIgnoreCase(ench.getRegistryName().toString())) {
				int initialLevel = listnbt.getCompound(i).getShort("lvl");
				
				 finalLevel = initialLevel + lvl;
				index = i;
				addedEnch = true;

			} 
		}
		
		if(!addedEnch) {

			stack.addEnchantment(ench, lvl);

		} else {
			listnbt.remove(index);
			stack.addEnchantment(ench, finalLevel);

		}
	}
	   public static void removeEnchantment(Enchantment ench,int lvl, ItemStack stack) {
		   
		     if(!stack.hasTag())
		    	 return;
		     
		      if (!stack.getTag().contains("Enchantments", 9)) {
		         stack.getTag().put("Enchantments", new ListNBT());
		      }

		      ListNBT listnbt = stack.getTag().getList("Enchantments", 10);
		      boolean removedEnch = false;
		     for(int i = 0; i < listnbt.size(); i++) {
		    	if( (listnbt.getCompound(i).getString("id").equals(ench.getRegistryName().toString()))) {


		    		int finalLevel  = listnbt.getCompound(i).getShort("lvl") - lvl;
		    		
		    		
		    		listnbt.remove(i);
		    		if(finalLevel > 0) {
		    		stack.addEnchantment(ench, finalLevel);
		    		}
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
	@Override
	public List<Ability> getBlackList() {
		return Arrays.asList(ModAbilities.KO_ABILITY,ModAbilities.GYO_ABILITY,ModAbilities.ZETSU_ABILITY);
	}


}
