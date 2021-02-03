package imnotjahan.mod.danmachi.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class SmithingGUI extends Container
{

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
}
