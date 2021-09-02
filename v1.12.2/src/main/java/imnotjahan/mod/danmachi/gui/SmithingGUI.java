package imnotjahan.mod.danmachi.gui;

import imnotjahan.mod.danmachi.gui.container.GuildContainer;
import imnotjahan.mod.danmachi.gui.container.SmithingContainer;
import imnotjahan.mod.danmachi.init.ItemInit;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class SmithingGUI extends GuiContainer
{
    private static final ResourceLocation GuiTextures = new ResourceLocation(Reference.MODID + ":textures/gui/container/smithing.png");
    private final InventoryPlayer playerInventory;

    public SmithingGUI(InventoryPlayer playerInventory)
    {
        super(new SmithingContainer(playerInventory));
        this.playerInventory = playerInventory;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString("Smithing Anvil", 8, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, 70, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GuiTextures);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}
