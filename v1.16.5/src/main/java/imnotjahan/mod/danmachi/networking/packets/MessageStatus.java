package imnotjahan.mod.danmachi.networking.packets;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.init.Stats;
import imnotjahan.mod.danmachi.networking.ClientPacketHandler;
import imnotjahan.mod.danmachi.util.exceptions.MissingStatus;
import net.minecraft.block.DoorBlock;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageStatus
{
    public IStatus status;
    private final boolean clientSide;

    // For when the name is changed on the server
    public static void handle(MessageStatus msg, Supplier<NetworkEvent.Context> ctx)
    {
        if(!msg.clientSide)
        {
            ctx.get().enqueueWork(() ->
                    DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleStatus(msg, ctx)));
        } else
        {
            ctx.get().enqueueWork(() ->
            {
                ServerPlayerEntity sender = ctx.get().getSender();

                if(sender == null) return;

                IStatus status = sender.getCapability(StatusProvider.STATUS_CAP, Status.capSide)
                        .orElseThrow(MissingStatus::new);

                if(status.getLevel() != msg.status.getLevel()) sender.awardStat(Stats.LEVEL);

                status.setArray(msg.status.getArray());
                status.setFamiliaNO(msg.status.getFamiliaNO());
            });
            ctx.get().setPacketHandled(true);
        }
    }

    public static MessageStatus decode(PacketBuffer buf)
    {
        IStatus status = new Status();

        status.setFamiliaNO(buf.readInt());
        status.setArray(buf.readVarIntArray());

        return new MessageStatus(status);
    }

    public static void encode(MessageStatus message, PacketBuffer buf)
    {
        buf.writeInt(message.status.getFamiliaNO());
        buf.writeVarIntArray(message.status.getArray());
    }

    /** If you're calling this from the client, make clientSide true */
    public MessageStatus(IStatus status, boolean clientSide)
    {
        this.status = status;
        this.clientSide = clientSide;
    }

    public MessageStatus(IStatus status)
    {
        this.status = status;
        clientSide = false;
    }

    public MessageStatus()
    {
        status = new Status();
        clientSide = false;
    }
}
