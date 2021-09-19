package imnotjahan.mod.danmachi.networking;

import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.networking.packets.MessageStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientPacketHandler
{
    public static void handleStatus(MessageStatus msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            ClientPlayerEntity sender = Minecraft.getInstance().player;
            sender.getCapability(StatusProvider.STATUS_CAP, Status.capSide).orElseThrow(ArithmeticException::new)
                    .setArray(msg.status.getArray());
        });
        ctx.get().setPacketHandled(true);
    }
}
