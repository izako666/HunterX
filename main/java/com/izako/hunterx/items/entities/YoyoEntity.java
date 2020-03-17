package com.izako.hunterx.items.entities;

import com.izako.hunterx.init.ModItems;
import com.izako.hunterx.registerers.ModEventSubscriber;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class YoyoEntity extends ProjectileItemEntity{

	   private static final DataParameter<ItemStack> ITEMSTACK_DATA = EntityDataManager.createKey(YoyoEntity.class, DataSerializers.ITEMSTACK);
	  public YoyoEntity(EntityType<? extends YoyoEntity> type, World worldIn) {
	      super(type, worldIn);
	   }

	   public YoyoEntity(EntityType<? extends YoyoEntity> type, double x, double y, double z, World worldIn) {
	      super(type, x, y, z, worldIn);
	   }

	   public YoyoEntity(EntityType<? extends YoyoEntity> type, LivingEntity livingEntityIn, World worldIn) {

		   super(type, livingEntityIn, worldIn);
	   }

	   public YoyoEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	    {
	        super(ModEventSubscriber.type, worldIn);
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

	
	
	 @Override
	  public IPacket<?> createSpawnPacket() {
	     return  NetworkHooks.getEntitySpawningPacket(this);
  
	 }

	@Override
	protected Item func_213885_i() {
		// TODO Auto-generated method stub
		return ModItems.YOYO;
	}

	  @Override
	  protected ItemStack func_213882_k() {
	      return this.getDataManager().get(ITEMSTACK_DATA);
	   }
	  
	  @Override
	  protected void registerData() {
	      this.getDataManager().register(ITEMSTACK_DATA, ItemStack.EMPTY);
	   }
}
