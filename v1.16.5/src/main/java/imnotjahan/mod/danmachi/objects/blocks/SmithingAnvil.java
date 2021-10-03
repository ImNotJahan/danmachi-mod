package imnotjahan.mod.danmachi.objects.blocks;

import imnotjahan.mod.danmachi.objects.blocks.tileentities.SmithingTile;
import net.minecraft.block.*;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SmithingAnvil extends ContainerBlock
{
    private static final VoxelShape BASE = Block.box(2.0D, 0.0D, 2.0D,
            14.0D, 4.0D, 14.0D);
    private static final VoxelShape X_LEG1 = Block.box(3.0D, 4.0D, 4.0D,
            13.0D, 5.0D, 12.0D);
    private static final VoxelShape X_LEG2 = Block.box(4.0D, 5.0D, 6.0D,
            12.0D, 10.0D, 10.0D);
    private static final VoxelShape X_TOP = Block.box(0.0D, 10.0D, 3.0D,
            16.0D, 16.0D, 13.0D);
    private static final VoxelShape Z_LEG1 = Block.box(4.0D, 4.0D, 3.0D,
            12.0D, 5.0D, 13.0D);
    private static final VoxelShape Z_LEG2 = Block.box(6.0D, 5.0D, 4.0D,
            10.0D, 10.0D, 12.0D);
    private static final VoxelShape Z_TOP = Block.box(3.0D, 10.0D, 0.0D,
            13.0D, 16.0D, 16.0D);
    private static final VoxelShape Z_AXIS_AABB = VoxelShapes.or(BASE, Z_LEG1, Z_LEG2, Z_TOP);

    public SmithingAnvil(AbstractBlock.Properties properties)
    {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity entity,
                                Hand hand, BlockRayTraceResult result)
    {
        if (world.isClientSide) return ActionResultType.SUCCESS;

        INamedContainerProvider namedContainerProvider = this.getMenuProvider(state, world, pos);
        if (namedContainerProvider != null)
        {

            if (!(entity instanceof ServerPlayerEntity)) return ActionResultType.FAIL;

            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)entity;
            NetworkHooks.openGui(serverPlayerEntity, namedContainerProvider, (packetBuffer)->{});
        }
        return ActionResultType.SUCCESS;
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_,
                               ISelectionContext p_220053_4_) {
        return Z_AXIS_AABB;
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader p_196283_1_)
    {
        return new SmithingTile();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_)
    {
        return BlockRenderType.MODEL;
    }
}