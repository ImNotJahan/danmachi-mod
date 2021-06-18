package imnotjahan.mod.danmachi.util.handlers;

import imnotjahan.mod.danmachi.gui.GuildGUI;
import imnotjahan.mod.danmachi.gui.SmithingGUI;
import imnotjahan.mod.danmachi.gui.container.GuildContainer;
import imnotjahan.mod.danmachi.gui.container.SmithingContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch(ID)
        {
            case 0:
                return new GuildContainer(player.inventory);

            case 1:
                return new SmithingContainer(player.inventory);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch(ID)
        {
            case 0:
                return new GuildGUI(player.inventory);

            case 1:
                return new SmithingGUI(player.inventory);
        }

        return null;
    }
}
