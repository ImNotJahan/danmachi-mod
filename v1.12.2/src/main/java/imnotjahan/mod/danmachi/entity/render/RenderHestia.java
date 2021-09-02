package imnotjahan.mod.danmachi.entity.render;

import imnotjahan.mod.danmachi.entity.EntityHestia;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHestia extends RenderLiving<EntityHestia>
{
    public RenderHestia(RenderManager manager)
    {
        super(manager, new ModelPlayer(0, false), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityHestia entity)
    {
        return new ResourceLocation(Reference.MODID + ":textures/entity/hestia.png");

    }

    @Override
    protected void applyRotations(EntityHestia entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
