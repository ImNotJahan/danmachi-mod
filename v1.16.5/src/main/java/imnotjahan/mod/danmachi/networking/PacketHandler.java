package imnotjahan.mod.danmachi.networking;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.networking.packets.MessageClientStatus;
import imnotjahan.mod.danmachi.networking.packets.MessageStatus;
import imnotjahan.mod.danmachi.networking.packets.TellToRefreshClient;
import imnotjahan.mod.danmachi.util.exceptions.MissingStatus;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Optional;

public class PacketHandler
{
    private static final String PROTOCOL_VERSION = "6";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Main.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );


    private static int id = 0;
    public static void init()
    {
        INSTANCE.registerMessage(id++, MessageStatus.class,
                MessageStatus::encode, MessageStatus::decode, MessageStatus::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        INSTANCE.registerMessage(id++, MessageClientStatus.class,
                MessageClientStatus::encode, MessageClientStatus::decode, MessageClientStatus::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(id++, TellToRefreshClient.class,
                TellToRefreshClient::encode, TellToRefreshClient::decode, TellToRefreshClient::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }

    public static void refreshServer(IStatus status)
    {
        PacketHandler.INSTANCE.sendToServer(new MessageStatus(status));
    }

    public static void refreshClient(ServerPlayerEntity player)
    {
        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new MessageClientStatus(
                player.getCapability(StatusProvider.STATUS_CAP)
                        .orElseThrow(MissingStatus::new)));
    }

    public static void refreshClientFromClient()
    {
        PacketHandler.INSTANCE.sendToServer(new TellToRefreshClient());
    }

    public static void openArmorDress()
    {
        PacketHandler.INSTANCE.sendToServer(new MessageStatus("armor_dress"));
    }
}
