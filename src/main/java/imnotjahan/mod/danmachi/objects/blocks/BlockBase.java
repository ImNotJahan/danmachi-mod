package imnotjahan.mod.danmachi.objects.blocks;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.init.BlockInit;
import imnotjahan.mod.danmachi.init.ItemInit;
import imnotjahan.mod.danmachi.util.Reference;
import imnotjahan.mod.danmachi.util.interfaces.IHasModel;
import imnotjahan.mod.danmachi.world.WorldData;
import imnotjahan.mod.danmachi.world.dimension.CustomTeleporter;
import jdk.jfr.internal.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jline.utils.Log;

import javax.annotation.Nullable;

public class BlockBase extends Block implements IHasModel
{
    public BlockBase(String name, Material material, float hardness)
    {
        this(name, material, hardness, true);
    }

    public BlockBase(String name, Material material, float hardness, boolean putInInv)
    {
        this(name, material, hardness, 0, putInInv);
    }

    public BlockBase(String name, Material material, float hardness, float brightness)
    {
        this(name, material, hardness, brightness, true);
    }

    public BlockBase(String name, Material material, float hardness, float brightness, boolean putInInv)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.creativeTab);

        setHardness(hardness);

        BlockInit.BLOCKS.add(this);

        setLightLevel(brightness);

        if(putInInv)
        {
            ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        }
    }

    public boolean isFullCube(IBlockState state)
    {
        return !(getUnlocalizedName().equals("tile.magic_stone_lamp"));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return !(getUnlocalizedName().equals("tile.magic_stone_lamp"));
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (entityIn instanceof EntityPlayerMP && state == BlockInit.DUNGEON_PORTAL.getDefaultState())
        {
            EntityPlayerMP player = (EntityPlayerMP) entityIn;

            int dimensionId = worldIn.provider.getDimension();

            if(dimensionId == Reference.DUNGEON_ID)
            {
                WorldData data = WorldData.get(worldIn);
                CustomTeleporter.teleportToDimension(player, 0, data.getBabelCenter().getX(), data.getBabelCenter().getY(), data.getBabelCenter().getZ());            } else if(dimensionId == 0)
            {
                CustomTeleporter.teleportToDimension(player, Reference.DUNGEON_ID, 0, Math.min(ModConfig.dungeonHeight, 256) - 3, 0);
            }
        }
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        if(blockState == BlockInit.DUNGEON_PORTAL.getDefaultState())
        {
            return NULL_AABB;
        }
        return super.getCollisionBoundingBox(blockState, worldIn, pos);
    }
}