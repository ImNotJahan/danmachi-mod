package imnotjahan.mod.danmachi.gui;

import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class MindGUI extends Gui
{
    private static final ResourceLocation GUI_BARS_TEXTURES = new ResourceLocation(
            Reference.MODID + ":textures/gui/bars.png");
    private static int x, y;

    public MindGUI()
    {
        Minecraft mc = Minecraft.getMinecraft();

        ScaledResolution scaledresolution = new ScaledResolution(mc);
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight() - 12;

        int k = i - 100;

        x = k;
        y = j;

        mc.getTextureManager().bindTexture(GUI_BARS_TEXTURES);
        this.render();
        String s = "Firebolt";
        mc.fontRenderer.drawStringWithShadow(s, k, (float)(j - 9), 16777215);
    }

    public void render()
    {
        this.drawTexturedModalRect(x, y, 0, 0, 182, 5);

        int i = (int)(.5f * 183.0F);

        this.drawTexturedModalRect(x, y, 0, 5, i, 5);
        this.drawTexturedModalRect(x, y, 0, 10, i, 5);
    }
}
