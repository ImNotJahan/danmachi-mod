package imnotjahan.mod.danmachi.util.handlers;

import imnotjahan.mod.danmachi.entity.*;
import imnotjahan.mod.danmachi.entity.render.*;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler
{
    public static void registerEntityRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityGoblin.class, RenderGoblin::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGuildMember.class, RenderGuildMember::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDealer.class, RenderDealer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHellhound.class, RenderHellhound::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityKobold.class, RenderKobold::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityUnicorn.class, RenderUnicorn::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAlmiraj.class, RenderAlmiraj::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMinotaur.class, RenderMinotaur::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityNeedleRabbit.class, RenderNeedleRabbit::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLygerfang.class, RenderLygerfang::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityKillerAnt.class, RenderKillerAnt::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHestia.class, RenderHestia::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLoki.class, RenderLoki::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInfantDragon.class, RenderInfantDragon::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHermes.class, RenderHermes::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySoma.class, RenderSoma::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBugbear.class, RenderBugbear::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHobgoblin.class, RenderHobgoblin::new);
    }
}
