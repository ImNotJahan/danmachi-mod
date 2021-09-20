package imnotjahan.mod.danmachi.world.dimension.chunkgenerators;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.WorldGenRegion;

import java.util.function.Supplier;

public abstract class ChunkGeneratorBase extends NoiseChunkGenerator
{
    protected final long seed;
    protected final Supplier<DimensionSettings> dimensionSettings;
    private final boolean shouldGenerateBedrock;

    public ChunkGeneratorBase(BiomeProvider provider, long seed, Supplier<DimensionSettings> settings, boolean shouldGenerateBedrock)
    {
        super(provider, seed, settings);
        this.seed = seed;
        this.dimensionSettings = settings;
        this.shouldGenerateBedrock = shouldGenerateBedrock;
    }

    public ChunkGeneratorBase(BiomeProvider provider, long seed, Supplier<DimensionSettings> settings)
    {
        this(provider, seed, settings, true);
    }

    @Override
    public int getGenDepth()
    {
        return 256;
    }

    protected static int getIndex(int x, int y, int z) {
        return x << 12 | z << 8 | y;
    }

    public final boolean shouldGenerateBedrock()
    {
        return shouldGenerateBedrock;
    }

    protected final ChunkPos getPos(WorldGenRegion primer)
    {
        return new ChunkPos(primer.getCenterX(), primer.getCenterZ());
    }

    protected final BlockPos withY(BlockPos old, int y)
    {
        return new BlockPos(old.getX(), y, old.getZ());
    }
}