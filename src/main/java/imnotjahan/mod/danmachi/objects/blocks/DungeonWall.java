package imnotjahan.mod.danmachi.objects.blocks;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.init.BlockInit;
import imnotjahan.mod.danmachi.init.ItemInit;
import imnotjahan.mod.danmachi.objects.itemblocks.DungeonWallItem;
import imnotjahan.mod.danmachi.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.*;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DungeonWall extends Block implements IHasModel
{
    public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 3);

    public DungeonWall()
    {
        super(Material.ROCK);
        setUnlocalizedName("dungeon_wall");
        setRegistryName("dungeon_wall");
        setCreativeTab(Main.creativeTab);

        setHardness(7);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new DungeonWallItem(this).setRegistryName(this.getRegistryName()));

        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, Integer.valueOf(0)));
    }

    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(TYPE)).intValue();
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(TYPE, Integer.valueOf(meta));
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {TYPE});
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 1, "type=1");
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 2, "type=2");
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 3, "type=3");
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this));
        items.add(new ItemStack(this, 1, 1));
        items.add(new ItemStack(this, 1, 2));
        items.add(new ItemStack(this, 1, 3));
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(TYPE, Integer.valueOf(meta));
    }
}