package imnotjahan.mod.danmachi.networking.packets;

import imnotjahan.mod.danmachi.networking.PacketHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TellToRefreshClient
{
    public static void handle(TellToRefreshClient msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            ServerPlayerEntity sender = ctx.get().getSender();
            if(sender == null) return;

            PacketHandler.refreshClient(sender);
        });
        ctx.get().setPacketHandled(true);
    }

    public static TellToRefreshClient decode(PacketBuffer buf)
    {
        return new TellToRefreshClient();
    }

    public static void encode(TellToRefreshClient message, PacketBuffer buf)
    {
    }

    public TellToRefreshClient()
    {
    }
}
