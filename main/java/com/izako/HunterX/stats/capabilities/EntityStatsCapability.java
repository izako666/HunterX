package com.izako.HunterX.stats.capabilities;

import com.izako.HunterX.abilities.EnumNenType;
import com.izako.HunterX.init.ListAbilities;
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
        tag.setInteger("nenCapacity", instance.getNenCapacity());
        tag.setString("nenType", instance.getNenType().name());
        instance.getQuests().forEach((k, v) -> {
            tag.setInteger(k, v);

        });
        instance.getAbilities().forEach((a) ->{
        	tag.setString(a.getID(), a.getID());
        	System.out.println(a.getID());
        });
        
        instance.getIsPassiveActiveAll().forEach((k, v) -> {
        	tag.setBoolean(k + "isPassiveActive", v);
        	System.out.println(tag.getBoolean(k + "isPassiveActive"));
        });
        instance.getIsOnCooldownAll().forEach((k, v) -> {
        	tag.setBoolean(k + "isOnCooldown", v);
        });
        
       for(int i = 0; i < instance.getSlotsList().length; i++) {
    	   if(instance.getSlotsList()[i] != null) {
    	   tag.setInteger("slot" + instance.getSlotsList()[i].getID(), i);
    	   System.out.println(instance.getSlotsList()[i].getID());
    	   }
       }
   
     
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
        instance.setNenCapacity(tag.getInteger("nenCapacity"));
        try {
        instance.setNenType(EnumNenType.valueOf(tag.getString("nenType")));
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        
        tag.getKeySet().forEach((k) -> {
        	if(k.startsWith("quest")) {
        		instance.giveQuest(k, tag.getInteger(k));
        		System.out.println(k);
        	}
        });
        
        tag.getKeySet().forEach((k) -> {
        	if(k.startsWith("Ability") && !k.endsWith("isPassiveActive") && !k.endsWith("isOnCooldown")) {
        	instance.giveAbility(ListAbilities.getAbilityFromID(k));
        	System.out.println(k);
        		
        	}
        });
      
        tag.getKeySet().forEach((k) -> {
        	if(k.endsWith("isPassiveActive")) {
        		instance.setIsPassiveActive(tag.getBoolean(k), k.substring(0, k.length() - "isPassiveActive".length()));
            	System.out.println(instance.getIsPassiveActiveAll().values());
        	}
        });

        tag.getKeySet().forEach((k) -> {
        	if(k.endsWith("isOnCooldown")) {
        		instance.setIsOnCooldown(tag.getBoolean(k), k.substring(0, k.length() - "isOnCooldown".length()));
        	System.out.println(instance.getIsOnCooldownAll().values());
        	}
        });
        tag.getKeySet().forEach((k) -> {
        	if(k.startsWith("slot")) {
        		instance.setAbilityToSlot(tag.getInteger(k), ListAbilities.getAbilityFromID(k.substring(4)));
        	}
        });
    }


}
