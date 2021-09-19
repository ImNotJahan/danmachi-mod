package imnotjahan.mod.danmachi.world.dimension;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.world.dimension.chunkgenerators.DungeonChunkGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class Dimensions
{
    public static long seed;

    public static void init()
    {
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(Reference.MODID, "dungeon"),
                DungeonChunkGenerator.CODEC);
    }
}