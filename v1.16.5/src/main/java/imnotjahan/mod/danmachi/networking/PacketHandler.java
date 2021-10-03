package imnotjahan.mod.danmachi.networking;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.networking.packets.MessageStatus;
import imnotjahan.mod.danmachi.util.exceptions.MissingStatus;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler
{
    private static final String PROTOCOL_VERSION = "5";
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

    public static void refreshServer(IStatus status)
    {
        PacketHandler.INSTANCE.sendToServer(new MessageStatus(status, true));
    }

    public static void refreshClient(ServerPlayerEntity player)
    {
        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new MessageStatus(
                player.getCapability(StatusProvider.STATUS_CAP, Status.capSide)
                        .orElseThrow(MissingStatus::new)));
    }

    public static void openArmorDress()
    {
        PacketHandler.INSTANCE.sendToServer(new MessageStatus("armor_dress"));
    }
}
