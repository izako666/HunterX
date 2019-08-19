package com.izako.HunterX.izapi;

import javax.annotation.Nullable;

import akka.actor.FSM.Timer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentUtils;

public class Misc {

	public static void sendMsg(EntityPlayer player, String msg, @Nullable Integer time) {
		Integer timePassed = 0;
		if(time == null) {
		player.sendMessage(new TextComponentString(msg));
		} else if(time != null) {
			while(timePassed < time) {

				time--;
				System.out.println(time);
			
	
			}
			if(timePassed == time) {
				player.sendMessage(new TextComponentString(msg));
				}
			
		}
	}
}
