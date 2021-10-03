package imnotjahan.mod.danmachi.entities.rendering;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.entities.Bugbear;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PolarBearModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BugbearRenderer extends MobRenderer<Bugbear, PolarBearModel<Bugbear>>
{
    public BugbearRenderer(EntityRendererManager context)
    {
        super(context, new PolarBearModel<>(), 1);
    }

    @Override
    public ResourceLocation getTextureLocation(Bugbear p_110775_1_)
    {
        return new ResourceLocation(Main.MODID, "textures/entity/bugbear.png");
    }
}