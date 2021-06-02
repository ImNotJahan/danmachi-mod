package imnotjahan.mod.danmachi.gui;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.keybinds.Keybinds;
import imnotjahan.mod.danmachi.network.MessageStatus;
import imnotjahan.mod.danmachi.network.NetworkHandler;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.Collections;

public class StatusGUI extends GuiScreen
{

    private static final char[] statLetters = new char[]{'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A', 'S'};

    IStatus capability;

    @Override
    public void initGui()
    {
        GuiLabel statusText;
        GuiLabel skillsText;
        GuiLabel abilitiesText;

        this.labelList.clear();
        this.labelList.add(statusText = new GuiLabel(fontRenderer, 1, this.width / 2, this.height / 2, 0, 20, 0xFFFFFF));

        this.labelList.add(skillsText = new GuiLabel(fontRenderer, 1, this.width / 2, this.height / 2, 0, 20, 0xFFFFFF));
        this.labelList.add(abilitiesText = new GuiLabel(fontRenderer, 1, this.width / 2, this.height / 2, 0, 20, 0xFFFFFF));

        capability = Minecraft.getMinecraft().player.getCapability(StatusProvider.STATUS_CAP, Status.capSide);

        int level = capability.get(6);
        int strength = capability.get(1);
        int endurance = capability.get(2);
        int dexterity = capability.get(3);
        int agility = capability.get(4);
        int magic = capability.get(5);

        Status.Ability[] abilities = capability.getAbilities();
        Status.Skill[] skills = capability.getSkills();
        Status.Magic[] spells = capability.getSpells();

        if(capability.getFalna())
        {
            statusText.addLine(I18n.format("gui.status.level") + " " + level);
            statusText.addLine(I18n.format("gui.status.strength") + " " + getStatLetter(strength) + strength);
            statusText.addLine(I18n.format("gui.status.endurance") + " " + getStatLetter(endurance) + endurance);
            statusText.addLine(I18n.format("gui.status.dexterity") + " " + getStatLetter(dexterity) + dexterity);
            statusText.addLine(I18n.format("gui.status.agility") + " " + getStatLetter(agility) + agility);
            statusText.addLine(I18n.format("gui.status.magic") + " " + getStatLetter(magic) + magic);

            if(skills.length > 0)
            {
                skillsText.addLine(I18n.format("gui.status.skills"));
                for (Status.Skill skill : skills)
                {
                    skillsText.addLine(skill.toString());
                }
            }

            if(spells.length > 0)
            {
                skillsText.addLine("");
                skillsText.addLine(I18n.format("gui.status.spells"));
                for(int k = 0; k < spells.length; k++)
                {
                    skillsText.addLine(spells[k].toString());
                }
            }

            if(abilities.length > 0)
            {
                abilitiesText.addLine(I18n.format("gui.status.abilities"));
                for (Status.Ability ability : abilities)
                {
                    int abilityStat = ability.getStat();
                    abilitiesText.addLine(ability.toString() + ": " + getStatLetter(abilityStat) + abilityStat);
                }
            }
        } else
        {
            statusText.addLine(I18n.format("gui.status.no_falna"));
        }

        statusText.setCentered();
        skillsText.setCentered();
        abilitiesText.setCentered();

        skillsText.x = 100;
        abilitiesText.x = width - 100;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        String familia = mc.player.getCapability(StatusProvider.STATUS_CAP, Status.capSide).getFamilia();

        drawDefaultBackground();
        
        if(!familia.equals(""))
        {
            mc.getTextureManager().bindTexture(new ResourceLocation(String.format("%s:textures/gui/falnas/%s.png", Reference.MODID, familia)));
            drawModalRectWithCustomSizedTexture(0, 0, 0, 0, width, height, width, height);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        super.keyTyped(typedChar, keyCode);

        if(keyCode == Keybinds.status.getKeyCode())
        {
            this.mc.displayGuiScreen(null);

            if (this.mc.currentScreen == null)
            {
                this.mc.setIngameFocus();
            }
        }
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    private String getStatLetter(int stat)
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