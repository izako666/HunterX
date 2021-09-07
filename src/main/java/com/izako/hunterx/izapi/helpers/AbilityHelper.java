package com.izako.hunterx.izapi.helpers;

import com.izako.hunterx.izapi.ability.ExplosionAbility;
import net.minecraft.entity.Entity;

public class AbilityHelper
{
    public static ExplosionAbility newExplosion(Entity entity, double posX, double posY, double posZ, float size)
    {
        return new ExplosionAbility(entity, posX, posY, posZ, size);
    }
}
