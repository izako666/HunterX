package com.izako.HunterX.stats.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class EntityStatsCapability implements Capability.IStorage<IEntityStats>{



    @Override

    public NBTBase writeNBT(Capability<IEntityStats> capability, IEntityStats instance, EnumFacing side) {

        final NBTTagCompound tag = new NBTTagCompound();

        tag.setDouble("healthstat", instance.getHealthStat());
        tag.setDouble("speedstat", instance.getSpeedStat());
        tag.setDouble("defensestat", instance.getDefenseStat());
        tag.setDouble("attackstat", instance.getAttackStat());
        tag.setBoolean("isHunter", instance.isHunter());
        return tag;

    }



    @Override

    public void readNBT(Capability<IEntityStats> capability, IEntityStats instance, EnumFacing side, NBTBase nbt) {

        final NBTTagCompound tag = (NBTTagCompound) nbt;

        instance.setHealthStat(tag.getDouble("healthstat"));
        instance.setSpeedStat(tag.getDouble("speedstat"));
        instance.setDefenseStat(tag.getDouble("defensestat"));
        instance.setAttackStat(tag.getDouble("attackstat"));
        instance.setIsHunter(tag.getBoolean("isHunter"));

        

    }


}
