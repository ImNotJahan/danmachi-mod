package imnotjahan.mod.danmachi.entities.rendering;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.entities.Unicorn;
import imnotjahan.mod.danmachi.entities.models.UnicornModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UnicornRenderer extends MobRenderer<Unicorn, UnicornModel<Unicorn>>
{
    public UnicornRenderer(EntityRendererManager context)
    {
        super(context, new UnicornModel<>(0), 1);
    }

    @Override
    public ResourceLocation getTextureLocation(Unicorn p_110775_1_)
    {
        return new ResourceLocation(Reference.MODID, "textures/entity/unicorn.png");
    }
}