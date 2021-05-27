package imnotjahan.mod.danmachi.world.gen;

import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.world.WorldData;
import imnotjahan.mod.danmachi.world.gen.generators.WorldGenStructure;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import org.jline.utils.Log;

import java.util.Arrays;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenCustomStructures implements IWorldGenerator
{
    public static final WorldGenStructure GOD_HOUSE = new WorldGenStructure("god_house");
    public static final WorldGenStructure BABEL = new WorldGenStructure("babel");
    private boolean tempGeneratedBabel = false;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if (world.provider.getDimension() == 0)
        {
            generateStructure(GOD_HOUSE, world, random, chunkX, chunkZ, ModConfig.godHouseGenerationChance, Blocks.GRASS, BiomePlains.class);

            WorldData data = WorldData.get(world);
            if (!data.babelCreated() && world.getWorldType() != WorldType.FLAT && !tempGeneratedBabel)
            {
                BlockPos babelPos = new BlockPos(0, calculateSolidGenerationHeight(world, 0, 0), 0);
                BABEL.generate(world, random, babelPos);

                tempGeneratedBabel = true;

                data.createBabel();
                data.setBabelCenter(babelPos.offset(EnumFacing.EAST, 14).offset(EnumFacing.SOUTH, 14).offset(EnumFacing.UP, 2));
            }
        }
    }

    private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?> classes)
    {
        ArrayList<Class<?>> classesList = new ArrayList<>(Arrays.asList(classes));

        int x = (chunkX * 16) + random.nextInt(15);
        int z = (chunkZ * 16) + random.nextInt(15);
        int y = calculateGenerationHeight(world, x, z, new Block[]{ topBlock });
        BlockPos pos = new BlockPos(x, y, z);

        Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

        if((classesList.contains(biome) || classesList.isEmpty()) && ModConfig.generateCustomStructures)
        {
            if(random.nextInt(chance) == 0)
            {
                generator.generate(world, random, pos);
            }
        }
    }

    private static int calculateGenerationHeight(World world, int x, int z, Block[] topBlock)
    {
        int y = world.getHeight();
        boolean foundGround = false;

        while(!foundGround && y-- >= 0)
        {
            Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();

            for(int k = 0; k < topBlock.length; k++)
            {
                if(topBlock[k] == block)
                {
                    foundGround = true;
                }
            }
        }

        return y;
    }

    private static int calculateSolidGenerationHeight(World world, int x, int z)
    {
        int y = world.getHeight();
        boolean foundGround = false;

        while(!foundGround && y-- >= 0)
        {
            Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();

            if(block.getMaterial(block.getDefaultState()).blocksMovement())
            {
                foundGround = true;
            }
        }

        return y;
    }
}
