package imnotjahan.mod.danmachi.gui.container;

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

        int level = status.get(6);
        int strength = status.get(1);
        int endurance = status.get(2);
        int dexterity = status.get(3);
        int agility = status.get(4);
        int magic = status.get(5);

        minecraft.getTextureManager().bind(new ResourceLocation(String.format("%s:textures/gui/falnas/%s.png",
                Reference.MODID, "hestia")));
        blit(stack, 0, 0, 0, 0, width,height, width, height);

        drawCenteredString(stack, font, new StringTextComponent("Level " + level),
                width / 2, 20, 0xFFFFFF);
        drawCenteredString(stack, font, new StringTextComponent("Strength: " + strength),
                width / 2, 40, 0xFFFFFF);
        drawCenteredString(stack, font, new StringTextComponent("Endurance: " + endurance),
                width / 2, 60, 0xFFFFFF);
        drawCenteredString(stack, font, new StringTextComponent("Dexterity: " + dexterity),
                width / 2, 80, 0xFFFFFF);
        drawCenteredString(stack, font, new StringTextComponent("Agility: " + agility),
                width / 2, 100, 0xFFFFFF);
        drawCenteredString(stack, font, new StringTextComponent("Magic: " + magic),
                width / 2, 120, 0xFFFFFF);

        super.render(stack, mouseX, mouseY, tick);
    }
}