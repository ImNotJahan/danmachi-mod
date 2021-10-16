package imnotjahan.mod.danmachi.world.dimension.chunkgenerators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import imnotjahan.mod.danmachi.init.Entities;
import imnotjahan.mod.danmachi.world.CaveFloor;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class DungeonChunkGenerator extends ChunkGeneratorBase
{
    final long seed;
    
    public static final Codec<DungeonChunkGenerator> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BiomeProvider.CODEC.fieldOf("biome_source").forGetter(ChunkGenerator::getBiomeSource),
            Codec.LONG.fieldOf("seed").stable().orElseGet(() -> (long)1).forGetter((obj) -> obj.seed),
            DimensionSettings.CODEC.fieldOf("settings").forGetter(DungeonChunkGenerator::getDimensionSettings)
    ).apply(instance, instance.stable(DungeonChunkGenerator::new)));

    public DungeonChunkGenerator(BiomeProvider provider, long seed, Supplier<DimensionSettings> settings)
    {
        super(provider, seed, settings, false);
        this.seed = seed;

        generationInit();
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() 
    {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed)
    {
        return new DungeonChunkGenerator(this.biomeSource.withSeed(seed), seed, this.dimensionSettings);
    }

    private Supplier<DimensionSettings> getDimensionSettings() 
    {
        return this.dimensionSettings;
    }

    
    public static final int FLOOR_HEIGHT = 10;
    public static final int DUNGEON_HEIGHT = 256;

    private final Vector2f[] stairChunks = new Vector2f[100];
    private final Boolean[] generatedStairs = new Boolean[100];
    private final boolean[][][] maps = new boolean[DUNGEON_HEIGHT / FLOOR_HEIGHT + 1][CaveFloor.width][CaveFloor.height];
    List<BlockPos> dontPlaceHere = new ArrayList<>();

    private void generationInit()
    {
        for(int k = 0; k < DUNGEON_HEIGHT / FLOOR_HEIGHT; k++)
        {
            boolean[][] map = new boolean[CaveFloor.width][CaveFloor.height];
            maps[k] = CaveFloor.initialiseMap(map);
        }

        Arrays.fill(generatedStairs, false);

        for(int k = 0; k < stairChunks.length; k++)
        {
            int floorSize = (int)Math.floor(k * FLOOR_HEIGHT / 16);

            stairChunks[k] = new Vector2f((int)(Math.random() * floorSize) - floorSize / 2,
                    (int)(Math.random() * floorSize)  - floorSize / 2);
        }
    }

    @Override
    public void applyBiomeDecoration(WorldGenRegion p_230351_1_, StructureManager p_230351_2_)
    {

    }

    @Override
    public void fillFromNoise(IWorld world, StructureManager manager, IChunk chunk)
    {
        int x = chunk.getPos().x;
        int z = chunk.getPos().z;

        for (int y = 0; y < DUNGEON_HEIGHT; y++)
        {
            int height = DUNGEON_HEIGHT - y;
            int floor = (int)Math.floor(height / 10);

            for (int xx = 0; xx < 16; xx++)
            {
                for (int zz = 0; zz < 16; zz++)
                {
                    int stairX = (int)Math.floor(Math.random() * 16);

                    if(stairChunks[floor].equals(new Vector2f(x, z)) && y % FLOOR_HEIGHT == FLOOR_HEIGHT - 1
                            && !generatedStairs[floor])
                    {
                        for(int k = 0; k < FLOOR_HEIGHT; k++)
                        {
                            for(int j = 0; j < FLOOR_HEIGHT - k; j++)
                            {
                                int yy = y + 1 - FLOOR_HEIGHT;
                                BlockPos pos = new BlockPos(stairX, Math.max(yy + FLOOR_HEIGHT, 0), Math.max(-j + FLOOR_HEIGHT - 2, 0));
                                BlockPos realPos = new BlockPos(pos.getX() + x * 16, pos.getY(), pos.getZ() + z * 16);

                                chunk.setBlockState(new BlockPos(stairX, Math.max(0, y + 2 + k - FLOOR_HEIGHT),
                                                k + j),
                                        getFillerBlock(floor), false);

                                dontPlaceHere.add(realPos);
                            }
                        }

                        for(int j = 0; j < 4; j++)
                        {
                            int yy = y + 1 - FLOOR_HEIGHT;
                            BlockPos pos = new BlockPos(stairX, Math.max(yy + FLOOR_HEIGHT, 0),
                                    Math.max(-j + FLOOR_HEIGHT - 2, 0));
                            BlockPos realPos = new BlockPos(pos.getX() + x * 16, pos.getY(), pos.getZ() + z * 16);
                            dontPlaceHere.add(realPos);
                        }

                        generatedStairs[floor] = true;
                    }

                    BlockPos pos = new BlockPos(x * 16 + xx, y, z * 16 + zz);

                    if(y == DUNGEON_HEIGHT - 1) chunk.setBlockState(new BlockPos(xx, y, zz), imnotjahan.mod.danmachi.init.Blocks.DUNGEON_PORTAL.defaultBlockState(), false);
                    else
                    {
                        if (!(xx + 16 * x <= DUNGEON_HEIGHT - y + 25)
                                || !(zz + 16 * z <= DUNGEON_HEIGHT - y + 25)
                                || !(xx + 16 * x >= -DUNGEON_HEIGHT + y - 25)
                                || !(zz + 16 * z >= -DUNGEON_HEIGHT + y - 25)
                                || y % FLOOR_HEIGHT == 0)
                        {
                            chunk.setBlockState(new BlockPos(xx, y, zz), getFillerBlock(floor), false);
                        }

                        if (maps[floor][(Math.max(x, 0) * 16 + xx) % (CaveFloor.width - 1)]
                                [(Math.max(z, 0) * 16 + zz) % (CaveFloor.height - 1)]
                                && y % FLOOR_HEIGHT == 1 &&
                                !dontPlaceHere.contains(pos))
                        {
                            for (int k = 0; k < FLOOR_HEIGHT; k++)
                            {
                                chunk.setBlockState(new BlockPos(xx, y + k, zz), getFillerBlock(floor), false);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getGenDepth()
    {
        return DUNGEON_HEIGHT;
    }

    private static final BlockState[] greenBlocks = new BlockState[]{ Blocks.MYCELIUM.defaultBlockState(),
            Blocks.MOSSY_COBBLESTONE.defaultBlockState(), Blocks.COARSE_DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState() };
    private static final BlockState[] commonBlocks = new BlockState[]{ Blocks.COBBLESTONE.defaultBlockState(),
            Blocks.MOSSY_COBBLESTONE.defaultBlockState(), Blocks.COARSE_DIRT.defaultBlockState() };
    private static final BlockState[] iceBlocks = new BlockState[]{ Blocks.ICE.defaultBlockState(),
            Blocks.PACKED_ICE.defaultBlockState(), Blocks.SNOW_BLOCK.defaultBlockState() };
    private static final BlockState[] mistBlocks = new BlockState[]{ Blocks.DIRT.defaultBlockState(),
            Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.SAND.defaultBlockState(), Blocks.SANDSTONE.defaultBlockState() };
    private static final BlockState[] caveBlocks = new BlockState[]{ Blocks.DIRT.defaultBlockState(),
            Blocks.PACKED_ICE.defaultBlockState(), Blocks.BASALT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState(), Blocks.GRANITE.defaultBlockState() };

    private static final BlockState[] uncommonBlocks = new BlockState[]{ imnotjahan.mod.danmachi.init.Blocks.ADAMANTITE_ORE.defaultBlockState()};
    private static final BlockState[] rareBlocks = new BlockState[]{ imnotjahan.mod.danmachi.init.Blocks.ORICHALCUM_ORE.defaultBlockState() };
    private BlockState getFillerBlock(int floor)
    {
        int chance = (int) (Math.random() * 100);

        if(floor <= 4)
        {
            if (chance < 33)
            {
                return commonBlocks[(int) (Math.random() * commonBlocks.length)];
            }

            return Blocks.STONE.defaultBlockState();
        } else if(floor <= 9)
        {
            if (chance < 33)
            {
                return greenBlocks[(int) (Math.random() * greenBlocks.length)];
            }

            return Blocks.COARSE_DIRT.defaultBlockState();
        } else if(floor <= 12)
        {
            if (chance < 33)
            {
                if (chance < 6) return uncommonBlocks[(int) (Math.random() * uncommonBlocks.length)];
                return mistBlocks[(int) (Math.random() * mistBlocks.length)];
            }

            return Blocks.COARSE_DIRT.defaultBlockState();
        } else if(floor <= 17)
        {
            if (chance < 33)
            {
                if (chance < 1) return rareBlocks[(int) (Math.random() * rareBlocks.length)];
                if (chance < 6) return uncommonBlocks[(int) (Math.random() * uncommonBlocks.length)];
                return caveBlocks[(int) (Math.random() * caveBlocks.length)];
            }

            return Blocks.COARSE_DIRT.defaultBlockState();
        } else
        {
            if (chance < 33)
            {
                if (chance < 1) return rareBlocks[(int) (Math.random() * rareBlocks.length)];
                if (chance < 6) return uncommonBlocks[(int) (Math.random() * uncommonBlocks.length)];
                return iceBlocks[(int) (Math.random() * iceBlocks.length)];
            }

            return Blocks.STONE.defaultBlockState();
        }
    }
}
