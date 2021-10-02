package imnotjahan.mod.danmachi.networking;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.networking.packets.MessageStatus;
import imnotjahan.mod.danmachi.util.exceptions.MissingStatus;
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

            IStatus status = sender.getCapability(StatusProvider.STATUS_CAP, Status.capSide).orElseThrow(MissingStatus::new);

            status.setArray(msg.status.getArray());
            status.setFamilia(msg.status.getFamilia());
        });
        ctx.get().setPacketHandled(true);
    }
}
