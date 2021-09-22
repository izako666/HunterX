package com.izako.hunterx.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModEffects;
import com.izako.wypi.WyHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


import java.nio.charset.StandardCharsets;
import java.util.List;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ActivationEvent
{
    @SubscribeEvent
    public static void BombingActivation(ServerChatEvent e)
    {
        e.setCanceled(true);

        MinecraftServer server = e.getPlayer().server;
        for (ServerPlayerEntity player : server.getPlayerList().getPlayers())
        {
            if(player.getDistance(e.getPlayer()) < 50)
            {
                if  (e.getPlayer().isPotionActive(ModEffects.COUNTDOWN_ACTIVATOR))
                {
                    PlayerEntity p = e.getPlayer();
                    World world = p.getEntityWorld();
                    List entities = WyHelper.getEntitiesNear(p.getPosition(), world, 20);
                    entities.remove(player);
                    if (entities.size() > 0)
                    {
                        for (int i = 0; i <= entities.size() -1; i++)
                        {
                            LivingEntity target = (LivingEntity) entities.get(i);
                            if (target.isPotionActive(ModEffects.COUNTDOWN_PASSIVE))
                            {
                                if (target instanceof PlayerEntity)
                                {
                                    String message = e.getMessage();
                                    String username = e.getUsername();
                                    ITextComponent nameTarget = target.getName();
                                    System.out.println(message);
                                    StringTextComponent component = new StringTextComponent(username + "> " + message);
                                    if ("Im the bomber".equals(message))
                                    {
                                        target.addPotionEffect(new EffectInstance(ModEffects.COUNTDOWN, 1000000, 1));

                                        server.sendMessage(component);
                                        player.sendMessage(component);
                                    }
                                    else
                                    {
                                        server.sendMessage(component);
                                        player.sendMessage(component);
                                    }
                                }
                            }
                            else
                            {
                                String message = e.getMessage();
                                String username = e.getUsername();
                                StringTextComponent component = new StringTextComponent(username + "> " + message);
                                server.sendMessage(component);
                                player.sendMessage(component);
                            }

                        }
                    }
                    else
                    {
                        String message = e.getMessage();
                        String username = e.getUsername();
                        StringTextComponent component = new StringTextComponent(username + "> " + message);
                        server.sendMessage(component);
                        player.sendMessage(component);
                    }
                }
                else
                {
                    String message = e.getMessage();
                    String username = e.getUsername();
                    StringTextComponent component = new StringTextComponent(username + "> " + message);
                    server.sendMessage(component);
                    player.sendMessage(component);
                }
            }
            else
            {
                String message = e.getMessage();
                String username = e.getUsername();
                StringTextComponent component = new StringTextComponent(username + "> " + message);
                server.sendMessage(component);
                player.sendMessage(component);
            }
        }
    }
}
