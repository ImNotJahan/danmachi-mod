package imnotjahan.mod.danmachi.entities.rendering;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.entities.Hellhound;
import imnotjahan.mod.danmachi.entities.models.HellhoundModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HellhoundRenderer extends MobRenderer<Hellhound, HellhoundModel<Hellhound>>
{
    public HellhoundRenderer(EntityRendererManager context)
    {
        super(context, new HellhoundModel<>(), 1);
    }

    @Override
    public ResourceLocation getTextureLocation(Hellhound p_110775_1_)
    {
        return new ResourceLocation(Main.MODID, "textures/entity/hellhound.png");
    }
}