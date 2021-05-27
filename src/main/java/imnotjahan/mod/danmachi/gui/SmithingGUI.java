package imnotjahan.mod.danmachi.gui;

import imnotjahan.mod.danmachi.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

import java.io.IOException;

public class SmithingGUI extends GuiScreen
{
    public SmithingGUI(int value)
    {
        //forgableItem = value;
    }

    public enum ForgableItem
    {
        ORICHALCUM,
        ADAMANTITE,
        IRON,
        WOOD,
        UNICORN_HORN,
        MINOTAUR_HORN,
        MYTHRIL,
        NONE
    }

    static public ForgableItem forgableItem = ForgableItem.NONE;

    @Override
    public void initGui()
    {
        if(forgableItem.equals(ForgableItem.ORICHALCUM))
        {
            createButton("Desperate", 0, 16);
        } else if(forgableItem.equals(ForgableItem.ADAMANTITE))
        {
            createButton("Fire Magic Sword", 0, 12);
            createButton("Ice Magic Sword", 1, 13);
            createButton("Fortia Spear", 2, 27);
            createButton("Urga Blade", 3, 40);
        } else if(forgableItem.equals(ForgableItem.IRON))
        {
            createButton("Kotetsu", 0, 15);
            createButton("Kodachi Futaba", 1, 17);
            createButton("Greatsword", 2, 14);
            createButton("Great Podao Zaga", 3, 18);
            createButton("Dagger", 4, 23);
        } else if(forgableItem.equals(ForgableItem.MYTHRIL))
        {
            createButton("Hestia Knife", 0, 22);
        } else if(forgableItem.equals(ForgableItem.UNICORN_HORN))
        {
            createButton("Hakugen", 0, 21);
        } else if(forgableItem.equals(ForgableItem.MINOTAUR_HORN))
        {
            createButton("Ushiwakamaru", 0, 24);
        }
    }

    private void createButton(String name, int index, int id)
    {
        buttonList.add(new GuiButton(id, this.width / 2 - 100, 100 + 30 * index, name));
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return true;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        Minecraft.getMinecraft().player.inventory.addItemStackToInventory(
                new ItemStack(ItemInit.ITEMS.get(button.id)));
        forgableItem = ForgableItem.NONE;
        mc.displayGuiScreen(null);
    }
}
