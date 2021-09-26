package imnotjahan.mod.danmachi.networking.packets;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.init.Stats;
import imnotjahan.mod.danmachi.networking.ClientPacketHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageClientStatus
{
    public IStatus status;

    public static void handle(MessageClientStatus msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            ServerPlayerEntity sender = ctx.get().getSender();

            if(sender == null) return;

            IStatus status = sender.getCapability(StatusProvider.STATUS_CAP, Status.capSide)
                    .orElseThrow(ArithmeticException::new);

            if(status.getLevel() != msg.status.getLevel()) sender.awardStat(Stats.LEVEL);

            status.setArray(msg.status.getArray());
        });
        ctx.get().setPacketHandled(true);
    }

    public static MessageClientStatus decode(PacketBuffer buf)
    {
        IStatus status = new Status();

        status.setFamilia(buf.readUtf());
        status.setArray(buf.readVarIntArray());

        return new MessageClientStatus(status);
    }

    public static void encode(MessageClientStatus message, PacketBuffer buf)
    {
        buf.writeUtf(message.status.getFamilia());
        buf.writeVarIntArray(message.status.getArray());
    }

    public MessageClientStatus(IStatus status)
    {
        this.status = status;
    }

    public MessageClientStatus()
    {
        status = new Status();
    }
}
