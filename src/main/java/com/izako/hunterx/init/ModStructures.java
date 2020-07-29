package com.izako.hunterx.init;

import com.izako.hunterx.Main;
import com.izako.hunterx.structures.BlimpPiece;
import com.izako.hunterx.structures.BlimpStructure;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.registries.IForgeRegistry;

public class ModStructures {

    public static final Structure<NoFeatureConfig> BLIMP = new BlimpStructure(NoFeatureConfig::deserialize);

    public static final Feature<?> BLIMPFEATURE  = BLIMP.setRegistryName(Main.MODID, "blimp");
    public static IStructurePieceType BLIMP_PIECE_TYPE;


    public static void registerStructure(IForgeRegistry<Feature<?>> registry) {
        registry.register(BLIMPFEATURE);
        BLIMP_PIECE_TYPE = IStructurePieceType.register(BlimpPiece::new, "BP");
    }

}
