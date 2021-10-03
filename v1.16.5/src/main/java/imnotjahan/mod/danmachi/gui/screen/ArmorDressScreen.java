package imnotjahan.mod.danmachi.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.gui.container.ArmorDressContainer;
import imnotjahan.mod.danmachi.gui.container.SmithingContainer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ArmorDressScreen extends ContainerScreen<ArmorDressContainer>
{
    private static final ResourceLocation GuiTextures = new ResourceLocation(Reference.MODID
            + ":textures/gui/container/armor_dress.png");

    public ArmorDressScreen(ArmorDressContainer container, PlayerInventory playerInventory, ITextComponent title)
    {
        super(container, playerInventory, new StringTextComponent("Armor Dress"));
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

    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_)
    {
        this.renderBackground(p_230430_1_);
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
        this.renderTooltip(p_230430_1_, p_230430_2_, p_230430_3_);
    }
}
