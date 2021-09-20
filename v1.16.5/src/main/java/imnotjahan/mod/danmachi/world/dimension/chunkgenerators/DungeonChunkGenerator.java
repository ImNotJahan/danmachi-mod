package imnotjahan.mod.danmachi.world.dimension.chunkgenerators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import imnotjahan.mod.danmachi.init.Entities;
import imnotjahan.mod.danmachi.world.CaveFloor;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
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

    
    int FLOOR_HEIGHT = 10;
    int DUNGEON_HEIGHT = 256;

    private Vector2f[] stairChunks = new Vector2f[100];
    private Boolean[] generatedStairs = new Boolean[100];
    private boolean[][][] maps = new boolean[CaveFloor.width][CaveFloor.height][DUNGEON_HEIGHT / FLOOR_HEIGHT];
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
    public List<MobSpawnInfo.Spawners> getMobsAt(Biome p_230353_1_, StructureManager p_230353_2_, EntityClassification p_230353_3_, BlockPos p_230353_4_)
    {
        List<MobSpawnInfo.Spawners> dungeonMonsters = new ArrayList<MobSpawnInfo.Spawners>()
        {{
            // Spawn chance, minimum count, maximum count
            add(new MobSpawnInfo.Spawners(Entities.GOBLIN.get(), 20, 4, 10));
            add(new MobSpawnInfo.Spawners(Entities.ALMIRAJ.get(), 20, 4, 10));
            add(new MobSpawnInfo.Spawners(Entities.METAL_RABBIT.get(), 20, 4, 10));
            add(new MobSpawnInfo.Spawners(Entities.BUGBEAR.get(), 20, 0, 5));
            add(new MobSpawnInfo.Spawners(Entities.HELLHOUND.get(), 20, 0, 10));
            add(new MobSpawnInfo.Spawners(Entities.KOBOLD.get(), 20, 0, 10));
            add(new MobSpawnInfo.Spawners(Entities.MINOTAUR.get(), 20, 0, 5));
            add(new MobSpawnInfo.Spawners(Entities.UNICORN.get(), 20, 0, 1));
        }};
        return super.getMobsAt(p_230353_1_, p_230353_2_, p_230353_3_, p_230353_4_);
    }

    @Override
    public void fillFromNoise(IWorld world, StructureManager manager, IChunk chunk)
    {
        int x = chunk.getPos().x;
        int z = chunk.getPos().z;

        for (int y = 0; y < DUNGEON_HEIGHT; y++)
        {
            BlockState iblockstate = Blocks.STONE.defaultBlockState();

            int height = DUNGEON_HEIGHT - y;
            int floor = (int)Math.floor(height != 0 ? height / 10 : 0);

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
                                        iblockstate, false);

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
                    if(!(xx + 16 * x <= DUNGEON_HEIGHT - y + 25)
                            || !(zz + 16 * z <= DUNGEON_HEIGHT - y + 25)
                            || !(xx + 16 * x >= -DUNGEON_HEIGHT + y - 25)
                            || !(zz + 16 * z >= -DUNGEON_HEIGHT + y - 25)
                            || y % FLOOR_HEIGHT == 0
                            || iblockstate == imnotjahan.mod.danmachi.init.Blocks.DUNGEON_PORTAL.defaultBlockState())
                    {

                        if(!dontPlaceHere.contains(pos))
                        {
                            chunk.setBlockState(new BlockPos(xx, y, zz), iblockstate, false);
                        }
                    }

                    if(maps[(Math.max(x, 0) * 16 + xx) % (CaveFloor.width - 1)]
                            [(Math.max(z, 0) * 16 + zz) % (CaveFloor.height - 1)]
                            [0] && y % FLOOR_HEIGHT == 1 &&
                            !dontPlaceHere.contains(pos))
                    {
                        for(int k = 0; k < FLOOR_HEIGHT; k++)
                        {
                            chunk.setBlockState(new BlockPos(xx, y + k, zz), iblockstate, false);
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
}
