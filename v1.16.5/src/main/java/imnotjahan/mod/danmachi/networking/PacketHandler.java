package imnotjahan.mod.danmachi.networking;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.networking.packets.MessageStatus;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler
{
    private static final String PROTOCOL_VERSION = "2";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Reference.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int id = 0;
    public static void init()
    {
        INSTANCE.registerMessage(id++, MessageStatus.class,
                MessageStatus::encode, MessageStatus::decode, MessageStatus::handle);
    }
}
