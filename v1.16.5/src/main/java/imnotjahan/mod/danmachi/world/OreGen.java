package imnotjahan.mod.danmachi.world;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.init.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class OreGen
{
    private static final ArrayList<ConfiguredFeature<?, ?>> overworldOres = new ArrayList<>();

    public static void registerOres()
    {
        overworldOres.add(register("mythril_ore", Feature.ORE.configured(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.MYTHRIL_ORE.getBlock().defaultBlockState(), 9))
                .range(32).squared()
                .countRandom(2)));

        overworldOres.add(register("damascus_ore", Feature.ORE.configured(new OreFeatureConfig(
                        OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DAMASCUS_ORE.getBlock().defaultBlockState(), 3))
                .range(16).squared()
                .countRandom(2)));

        overworldOres.add(register("nosteel_ore", Feature.ORE.configured(new OreFeatureConfig(
                        OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.NOSTEEL_ORE.getBlock().defaultBlockState(), 3))
                .range(16).squared()
                .countRandom(2)));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void gen(BiomeLoadingEvent event)
    {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();

        OreGen.registerOres();

        for(ConfiguredFeature<?, ?> ore : overworldOres)
        {
            if (ore != null) generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
        }
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?>
    register(String name, ConfiguredFeature<FC, ?> configuredFeature)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Reference.MODID + ":" + name, configuredFeature);
    }

}
