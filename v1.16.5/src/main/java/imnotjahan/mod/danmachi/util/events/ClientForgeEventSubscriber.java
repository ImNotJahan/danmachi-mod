package imnotjahan.mod.danmachi.util.events;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.gui.screen.StatusGui;
import imnotjahan.mod.danmachi.networking.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEventSubscriber
{
    @SubscribeEvent
    public static void onClientTickEvent(TickEvent.ClientTickEvent event)
    {
        if(event.phase == TickEvent.Phase.START && event.side == LogicalSide.CLIENT)
        {
            if(ClientEventSubscriber.STATUS.isDown())
            {
                Minecraft.getInstance().setScreen(new StatusGui());
            } else if(ClientEventSubscriber.ARMOR_DRESS.isDown())
            {
                PacketHandler.openArmorDress();
            }
        }
    }
}
