package imnotjahan.mod.danmachi.world.dimension.dungeon;

import imnotjahan.mod.danmachi.init.BiomeInit;
import imnotjahan.mod.danmachi.init.DimensionInit;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import org.jline.utils.Log;

import javax.annotation.Nullable;

public class DungeonDimension extends WorldProvider
{
    public DungeonDimension()
    {
        this.biomeProvider = new BiomeProviderSingle(BiomeInit.DUNGEON_DIMENSION);
        this.hasSkyLight = false;
    }

    @Override
    public DimensionType getDimensionType()
    {
        return DimensionInit.DUNGEON;
    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorDungeon(this.world, this.world.getSeed());
    }

    @Override
    public boolean canRespawnHere()
    {
        return false;
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return false;
    }

    @Override
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
    {
        return new Vec3d(0, 0, 0);
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks)
    {
        return 0.5f;
    }
}
