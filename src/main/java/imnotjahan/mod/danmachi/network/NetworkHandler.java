package imnotjahan.mod.danmachi.network;

import imnotjahan.mod.danmachi.util.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler
{
    private static SimpleNetworkWrapper INSTANCE;

    public static void init()
    {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

        INSTANCE.registerMessage(MessageStatus.class, MessageStatus.class, 0, Side.CLIENT);
    }

    public static void sendToPlayer(IMessage message)
    {
        INSTANCE.sendToAll(message);
    }
}
