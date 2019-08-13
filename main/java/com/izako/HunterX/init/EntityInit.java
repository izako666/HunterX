package com.izako.HunterX.init;

import com.izako.HunterX.Main;
import com.izako.HunterX.entity.BossExam;
import com.izako.HunterX.entity.Empty;
import com.izako.HunterX.entity.EntityStats;
import com.izako.HunterX.entity.Examiner;
import com.izako.HunterX.entity.Thug;
import com.izako.HunterX.items.entities.EntityBullet;
import com.izako.HunterX.items.entities.EntityCard;
import com.izako.HunterX.items.entities.EntityNeedle;
import com.izako.HunterX.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class EntityInit {
	
	public static void registerEntities() {
		registerEntity("Thug", Thug.class, Reference.THUG, 50, 65280, 17664);
		registerEntity("Examiner", Examiner.class, Reference.EXAMINER, 50, 0, 999999);
		registerEntity("BossExam", BossExam.class, Reference.BOSSEXAM, 50, 001011, 101101);
		registerEntity("EntityStats", EntityStats.class, Reference.ENTITYSTATS, 50, 110100, 010010);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "EntityCard"), EntityCard.class, "EntityCard", 124, Main.instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "EntityNeedle"), EntityNeedle.class, "EntityCard", 125, Main.instance, 64, 10, true);
<<<<<<< HEAD
	//	EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "EntityCard"), YoyoProjectile.class, "YoyoProjectile", 126, Main.instance, 64, 10, true);
=======
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "EntityCard"), YoyoProjectile.class, "YoyoProjectile", 126, Main.instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "EntityBullet"), EntityBullet.class, "EntityBullet", 127, Main.instance, 64, 10, true);
>>>>>>> 920ffd5a73cb330de45adf43f12ce21c0fa47722

		addSpawns();
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
		
	}
	
	
	 private static void addSpawns() {
		 copySpawns(EntityStats.class,EnumCreatureType.MONSTER, EntityCreeper.class, EnumCreatureType.MONSTER, 100, 1);
		 
		 copySpawns(Empty.class,EnumCreatureType.MONSTER, EntityCreeper.class, EnumCreatureType.MONSTER, 100, 1);
		 
		 copySpawns(Thug.class, EnumCreatureType.MONSTER, EntityCreeper.class, EnumCreatureType.MONSTER, 10, 1);
		 
		 copySpawns(Examiner.class, EnumCreatureType.MONSTER, EntityCreeper.class, EnumCreatureType.MONSTER, 1, 1);
		  
		  
		 }
		 
		 private static Biome[] getBiomes(final BiomeDictionary.Type type) {
		  return BiomeDictionary.getBiomes(type).toArray(new Biome[0]);
		 }

	 private static void copySpawns(final Class<? extends EntityLiving> classToAdd, final EnumCreatureType creatureTypeToAdd, final Class<? extends EntityLiving> classToCopy, final EnumCreatureType creatureTypeToCopy, int spawnrate, int MaxInGroup) {
		  for (final Biome biome : ForgeRegistries.BIOMES) {
		   biome.getSpawnableList(creatureTypeToCopy).stream()
		     .filter(entry -> entry.entityClass == classToCopy)
		     .findFirst()
		     .ifPresent(spawnListEntry ->biome
		     .getSpawnableList(creatureTypeToAdd)
		     .add(new Biome.SpawnListEntry(classToAdd, spawnrate, 1, 1)));
		  }
		}
		} 
		
