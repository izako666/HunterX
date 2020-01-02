package com.izako.HunterX.items.tools;

import java.util.UUID;

import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.items.ItemBase;
import com.izako.HunterX.izapi.Misc;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.EntityStatsClientSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class HunterCard extends ItemBase {
	
	private AttributeModifier auraModifier;
	public static UUID aura_attributemodifier_uuid = UUID.fromString("aa18be1b-b1d0-4917-a168-42dd240d000d");
	
	public HunterCard(String name) {
		super(name);
		 this.maxStackSize = 1;
	     this.setCreativeTab(ModItems.HunterX);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		IEntityStats stats = playerIn.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		
		Misc.sendMsg(playerIn, ""+stats.getAttackStat(), null);
		Misc.sendMsg(playerIn, ""+stats.getHealthStat(), null);
		Misc.sendMsg(playerIn, ""+stats.getDefenseStat(), null);
		
		stats.setAura(20);
		if(playerIn instanceof EntityPlayerMP) {
			ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getAura(), 5, false), (EntityPlayerMP) playerIn);
		}
		
		
		
		
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
