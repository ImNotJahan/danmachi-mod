package imnotjahan.mod.danmachi.objects.blocks;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.gui.SmithingGUI;
import imnotjahan.mod.danmachi.init.BlockInit;
import imnotjahan.mod.danmachi.init.ItemInit;
import imnotjahan.mod.danmachi.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

import static java.lang.Math.pow;

public class SmithingAnvil extends Block implements IHasModel
{
    public static final PropertyInteger HOLDING = PropertyInteger.create("holding", 0, 7);
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    protected static final AxisAlignedBB X_AXIS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.125D, 1.0D, 1.0D, 0.875D);
    protected static final AxisAlignedBB Z_AXIS_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.0D, 0.875D, 1.0D, 1.0D);

    public SmithingAnvil()
    {
        super(Material.ANVIL);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HOLDING, 0));
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
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        EnumFacing enumfacing = placer.getHorizontalFacing().rotateY();

        return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, 0, placer)
                .withProperty(FACING, enumfacing);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (playerIn.inventory.getCurrentItem().getItem() == ItemInit.HAMMER)
        {
            Minecraft.getMinecraft().displayGuiScreen(new SmithingGUI(state.getValue(HOLDING)));
        }

        Item[] craftingMaterials = new Item[]{ ItemInit.ORICHALCUM_INGOT,
                ItemInit.ADAMANTITE_INGOT,
                Items.IRON_INGOT,
                ItemInit.UNICORN_HORN,
                ItemInit.MINOTAUR_HORN,
                ItemInit.MYTHRIL_INGOT};

        for(int k = 0; k < craftingMaterials.length; k++)
        {
            if(playerIn.inventory.getCurrentItem().getItem() == craftingMaterials[k])
            {
                switch (k)
                {
                    case 0:
                        SmithingGUI.forgableItem = SmithingGUI.ForgableItem.ORICHALCUM;
                        state = state.withProperty(FACING, state.getValue(FACING)).withProperty(HOLDING, 1);
                        worldIn.setBlockState(pos, state);
                        break;

                    case 1:
                        SmithingGUI.forgableItem = SmithingGUI.ForgableItem.ADAMANTITE;
                        state = state.withProperty(FACING, state.getValue(FACING)).withProperty(HOLDING, 2);
                        worldIn.setBlockState(pos, state);
                        break;

                    case 2:
                        SmithingGUI.forgableItem = SmithingGUI.ForgableItem.IRON;
                        state = state.withProperty(FACING, state.getValue(FACING)).withProperty(HOLDING, 0);
                        worldIn.setBlockState(pos, state);
                        break;

                    case 3:
                        SmithingGUI.forgableItem = SmithingGUI.ForgableItem.UNICORN_HORN;
                        state = state.withProperty(FACING, state.getValue(FACING)).withProperty(HOLDING, 0);
                        worldIn.setBlockState(pos, state);
                        break;

                    case 4:
                        SmithingGUI.forgableItem = SmithingGUI.ForgableItem.MINOTAUR_HORN;
                        state = state.withProperty(FACING, state.getValue(FACING)).withProperty(HOLDING, 0);
                        worldIn.setBlockState(pos, state);
                        break;

                    case 5:
                        SmithingGUI.forgableItem = SmithingGUI.ForgableItem.MYTHRIL;
                        state = state.withProperty(FACING, state.getValue(FACING)).withProperty(HOLDING, 0);
                        worldIn.setBlockState(pos, state);
                        break;
                }

                playerIn.inventory.getCurrentItem().setCount(0);
            }
        }

        if(playerIn.inventory.getCurrentItem().isEmpty())
        {
            if(SmithingGUI.forgableItem.equals(SmithingGUI.ForgableItem.ORICHALCUM))
            {
                Minecraft.getMinecraft().player.inventory.addItemStackToInventory(
                        new ItemStack(ItemInit.ORICHALCUM_INGOT));
            } else if(SmithingGUI.forgableItem.equals(SmithingGUI.ForgableItem.ADAMANTITE))
            {
                Minecraft.getMinecraft().player.inventory.addItemStackToInventory(
                        new ItemStack(ItemInit.ADAMANTITE_INGOT));
            } else if(SmithingGUI.forgableItem.equals(SmithingGUI.ForgableItem.IRON))
            {
                Minecraft.getMinecraft().player.inventory.addItemStackToInventory(
                        new ItemStack(Items.IRON_INGOT));
            } else if(SmithingGUI.forgableItem.equals(SmithingGUI.ForgableItem.MYTHRIL))
            {
                Minecraft.getMinecraft().player.inventory.addItemStackToInventory(
                        new ItemStack(ItemInit.MYTHRIL_INGOT));
            } else if(SmithingGUI.forgableItem.equals(SmithingGUI.ForgableItem.UNICORN_HORN))
            {
                Minecraft.getMinecraft().player.inventory.addItemStackToInventory(
                        new ItemStack(ItemInit.UNICORN_HORN));
            } else if(SmithingGUI.forgableItem.equals(SmithingGUI.ForgableItem.MINOTAUR_HORN))
            {
                Minecraft.getMinecraft().player.inventory.addItemStackToInventory(
                        new ItemStack(ItemInit.MINOTAUR_HORN));
            }

            SmithingGUI.forgableItem = SmithingGUI.ForgableItem.NONE;
            state = state.withProperty(FACING, state.getValue(FACING)).withProperty(HOLDING, 0);
            worldIn.setBlockState(pos, state);
        }

        return true;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        EnumFacing enumfacing = state.getValue(FACING);
        return enumfacing.getAxis() == EnumFacing.Axis.X ? X_AXIS_AABB : Z_AXIS_AABB;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return true;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3)).withProperty(HOLDING, (int)(meta / pow(10, 1)));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(FACING).getHorizontalIndex();
        i += 10 * state.getValue(HOLDING);
        return i;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.getBlock() != this ? state : state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, HOLDING});
    }
}