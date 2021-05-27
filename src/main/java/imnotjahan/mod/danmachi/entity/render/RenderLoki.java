package imnotjahan.mod.danmachi.entity.render;

import imnotjahan.mod.danmachi.entity.EntityLoki;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLoki extends RenderLiving<EntityLoki>
{
    public RenderLoki(RenderManager manager)
    {
        super(manager, new ModelPlayer(0, true), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLoki entity)
    {
        return new ResourceLocation(Reference.MODID + ":textures/entity/loki.png");

    }

    @Override
    protected void applyRotations(EntityLoki entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
