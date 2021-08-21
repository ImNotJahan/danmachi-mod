package imnotjahan.mod.danmachi.gui;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.init.ItemInit;
import imnotjahan.mod.danmachi.network.MessageStatus;
import imnotjahan.mod.danmachi.network.NetworkHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Set;

public class GodGUI extends GuiScreen
{
    private GuiLabel godDialog;

    String godName;
    boolean hasBottle;

    IStatus status;

    @Override
    public void initGui()
    {
        refreshGui();

        status = mc.player.getCapability(StatusProvider.STATUS_CAP, Status.capSide);

        assert status != null;
        String familia = status.getFamilia();

        if(familia.equals(""))
        {
            godDialog.addLine(I18n.format("god." + godName + ".stranger_greeting"));
            addButtons(new String[]{"Can I join your familia?", "Nothing"});
        }
        else if(familia.equals(godName))
        {
            godDialog.addLine(String.format(I18n.format("god." + godName + ".familia_greeting"),
                    mc.player.getDisplayName()));

            ArrayList<String> questions = new ArrayList<>();
            questions.add("Can you update my status?");

            if(hasBottle) questions.add("Can I have some of your blood?");
            if(status.canLevelUp()) questions.add("Can I level up now");
            if(familia.equals("soma")) questions.add("Can I have some soma?");

            questions.add("Nothing");

            addButtons((String[])questions.toArray());
        } else
        {
            godDialog.addLine(I18n.format("god." + godName + ".other_familia_greeting", familia));
            addButtons(new String[]{"Nothing"});
        }
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        refreshGui();

        switch(button.displayString)
        {
            case "Can I join your familia?":
                godDialog.addLine(I18n.format("god." + godName + ".can_join_answer"));
                godDialog.addLine(I18n.format("god." + godName + ".can_join_secondary_answer"));

                addButtons(new String[]{"Ok"});
                break;

            case "Nothing":
            case "Got it":
            case "Thanks":
            case "Nevermind":
            case "Nice":
                mc.displayGuiScreen(null);
                break;

            case "Ok":
                godDialog.addLine(I18n.format("god." + godName + ".added_falna"));

                status.giveFalna();
                status.setFamilia(godName);

                NetworkHandler.sendToServer(new MessageStatus(status, mc.player));

                addButtons(new String[]{"Thanks"});
                break;

            case "Yes!":
            case "Can I level up now":
            {
                Set<Status.Ability> setAbilities = status.levelUp();
                Status.Ability[] abilities = new Status.Ability[setAbilities.size()];

                for(int k = 0; k < abilities.length; k ++)
                {
                    abilities[k] = setAbilities.iterator().next();
                }

                godDialog.addLine(I18n.format("god." + godName + ".leveled_up", status.getLevel()));
                godDialog.addLine(I18n.format("god." + godName + ".choosable_abilities"));
                godDialog.addLine(I18n.format("god." + godName + ".ability_ask"));

                String[] abilityStrings = new String[abilities.length];

                for (int k = 0; k < abilities.length; k++)
                {
                    abilityStrings[k] = abilities[k].toString();
                }
                addButtons(abilityStrings);
            }
                break;

            case "Okay":
                if(status.getLevel() > status.get(6))
                {
                    godDialog.addLine(I18n.format("god." + godName + ".can_level"));
                    addButtons(new String[]{"Yes!"});
                } else
                {
                    if(status.updateStatus() >= ModConfig.requiredSkillExcelia && Math.random() < ModConfig.skillChance)
                    {
                        if(status.grantSkill())
                        {
                            godDialog.addLine(I18n.format("god." + godName + ".achieved_skill"));
                            addButtons(new String[]{"Nice"});
                        } else
                        {
                            godDialog.addLine(I18n.format("god." + godName + ".updated_status"));
                            addButtons(new String[]{"Thanks"});
                        }
                    } else
                    {
                        godDialog.addLine(I18n.format("god." + godName + ".updated_status"));
                        addButtons(new String[]{"Thanks"});
                    }
                }

                NetworkHandler.sendToServer(new MessageStatus(status, mc.player));
                break;

            case "Can you update my status":
                godDialog.addLine(I18n.format("god." + godName + ".updating_status"));
                addButtons(new String[]{"Okay"});
                break;

            case "Can I take some of your blood?":
                godDialog.addLine(I18n.format("god." + godName + ".blood_response"));
                addButtons(new String[]{"It's for a weapon", "Nevermind"});
                break;

            case "It's for a weapon":
                godDialog.addLine(I18n.format("god." + godName + ".explained_blood"));
                godDialog.addLine(I18n.format("god." + godName + ".gave_blood"));
                addButtons(new String[]{"Thanks"});
                mc.player.inventory.setInventorySlotContents(mc.player.inventory.currentItem, new ItemStack(ItemInit.ICHOR));
                break;

            case "Can I have some soma?":
                mc.player.inventory.setInventorySlotContents(mc.player.inventory.getFirstEmptyStack(),
                        new ItemStack(ItemInit.SOMA));
                mc.displayGuiScreen(null);
                break;

            default:
            {
                Status.Ability[] abilities = Status.Ability.values();
                for(Status.Ability ability : abilities)
                {
                    if (button.displayString.equals(ability.toString()))
                    {
                        status.grantAbility(ability);
                        godDialog.addLine(I18n.format("god." + godName + ".added_ability"));
                        addButtons(new String[]{"Thanks"});
                    } else
                    {
                        addButtons(new String[]{"Nevermind"});
                    }
                }
            }
            break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return true;
    }

    private void refreshGui()
    {
        buttonList.clear();
        labelList.clear();

        this.labelList.add(godDialog = new GuiLabel(fontRenderer, 1, this.width / 2,
                50, 0, 20, 0xFFFFFF));

        godDialog.setCentered();
    }

    public GodGUI(String godName, boolean flag)
    {
        this.godName = godName;
        this.hasBottle = flag;
    }

    private void addButtons(String[] strings)
    {
        for(int k = 0; k < strings.length; k++)
        {
            buttonList.add(new GuiButton(k, this.width / 2 - 100, 100 + 30 * k, strings[k]));
        }
    }
}