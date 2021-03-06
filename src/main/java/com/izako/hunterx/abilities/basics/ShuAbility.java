package com.izako.hunterx.abilities.basics;

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
		p.getHeldItemMainhand().addEnchantment(Enchantments.UNBREAKING, (int) (Helper.getTrueValue(4, this, p)));
		p.getHeldItemMainhand().addEnchantment(Enchantments.SHARPNESS, (int) (Helper.getTrueValue(3, this, p)));
		p.getHeldItemMainhand().getOrCreateTag().putBoolean("activeshu", true);
		super.onStartPassive(p);
		
		Helper.consumeAura(20, p, this);
	}
	@Override
	public void onEndPassive(LivingEntity p) {
		if(!p.getHeldItemMainhand().hasTag())
			return;
		if(p.getHeldItemMainhand().getTag().getBoolean("activeshu")) {
		ShuAbility.removeEnchantment(Enchantments.UNBREAKING, p.getHeldItemMainhand());
		ShuAbility.removeEnchantment(Enchantments.SHARPNESS, p.getHeldItemMainhand());
		}
		p.getHeldItemMainhand().getTag().remove("activeshu");
		
		if(p.getHeldItemMainhand().getTag().contains("Enchantments", 9) &&  p.getHeldItemMainhand().getTag().getList("Enchantments", 10).isEmpty()) {
			p.getHeldItemMainhand().getTag().remove("Enchantments");
		}
		if(p.getHeldItemMainhand().getTag().isEmpty()) {
			p.getHeldItemMainhand().setTag(null);
		}
		super.onEndPassive(p);
	}
	
	   public static void removeEnchantment(Enchantment ench, ItemStack stack) {
		     if(!stack.hasTag())
		    	 return;
		     
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
	@Override
	public List<Ability> getBlackList() {
		return Arrays.asList(ModAbilities.KO_ABILITY,ModAbilities.GYO_ABILITY,ModAbilities.ZETSU_ABILITY);
	}


}
