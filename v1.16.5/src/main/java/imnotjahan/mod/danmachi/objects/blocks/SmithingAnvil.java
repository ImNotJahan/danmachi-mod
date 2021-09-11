package imnotjahan.mod.danmachi.objects.blocks;

import imnotjahan.mod.danmachi.objects.blocks.tileentities.SmithingTile;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SmithingAnvil extends ContainerBlock
{
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

    @Override
    public TileEntity newBlockEntity(IBlockReader p_196283_1_)
    {
        return new SmithingTile();
    }
}