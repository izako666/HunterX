package com.izako.wypi.abilities.events;

import com.izako.wypi.abilities.projectiles.AbilityProjectileEntity;

import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

public class AbilityProjectileEvents
{
	@Cancelable
	public static class Hit extends Event
	{	
		private AbilityProjectileEntity projectile;
		private RayTraceResult hit;
		
		public Hit(AbilityProjectileEntity abilityProjectileEntity, RayTraceResult hit)
		{
			this.projectile = abilityProjectileEntity;
			this.hit = hit;
		}
		
		public AbilityProjectileEntity getProjectile()
		{
			return this.projectile;
		}
		
		public RayTraceResult getHit()
		{
			return this.hit;
		}
	}
}
