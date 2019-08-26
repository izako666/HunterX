package com.izako.HunterX.abilities;

import com.izako.HunterX.izapi.abilities.Ability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.math.Vec3d;


public class AbilityJajanken extends Ability{

	public AbilityJajanken(String str) {
		super(str);
	}
	
	@Override
	public void startAbility(EntityPlayer player) {
		if(!player.getEntityWorld().isRemote) {
			EntitySnowball snow = new EntitySnowball(player.getEntityWorld(), player.posX, player.posY, player.posZ);
	        Vec3d aim = player.getLookVec();
			snow.setPosition(player.posX + aim.x, player.posY + player.eyeHeight, player.posZ + aim.z);
    		snow.motionX = aim.x * 4;
    		snow.motionY = aim.y * 4;
    		snow.motionZ = aim.z * 4;
	       

            player.getEntityWorld().spawnEntity(snow);

		}
	}

}
