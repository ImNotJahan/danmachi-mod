package imnotjahan.mod.danmachi.entities.rendering;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.entities.Almiraj;
import imnotjahan.mod.danmachi.entities.Goblin;
import imnotjahan.mod.danmachi.entities.models.GoblinModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.RabbitModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AlmirajRenderer extends MobRenderer<Almiraj, RabbitModel<Almiraj>>
{
    public AlmirajRenderer(EntityRendererManager context)
    {
        super(context, new RabbitModel(), 1);
    }

    @Override
    public ResourceLocation getTextureLocation(Almiraj p_110775_1_)
    {
        return new ResourceLocation(Reference.MODID, "textures/entity/almiraj.png");
    }
}