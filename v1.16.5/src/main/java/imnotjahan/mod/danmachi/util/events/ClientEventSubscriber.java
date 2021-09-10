package imnotjahan.mod.danmachi.util.events;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.entities.rendering.*;
import imnotjahan.mod.danmachi.init.Entities;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ClientEventSubscriber
{
    public static final KeyBinding STATUS = new KeyBinding("key.status", GLFW.GLFW_KEY_V,
            "key.categories.danmachi");
    public static final KeyBinding ARMOR_DRESS = new KeyBinding("key.armor_dress", GLFW.GLFW_KEY_U,
            "key.categories.danmachi");

    static
    {
        ClientRegistry.registerKeyBinding(STATUS);
        ClientRegistry.registerKeyBinding(ARMOR_DRESS);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(Entities.GOBLIN.get(),
                GoblinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Entities.MINOTAUR.get(),
                MinotaurRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Entities.HESTIA.get(),
                HestiaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Entities.HERMES.get(),
                HermesRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Entities.LOKI.get(),
                LokiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Entities.SOMA.get(),
                SomaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Entities.METAL_RABBIT.get(),
                MetalRabbitRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Entities.ALMIRAJ.get(),
                AlmirajRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Entities.BUGBEAR.get(),
                BugbearRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Entities.GUILD_MEMBER.get(),
                GuildRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Entities.HELLHOUND.get(),
                HellhoundRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Entities.UNICORN.get(),
                UnicornRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Entities.KOBOLD.get(),
                KoboldRenderer::new);
    }

    public static void init() {}
}
