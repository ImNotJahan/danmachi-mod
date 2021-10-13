package imnotjahan.mod.danmachi.networking.packets;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.gui.container.ArmorDressContainer;
import imnotjahan.mod.danmachi.init.Stats;
import imnotjahan.mod.danmachi.networking.ClientPacketHandler;
import imnotjahan.mod.danmachi.util.exceptions.MissingStatus;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
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
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleStatus(msg, ctx)));
    }

    public static MessageClientStatus decode(PacketBuffer buf)
    {
        IStatus status = new Status();

        status.setFamiliaNO(buf.readInt());
        status.setArray(buf.readVarIntArray());

        return new MessageClientStatus(status);
    }

    public static void encode(MessageClientStatus message, PacketBuffer buf)
    {
        buf.writeInt(message.status.getFamiliaNO());
        buf.writeVarIntArray(message.status.getArray());
    }

    public MessageClientStatus(IStatus status)
    {
        this.status = status;
    }
}
