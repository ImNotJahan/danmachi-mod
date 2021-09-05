package imnotjahan.mod.danmachi.gui.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.init.Items;
import net.minecraft.client.gui.screen.AddServerScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public final class GodGui extends Screen
{
    String godName;
    boolean wantsBlood = false;

    public GodGui(String godName, boolean wantsBlood)
    {
        super(new StringTextComponent(godName));
        this.godName = godName;
        this.wantsBlood = wantsBlood;
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

        if(!GetStatus().getFalna())
        {
            buttons.add(
                    new Button(this.width / 2 - 100, 100 + 30, 200,
                            20, new StringTextComponent("Could I join your familia"), (p_214288_1_) ->
                    {
                        currentGodResponse = "Well I don't seem to have any at the moment, so why not";
                        currentGodResponse2 = "Give me a moment, and I'll give you a falna";

                        ClearButtons();
                        buttons.add(
                                new Button(this.width / 2 - 100, 100 + 30, 200,
                                        20, new StringTextComponent("Okay"), (p_214288_2_) ->
                                {
                                    ClearButtons();
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
        } else if(GetStatus().getFamilia() == godName)
        {
            currentGodResponse = "Hey " + minecraft.player.getName().getString();
            buttons.add(
                    new Button(this.width / 2 - 100, 100 + 30, 200,
                            20, new StringTextComponent("Can you update my status"), (p_214288_1_) ->
                    {
                        currentGodResponse = "Sure, just give me a second to find a needle";

                        ClearButtons();
                        buttons.add(
                                new Button(this.width / 2 - 100, 100 + 30, 200,
                                        20, new StringTextComponent("Okay"), (p_214288_2_) ->
                                {
                                    ClearButtons();
                                    currentGodResponse = "Anddd your status is now updated!";

                                    GetStatus().updateStatus();

                                    buttons.add(
                                            new Button(this.width / 2 - 100, 100 + 30, 200,
                                                    20, new StringTextComponent("Nice"), (p_214288_3_) ->
                                            {
                                                minecraft.setScreen(null);
                                            }));
                                }));
                    }
                    ));
        } else
        {
            currentGodResponse = "Ah, what would a child of " + GetStatus().getFamilia() + "need from me";
        }

        if(wantsBlood)
        {
            buttons.add(
                new Button(this.width / 2 - 100, 100, 200,
                        20, new StringTextComponent("Can I have some blood?"), (p_214288_1_) ->
                {
                    ClearButtons();
                    currentGodResponse = "Excuse me?";

                    buttons.add(
                            new Button(this.width / 2 - 100, 100 + 30, 200,
                                    20, new StringTextComponent("I need it for a weapon"), (p_214288_2_) ->
                            {
                                ClearButtons();
                                currentGodResponse = "Oh okay.. Well don't do anything weird with it";

                                minecraft.player.inventory.setItem(minecraft.player.inventory.selected,
                                        new ItemStack(Items.ICHOR));

                                buttons.add(
                                        new Button(this.width / 2 - 100, 100 + 30, 200,
                                                20, new StringTextComponent("Thanks"), (p_214288_3_) ->
                                        {
                                            minecraft.setScreen(null);
                                        }));
                            }));
                }));
        }
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

    void ClearButtons()
    {
        for(Button button : buttons)
        {
            button.active = false;
            button.visible = false;
        }

        buttons.clear();
    }
}