package imnotjahan.mod.danmachi.gui.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import imnotjahan.mod.danmachi.Reference;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public final class Status extends Screen
{
    String familia = "hestia";

    public Status()
    {
        super(new StringTextComponent("Status"));
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float tick)
    {
        this.renderBackground(stack);

        minecraft.getTextureManager().bind(new ResourceLocation(String.format("%s:textures/gui/falnas/%s.png",
                Reference.MODID, familia)));

        drawCenteredString(stack, font, new StringTextComponent("Level 1"),
                width / 2, 20, 0xFFFFFF);
        drawCenteredString(stack, font, new StringTextComponent("Strength 1"),
                width / 2, 40, 0xFFFFFF);
        drawCenteredString(stack, font, new StringTextComponent("Endurance 1"),
                width / 2, 60, 0xFFFFFF);
        drawCenteredString(stack, font, new StringTextComponent("Dexterity 1"),
                width / 2, 80, 0xFFFFFF);
        drawCenteredString(stack, font, new StringTextComponent("Agility 1"),
                width / 2, 100, 0xFFFFFF);
        drawCenteredString(stack, font, new StringTextComponent("Magic 1"),
                width / 2, 120, 0xFFFFFF);

        super.render(stack, mouseX, mouseY, tick);
    }
}