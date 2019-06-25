package com.izako.HunterX.items.entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityNeedle extends EntityThrowable {

	int maxticks = 150;
	private EntityPlayer owner;

	public EntityNeedle(World worldIn, EntityPlayer playerIn, double x, double y, double z) {
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
				int damageAmount = 8;
				result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), (float) damageAmount);

			}

		}

		if (ticksExisted > 1) {
			this.motionX = 0;
			this.motionY = 0;
			this.motionZ = 0;
		}

	}

}
