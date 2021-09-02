package imnotjahan.mod.danmachi.world.gen;

import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.init.BlockInit;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class GenerateOres implements IWorldGenerator
{
    private WorldGenerator mythril_ore, adamantite_ore, orichalcum_ore;

    public GenerateOres()
    {
        mythril_ore = new WorldGenMinable(BlockInit.MYTHRIL_ORE.getDefaultState(), 9);
        adamantite_ore = new WorldGenMinable(BlockInit.ADAMANTITE_ORE.getDefaultState(), 6, BlockMatcher.forBlock(BlockInit.DUNGEON_WALL));
        orichalcum_ore = new WorldGenMinable(BlockInit.ORICHALCUM_ORE.getDefaultState(), 4, BlockMatcher.forBlock(BlockInit.DUNGEON_WALL));
    }

    private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight)
    {
        int heightDifference = maxHeight - minHeight + 1;
        for(int k = 0; k < chance; k++)
        {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDifference);
            int z = chunkZ * 16 + rand.nextInt(16);

            gen.generate(world, rand, new BlockPos(x, y, z));
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        runGenerator(mythril_ore, world, random, chunkX, chunkZ, ModConfig.oreGenerationChances.get("mythril"), 0, 40);
        runGenerator(adamantite_ore, world, random, chunkX, chunkZ, ModConfig.oreGenerationChances.get("adamantite"), 0, ModConfig.dungeonHeight - ModConfig.dungeonFloorHeight * ModConfig.generateAdamantiteFloor);
        runGenerator(orichalcum_ore, world, random, chunkX, chunkZ, ModConfig.oreGenerationChances.get("orichalcum"), 0, ModConfig.dungeonHeight - ModConfig.dungeonFloorHeight * ModConfig.generateOrichalcumFloor);
    }
}
