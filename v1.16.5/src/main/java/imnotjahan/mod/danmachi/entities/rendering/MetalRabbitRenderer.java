package imnotjahan.mod.danmachi.entities.rendering;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.entities.MetalRabbit;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.RabbitModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MetalRabbitRenderer extends MobRenderer<MetalRabbit, RabbitModel<MetalRabbit>>
{
    public MetalRabbitRenderer(EntityRendererManager context)
    {
        super(context, new RabbitModel(), 1);
    }

    @Override
    public ResourceLocation getTextureLocation(MetalRabbit p_110775_1_)
    {
        return new ResourceLocation(Main.MODID, "textures/entity/metal_rabbit.png");
    }
}