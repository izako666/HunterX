package com.izako.hunterx.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModEffects;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class CountdownEvent
{
    @SubscribeEvent
    public static void tick(LivingEvent.LivingUpdateEvent e)
    {
        if(e.getEntityLiving().ticksExisted % 100 == 0)
        {
            if (e.getEntityLiving().isPotionActive(ModEffects.COUNTDOWN_PASSIVE))
            {

            }
            else
                return;
        }
    }
}
