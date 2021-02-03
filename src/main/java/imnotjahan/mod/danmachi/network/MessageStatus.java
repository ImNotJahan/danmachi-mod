package imnotjahan.mod.danmachi.network;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import org.jline.utils.Log;

public class MessageStatus extends MessageBase<MessageStatus>
{
    private IStatus status = new Status();
    private String username = "";
    private EntityPlayerSP player;

    @Override
    public void handleClientSide(MessageStatus message, EntityPlayer player)
    {
        String realUsername = Minecraft.getMinecraft().player.getName();
        EntityPlayerMP playerMP = (EntityPlayerMP)player;

        if(true)
        {
            IStatus statuss = message.status;

            IStatus old = playerMP.getCapability(StatusProvider.STATUS_CAP, Status.capSide);
            old.set(0, statuss.get(0));
            old.set(1, statuss.get(1));
            old.set(2, statuss.get(2));
            old.set(3, statuss.get(3));
            old.set(4, statuss.get(4));
            old.set(5, statuss.get(5));
            old.set(6, statuss.get(6));
            old.set(7, statuss.get(7));

            Log.info("changed");
        }

        Log.info(realUsername + " equals " + message.username + "? " + (realUsername == message.username));
    }

    @Override
    public void handleServerSide(MessageStatus message, EntityPlayer player)
    {

    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        if(buf.isReadable())
        {
            status.set(0, buf.readInt());
            status.set(1, buf.readInt());
            status.set(2, buf.readInt());
            status.set(3, buf.readInt());
            status.set(4, buf.readInt());
            status.set(5, buf.readInt());
            status.set(6, buf.readInt());
            status.set(7, buf.readInt());

            username = ByteBufUtils.readUTF8String(buf);
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        status = player.getCapability(StatusProvider.STATUS_CAP, Status.capSide);

        buf.writeInt(status.get(0));
        buf.writeInt(status.get(1));
        buf.writeInt(status.get(2));
        buf.writeInt(status.get(3));
        buf.writeInt(status.get(4));
        buf.writeInt(status.get(5));
        buf.writeInt(status.get(6));
        buf.writeInt(status.get(7));

        ByteBufUtils.writeUTF8String(buf, username);
    }

    public MessageStatus(String username, IStatus status, EntityPlayerSP player)
    {
        this.username = username;
        this.status = status;
        this.player = player;
    }

    public MessageStatus()
    {

    }
}