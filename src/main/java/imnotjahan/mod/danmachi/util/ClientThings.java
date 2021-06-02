package imnotjahan.mod.danmachi.util;

import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.gui.GodGUI;
import imnotjahan.mod.danmachi.gui.GuildGUI;
import imnotjahan.mod.danmachi.gui.MindGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ClientThings
{
    public static MindGUI mindGui;

    public static EntityPlayer getPlayer()
    {
        return Minecraft.getMinecraft().player;
    }

    public static void showGodGUI(String godName, boolean flag)
    {
        Minecraft.getMinecraft().displayGuiScreen(new GodGUI(godName, flag));
    }

    public static void openGuidebook(EntityPlayer playerIn)
    {
        ItemStack book = new ItemStack(Items.WRITTEN_BOOK);

        NBTTagList bookPages = new NBTTagList();

        for (int k = 0; k < ModConfig.guideBookText.length; k++)
        {
            bookPages.appendTag(new NBTTagString(ModConfig.guideBookText[k]));
        }

        book.setTagInfo("pages", bookPages);
        book.setTagInfo("author", new NBTTagString("Albert Valdstejn"));
        book.setTagInfo("title", new NBTTagString("Guide Book"));

        Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBook(playerIn, book, false));
    }
}
