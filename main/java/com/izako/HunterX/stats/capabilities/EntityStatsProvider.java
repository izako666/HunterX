package com.izako.HunterX.stats.capabilities;



import net.minecraft.nbt.NBTTagCompound;

import net.minecraft.util.EnumFacing;

import net.minecraftforge.common.capabilities.Capability;

import net.minecraftforge.common.capabilities.CapabilityInject;

import net.minecraftforge.common.capabilities.ICapabilitySerializable;



import javax.annotation.Nonnull;

import java.util.Objects;



/**

 * Handles the injection of a capability and input/output of NBTTags

 */

public class EntityStatsProvider implements ICapabilitySerializable<NBTTagCompound> {



    /**

     * Injects the capability to allow for an instance to be extracted

     */

    @CapabilityInject(IEntityStats.class)

    public static final Capability<IEntityStats> ENTITY_STATS = null;



    /**

     * Creates a new instance of the capability injection

     */

    private IEntityStats instance = Objects.requireNonNull(ENTITY_STATS).getDefaultInstance();



    @Override

    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {



        return capability == ENTITY_STATS;

    }



    

    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {



        return hasCapability(capability, facing) ? ENTITY_STATS.<T>cast(instance) : null;

    }



    @Override

    public NBTTagCompound serializeNBT() {



        return (NBTTagCompound) ENTITY_STATS.getStorage().writeNBT(ENTITY_STATS, instance, null);

    }



    @Override

    public void deserializeNBT(NBTTagCompound nbt) {



        ENTITY_STATS.getStorage().readNBT(ENTITY_STATS, instance, null, nbt);

    }



}