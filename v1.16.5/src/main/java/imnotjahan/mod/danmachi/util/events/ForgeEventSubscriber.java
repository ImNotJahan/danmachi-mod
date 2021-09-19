package imnotjahan.mod.danmachi.util.events;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.commands.DanmachiLog;
import imnotjahan.mod.danmachi.networking.PacketHandler;
import imnotjahan.mod.danmachi.networking.packets.MessageStatus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
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

    // Network refreshing
    @SubscribeEvent
    public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new MessageStatus(
                player.getCapability(StatusProvider.STATUS_CAP, Status.capSide).orElseThrow(ArithmeticException::new)));
    }

    @SubscribeEvent
    public static void playerDeath(LivingDeathEvent event)
    {
        if(!(event.getEntity() instanceof PlayerEntity)) return;

        ServerPlayerEntity player = (ServerPlayerEntity) event.getEntity();
        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new MessageStatus(
                player.getCapability(StatusProvider.STATUS_CAP, Status.capSide).orElseThrow(ArithmeticException::new)));
    }
}
