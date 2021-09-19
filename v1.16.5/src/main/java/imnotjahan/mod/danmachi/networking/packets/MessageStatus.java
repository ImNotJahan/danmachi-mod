package imnotjahan.mod.danmachi.networking.packets;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.networking.ClientPacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageStatus
{
    public IStatus status;

    // For when the name is changed on the server
    public static void handle(MessageStatus msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleStatus(msg, ctx)));
        ctx.get().setPacketHandled(true);
    }

    public static MessageStatus decode(PacketBuffer buf)
    {
        IStatus status = new Status();

        status.setFamilia(buf.readUtf());
        status.setArray(buf.readVarIntArray());

        return new MessageStatus(status);
    }

    public static void encode(MessageStatus message, PacketBuffer buf)
    {
        buf.writeUtf(message.status.getFamilia());
        buf.writeVarIntArray(message.status.getArray());
    }

    public MessageStatus(IStatus status)
    {
        this.status = status;
    }

    public MessageStatus()
    {
        status = new Status();
    }
}
