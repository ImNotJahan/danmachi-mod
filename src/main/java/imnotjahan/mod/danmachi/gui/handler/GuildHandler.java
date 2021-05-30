package imnotjahan.mod.danmachi.gui.handler;

import imnotjahan.mod.danmachi.gui.GuildGUI;
import imnotjahan.mod.danmachi.gui.container.GuildContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuildHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return new GuildContainer(player.inventory);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return new GuildGUI(player.inventory);
    }
}
