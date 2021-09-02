package imnotjahan.mod.danmachi.gui.container;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ArmorDressScreen extends Screen
{
    public ArmorDressScreen()
    {
        super(new StringTextComponent("Armor Dress"));
    }
}
