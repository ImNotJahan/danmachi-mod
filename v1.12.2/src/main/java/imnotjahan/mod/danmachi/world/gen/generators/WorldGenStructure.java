package imnotjahan.mod.danmachi.world.gen.generators;

import imnotjahan.mod.danmachi.init.BlockInit;
import imnotjahan.mod.danmachi.util.Reference;
import imnotjahan.mod.danmachi.util.interfaces.IStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;
import java.util.concurrent.BlockingDeque;

public class WorldGenStructure extends WorldGenerator implements IStructure
{
    public String name;

    public WorldGenStructure(String name)
    {
        this.name = name;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        generateStructure(worldIn, position);
        return true;
    }

    public void generateStructure(World world, BlockPos pos)
    {
        MinecraftServer mcServer = world.getMinecraftServer();
        TemplateManager manager = worldServer.getStructureTemplateManager();
        ResourceLocation location = new ResourceLocation(Reference.MODID, name);
        Template template = manager.get(mcServer, location);

        if(template != null)
        {
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);

            switch(name)
            {
                case "babel":
                    template.addBlocksToWorldChunk(world, pos, settings);
                    world.setBlockState(pos.offset(EnumFacing.SOUTH, 14).offset(EnumFacing.EAST, 10).offset(EnumFacing.UP, 1), BlockInit.DUNGEON_PORTAL.getDefaultState());
                    world.setBlockState(pos.offset(EnumFacing.SOUTH, 14).offset(EnumFacing.EAST, 10).offset(EnumFacing.UP, 2), BlockInit.DUNGEON_PORTAL.getDefaultState());

                    world.setBlockState(pos.offset(EnumFacing.SOUTH, 10).offset(EnumFacing.EAST, 14).offset(EnumFacing.UP, 1), BlockInit.DUNGEON_PORTAL.getDefaultState());
                    world.setBlockState(pos.offset(EnumFacing.SOUTH, 10).offset(EnumFacing.EAST, 14).offset(EnumFacing.UP, 2), BlockInit.DUNGEON_PORTAL.getDefaultState());

                    world.setBlockState(pos.offset(EnumFacing.SOUTH, 18).offset(EnumFacing.EAST, 14).offset(EnumFacing.UP, 1), BlockInit.DUNGEON_PORTAL.getDefaultState());
                    world.setBlockState(pos.offset(EnumFacing.SOUTH, 18).offset(EnumFacing.EAST, 14).offset(EnumFacing.UP, 2), BlockInit.DUNGEON_PORTAL.getDefaultState());

                    world.setBlockState(pos.offset(EnumFacing.SOUTH, 14).offset(EnumFacing.EAST, 18).offset(EnumFacing.UP, 1), BlockInit.DUNGEON_PORTAL.getDefaultState());
                    world.setBlockState(pos.offset(EnumFacing.SOUTH, 14).offset(EnumFacing.EAST, 18).offset(EnumFacing.UP, 2), BlockInit.DUNGEON_PORTAL.getDefaultState());
                    break;

                case "cave":
                    pos.down(20);
                    template.addBlocksToWorldChunk(world, pos, settings);
                    break;

                case "god_house":
                    template.addBlocksToWorldChunk(world, pos, settings);
                    break;
            }
        }
    }
}
