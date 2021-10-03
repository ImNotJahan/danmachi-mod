package imnotjahan.mod.danmachi.entities.rendering;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.entities.Goblin;
import imnotjahan.mod.danmachi.entities.models.GoblinModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoblinRenderer extends MobRenderer<Goblin, GoblinModel>
{
    public GoblinRenderer(EntityRendererManager context)
    {
        super(context, new GoblinModel(0), 1);
    }

    @Override
    public ResourceLocation getTextureLocation(Goblin p_110775_1_)
    {
        return new ResourceLocation(Main.MODID, "textures/entity/goblin.png");
    }
}