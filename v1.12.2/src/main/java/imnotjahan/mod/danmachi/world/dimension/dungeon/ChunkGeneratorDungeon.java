package imnotjahan.mod.danmachi.world.dimension.dungeon;

import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.init.BiomeInit;
import imnotjahan.mod.danmachi.init.BlockInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;

import javax.annotation.Nullable;
import javax.vecmath.Vector2d;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorDungeon implements IChunkGenerator
{
    private static final int dungeonHeight = Math.min(ModConfig.dungeonHeight, 256);
    private static final int floorHeight = ModConfig.dungeonFloorHeight;

    private final World world;
    private final Random random;
    private final IBlockState[] cachedBlockIDs = new IBlockState[dungeonHeight];
    private MapGenBase caveGenerator = new MapGenCaves();
    private Vector2d[] stairChunks = new Vector2d[100];
    private Boolean[] generatedStairs = new Boolean[100];
    private boolean[][][] maps = new boolean[CaveFloor.width][CaveFloor.height][dungeonHeight/floorHeight];

    public ChunkGeneratorDungeon(World worldIn, long seed)
    {
        for(int k = 0; k < dungeonHeight/floorHeight; k++)
        {
            boolean[][] map = new boolean[CaveFloor.width][CaveFloor.height];
            maps[k] = CaveFloor.initialiseMap(map);
        }

        caveGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(caveGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE);

        this.world = worldIn;
        this.random = new Random(seed);

        for(int k = 0; k < dungeonHeight; k++)
        {
            cachedBlockIDs[k] = BlockInit.DUNGEON_WALL.getStateFromMeta(0);
        }

        for(int k = 0; k < generatedStairs.length; k++)
        {
            generatedStairs[k] = false;
        }

        cachedBlockIDs[dungeonHeight - 1] = BlockInit.DUNGEON_PORTAL.getDefaultState();

        for(int k = 0; k < stairChunks.length; k++)
        {
            int floorSize = (int)Math.floor(k * floorHeight / 16);

            stairChunks[k] = new Vector2d((int)(Math.random() * floorSize) - floorSize / 2,
                    (int)(Math.random() * floorSize)  - floorSize / 2);
        }
    }

    public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer)
    {
        if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.world)) return;

        for (int i = 0; i < 16; ++i)
        {
            for (int j = 0; j < 16; ++j)
            {
                Biome biome = BiomeInit.DUNGEON_DIMENSION;
                biome.genTerrainBlocks(this.world, world.rand, primer, x * 16 + i, z * 16 + j, 10);
            }
        }
    }

    List<BlockPos> dontPlaceHere = new ArrayList<>();

    public Chunk generateChunk(int x, int z)
    {
        ChunkPrimer chunkprimer = new ChunkPrimer();

        boolean generateCaves = ModConfig.generateCavesInDungeon;

        for (int y = 0; y < this.cachedBlockIDs.length; y++)
        {
            IBlockState iblockstate = this.cachedBlockIDs[y];

            int height = ModConfig.dungeonHeight - y;
            int floor = (int)Math.floor(height != 0 ? height / ModConfig.dungeonFloorHeight : 0);

            if (iblockstate != null)
            {
                for (int xx = 0; xx < 16; xx++)
                {
                    for (int zz = 0; zz < 16; zz++)
                    {
                        int stairX = (int)Math.floor(Math.random() * 15);

                        if(stairChunks[floor].equals(new Vector2d(x, z)) && y % floorHeight == floorHeight - 1
                                && !generatedStairs[floor])
                        {
                            for(int k = 0; k < floorHeight; k++)
                            {
                                for(int j = 0; j < floorHeight - k; j++)
                                {
                                    int yy = y + 1 - floorHeight;
                                    BlockPos pos = new BlockPos(stairX, Math.max(yy + floorHeight, 0), Math.max(-j + floorHeight - 2, 0));
                                    BlockPos realPos = new BlockPos(pos.getX() + x * 16, pos.getY(), pos.getZ() + z * 16);

                                    chunkprimer.setBlockState(stairX, Math.max(0, y + 2 + k - floorHeight), k + j, iblockstate);
                                    dontPlaceHere.add(realPos);
                                }
                            }

                            for(int j = 0; j < 4; j++)
                            {
                                int yy = y + 1 - floorHeight;
                                BlockPos pos = new BlockPos(stairX, Math.max(yy + floorHeight, 0), Math.max(-j + floorHeight - 2, 0));
                                BlockPos realPos = new BlockPos(pos.getX() + x * 16, pos.getY(), pos.getZ() + z * 16);
                                dontPlaceHere.add(realPos);
                            }

                            generatedStairs[floor] = true;
                        }

                        BlockPos pos = new BlockPos(x * 16 + xx, y, z * 16 + zz);
                        if(!(xx + 16 * x <= dungeonHeight - y + 25)
                                || !(zz + 16 * z <= dungeonHeight - y + 25)
                                || !(xx + 16 * x >= -dungeonHeight + y - 25)
                                || !(zz + 16 * z >= -dungeonHeight + y - 25)
                                || y % floorHeight == 0
                                || iblockstate == BlockInit.DUNGEON_PORTAL.getDefaultState())
                        {
                            if(iblockstate.getBlock() == BlockInit.DUNGEON_WALL)
                            {
                                switch((int)(Math.random() * 10))
                                {
                                    case 0:
                                        iblockstate = iblockstate.getBlock().getStateFromMeta(1);
                                        break;

                                    case 1:
                                        iblockstate = iblockstate.getBlock().getStateFromMeta(2);
                                        break;

                                    case 2:
                                        iblockstate = iblockstate.getBlock().getStateFromMeta(3);
                                        break;

                                    default:
                                        iblockstate = iblockstate.getBlock().getStateFromMeta(0);
                                        break;
                                }
                            }

                            if(!dontPlaceHere.contains(pos))
                            {
                                chunkprimer.setBlockState(xx, y, zz, iblockstate);
                            }
                        }

                        if(maps[(Math.max(x, 0) * 16 + xx) % (CaveFloor.width - 1)]
                                [(Math.max(z, 0) * 16 + zz) % (CaveFloor.height - 1)]
                                [0] && y % floorHeight == 1 &&
                                !dontPlaceHere.contains(pos))
                        {
                            for(int k = 0; k < floorHeight; k++)
                            {
                                switch((int)(Math.random() * 10))
                                {
                                    case 0:
                                        iblockstate = iblockstate.getBlock().getStateFromMeta(1);
                                        break;

                                    case 1:
                                        iblockstate = iblockstate.getBlock().getStateFromMeta(2);
                                        break;

                                    case 2:
                                        iblockstate = iblockstate.getBlock().getStateFromMeta(3);
                                        break;

                                    default:
                                        iblockstate = iblockstate.getBlock().getStateFromMeta(0);
                                        break;
                                }
                                chunkprimer.setBlockState(xx, y + k, zz, iblockstate);
                            }
                        }
                    }
                }
            }
        }

        replaceBiomeBlocks(x, z, chunkprimer);

        if(generateCaves)
        {
            this.caveGenerator.generate(this.world, x, z, chunkprimer);
        }

        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);

        chunk.generateSkylightMap();
        return chunk;
    }

    public void populate(int x, int z)
    {
        net.minecraft.block.BlockFalling.fallInstantly = true;
        this.random.setSeed(this.world.getSeed());
        long k = this.random.nextLong() / 2L * 2L + 1L;
        long l = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed((long)x * k + (long)z * l ^ this.world.getSeed());

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.random, x, z, false);
        net.minecraft.block.BlockFalling.fallInstantly = false;
    }

    public boolean generateStructures(Chunk chunkIn, int x, int z)
    {
        return false;
    }

    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        Biome biome = BiomeInit.DUNGEON_DIMENSION;
        return biome.getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored)
    {
        return null;
    }

    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos)
    {
        return false;
    }

    public void recreateStructures(Chunk chunkIn, int x, int z)
    {

    }
}