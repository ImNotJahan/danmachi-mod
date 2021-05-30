package imnotjahan.mod.danmachi.util;

import imnotjahan.mod.danmachi.gui.GodGUI;
import imnotjahan.mod.danmachi.gui.GuildGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ClientThings
{
    public static EntityPlayer getPlayer()
    {
        return Minecraft.getMinecraft().player;
    }

    public static void showGodGUI(String godName, boolean flag)
    {
        Minecraft.getMinecraft().displayGuiScreen(new GodGUI(godName, flag));
    }
}
