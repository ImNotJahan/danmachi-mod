package imnotjahan.mod.danmachi.init;

import imnotjahan.mod.danmachi.world.biome.BiomeDungeon;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit
{
    public static Biome DUNGEON_DIMENSION = new BiomeDungeon();

    public static void RegisterBiomes()
    {
        //InitBiome(DUNGEON_DIMENSION, "Dungeon", BiomeType.COOL, Type.SPOOKY, Type.DENSE, Type.WET);
    }

    private static Biome InitBiome(Biome biome, String name, BiomeType biomeType, Type... types)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
        BiomeManager.addSpawnBiome(biome);
        return biome;
    }
}
