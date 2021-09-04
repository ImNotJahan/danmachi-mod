package imnotjahan.mod.danmachi.gui.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import net.minecraft.client.gui.screen.AddServerScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public final class GodGui extends Screen
{
    String godName;
    public GodGui(String godName)
    {
        super(new StringTextComponent(godName));
        this.godName = godName;
    }

    public GodGui()
    {
        super(new StringTextComponent("God"));
    }

    String currentGodResponse = "What do you want";
    String currentGodResponse2 = "";
    List<Button> buttons = new ArrayList<>();

    @Override
    protected void init()
    {
        buttons.add(
                new Button(this.width / 2 - 100, 100 + 30 * 2, 200,
                        20, new StringTextComponent("Nothing"), (p_214288_1_) ->
                        minecraft.setScreen(null)
                ));

        buttons.add(
                new Button(this.width / 2 - 100, 100 + 30, 200,
                20, new StringTextComponent("Could I join your familia"), (p_214288_1_) ->
                {
                    currentGodResponse = "Well I don't seem to have any at the moment, so why not";
                    currentGodResponse2 = "Give me a moment, and I'll give you a falna";

                    buttons.clear();
                    buttons.add(
                        new Button(this.width / 2 - 100, 100 + 30, 200,
                        20, new StringTextComponent("Okay"), (p_214288_2_) ->
                        {
                            buttons.clear();
                            currentGodResponse = "Anddddd done";
                            currentGodResponse2 = "";

                            GetStatus().setFamilia(godName);
                            GetStatus().giveFalna();

                            buttons.add(
                                    new Button(this.width / 2 - 100, 100 + 30, 200,
                                            20, new StringTextComponent("Thanks!"), (p_214288_3_) ->
                                    {
                                        minecraft.setScreen(null);
                                    }));
                        }));
                }
        ));
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float tick)
    {
        this.renderBackground(stack);
        drawCenteredString(stack, font, currentGodResponse, width / 2, 50, 0xFFFFFF);
        drawCenteredString(stack, font, currentGodResponse2, width / 2, 80, 0xFFFFFF);

        buttons.forEach(button -> addButton(button));

        super.render(stack, mouseX, mouseY, tick);
    }

    protected IStatus GetStatus()
    {
        return minecraft.player.getCapability(StatusProvider.STATUS_CAP, Status.capSide)
                .orElse(new Status());
    }
}