package imnotjahan.mod.danmachi.objects.blocks;

import imnotjahan.mod.danmachi.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class DungeonPortal extends Block
{
    public static final RegistryKey<World> DUNGEON = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("dungeon"));

    public DungeonPortal()
    {
        super(Block.Properties.of(Material.PORTAL).strength(100).noCollission());
    }

    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity)
    {
        if(!(entity instanceof ServerPlayerEntity)) return;

        RegistryKey<World> dungeonKey = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(Main.MODID,
                "dungeon_dimension"));
        ServerWorld dungeon = entity.getCommandSenderWorld().getServer().getLevel(dungeonKey);

        ((ServerPlayerEntity) entity).teleportTo(dungeon, 0, 250, 0, 0, 0);
    }
}
