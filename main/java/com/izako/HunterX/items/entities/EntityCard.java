package com.izako.HunterX.items.entities;

import javax.swing.text.html.parser.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCard extends EntityThrowable {
	int maxticks = 150;
	private EntityPlayer owner;

	
	
	public EntityCard(World worldIn) {super(worldIn);}
	public EntityCard(World worldIn, EntityLivingBase throwerIn) {super(worldIn, throwerIn);}
	public EntityCard(World worldIn, EntityPlayer playerIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		owner = playerIn;

	}

	@Override
	public void onEntityUpdate() {
		if (this.ticksExisted > maxticks) {
			this.setDead();
		}

	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(RayTraceResult result) {
		if (!this.world.isRemote && ticksExisted > 1) {

			if (!this.world.isRemote) {
				this.world.setEntityState(this, (byte) 3);

			}

			if (result.entityHit != null && ticksExisted > 1) {
				int i = 6;
				result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), (float) i);

			}

		}

		if (ticksExisted > 1) {
			this.motionX = 0;
			this.motionY = 0;
			this.motionZ = 0;
		}

	}

}
