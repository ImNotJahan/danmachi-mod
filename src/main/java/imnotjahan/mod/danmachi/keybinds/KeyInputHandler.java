package imnotjahan.mod.danmachi.keybinds;

import imnotjahan.mod.danmachi.gui.StatusGUI;
import imnotjahan.mod.danmachi.magic.Firebolt;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyInputHandler
{
    StatusGUI gui = new StatusGUI();

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if (Keybinds.status.isPressed())
        {
            Minecraft.getMinecraft().displayGuiScreen(gui);
        } else if(Keybinds.magic.isPressed())
        {
            Firebolt.fire(Minecraft.getMinecraft().player);
        }
    }
}