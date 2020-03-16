package com.izako.hunterx.items.entities;

import com.izako.hunterx.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class YoyoEntity extends ProjectileItemEntity{

	  public YoyoEntity(EntityType<? extends YoyoEntity> type, World worldIn) {
	      super(type, worldIn);
	   }

	   public YoyoEntity(EntityType<? extends YoyoEntity> type, double x, double y, double z, World worldIn) {
	      super(type, x, y, z, worldIn);
	   }

	   public YoyoEntity(EntityType<? extends YoyoEntity> type, LivingEntity livingEntityIn, World worldIn) {
	      super(type, livingEntityIn, worldIn);
	   }

	@Override
	protected Item func_213885_i() {
		// TODO Auto-generated method stub
		return ModItems.YOYO;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
	      if (result.getType() == RayTraceResult.Type.ENTITY ) {
	          Entity entity = ((EntityRayTraceResult)result).getEntity();
	          entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)20);
	          System.out.println("success");
	       }

	       if (!this.world.isRemote) {
	          this.world.setEntityState(this, (byte)3);
	          this.remove();
	       }

	    }

}
