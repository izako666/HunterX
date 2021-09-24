package com.izako.hunterx.abilities.hatsus.genthru;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModEffects;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.ability.PunchAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;

public class CountdownAbility extends PunchAbility
{
    public CountdownAbility()
    {
        this.props = new Ability.Properties(this).setAbilityType(AbilityType.PASSIVE).setMaxPassive(Integer.MAX_VALUE).setNenType(NenType.CONJURER).setMaxCooldown(2 * 20);
    }
    @Override
    public String getId() {return "count_down";}

    @Override
    public String getName() {return "Count Down";}

    @Override
    public String getDesc() {return "Sets the nen of the user on a target until it activates to turn into a bomb";}

    @Override
    public void duringPassive(LivingEntity p)
    {
        super.duringPassive(p);
        IAbilityData abilityData = AbilityDataCapability.get(p);
    }

    @Override
    public void onEndPassive(LivingEntity p) {super.duringPassive(p);}

    @Override
    public float onPunch(LivingEntity p, LivingEntity target)
    {
        p.addPotionEffect(new EffectInstance(ModEffects.COUNTDOWN_ACTIVATOR, 10000000, 1));
        target.addPotionEffect(new EffectInstance(ModEffects.COUNTDOWN_PASSIVE, 1000000, 1));
        Helper.endAbilitySafe(p, this);
        Helper.consumeAura(50, p, this);
        return Helper.getTrueValue(0, this, p);
    }
}
