package imnotjahan.mod.danmachi.gui;

import imnotjahan.mod.danmachi.gui.container.GuildContainer;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuildGUI extends GuiContainer
{
    private static final ResourceLocation GuiTextures = new ResourceLocation(Reference.MODID + ":textures/gui/container/guild.png");
    private final InventoryPlayer playerInventory;

    public GuildGUI(InventoryPlayer playerInventory)
    {
        super(new GuildContainer(playerInventory));
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
        this.fontRenderer.drawString("Guild Member", this.xSize / 2 - this.fontRenderer.getStringWidth("Guild Member") / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 129 + 2, 4210752);
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