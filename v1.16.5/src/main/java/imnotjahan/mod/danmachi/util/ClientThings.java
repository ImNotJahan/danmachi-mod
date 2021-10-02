package imnotjahan.mod.danmachi.util;

import imnotjahan.mod.danmachi.gui.screen.GodGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;

public class ClientThings
{
    /**
     * For showing the gods interact menu, because having this in a class that
     * is also on the server would crash any dedicated one.
     * @param godName The name of the god
     * @param wantingBlood If the option to ask the god for blood should appear
     */
    public static void showGodGui(String godName, boolean wantingBlood)
    {
        Minecraft.getInstance().setScreen(new GodGui(godName, wantingBlood));
    }

    public static <A extends BipedModel<?>> A getPlayerModel(float size)
    {
        return (A) new PlayerModel(size, false);
    }
}
