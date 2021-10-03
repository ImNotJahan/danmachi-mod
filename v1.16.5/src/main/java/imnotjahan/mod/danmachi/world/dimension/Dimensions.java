package imnotjahan.mod.danmachi.world.dimension;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.world.dimension.chunkgenerators.DungeonChunkGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class Dimensions
{
    public static long seed;

    public static void init()
    {
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(Main.MODID, "dungeon"),
                DungeonChunkGenerator.CODEC);
    }
}