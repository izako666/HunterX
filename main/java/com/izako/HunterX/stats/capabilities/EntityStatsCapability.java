package com.izako.HunterX.stats.capabilities;

import com.izako.HunterX.init.ListQuests;

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
        tag.setBoolean("hasKilledKiriko", instance.hasKilledKiriko());
        tag.setBoolean("hasStarted2ndPhase", instance.hasStarted2ndPhase());
        tag.setDouble("timeHasRun", instance.timeHasRun());
        tag.setBoolean("hasStarted3rdPhase", instance.hasStarted3rdPhase());
        tag.setBoolean("hasKilledBoss", instance.hasKilledBoss());
        instance.getQuests().forEach((k, v) -> {
            tag.setInteger(k, v);

        });
     
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
        instance.setHasKilledKiriko(tag.getBoolean("hasKilledKiriko"));
        instance.setHasStarted2ndPhase(tag.getBoolean("hasStarted2ndPhase"));
        instance.setTimeHasRun(tag.getDouble("timeHasRun"));
        instance.setHasStarted3rdPhase(tag.getBoolean("hasStarted3rdPhase"));
        instance.setHasKilledBoss(tag.getBoolean("hasKilledBoss"));
        
        tag.getKeySet().forEach((k) -> {
        	if(k.startsWith("quest")) {
        		instance.giveQuest(k, tag.getInteger(k));
        	}
        });
      

    }


}
