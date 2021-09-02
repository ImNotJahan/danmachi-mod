package imnotjahan.mod.danmachi.network;

import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler
{
    private static SimpleNetworkWrapper INSTANCE;
    private static SimpleNetworkWrapper INSTANCE_2;

    public static void init()
    {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
        INSTANCE.registerMessage(MessageStatus.class, MessageStatus.class, 0, Side.SERVER);

        INSTANCE_2 = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID + "_client");
        INSTANCE_2.registerMessage(MessageStatus.class, MessageStatus.class, 0, Side.CLIENT);
    }

    public static void sendToServer(IMessage message)
    {
        INSTANCE.sendToServer(message);
    }

    public static void refreshThing(IMessage message, EntityPlayerMP player)
    {
        INSTANCE_2.sendTo(message, player);
    }
}
