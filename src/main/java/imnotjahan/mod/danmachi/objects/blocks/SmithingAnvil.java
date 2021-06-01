package imnotjahan.mod.danmachi.objects.blocks;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.init.BlockInit;
import imnotjahan.mod.danmachi.init.ItemInit;
import imnotjahan.mod.danmachi.util.interfaces.IHasModel;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class SmithingAnvil extends BlockAnvil implements IHasModel
{
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public SmithingAnvil()
    {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setCreativeTab(Main.creativeTab);

        setHardness(5.0F);
        setSoundType(SoundType.ANVIL);
        setResistance(2000.0F);
        setUnlocalizedName("smithing_anvil");
        setRegistryName("smithing_anvil");

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this));
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!player.world.isRemote)
        {
            player.openGui(Main.instance, 1, player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
        }

        return true;
    }
}