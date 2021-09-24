package com.izako.hunterx.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModEffects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ActivatedEvent
{
    @SubscribeEvent
    public static void tick(LivingEvent.LivingUpdateEvent e)
    {
        if (e.getEntityLiving().isPotionActive(ModEffects.COUNTDOWN))
        {
            int duration = e.getEntityLiving().getActivePotionEffect(ModEffects.COUNTDOWN).getDuration();
            LivingEntity p = e.getEntityLiving();
            World world = e.getEntityLiving().world;
            if (duration == 10)
            {
                if (!world.isRemote)
                {
                    world.createExplosion(null, p.getPosX(), p.getPosY()+2, p.getPosZ(), 2.0F, Explosion.Mode.BREAK).doExplosionA();
                    p.attackEntityFrom(DamageSource.causeExplosionDamage(p), 500);
                }
                p.world.addParticle(ParticleTypes.EXPLOSION, p.getPosX(), p.getPosY()
                         +1, p.getPosZ(), 0, 0, 0);
                p.world.addParticle(ParticleTypes.SMOKE, p.getPosX(), p.getPosY() + 1, p.getPosZ(), 0, 0, 0);
            }
        }
    }
}

