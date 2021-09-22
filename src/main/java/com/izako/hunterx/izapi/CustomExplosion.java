package com.izako.hunterx.izapi;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CustomExplosion extends Explosion
{
    private World world;
    private Entity exploder;
    private double explosionX;
    private double explosionY;
    private double explosionZ;
    private float explosionSize;

    private final List<BlockPos> affectedBlockPositions = Lists.newArrayList();
    private final Map<PlayerEntity, Vec3d> playerKnockbackMap = Maps.newHashMap();
    private final Random random = new Random();

    private boolean canStartFireAfterExplosion = false;
    private boolean canDestroyBlocks = true;
    private boolean canDropBlocksAfterExplosion = false;
    private boolean canDamageEntities = true;
    private boolean canDamageOwner = false;
    private boolean canAlwaysDamage = true;
    private boolean canProduceExplosionSound = true;
    private boolean protectOwnerFromFalling = false;
    private boolean canCauseKnockback = true;
    private float staticDamage = 0;
    private float staticBlockResistance = 0;
    private int heightDifference = 0;

    public CustomExplosion(Entity entity, double posX, double posY, double posZ, float power)
    {
        super(entity.world, entity, posX, posY, posZ, power, false, Mode.DESTROY);
        this.world = entity.world;
        this.exploder = entity;
        this.explosionSize = power;
        this.explosionX = posX;
        this.explosionY = posY;
        this.explosionZ = posZ;
    }

    public double getStaticDamage()
    {
        return this.staticDamage;
    }

    public void setStaticDamage(float damage)
    {
        this.staticDamage = damage;
    }

    public double getStaticBlockResistance()
    {
        return this.staticBlockResistance;
    }

    public void setStaticBlockResistance(float damage)
    {
        this.staticBlockResistance = damage;
    }

    public void setHeightDifference(int heightDifference)
    {
        this.heightDifference = heightDifference;
    }

    public void setDamageOwner(boolean damageOwner)
    {
        this.canDamageOwner = damageOwner;
    }

    public void setDamageEntities(boolean damageEntities)
    {
        this.canDamageEntities = damageEntities;
    }

    public void setDropBlocksAfterExplosion(boolean canDrop)
    {
        this.canDropBlocksAfterExplosion = canDrop;
    }

    public void setFireAfterExplosion(boolean hasFire)
    {
        this.canStartFireAfterExplosion = hasFire;
    }

    public void setDestroyBlocks(boolean canDestroyBlocks)
    {
        this.canDestroyBlocks = canDestroyBlocks;
    }


    public boolean getAlwaysDamage()
    {
        return this.canAlwaysDamage;
    }

    public void setAlwaysDamage(boolean flag)
    {
        this.canAlwaysDamage = flag;
    }

    public void setProtectOwnerFromFalling(boolean flag)
    {
        this.protectOwnerFromFalling = flag;
    }


    public void setExplosionSound(boolean hasSound)
    {
        this.canProduceExplosionSound = hasSound;
    }

    private void resetDamage(LivingEntity entity) {
        entity.hurtTime = entity.hurtResistantTime = 0;
    }

    public void disableExplosionKnockback() {
        this.canCauseKnockback = false;
    }

    public void doExplosion()
    {


        Set<BlockPos> set = Sets.newHashSet();

        int size = 32;
        for (int j = 0; j < size; ++j)
        {
            for (int k = 0; k < size; ++k)
            {
                for (int l = 0; l < size; ++l)
                {
                    if (j == 0 || j == (size - 1) || k == 0 || k == (size - 1) || l == 0 || l == (size - 1))
                    {
                        double d0 = j / (float)(size - 1) * 2.0F - 1.0F;
                        double d1 = k / (float)(size - 1) * 2.0F - 1.0F;
                        double d2 = l / (float)(size - 1) * 2.0F - 1.0F;
                        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                        d0 = d0 / d3;
                        d1 = d1 / d3;
                        d2 = d2 / d3;
                        float f = this.explosionSize * (0.7F + this.world.rand.nextFloat() * 0.6F);
                        double eX = this.explosionX;
                        double eY = this.explosionY;
                        double eZ = this.explosionZ;

                        for (float f1 = 0.3F; f > 0.0F; f -= 0.22500001F)
                        {
                            BlockPos blockpos = new BlockPos(eX, eY, eZ);
                            BlockState BlockState = this.world.getBlockState(blockpos);
                            IFluidState ifluidstate = this.world.getFluidState(blockpos);


                            if (f > 0.0F && (this.exploder == null || this.exploder.canExplosionDestroyBlock(this, this.world, blockpos, BlockState, f)))
                            {
                                set.add(blockpos);
                            }

                            eX += d0 * 0.3F;
                            eY += d1 * 0.3F;
                            eZ += d2 * 0.3F;
                        }
                    }
                }
            }
        }

        this.affectedBlockPositions.addAll(set);
        float f3 = this.explosionSize * 2.0F;
        int k1 = MathHelper.floor(this.explosionX - f3 - 1.0D);
        int l1 = MathHelper.floor(this.explosionX + f3 + 1.0D);
        int i2 = MathHelper.floor(this.explosionY - f3 - 1.0D);
        int i1 = MathHelper.floor(this.explosionY + f3 + 1.0D);
        int j2 = MathHelper.floor(this.explosionZ - f3 - 1.0D);
        int j1 = MathHelper.floor(this.explosionZ + f3 + 1.0D);
        List<Entity> list;
        list = this.world.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB(k1, i2, j2, l1, i1, j1));
        net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.world, this, list, f3);
        Vec3d vec3d = new Vec3d(this.explosionX, this.explosionY, this.explosionZ);

        if(this.canDamageEntities)
        {
            for (int k2 = 0; k2 < list.size(); ++k2)
            {
                Entity entity = list.get(k2);

                if (!entity.isImmuneToExplosions())
                {
                    double distance = entity.getDistanceSq(this.explosionX, this.explosionY, this.explosionZ) / f3;
                    if (distance <= 1.0D)
                    {
                        double xDistance = entity.getPosX() - this.explosionX;
                        double yDistance = entity.getPosY() + entity.getEyeHeight() - this.explosionY;
                        double zDistance = entity.getPosZ() - this.explosionZ;
                        double squareDistance = MathHelper.sqrt(xDistance * xDistance + yDistance * yDistance + zDistance * zDistance);
                        if (squareDistance != 0.0D)
                        {
                            xDistance = xDistance / squareDistance;
                            yDistance = yDistance / squareDistance;
                            zDistance = zDistance / squareDistance;
                            double blockDensity = this.getStaticBlockResistance() > 0 ? 0 : Explosion.getBlockDensity(vec3d, entity);
                            double power = (1.0D - distance) * blockDensity;


                            if (entity instanceof LivingEntity && this.getAlwaysDamage())
                                this.resetDamage((LivingEntity) entity);

                                // if (this.staticDamage > 0)
                                //                            {
                                //                                entity.attackEntityFrom(this.getDamageSource(), this.staticDamage);
                                //                            }
                            else
                            {
                                float damage = ((float) ((power * power + power) / 2.0D * 7.0D * f3 + 1.0D));
                                entity.attackEntityFrom(this.getDamageSource(), damage);
                            }

                            double blastDamageReduction = power;

                            if (entity instanceof LivingEntity)
                                blastDamageReduction = ProtectionEnchantment.getBlastDamageReduction((LivingEntity) entity, power);

                            if(this.canCauseKnockback)
                            {
                                entity.setMotion(entity.getMotion().add(xDistance * blastDamageReduction, yDistance * blastDamageReduction, zDistance * blastDamageReduction));
                                if (entity instanceof PlayerEntity)
                                {
                                    PlayerEntity playerEntity = (PlayerEntity) entity;
                                    if (!playerEntity.isSpectator() && (!playerEntity.isCreative() || !playerEntity.abilities.isFlying))
                                    {
                                        this.playerKnockbackMap.put(playerEntity, new Vec3d(xDistance * power, yDistance * power, zDistance * power));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (this.canProduceExplosionSound)
            this.world.playSound((PlayerEntity) null, this.explosionX, this.explosionY, this.explosionZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);

        //	ModNetwork.sendToAllAround(new SParticlesPacket(this.smokeParticles, this.explosionX, this.explosionY, this.explosionZ), (LivingEntity) this.exploder);




    }

    private static void func_229976_a_(ObjectArrayList<Pair<ItemStack, BlockPos>> p_229976_0_, ItemStack p_229976_1_, BlockPos p_229976_2_)
    {
        int i = p_229976_0_.size();

        for (int j = 0; j < i; ++j)
        {
            Pair<ItemStack, BlockPos> pair = p_229976_0_.get(j);
            ItemStack itemstack = pair.getFirst();
            if (ItemEntity.func_226532_a_(itemstack, p_229976_1_))
            {
                ItemStack itemstack1 = ItemEntity.func_226533_a_(itemstack, p_229976_1_, 16);
                p_229976_0_.set(j, Pair.of(itemstack1, pair.getSecond()));
                if (p_229976_1_.isEmpty())
                {
                    return;
                }
            }
        }

        p_229976_0_.add(Pair.of(p_229976_1_, p_229976_2_));
    }

    @Override
    public Map<PlayerEntity, Vec3d> getPlayerKnockbackMap()
    {
        return this.playerKnockbackMap;
    }

}
