package imnotjahan.mod.danmachi.entity.render;

import imnotjahan.mod.danmachi.entity.EntityMetalRabbit;
import imnotjahan.mod.danmachi.entity.EntityNeedleRabbit;
import imnotjahan.mod.danmachi.entity.model.ModelAlmiraj;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.model.ModelRabbit;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMetalRabbit extends RenderLiving<EntityMetalRabbit>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/metal_rabbit.png");

    public RenderMetalRabbit(RenderManager manager)
    {
        super(manager, new ModelRabbit(), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMetalRabbit entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityMetalRabbit entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
