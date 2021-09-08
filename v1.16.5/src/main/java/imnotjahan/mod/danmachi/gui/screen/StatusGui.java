package imnotjahan.mod.danmachi.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;

import java.util.Collections;

public final class StatusGui extends Screen
{
    LazyOptional<IStatus> lazyStatus;
    IStatus status;

    public StatusGui()
    {
        super(new StringTextComponent("StatusGui"));
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float tick)
    {
        this.renderBackground(stack);

        if(minecraft != null)
        {
            lazyStatus = minecraft.player.getCapability(StatusProvider.STATUS_CAP, Status.capSide);
            status = lazyStatus.orElse(new Status());
        }

        if(status != null && status.getFalna())
        {
            int level = status.get(6);
            int strength = status.get(1);
            int endurance = status.get(2);
            int dexterity = status.get(3);
            int agility = status.get(4);
            int magic = status.get(5);

            minecraft.getTextureManager().bind(new ResourceLocation(String.format("%s:textures/gui/falnas/%s.png",
                    Reference.MODID, status.getFamilia())));
            blit(stack, 0, 0, 0, 0, width, height, width, height);

            drawCenteredString(stack, font, new StringTextComponent("Level " + level),
                    width / 2, this.height / 2, 0xFFFFFF);
            drawCenteredString(stack, font, new StringTextComponent("Strength: " + strength),
                    width / 2, this.height / 2 + 20, 0xFFFFFF);
            drawCenteredString(stack, font, new StringTextComponent("Endurance: " + endurance),
                    width / 2, this.height / 2 + 40, 0xFFFFFF);
            drawCenteredString(stack, font, new StringTextComponent("Dexterity: " + dexterity),
                    width / 2, this.height / 2 + 60, 0xFFFFFF);
            drawCenteredString(stack, font, new StringTextComponent("Agility: " + agility),
                    width / 2, this.height / 2 + 80, 0xFFFFFF);
            drawCenteredString(stack, font, new StringTextComponent("Magic: " + magic),
                    width / 2, this.height / 2 + 100, 0xFFFFFF);
        } else
        {
            drawCenteredString(stack, font, new StringTextComponent(
                    "You need a falna to view your status"
                    ), width / 2, 20, 0xFFFFFF);
        }
        super.render(stack, mouseX, mouseY, tick);
    }

    private static final char[] statLetters = new char[]{'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A', 'S'};
    private static String getStatLetter(int stat)
    {
        int index = (int) Math.floor(stat / 100);
        if (index > 9)
        {
            return String.join("", Collections.nCopies(Math.min(index - 8, 3), "S"));
        } else
        {
            return Character.toString(statLetters[index]);
        }
    }
}