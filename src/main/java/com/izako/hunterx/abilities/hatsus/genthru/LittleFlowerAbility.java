package com.izako.hunterx.abilities.hatsus.genthru;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.*;
import com.izako.hunterx.izapi.helpers.AbilityHelper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.particles.ParticleTypes;


public class LittleFlowerAbility extends PunchAbility
{
    public LittleFlowerAbility()
    {
        this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxPassive(Integer.MAX_VALUE).setNenType(NenType.TRANSMUTER).setMaxCooldown(2 * 20);
    }
    @Override
    public String getId() {return "little_flower";}

    @Override
    public String getName() {return "Little Flower";}

    @Override
    public String getDesc() {return "Deals a devastating blow through an explosion that comes from the palm";}



    @Override
    public void duringPassive(LivingEntity p)
    {

        super.duringPassive(p);
        IAbilityData abilityData = AbilityDataCapability.get(p);

    }
    @Override
    public void onEndPassive(LivingEntity p)
    {
        super.duringPassive(p);
    }


   @Override
    public float onPunch(LivingEntity p, LivingEntity target) {
        ExplosionAbility explosion = AbilityHelper.newExplosion(p, p.getPosX(), p.getPosY(), p.getPosZ(), 3);
        explosion.setStaticDamage(20);
        explosion.setExplosionSound(true);
        explosion.doExplosion();
        p.world.addParticle(ParticleTypes.EXPLOSION, p.getPosX(), p.getPosY(), p.getPosZ(), 0, 0, 0);
        p.world.addParticle(ParticleTypes.SMOKE, p.getPosX(), p.getPosY(), p.getPosZ(), 0, 0, 0);
        Helper.consumeAura(20, p, this);
        Helper.endAbilitySafe(p, this);
        return Helper.getTrueValue(20, this, p);

    }
}

