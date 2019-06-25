package com.izako.HunterX.items.tools;
import com.google.common.collect.Multimap;
import com.izako.HunterX.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class KurapikasSword extends ItemSword{
	private final float AttackDamage;
	
	public KurapikasSword(String name, ToolMaterial material, int damage) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModItems.HunterX);
		
		ModItems.ITEMS.add(this);
		this.AttackDamage = damage;
		

	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
	if(playerIn.isSneaking()) {
		NBTTagCompound nbt = playerIn.getHeldItem(handIn).serializeNBT();
		if(this == ModItems.KURAPIKAS_SWORD_UNSHEATHED) {
		nbt.setString("id", ModItems.KURAPIKAS_SWORD.getRegistryName().toString());
		}
		else {
			nbt.setString("id", ModItems.KURAPIKAS_SWORD_UNSHEATHED.getRegistryName().toString());
			
		}
	 ItemStack stack = new ItemStack(nbt);
		return new ActionResult<ItemStack> (EnumActionResult.SUCCESS, stack);
	}
	else {
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
	}
	
	@Override
	 public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.AttackDamage, 0));
        }

        return multimap;
    }
	
}



















