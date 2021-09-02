package imnotjahan.mod.danmachi.init;

import imnotjahan.mod.danmachi.util.Reference;
import imnotjahan.mod.danmachi.world.dimension.dungeon.DungeonDimension;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionInit
{
    public static final DimensionType DUNGEON = DimensionType.register(
            "Dungeon",
            "_dungeon",
            Reference.DUNGEON_ID,
            DungeonDimension.class,
            false);

    public static void RegisterDimensions()
    {
        DimensionManager.registerDimension(Reference.DUNGEON_ID, DUNGEON);
    }
}
