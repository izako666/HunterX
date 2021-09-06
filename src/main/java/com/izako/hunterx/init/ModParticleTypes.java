package com.izako.hunterx.init;

import com.izako.hunterx.Main;
import com.izako.wypi.WyRegistry;
import com.izako.wypi.particles.GenericParticleData;
import com.izako.wypi.particles.SimpleParticle;

import net.minecraft.client.Minecraft;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticleTypes
{

	public static final ParticleType<GenericParticleData> GENERIC_PARTICLE = new ParticleType<>(true, GenericParticleData.DESERIALIZER);

	public static final ParticleType<GenericParticleData> PAPER = new ParticleType<>(true, GenericParticleData.DESERIALIZER);
	
	public static final ParticleType<GenericParticleData> GENERIC_AURA2 = new ParticleType<>(true, GenericParticleData.DESERIALIZER);
	
	public static final ParticleType<GenericParticleData> LIGHTNING = new ParticleType<>(true, GenericParticleData.DESERIALIZER);
	
	public static final ParticleType<GenericParticleData> GENERIC_AURA3 = new ParticleType<>(true,GenericParticleData.DESERIALIZER);



	static
	{
		WyRegistry.registerParticleType(GENERIC_PARTICLE, "Generic Particle");
		WyRegistry.registerParticleType(PAPER, "Paper");
		WyRegistry.registerParticleType(GENERIC_AURA2, "Generic Aura2");
		WyRegistry.registerParticleType(GENERIC_AURA3, "Generic Aura3");

		WyRegistry.registerParticleType(LIGHTNING, "lightning");


	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerParticleFactories(ParticleFactoryRegisterEvent event)
	{
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.PAPER, new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particles/paper.png")));
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.GENERIC_AURA2, new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particles/genericaura2.png")));
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.GENERIC_AURA3, new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particles/genericaura3.png")));
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.LIGHTNING, new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particles/lightning.png")));

	}
}