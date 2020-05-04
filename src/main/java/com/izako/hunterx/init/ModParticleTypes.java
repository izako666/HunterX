package com.izako.hunterx.init;

import com.izako.hunterx.Main;
import com.izako.wypi.WyRegistry;
import com.izako.wypi.particles.GenericParticleData;
import com.izako.wypi.particles.SimpleParticle;

import net.minecraft.client.Minecraft;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticleTypes
{

	public static final ParticleType<GenericParticleData> GENERIC_PARTICLE = new ParticleType<>(true, GenericParticleData.DESERIALIZER);

	static
	{
		WyRegistry.registerParticleType(GENERIC_PARTICLE, "Generic Particle");
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerParticleFactories(ParticleFactoryRegisterEvent event)
	{
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.GENERIC_PARTICLE, new SimpleParticle.Factory());
	}
}