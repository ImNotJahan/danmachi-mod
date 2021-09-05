package imnotjahan.mod.danmachi.util.events;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.commands.DanmachiLog;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ForgeEventSubscriber
{
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event)
    {
        new DanmachiLog(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof PlayerEntity)) return;

        event.addCapability(new ResourceLocation(Reference.MODID, "status"),
                new StatusProvider());
    }
}
