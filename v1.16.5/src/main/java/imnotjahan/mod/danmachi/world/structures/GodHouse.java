package imnotjahan.mod.danmachi.world.structures;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.init.Entities;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.ArrayList;
import java.util.List;

public class GodHouse extends Structure<NoFeatureConfig>
{
    public GodHouse()
    {
        super(NoFeatureConfig.CODEC);
    }

    @Override
    public GenerationStage.Decoration step()
    {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource,
                                  long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ,
                                  Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig)
    {
        BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
        int landHeight = chunkGenerator.getBaseHeight(centerOfChunk.getX(), centerOfChunk.getZ(),
                Heightmap.Type.WORLD_SURFACE_WG);

        IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ());
        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));

        return topBlock.getFluidState().isEmpty();
    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList()
    {
        List<MobSpawnInfo.Spawners> gods = new ArrayList<MobSpawnInfo.Spawners>()
        {{
            // Spawn chance, minimum count, maximum count
            add(new MobSpawnInfo.Spawners(Entities.HESTIA.get(), 20, 0, 1));
            add(new MobSpawnInfo.Spawners(Entities.HERMES.get(), 20, 0, 1));
            add(new MobSpawnInfo.Spawners(Entities.LOKI.get(), 20, 0, 1));
            add(new MobSpawnInfo.Spawners(Entities.SOMA.get(), 20, 0, 1));
        }};

        return gods;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory()
    {
        return GodHouse.Start::new;
    }

    public static class Start extends StructureStart<NoFeatureConfig>
    {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ,
                     MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn)
        {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator,
                                   TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn,
                                   NoFeatureConfig config)
        {
            // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            BlockPos blockpos = new BlockPos(x, 0, z);

            JigsawManager.addPieces(dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
                            .get(new ResourceLocation(Main.MODID, "house/start_pool")),
                            10), AbstractVillagePiece::new, chunkGenerator, templateManagerIn,
                    blockpos, this.pieces, this.random,false,true);

            this.pieces.forEach(piece -> piece.move(0, 1, 0));
            this.pieces.forEach(piece -> piece.getBoundingBox().y0 -= 1); // y0 = minimum y

            this.calculateBoundingBox();
        }
    }
}