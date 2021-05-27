package imnotjahan.mod.danmachi.network;

import imnotjahan.mod.danmachi.gui.GodGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ClientMessageBase
{
    public static EntityPlayer getPlayer()
    {
        return Minecraft.getMinecraft().player;
    }
    public static void showGodGUI(String godName, boolean flag)
    {
        Minecraft.getMinecraft().displayGuiScreen(new GodGUI(godName, flag));;
    }
}
