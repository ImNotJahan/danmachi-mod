package imnotjahan.mod.danmachi.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.gui.container.SmithingContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class SmithingScreen extends ContainerScreen<SmithingContainer>
{
    private static final ResourceLocation GuiTextures = new ResourceLocation(Reference.MODID
            + ":textures/gui/container/smithing.png");

    public SmithingScreen(SmithingContainer container, PlayerInventory playerInventory, ITextComponent title)
    {
        super(container, playerInventory, new StringTextComponent("Smithing Anvil"));
    }

    @Override
    protected void renderBg(MatrixStack stack, float p_230450_2_, int p_230450_3_, int p_230450_4_)
    {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(GuiTextures);

        int i = (this.width - getXSize()) / 2;
        int j = (this.height - getYSize()) / 2;

        this.blit(stack, i, j, 0, 0, getXSize(), getYSize());
    }
}
