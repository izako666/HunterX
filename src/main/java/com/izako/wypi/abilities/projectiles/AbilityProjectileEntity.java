package com.izako.wypi.abilities.projectiles;

import java.io.Serializable;
import java.util.List;

import com.izako.wypi.abilities.events.AbilityProjectileEvents;
import com.izako.wypi.abilities.renderers.AbilityProjectileRenderer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.Tag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkHooks;

public class AbilityProjectileEntity extends ThrowableEntity
{
	private int life = 64;
	private int maxLife = 64;
	private double collisionSize = 1;
	private float damage = 1;
	private float gravity = 0.0001F;
	private boolean isPhysical = false;
	private boolean canPassThroughBlocks = false;
	private boolean canPassThroughEntities = false;
	private boolean canGetStuckInGround = false;
	protected boolean stuckInGround = false;

	// Setting the defaults so that no crash occurs and so they will be null safe.
	public IOnEntityImpact onEntityImpactEvent = (hitEntity) -> { this.onBlockImpactEvent.onImpact(hitEntity.getPosition()); };
	public IOnBlockImpact onBlockImpactEvent = (hit) -> { };
	public IOnTick onTickEvent = () -> {};
	public IWithEffects withEffects = () -> { return new EffectInstance[0]; };

	public AbilityProjectileEntity(EntityType type, World world)
	{
		super(type, world);
	}

	public AbilityProjectileEntity(EntityType type, World world, double x, double y, double z)
	{
		super(type, x, y, z, world);
	}

	public AbilityProjectileEntity(EntityType type, World world, LivingEntity player)
	{
		super(type, player, world);
		this.maxLife = life;
	}

	@Override
	public void tick()
	{		
		super.tick();

		if(!this.world.isRemote)
		{
			if (this.life <= 0)
			{
				this.life = this.maxLife;
				this.remove();
			}
			else
				this.life--;
		}
		
		Vec3d vec31 = new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ());
		Vec3d vec3 = new Vec3d(this.getPosX() + this.getMotion().x, this.getPosY() + this.getMotion().y, this.getPosZ() + this.getMotion().z);
		RayTraceResult hit = this.world.rayTraceBlocks(new RayTraceContext(vec3, vec31, BlockMode.OUTLINE, FluidMode.ANY, this));

		double sizeX = this.collisionSize;
		double sizeY = this.collisionSize;
		double sizeZ = this.collisionSize;

		AxisAlignedBB aabb = new AxisAlignedBB(this.getPosX(), this.getPosY(), this.getPosZ(), this.getPosX(), this.getPosY(), this.getPosZ()).grow(sizeX, sizeY, sizeZ);
		List list = world.getEntitiesWithinAABB(LivingEntity.class, aabb);

		Entity entity = null;

		for (int i = 0; i < list.size(); ++i)
		{
			Entity target = (Entity) list.get(i);

			if (target.canBeCollidedWith() && (target != this.getThrower() || this.ticksExisted >= 5))
			{
				entity = target;
			}
		}
		
		if(entity == this.getThrower())
			return;
		
		if (entity != null)
			hit = new EntityRayTraceResult(entity);

		if (hit.getType() == RayTraceResult.Type.ENTITY && ((EntityRayTraceResult) hit).getEntity() instanceof LivingEntity) 
			this.onImpact(hit);
			
		
		this.onTickEvent.onTick();
		
	}

	@Override
	public boolean handleFluidAcceleration(Tag<Fluid> fluidTag)
	{
		if(this.inWater)
			this.doWaterSplashEffect();
		return false;
	}

	@Override
	protected void onImpact(RayTraceResult hit)
	{
		if(!this.world.isRemote)
		{
			if(hit.getType() == RayTraceResult.Type.ENTITY)
			{
				EntityRayTraceResult entityHit = (EntityRayTraceResult) hit;

				if(entityHit.getEntity() instanceof LivingEntity && this.getThrower() != null)
				{
					LivingEntity hitEntity = (LivingEntity) entityHit.getEntity();

					if(hitEntity == this.getThrower())
						return;
					
					AbilityProjectileEvents.Hit event = new AbilityProjectileEvents.Hit(this, hit);
					if(MinecraftForge.EVENT_BUS.post(event))
						return;
					
					hitEntity.attackEntityFrom(this.causeAbilityProjectileDamage(), this.damage);
						
					if(this.withEffects.getEffects().length > 0)
					{
						for(EffectInstance instance : this.withEffects.getEffects())
						{
							hitEntity.addPotionEffect(instance);
							if(this.getThrower() instanceof ServerPlayerEntity)
								((ServerPlayerEntity)this.getThrower()).connection.sendPacket(new SPlayEntityEffectPacket(hitEntity.getEntityId(), instance));
						}
					}
							
					this.onEntityImpactEvent.onImpact(hitEntity);
					if(!this.canPassThroughEntities)
						this.remove();
				}
			}
			else if(hit.getType() == RayTraceResult.Type.BLOCK)
			{
				BlockRayTraceResult blockHit = (BlockRayTraceResult) hit;
	
				AbilityProjectileEvents.Hit event = new AbilityProjectileEvents.Hit(this, hit);
				if(MinecraftForge.EVENT_BUS.post(event))
					return;
				
				if (!this.canPassThroughBlocks)
				{
					this.onBlockImpactEvent.onImpact(blockHit.getPos());
					if(!this.canGetStuckInGround)
						this.remove();
				}
			}
		}
	}
	
	public DamageSource causeAbilityProjectileDamage()
	{
		return new IndirectEntityDamageSource("ability_projectile", this, this.getThrower()).setProjectile();
	}
	
	@Override
	public void remove()
	{		
		super.remove();
	}

	@Override
	protected float getGravityVelocity()
	{
		return this.gravity;
	}

	@Override
	public boolean isImmuneToExplosions()
	{
		return true;
	}

	@Override
	protected void registerData()
	{
		
	}
	
	@Override
    public IPacket<?> createSpawnPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
    }
	

	
	/*
	 * 	Setters/Getters
	 */
	public double getCollisionSize() {
		return this.collisionSize;
	}
	public void setCollisionSize(double val) {
		this.collisionSize = val;
	}
	public int getLife()
	{
		return this.life;
	}
	
	public int getMaxLife()
	{
		return this.maxLife;
	}

	public void setMaxLife(int life)
	{
		this.maxLife = life;
		this.life = this.maxLife;
	}
	
	public void setPhysical()
	{
		this.isPhysical = true;
	}
	
	public void setPassThroughBlocks()
	{
		this.canPassThroughBlocks = true;
	}
	
	public void setPassThroughEntities()
	{
		this.canPassThroughEntities = true;
	}

	public void setCanGetStuckInGround()
	{
		this.canGetStuckInGround = true;
	}
	
	public void setDamage(float damage)
	{
		this.damage = damage;
	}
	
	public float getDamage()
	{
		return this.damage;
	}
	
	public void setGravity(float gravity)
	{
		this.gravity = gravity;
	}
	
	public boolean isStuckInGround()
	{
		return this.stuckInGround;
	}
	
	/*
	 *	Interfaces
	 */
	public interface IOnEntityImpact extends Serializable
	{
		void onImpact(LivingEntity hitEntity);
	}
	
	public interface IOnBlockImpact extends Serializable
	{
		void onImpact(BlockPos hitPos);
	}
	
	public interface IOnTick extends Serializable
	{
		void onTick();
	}
	
	public interface IWithEffects extends Serializable
	{
		EffectInstance[] getEffects();
	}
	
	
	public static class Data
	{
		private EntityType type;
		private Class entityClass;
		@OnlyIn(Dist.CLIENT)
		private AbilityProjectileRenderer.Factory factory;
		
		public Data(EntityType type, Class<? extends Entity> clz)
		{
			this.type = type;
			this.entityClass = clz;
		}
		
		public Data(EntityType type, Class<? extends Entity> clz, AbilityProjectileRenderer.Factory factory)
		{
			this.type = type;
			this.entityClass = clz;
			this.factory = factory;
		}
		
		public EntityType getEntityType()
		{
			return this.type;
		}
		
		public Class getEntityClass()
		{
			return this.entityClass;
		}
		
		@OnlyIn(Dist.CLIENT)
		public AbilityProjectileRenderer.Factory getFactory()
		{
			return this.factory;
		}
	}
	
}